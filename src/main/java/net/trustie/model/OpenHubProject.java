package net.trustie.model;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.trustie.utils.DateHandler;
import net.trustie.utils.Seperator;
import net.trustie.utils.StringHandler;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import core.AfterExtractor;
import core.Page;
import core.ValidateExtractor;
import us.codecraft.webmagic.model.annotation.ExtractBy;

@ExtractBy("//div[@id='projects_show_page']")
public class OpenHubProject implements AfterExtractor, ValidateExtractor {
	// private static String indefiniteArticleA = "a";
	// private static String indefiniteArticleAn = "an";
	private static String mostWrittenHeader = "mostly written in";
	private static String commentsPercentageTail = "number of source code comments";
	private static String codebaseStatusHeaderTail = "codebase";
	private static String teamScaleTail = "development team";
	private static String commitStatusTail = "commits";
	private static String estimateEffortTimeTail = "of effort";
	private static String firstCommitTimeHeader = "first commit in";
	private static String lastCommitTimeHeader = "most recent commit";

	@ExtractBy("//div[@class='project_title']/h1[@itemprop='name']/a/text()")
	private String name = "";
	@ExtractBy("//div[@id='widgets']/div[@id='project_header_activity_indicator']/div/text()")
	private String activity = "";
	// @ExtractBy("////div[@id='widgets']/div[@itemprop='interactionCount']/div[@class='float_right']/div[@class='use_count']/a/text()")
	// private String strUseCount = "";
   private int useCount = 0;

	@ExtractBy("//div[@class='span6']/div[@id='project_summary']/p/text()")
	private String description = "";

	private String tags = "";

	private String organization = "";

	// quick ref
	private String projectLinks = "";
	private String codeLocation = "";
	private String licenses = "";
	private String similarProjects = "";
	private String managers = "";

	// nut shell
	@ExtractBy("//div[@class='span6']/div[@class='well']/ul[@id='factoids']/li/div[@class='indent']")
	private List<String> codeInfos = null;
	private int commitNum = 0;
	private int contributorNum = 0;
	private int codeLinesNum = 0;
	private String mostWrittenIn = "";
	private String commentsPercentage = "";
	private String codebaseStatus = "";
	private String teamScale = "";
	private String commitStatus = "";
	private String estimateEffortTime = "";
	private Date firstCommitTime = null;
	private Date lastCommitTime = null;

	// languages
	@ExtractBy("//div[@class='span6']/table[@class=table]/tbody/tr[@class='float_left']/td[@style='width: 120px']")
	private List<String> languages = null;
	@ExtractBy("//div[@class='span6']/table[@class=table]/tbody/tr[@class='float_left']/td[@style='width: 20px']/span/span[@itemprop='ratingValue']")
	private List<String> percentages = null;
	private String languagePercentages ="";

	//activity
	@ExtractBy("//div[@class='span6']/div[@class='well']/table[@id='activity_table']/tbody/tr/td/small[@class='summary_timespan thirty_day']/allText()")
	private String activityDayTime = null;
	@ExtractBy("//div[@class='span6']/div[@class='well']/table[@id='activity_table']/tbody/tr/td/ul[@id='thirty_day_summary']/li/big/text()")
	private List<String> dayActivityInfos = null;
	private int dCommitNumber = 0;
	private int dContributorNumber = 0 ;
	@ExtractBy("//div[@class='span6']/div[@class='well']/table[@id='activity_table']/tbody/tr/td/ul[@id='thirty_day_summary']/li/span[@class = 'clear small']/a/allText()")
	private String newContributor = null;
	private int newContriNum = 0;
	
