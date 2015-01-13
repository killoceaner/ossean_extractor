package net.trustie.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;

import core.AfterExtractor;
import core.Page;
import core.ValidateExtractor;
import extension.StringHandler;
import us.codecraft.webmagic.model.annotation.ExtractBy;

  @ExtractBy("//*div[@id='main_wrapper']/div[@id='sideleft']/div[@id='news_main']")
public class CNblogsNews_Model implements AfterExtractor,ValidateExtractor{

	@ExtractBy("//div[@id='news_info']/span[@class='news_poster']/a/text()")
	private String news_poster;
	@ExtractBy("//div[@id='news_info']/span[@class='news_poster']/a/@href")
	private String news_poster_url;
	@ExtractBy("//div[@id='news_title']/a/text()")
	private String news_title;
	@ExtractBy("//div[@id='news_body']/allText()")
	private String news_body;
	@ExtractBy("//div[@id='news_info']/span[@class='time']/text()")
	private String relative_time;
	private String extractTime;
	@ExtractBy("//*div[@id='news_info']/span[@id='News_TotalView']/text()")
	private String strViewNum;
	private int view_num =0;
	@ExtractBy("//*div[@id='news_info']/span[@class='comment']/allText()")
	private String strCommentNum = "";
	private int comment_num = 0;
    @ExtractBy("//*div[@id='come_from']/a/text()")
	private String come_from;
    private String diggnum="0";
	private String burynum ="0";
	private String url;
	private String pageMD5;
	private String urlMD5;
	private String history = "0";
	private String tag;
	@ExtractBy("//div[@id='news_more_info']/div[@class='news_tags']/allText()")
	private String tags;

	@Override
	public void afterProcess(Page page) {
		// 对Url进行处理
		this.url = page.getPageUrl();
		// 对UrMD5l进行处理
		this.urlMD5 = DigestUtils.md5Hex(this.url);
		// 对extractTime进行处理
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		this.extractTime = bartDateFormat.format(new Date());
		// 对relative_time进行处理
		this.relative_time = relative_time.substring("发布于 ".length()) + ":00";
		// 对comment_num和view_num进行处理
		/*this.comment_num = StringHandler.extractIntFromString(strCommentNum);
		this.view_num = StringHandler.extractIntFromString(strViewNum);*/
		// 对tags进行处理
		if (tags != "") {
			this.tags = tags.substring("标签：".length());
			String s = "<" + tags.trim() + ">";
			s = s.replace(" ", "><");
			this.tag= s;
		}

		// url、urlMD5、tags、extractTime、ralative_time、comment_num、 view_num
		
	}

	public String getNews_poster() {
		return news_poster;
	}

	public void setNews_poster(String news_poster) {
		this.news_poster = news_poster;
	}

	public String getNews_poster_url() {
		return news_poster_url;
	}

	public void setNews_poster_url(String news_poster_url) {
		this.news_poster_url = news_poster_url;
	}

	public String getNews_title() {
		return news_title;
	}

	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}

	public String getNews_body() {
		return news_body;
	}

	public void setNews_body(String news_body) {
		this.news_body = news_body;
	}

	public String getRelative_time() {
		return relative_time;
	}

	public void setRelative_time(String relative_time) {
		this.relative_time = relative_time;
	}

	public String getExtractTime() {
		return extractTime;
	}

	public void setExtractTime(String extractTime) {
		this.extractTime = extractTime;
	}

	public String getStrViewNum() {
		return strViewNum;
	}

	public void setStrViewNum(String strViewNum) {
		this.strViewNum = strViewNum;
	}

	public int getView_num() {
		return view_num;
	}

	public void setView_num(int view_num) {
		this.view_num = view_num;
	}

	public String getStrCommentNum() {
		return strCommentNum;
	}

	public void setStrCommentNum(String strCommentNum) {
		this.strCommentNum = strCommentNum;
	}

	public int getComment_num() {
		return comment_num;
	}

	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}

	public String getCome_from() {
		return come_from;
	}

	public void setCome_from(String come_from) {
		this.come_from = come_from;
	}

	public String getDiggnum() {
		return diggnum;
	}

	public void setDiggnum(String diggnum) {
		this.diggnum = diggnum;
	}

	public String getBurynum() {
		return burynum;
	}

	public void setBurynum(String burynum) {
		this.burynum = burynum;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPageMD5() {
		return pageMD5;
	}

	public void setPageMD5(String pageMD5) {
		this.pageMD5 = pageMD5;
	}

	public String getUrlMD5() {
		return urlMD5;
	}

	public void setUrlMD5(String urlMD5) {
		this.urlMD5 = urlMD5;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	@Override
	public void validate(Page page) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				if (StringHandler.isLeastOneBlank(this.news_title, this.news_poster,
						this.news_poster_url)) {
					page.setResultSkip(this, true);
					return;
				}

				/*if (!page.getResultItems().isSkip()) {
					if (!StringHandler.canFormatterInteger(this.,
							this.topicScore)) {
						page.setResultSkip(this, true);
						return;
					}
*/
					if (!StringHandler
							.canFormatterDate(this.relative_time, this.extractTime)) {
						page.setResultSkip(this,true);
					}
	}

}
