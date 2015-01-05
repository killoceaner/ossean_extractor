package extension;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import core.ConsolePipeline;
import core.ModelPageProcessor;
import core.ModelPipeline;
import core.PageModelPipeline;
import core.Pipeline;
import core.Site;

public class OsseanExtractor extends TimerTask {
	private Site site;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	private String uuid;

	private PageDownloader downloader;

	private Timer timer;

	private ReadCursor readCursor;

	private ModelPipeline modelPipeline;

	protected List<Pipeline> pipelines = new ArrayList<Pipeline>();

	private ModelPageProcessor modelPageProcessor;

	private String cursorPath;

	public String defaultPath = "./cursor/cursor.txt";

	public List<String> modelName = new ArrayList<String>();

	protected OsseanExtractor(ModelPageProcessor modelPageProcessor) {
		this.modelPageProcessor = modelPageProcessor;
	}

	public OsseanExtractor(Site site, PageModelPipeline<?> pageModelPipeline,
			Class<?>... pageModels) {
		this(ModelPageProcessor.create(pageModels));
		this.site = site;
		this.modelPipeline = new ModelPipeline();
		this.addPipeline(modelPipeline);
		for (Class<?> pageModel : pageModels) {
			modelName.add(pageModel.getCanonicalName());
			if (pageModelPipeline != null) {
				this.modelPipeline.put(pageModel, pageModelPipeline);
			}
		}
	}

	public static OsseanExtractor create(Site site,
			PageModelPipeline<?> modelPipeline, Class<?>... pageModels) {
		return new OsseanExtractor(site, modelPipeline, pageModels);
	}

	public OsseanExtractor setDownloader(PageDownloader downloader) {
		this.downloader = downloader;
		return this;
	}

	public OsseanExtractor setUUID(String uuid) {
		this.uuid = uuid;
		return this;
	}

	public OsseanExtractor setCursorPath(String cursorPath) {
		this.cursorPath = cursorPath;
		return this;
	}

	public OsseanExtractor addPipeline(Pipeline pipeline) {
		this.pipelines.add(pipeline);
		return this;
	}

	public OsseanExtractor setPipelines(List<Pipeline> pipelines) {
		this.pipelines = pipelines;
		return this;
	}

	public OsseanExtractor clearPipeline() {
		pipelines = new ArrayList<Pipeline>();
		return this;
	}

	@Override
	public void run() {
		logger.info("One Extraction Task Started!");
		int beginId;
		// 如果配置文件中有记录，从记录几下来的位置开始取数据
		if (readCursor.getValue(uuid) != null)
			beginId = Integer.parseInt(readCursor.getValue(uuid).trim());
		// 如果配置文件中没有记录，从数据库中从头开始获取数据
		else
			beginId = this.downloader.getMinId();
		// 获取数据库中的url和RawText,但还没生产page对象
		List<RawPage> rawList = this.downloader.downloadPages(beginId, beginId
				+ this.site.getResultNum());
		// 生成对应的page对象
		createPageList(rawList);
		for (RawPage rawPage : rawList) {
			if (rawPage.getPage() != null
					&& !rawPage.getPage().getResultItems().isSkip()) {
				try {
					// 抽取page
					modelPageProcessor.process(rawPage.getPage());
					// 记录该page是否被抽取，但是还没有存储
					rawPage.setExtracted(true);
					if (!rawPage.getPage().getResultItems().isSkip()) {
						for(Pipeline pipeline:pipelines)
							pipeline.process(rawPage.getPage().getResultItems(), null);
						// 记录页面被是否保存下来
						if(rawPage.getPage().isAllResultSkip(modelName.toArray(new String[modelName.size()])));
						rawPage.setStored(true);
					}
				} catch (Exception e) {
					// 记录出错页面
					if (!rawPage.isExtracted()
							|| rawPage.getPage().getResultItems().isSkip())
						this.downloader.returnErrorPages(rawPage.getUrl());
				} finally {
					rawPage.printLogInfo();
				}
			}
			// 记录上次抽取的位置
			readCursor
					.setValue(uuid, String.valueOf(rawList.get(
							rawList.size() - 1).getId() + 1));
		}
	}

	public void start() {
		init();
		logger.info(this.uuid + " Extractor Started!");
		timer.schedule(this, site.getFirstTime(), site.getPeriod());
	}

	protected void init() {
		if (downloader == null) {
			logger.error("No Page Source!");
			System.exit(0);
		}
		if (timer == null) {
			timer = new Timer();
		}
		if (cursorPath == null) {
			this.cursorPath = defaultPath;
		}
		if (readCursor == null) {
			readCursor = new ReadCursor(cursorPath);
		}

		if (pipelines.isEmpty()) {
			pipelines.add((Pipeline) new ConsolePipeline());
		}
	}

	protected void createPageList(List<RawPage> rawPages) {
		for (RawPage rawPage : rawPages) {
			rawPage.generatPage();
		}
	}
}