	@ExtractBy("//div[@class='span6']/div[@class='well']/table[@id='activity_table']/td/small[@class='summary_timespan']/allText()")
	private String activityMonthTime = null;
	@ExtractBy("//div[@class=''span6]/div[@class='well']/table[@id='activity_table']/tbody/tr/td/ul[@class='unstyled nutshell']/li/big/text()")
	private List<String> monthActivityInfos = null;
	private int mCommitNumber = 0 ;
	private int mContributorNumber = 0 ;
	@ExtractBy("//div[@class=''span6]/div[@class='well']/table[@id='activity_table']/tbody/tr/td/ul[@class='unstyled nutshell']/li/span[@class='small clear']/allText()")
	private List<String> allTrend = null ;
	private String theCommitTrend = null ;
	private String theContriTrend = null ;
	
	//community
	@ExtractBy("//div[@class = 'proj_community_ratings']/div/span[@style='margin-left: 8px']/allText()")
	private String rateInfo = null ;
	private int rateNum = 0 ;
	@ExtractBy("//div[@class = 'proj_community_ratings']/div/div[@class = 'clear']/span[@class='float_left']/allText()")
    private String rateLevel = null ;		
	@ExtractBy("//div[@id='page']/div[@id='projects_show_page']/div[@class='span12 mezzo']/div/div[@class='float_left']/table[@id='recent_committers_table']/tbody/tr/td[@class ='recent_committers']/a/allText()")
	private List<String> RecenctContributors = null ;
	private String ReContributor = null ;
	
	//buttom informations
	@ExtractBy("//div[@id='projects_show_page']/div[@class='full-width mezzo margin_left_20 margin_right_20 margin_top_15']/div[@class = 'bottom-nav sidebar_project']/div[@class = 'actions']/ul[@class='nav nav-stacked nav-pills']/li[@class=' first']/a/@href")
	private List<String> firstLinks = null;
	private String newsLink = null;
	private String langLink = null;
	private String commitsLink = null;
	@ExtractBy("//div[@id='projects_show_page']/div[@class='full-width mezzo margin_left_20 margin_right_20 margin_top_15']/div[@class = 'bottom-nav sidebar_project']/div[@class = 'actions']/ul[@class='nav nav-stacked nav-pills']/li[@class=' ']/a/@href")
	private List<String> otherLinks = null;
	private String settingLink = null;
    private String sharingwidgetsLink = null;
    private String relatedprojectsLink = null;
    private String costestLink = null;
    private String contributorLink = null;
	
	private String collectTime;
	// @ExtractByUrl()
	private String url;
	private String urlMd5;
	private String pageMd5;
	private int history = 0;

