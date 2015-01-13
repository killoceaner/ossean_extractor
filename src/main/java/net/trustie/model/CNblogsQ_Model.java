package net.trustie.model;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import core.AfterExtractor;
import core.Page;
import core.ValidateExtractor;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import extension.StringHandler;

@ExtractBy("//*[@id='container']/div[@id='container_content']/div[@id='main']")
public class CNblogsQ_Model implements AfterExtractor, ValidateExtractor {

	private int questionId = -1;
    private String questionUrl = "";
    private String urlMD5 = "";
    private String pageMD5 = "";
    private String extractTime;
    private String tag = "";

	private int history = 0;

	@ExtractBy("//h1[@class='qitem_title']/a/text()")
	private String questionTitle;

	@ExtractBy("//*[@class='qitem_item']/div[@id='qes_content']/allText()")
	private String questionContent;

	@ExtractBy("//*[@class='qitem_item']/div[@class='qclear']/div[@id='d_tag']/a/allText()")
	private List<String> tags;

	@ExtractBy("//*[@class='q_digg_bury']/span[@id='q_diggbury_count']/text()")
	private String voteNum;

	@ExtractBy("//*[@class='qitem_publisher']/allText()")
	private String viewNum;

	@ExtractBy("//*[@class='qitem_publisher']/span[@class='red']/span[@id='question_award']/text()")
	private String scoreBean;

	@ExtractBy("//*[@class='qclear']/div[@class='question_author']/a[@class='bluelink]/@href")
	private String authorUrl;

	@ExtractBy("//*[@class='qclear']/div[@class='question_author']/a[@class='bluelink]/text()")
	private String author;

	@ExtractBy("//*[@class='qclear']/div[@class='question_author']/allText()")
	private String postTime;

	@ExtractBy("//*[@id='panelAnswerList']/div[@class='title_green']/text()")
	private String answerNum;

	@ExtractBy("//*[@id='panelBestAnswer']/div[@id='title_red']/text()")
	private String bestAnswer;

	public void afterProcess(Page page) {
		// 处理帖子的标签
		tag = StringHandler.combineTags(this.tags);

		// 处理浏览次数m
		this.viewNum = StringHandler.matchRightString(this.viewNum,
				"浏览: [0-9]+次");
		this.viewNum = StringHandler.matchRightString(this.viewNum, "[0-9]+");

		// 处理发帖时间
		this.postTime = StringHandler.matchRightString(this.postTime,
				"[0-9]{4,}-.*");

		// 处理作者的url
		if (StringUtils.isNotBlank(this.authorUrl)) {
			this.authorUrl = "http://q.cnblogs.com" + this.authorUrl;
		} else
			page.setResultSkip(authorUrl, true);

		// 处理园豆
		if (StringUtils.isNotBlank(this.scoreBean))
			this.scoreBean = this.scoreBean.trim();
		else
			this.scoreBean = "0";

		// 处理答案个数
		this.answerNum = StringHandler
				.findRigthString(this.answerNum, "(", ")").trim();
		if (this.answerNum == null)
		  page.setResultSkip(answerNum, true);/*.setModelSkip(this.getClass().getCanonicalName(), true);*/

		if (StringUtils.isNotBlank(this.bestAnswer)) {
			this.answerNum = String
					.valueOf(Integer.parseInt(this.answerNum) + 1);
		}

		// 处理voteNum
		this.voteNum = this.voteNum.trim();

		// 处理问题Id、url、pageMD5、urlMD5、抽取时间
		this.questionUrl = page.getPageUrl();
		this.pageMD5 = DigestUtils.md5Hex(this.questionTitle
				+ this.questionContent);
		this.urlMD5 = DigestUtils.md5Hex(this.questionUrl);

		String id = StringHandler.matchRightString(this.questionUrl, "[0-9]+");
		if (id != null)
			this.questionId = Integer.parseInt(id);

		// 处理extractorTime
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		this.extractTime = simpleDateFormat.format(new Date());
	}

	public void validate(Page page) {
		// TODO Auto-generated method stub
		if (!page.getResultItems()
				.getFieldSkip(this.getClass().getCanonicalName())) {
			if (!StringHandler.isAllNotBlank(this.questionTitle, this.author)) {
				page.setResultSkip(this, true);/*.setModelSkip(this.getClass().getCanonicalName(), true);*/
				return;
			}

			if (!StringHandler.canFormatterInteger(this.answerNum,
					this.viewNum, this.voteNum, this.scoreBean)) {
				page.setResultSkip(this, true);  /*.setModelSkip(this.getClass().getCanonicalName(), true);*/
				return;
			}

			if (!StringHandler
					.canFormatterDate(this.postTime, this.extractTime)) {
				page.setResultSkip(this, true);  /*.setModelSkip(this.getClass().getCanonicalName(), true);*/
				return;
			}

			if (this.questionId == -1)
				page.setResultSkip(this, true);  /*.setModelSkip(this.getClass().getCanonicalName(), true);*/
		}
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getUrlMD5() {
		return urlMD5;
	}

	public void setUrlMD5(String urlMD5) {
		this.urlMD5 = urlMD5;
	}

	public String getPageMD5() {
		return pageMD5;
	}

	public void setPageMD5(String pageMD5) {
		this.pageMD5 = pageMD5;
	}

	public String getExtractTime() {
		return extractTime;
	}

	public void setExtractTime(String extractTime) {
		this.extractTime = extractTime;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String getVoteNum() {
		return voteNum;
	}

	public void setVoteNum(String voteNum) {
		this.voteNum = voteNum;
	}

	public String getViewNum() {
		return viewNum;
	}

	public void setViewNum(String viewNum) {
		this.viewNum = viewNum;
	}

	public String getScoreBean() {
		return scoreBean;
	}

	public void setScoreBean(String scoreBean) {
		this.scoreBean = scoreBean;
	}

	public String getAuthorUrl() {
		return authorUrl;
	}

	public void setAuthorUrl(String authorUrl) {
		this.authorUrl = authorUrl;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPostTime() {
		return postTime;
	}

	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}

	public String getAnswerNum() {
		return answerNum;
	}

	public void setAnswerNum(String answerNum) {
		this.answerNum = answerNum;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getQuestionUrl() {
		return questionUrl;
	}

	public void setQuestionUrl(String questionUrl) {
		this.questionUrl = questionUrl;
	}

	public int getHistory() {
		return history;
	}

	public void setHistory(int history) {
		this.history = history;
	}

}