	public void afterProcess(Page page) {

		this.urlMd5 = DigestUtils.md5Hex(page.getPageUrl());
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		this.collectTime = bartDateFormat.format(new Date());

		Document doc = page.getHtml().getDocument();

		// use_count
		Elements useCountLabel = doc
				.select("div#widgets div[itemprop=interactionCount] div.float_right div.use_count a");
		if (useCountLabel.size() > 0) {
			String strUseCount = useCountLabel.get(0).text();
			this.useCount = Integer.parseInt(StringUtils.remove(strUseCount,
					','));
		}

		// tags
		Elements tags = doc.select("div#project_tags p a");
		String tag = null;
		List<String> listTags = new ArrayList<String>();
		for (Element e : tags) {
			tag = e.text();
			listTags.add(tag);
		}
		this.tags = StringHandler.combineTags(listTags);

		// quick reference
		// Elements quickRefs = doc.select("div.span6 div.well dl.");
		Elements quickRefs = doc.select("div.span6 div.well dl");
		if (quickRefs.size() > 0) {
			Element quickRef = quickRefs.get(0);
			handleQuickRef(quickRef);
		}

		// nutshell
		String nutShell0 = codeInfos.get(0);
		handleNutShell0(nutShell0);
		String nutShell1 = codeInfos.get(1);
		handleNutShell1(nutShell1);
		String nutShell2 = codeInfos.get(2);
		handleNutShell2(nutShell2);
		String nutShell3 = codeInfos.get(3);
		handleNutShell3(nutShell3);
		
		List<String> eList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();
		//language
		for(int i = 0 ;i<languages.size();i++){
			Element e = Jsoup.parse(languages.get(i));
			Element eValue = Jsoup.parse(percentages.get(i));
			//String strE = e.text();
			eList.add(e.text());
			//String strValue = eValue.text();
			valueList.add(eValue.text());
		}
		//this.languagePercentages = StringHandler.assemblyOSSEANMap(eList, valueList);
		
		//activity
		String strtmp1 = null;
		String strtmp2 = null;
		String strtmp3 = null;
		String strtmp4 = null;
		//this.dCommitNumber = Integer.parseInt(dayActivityInfos.get(0));
		//this.dContributorNumber = Integer.parseInt(dayActivityInfos.get(1));
		strtmp1 = dayActivityInfos.get(0);
		this.dCommitNumber = getInt(strtmp1);
		strtmp2 = dayActivityInfos.get(1); 
		this.dContributorNumber = getInt(strtmp2);
		strtmp3 = monthActivityInfos.get(0);
		this.mCommitNumber = getInt(strtmp3);
	    strtmp4 = monthActivityInfos.get(1);
	    this.mContributorNumber = getInt(strtmp4);
	    this.newContriNum = getInt(this.newContributor);
	    this.theCommitTrend = allTrend.get(0);
	    this.theContriTrend = allTrend.get(1);
	    
	    //community
	    this.rateNum = getInt(this.rateInfo);
	    this.ReContributor = StringHandler.combineTags(this.RecenctContributors);
	    
	    //button informations
	    String tmphref = "https://www.openhub.net";
	    this.newsLink = tmphref+this.firstLinks.get(0);
	    this.langLink = tmphref+this.firstLinks.get(1);
	    
	    this.commitsLink = tmphref+this.firstLinks.get(2);	    
	    this.settingLink = tmphref+this.otherLinks.get(0);
	    
	    this.sharingwidgetsLink =tmphref+this.otherLinks.get(1);
	    this.relatedprojectsLink = tmphref+this.otherLinks.get(2);
	    this.costestLink = tmphref+this.otherLinks.get(3);
	    this.contributorLink = tmphref+this.otherLinks.get(4);
	    
	}

	@Override
	public void validate(Page page) {
		// TODO Auto-generated method stub
		
	}

	private void handleQuickRef(Element quickRef) {
		Elements itemNames = quickRef.select("dt");
		Elements itemValues = quickRef.select("dd");
		Element e = null;
		Element eValue = null;
		for (int i = 0; i < itemNames.size(); i++) {
			e = itemNames.get(i);
			eValue = itemValues.get(i);
			String refName = e.text();
			switch (refName) {
			case "Organization:": {
				this.organization = eValue.text();
				break;
			}
			case "Project Links:": {
				Elements links = eValue.select("a");
				String[] tmp = new String[links.size()];
				Element ele = null;
				for (int j = 0; j < links.size(); j++) {
					ele = links.get(j);
					tmp[j] = ele.text() + Seperator.SOURCE_SEPERATOR
							+ ele.attr("href");
				}
				this.projectLinks = StringUtils.join(tmp,
						Seperator.OSSEAN_SEPERATOR);
				break;
			}
			case "Code Locations:": {
				Elements locs = eValue.select("a");
				if (locs.size() == 0) {
					this.codeLocation = eValue.text();
				} else {
					Element link = locs.get(0);
					this.codeLocation = link.text()
							+ Seperator.SOURCE_SEPERATOR + link.attr("href");
				}

				break;
			}
			case "Licenses:": {
				Elements links = eValue.select("a");
				List<String> listLicenses = new ArrayList<String>();
				// String[] tmp = new String[links.size()];
				for (int j = 0; j < links.size(); j++) {
					listLicenses.add(links.get(j).text());
					// tmp[j] = links.get(j).text();
				}
				this.licenses = StringHandler.combineTags(listLicenses);
				break;
			}
			case "Similar Projects:": {
				// System.out.println(eValue);
				Elements projects = eValue.select("td[width=49%]");
				// System.out.println(projects.size());
				String[] tmp = new String[projects.size()];
				Element ele = null;
				for (int j = 0; j < projects.size(); j++) {
					ele = projects.get(j);
					Element project = ele.select("a").get(0);
					tmp[j] = project.text() + Seperator.SOURCE_SEPERATOR
							+ project.attr("href");
				}
				this.similarProjects = StringUtils.join(tmp,
						Seperator.OSSEAN_SEPERATOR);
				break;
			}
			case "Managers:": {
				if ("Become the first manager for BugSystem".equals(eValue
						.text())) {
					break;
				}
				Elements users = eValue.select("a");
				String[] tmp = new String[users.size()];
				Element ele = null;
				for (int j = 0; j < users.size(); j++) {
					ele = users.get(j);
					tmp[j] = ele.text() + Seperator.SOURCE_SEPERATOR
							+ ele.attr("href");
				}
				this.managers = StringUtils.join(tmp,
						Seperator.OSSEAN_SEPERATOR);
				break;
			}
			default: {
				break;
			}
			}
		}
	}

	private Elements getAElements(String code) {
		Document doc = Jsoup.parse(code);
		Elements eles = doc.select("a");
		return eles;
	}

	private void handleNutShell0(String nutshell) {
		Elements eles = getAElements(nutshell);
		Element ele = null;
		ele = eles.get(0);
		this.commitNum = getInt(ele.text());
		ele = eles.get(1);
		this.contributorNum = getInt(ele.text());
		ele = eles.get(2);
		this.codeLinesNum = getInt(ele.text());
	}

	private void handleNutShell1(String nutshell) {
		Elements eles = getAElements(nutshell);
		Element ele = null;
		ele = eles.get(0);
		this.mostWrittenIn = StringHandler.removeHeader(ele.text(),
				OpenHubProject.mostWrittenHeader).trim();
		ele = eles.get(1);
		// System.out.println(ele.text());
		this.commentsPercentage = StringHandler.removeTail(ele.text(),
				OpenHubProject.commentsPercentageTail).trim();
		// System.out.println(this.commentsPercentage);
		this.commentsPercentage = StringHandler.removeIndefiniteArticles(
				this.commentsPercentage).trim();
		// System.out.println(this.commentsPercentage);
	}

	private void handleNutShell2(String nutshell) {
		Elements eles = getAElements(nutshell);
		Element ele = null;
		ele = eles.get(0);
		this.codebaseStatus = ele.text();
		ele = eles.get(1);
		this.teamScale = ele.text();
		ele = eles.get(2);
		this.commitStatus = ele.text();
	}

	private void handleNutShell3(String nutshell) {
		Elements eles = getAElements(nutshell);
		Element ele = null;
		ele = eles.get(0);
		this.estimateEffortTime = StringHandler.removeTail(ele.text(),
				OpenHubProject.estimateEffortTimeTail).trim();
		ele = eles.get(1);
		String firstCommitAt = StringHandler.removeHeader(ele.text(),
				OpenHubProject.firstCommitTimeHeader).trim();
		this.firstCommitTime = handleDateAt(firstCommitAt);
		ele = eles.get(2);
		String lastCommitAt = StringHandler.removeHeader(ele.text(),
				OpenHubProject.lastCommitTimeHeader).trim();
		System.out.println(lastCommitAt);
		lastCommitAt = StringHandler.removePreposition(lastCommitAt);
		//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
		this.lastCommitTime = DateHandler.stringToDate(DateHandler.formatAllTypeDate(lastCommitAt));//handleDateBefore(lastCommitAt);
	}

	private int getInt(String in) {
		return StringHandler.extractIntFromString(in);
	}

	private Date handleDateAt(String strDate) {
		String[] date = strDate.split(",");
		String strMonth = date[0];
		String strYear = date[1];
		switch (strMonth) {
		case "January": {
			strMonth = "01";
			break;
		}
		case "February": {
			strMonth = "02";
			break;
		}
		case "March": {
			strMonth = "03";
			break;
		}
		case "April": {
			strMonth = "04";
			break;
		}
		case "May": {
			strMonth = "05";
			break;
		}
		case "June": {
			strMonth = "06";
			break;
		}
		case "July": {
			strMonth = "07";
			break;
		}
		case "August": {
			strMonth = "08";
			break;
		}
		case "September": {
			strMonth = "09";
			break;
		}
		case "October": {
			strMonth = "10";
			break;
		}
		case "November": {
			strMonth = "11";
			break;
		}
		case "December": {
			strMonth = "12";
			break;
		}
		default: {
			strMonth = "00";
			break;
		}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String dateTmp = strYear + "-" + strMonth;
		Date rt = null;
		try {
			rt = sdf.parse(dateTmp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rt;
	}

//	private Date handleDateBefore(String strDate) {
//		// System.out.println(strDate);
//		int num = StringHandler.extractIntFromString(strDate);
//		String unit = StringHandler.getUnit(strDate);
//
//		return DateHandler.getDateBefore(num, unit);
//	}

	/**
	 * @return the mostWrittenHeader
	 */
	public static String getMostWrittenHeader() {
		return mostWrittenHeader;
	}

	/**
	 * @return the commentsPercentageTail
	 */
	public static String getCommentsPercentageTail() {
		return commentsPercentageTail;
	}

	/**
	 * @return the codebaseStatusHeaderTail
	 */
	public static String getCodebaseStatusHeaderTail() {
		return codebaseStatusHeaderTail;
	}

	/**
	 * @return the teamScaleTail
	 */
	public static String getTeamScaleTail() {
		return teamScaleTail;
	}

	/**
	 * @return the commitStatusTail
	 */
	public static String getCommitStatusTail() {
		return commitStatusTail;
	}

	/**
	 * @return the estimateEffortTimeTail
	 */
	public static String getEstimateEffortTimeTail() {
		return estimateEffortTimeTail;
	}

	/**
	 * @return the firstCommitTimeHeader
	 */
	public static String getFirstCommitTimeHeader() {
		return firstCommitTimeHeader;
	}

	/**
	 * @return the lastCommitTimeHeader
	 */
	public static String getLastCommitTimeHeader() {
		return lastCommitTimeHeader;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the activity
	 */
	public String getActivity() {
		return activity;
	}

	/**
	 * @return the useCount
	 */
	public int getUseCount() {
		return useCount;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * @return the organization
	 */
	public String getOrganization() {
		return organization;
	}

	/**
	 * @return the projectLinks
	 */
	public String getProjectLinks() {
		return projectLinks;
	}

	/**
	 * @return the codeLocation
	 */
	public String getCodeLocation() {
		return codeLocation;
	}

	/**
	 * @return the licenses
	 */
	public String getLicenses() {
		return licenses;
	}

	/**
	 * @return the similarProjects
	 */
	public String getSimilarProjects() {
		return similarProjects;
	}

	/**
	 * @return the managers
	 */
	public String getManagers() {
		return managers;
	}

	/**
	 * @return the codeInfos
	 */
	public List<String> getCodeInfos() {
		return codeInfos;
	}

	/**
	 * @return the commitNum
	 */
	public int getCommitNum() {
		return commitNum;
	}

	/**
	 * @return the contributorNum
	 */
	public int getContributorNum() {
		return contributorNum;
	}

	/**
	 * @return the codeLinesNum
	 */
	public int getCodeLinesNum() {
		return codeLinesNum;
	}

	/**
	 * @return the mostWrittenIn
	 */
	public String getMostWrittenIn() {
		return mostWrittenIn;
	}

	/**
	 * @return the commentsPercentage
	 */
	public String getCommentsPercentage() {
		return commentsPercentage;
	}

	/**
	 * @return the codebaseStatus
	 */
	public String getCodebaseStatus() {
		return codebaseStatus;
	}

	/**
	 * @return the teamScale
	 */
	public String getTeamScale() {
		return teamScale;
	}

	/**
	 * @return the commitStatus
	 */
	public String getCommitStatus() {
		return commitStatus;
	}

	/**
	 * @return the estimateEffortTime
	 */
	public String getEstimateEffortTime() {
		return estimateEffortTime;
	}

	/**
	 * @return the firstCommitTime
	 */
	public Date getFirstCommitTime() {
		return firstCommitTime;
	}

	/**
	 * @return the lastCommitTime
	 */
	public Date getLastCommitTime() {
		return lastCommitTime;
	}

	/**
	 * @return the collectTime
	 */
	public String getCollectTime() {
		return collectTime;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @return the urlMd5
	 */
	public String getUrlMd5() {
		return urlMd5;
	}

	/**
	 * @return the pageMd5
	 */
	public String getPageMd5() {
		return pageMd5;
	}

	/**
	 * @return the history
	 */
	public int getHistory() {
		return history;
	}

	/**
	 * @param mostWrittenHeader
	 *            the mostWrittenHeader to set
	 */
	public static void setMostWrittenHeader(String mostWrittenHeader) {
		OpenHubProject.mostWrittenHeader = mostWrittenHeader;
	}

	/**
	 * @param commentsPercentageTail
	 *            the commentsPercentageTail to set
	 */
	public static void setCommentsPercentageTail(String commentsPercentageTail) {
		OpenHubProject.commentsPercentageTail = commentsPercentageTail;
	}

	/**
	 * @param codebaseStatusHeaderTail
	 *            the codebaseStatusHeaderTail to set
	 */
	public static void setCodebaseStatusHeaderTail(
			String codebaseStatusHeaderTail) {
		OpenHubProject.codebaseStatusHeaderTail = codebaseStatusHeaderTail;
	}

	/**
	 * @param teamScaleTail
	 *            the teamScaleTail to set
	 */
	public static void setTeamScaleTail(String teamScaleTail) {
		OpenHubProject.teamScaleTail = teamScaleTail;
	}

	/**
	 * @param commitStatusTail
	 *            the commitStatusTail to set
	 */
	public static void setCommitStatusTail(String commitStatusTail) {
		OpenHubProject.commitStatusTail = commitStatusTail;
	}

	/**
	 * @param estimateEffortTimeTail
	 *            the estimateEffortTimeTail to set
	 */
	public static void setEstimateEffortTimeTail(String estimateEffortTimeTail) {
		OpenHubProject.estimateEffortTimeTail = estimateEffortTimeTail;
	}

	/**
	 * @param firstCommitTimeHeader
	 *            the firstCommitTimeHeader to set
	 */
	public static void setFirstCommitTimeHeader(String firstCommitTimeHeader) {
		OpenHubProject.firstCommitTimeHeader = firstCommitTimeHeader;
	}

	/**
	 * @param lastCommitTimeHeader
	 *            the lastCommitTimeHeader to set
	 */
	public static void setLastCommitTimeHeader(String lastCommitTimeHeader) {
		OpenHubProject.lastCommitTimeHeader = lastCommitTimeHeader;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param activity
	 *            the activity to set
	 */
	public void setActivity(String activity) {
		this.activity = activity;
	}

	/**
	 * @param useCount
	 *            the useCount to set
	 */
	public void setUseCount(int useCount) {
		this.useCount = useCount;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param tags
	 *            the tags to set
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}

	/**
	 * @param organization
	 *            the organization to set
	 */
	public void setOrganization(String organization) {
		this.organization = organization;
	}

	/**
	 * @param projectLinks
	 *            the projectLinks to set
	 */
	public void setProjectLinks(String projectLinks) {
		this.projectLinks = projectLinks;
	}

	/**
	 * @param codeLocation
	 *            the codeLocation to set
	 */
	public void setCodeLocation(String codeLocation) {
		this.codeLocation = codeLocation;
	}

	/**
	 * @param licenses
	 *            the licenses to set
	 */
	public void setLicenses(String licenses) {
		this.licenses = licenses;
	}

	/**
	 * @param similarProjects
	 *            the similarProjects to set
	 */
	public void setSimilarProjects(String similarProjects) {
		this.similarProjects = similarProjects;
	}

	/**
	 * @param managers
	 *            the managers to set
	 */
	public void setManagers(String managers) {
		this.managers = managers;
	}

	/**
	 * @param codeInfos
	 *            the codeInfos to set
	 */
	public void setCodeInfos(List<String> codeInfos) {
		this.codeInfos = codeInfos;
	}

	/**
	 * @param commitNum
	 *            the commitNum to set
	 */
	public void setCommitNum(int commitNum) {
		this.commitNum = commitNum;
	}

	/**
	 * @param contributorNum
	 *            the contributorNum to set
	 */
	public void setContributorNum(int contributorNum) {
		this.contributorNum = contributorNum;
	}

	/**
	 * @param codeLinesNum
	 *            the codeLinesNum to set
	 */
	public void setCodeLinesNum(int codeLinesNum) {
		this.codeLinesNum = codeLinesNum;
	}

	/**
	 * @param mostWrittenIn
	 *            the mostWrittenIn to set
	 */
	public void setMostWrittenIn(String mostWrittenIn) {
		this.mostWrittenIn = mostWrittenIn;
	}

	/**
	 * @param commentsPercentage
	 *            the commentsPercentage to set
	 */
	public void setCommentsPercentage(String commentsPercentage) {
		this.commentsPercentage = commentsPercentage;
	}

	/**
	 * @param codebaseStatus
	 *            the codebaseStatus to set
	 */
	public void setCodebaseStatus(String codebaseStatus) {
		this.codebaseStatus = codebaseStatus;
	}

	/**
	 * @param teamScale
	 *            the teamScale to set
	 */
	public void setTeamScale(String teamScale) {
		this.teamScale = teamScale;
	}

	/**
	 * @param commitStatus
	 *            the commitStatus to set
	 */
	public void setCommitStatus(String commitStatus) {
		this.commitStatus = commitStatus;
	}

	/**
	 * @param estimateEffortTime
	 *            the estimateEffortTime to set
	 */
	public void setEstimateEffortTime(String estimateEffortTime) {
		this.estimateEffortTime = estimateEffortTime;
	}

	/**
	 * @param firstCommitTime
	 *            the firstCommitTime to set
	 */
	public void setFirstCommitTime(Date firstCommitTime) {
		this.firstCommitTime = firstCommitTime;
	}

	/**
	 * @param lastCommitTime
	 *            the lastCommitTime to set
	 */
	public void setLastCommitTime(Date lastCommitTime) {
		this.lastCommitTime = lastCommitTime;
	}

	/**
	 * @param collectTime
	 *            the collectTime to set
	 */
	public void setCollectTime(String collectTime) {
		this.collectTime = collectTime;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @param urlMd5
	 *            the urlMd5 to set
	 */
	public void setUrlMd5(String urlMd5) {
		this.urlMd5 = urlMd5;
	}

	/**
	 * @param pageMd5
	 *            the pageMd5 to set
	 */
	public void setPageMd5(String pageMd5) {
		this.pageMd5 = pageMd5;
	}

	/**
	 * @param history
	 *            the history to set
	 */
	public void setHistory(int history) {
		this.history = history;
	}
	
	/**
	 * @return the languagePercentages
	 */
	public String getLanguagePercentages() {
		return languagePercentages;
	}

	/**
	 * @param languagePercentages the languagePercentages to set
	 */
	public void setLanguagePercentages(String languagePercentages) {
		this.languagePercentages = languagePercentages;
	}
	/**
	 * activity params
	 */
	public int getdCommitNumber() {
		return dCommitNumber;
	}

	public void setdCommitNumber(int dCommitNumber) {
		this.dCommitNumber = dCommitNumber;
	}

	public int getdContributorNumber() {
		return dContributorNumber;
	}

	public void setdContributorNumber(int dContributorNumber) {
		this.dContributorNumber = dContributorNumber;
	}

	public int getNewContriNum() {
		return newContriNum;
	}

	public void setNewContriNum(int newContriNum) {
		this.newContriNum = newContriNum;
	}

	public int getmCommitNumber() {
		return mCommitNumber;
	}

	public void setmCommitNumber(int mCommitNumber) {
		this.mCommitNumber = mCommitNumber;
	}

	public int getmContributorNumber() {
		return mContributorNumber;
	}

	public void setmContributorNumber(int mContributorNumber) {
		this.mContributorNumber = mContributorNumber;
	}

	public String getTheCommitTrend() {
		return theCommitTrend;
	}

	public void setTheCommitTrend(String theCommitTrend) {
		this.theCommitTrend = theCommitTrend;
	}

	public String getTheContriTrend() {
		return theContriTrend;
	}

	public void setTheContriTrend(String theContriTrend) {
		this.theContriTrend = theContriTrend;
	}
	public List<String> getLanguages() {
		return languages;
	}



	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}



	public String getActivityDayTime() {
		return activityDayTime;
	}



	public void setActivityDayTime(String activityDayTime) {
		this.activityDayTime = activityDayTime;
	}



	public String getNewContributor() {
		return newContributor;
	}



	public void setNewContributor(String newContributor) {
		this.newContributor = newContributor;
	}



	public String getActivityMonthTime() {
		return activityMonthTime;
	}



	public void setActivityMonthTime(String activityMonthTime) {
		this.activityMonthTime = activityMonthTime;
	}



	public int getRateNum() {
		return rateNum;
	}



	public void setRateNum(int rateNum) {
		this.rateNum = rateNum;
	}



	public String getRateLevel() {
		return rateLevel;
	}



	public void setRateLevel(String rateLevel) {
		this.rateLevel = rateLevel;
	}



	public String getReContributor() {
		return ReContributor;
	}



	public void setReContributor(String reContributor) {
		ReContributor = reContributor;
	}



	public String getNewsLink() {
		return newsLink;
	}



	public void setNewsLink(String newsLink) {
		this.newsLink = newsLink;
	}



	public String getLangLink() {
		return langLink;
	}



	public void setLangLink(String langLink) {
		this.langLink = langLink;
	}



	public String getCommitsLink() {
		return commitsLink;
	}



	public void setCommitsLink(String commitsLink) {
		this.commitsLink = commitsLink;
	}



	public String getSettingLink() {
		return settingLink;
	}



	public void setSettingLink(String settingLink) {
		this.settingLink = settingLink;
	}



	public String getSharingwidgetsLink() {
		return sharingwidgetsLink;
	}



	public void setSharingwidgetsLink(String sharingwidgetsLink) {
		this.sharingwidgetsLink = sharingwidgetsLink;
	}



	public String getRelatedprojectsLink() {
		return relatedprojectsLink;
	}



	public void setRelatedprojectsLink(String relatedprojectsLink) {
		this.relatedprojectsLink = relatedprojectsLink;
	}



	public String getCostestLink() {
		return costestLink;
	}



	public void setCostestLink(String costestLink) {
		this.costestLink = costestLink;
	}



	public String getContributorLink() {
		return contributorLink;
	}



	public void setContributorLink(String contributorLink) {
		this.contributorLink = contributorLink;
	}








}
