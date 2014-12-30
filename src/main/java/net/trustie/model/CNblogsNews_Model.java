package net.trustie.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;

import core.AfterExtractor;
import core.Page;
import extension.StringHandler;
import us.codecraft.webmagic.model.annotation.ExtractBy;

@ExtractBy("//*[@id='news_main']")
public class CNblogsNews_Model implements AfterExtractor {

	@ExtractBy("//div[@id='news_info']/span[1]/a/text()")
	private String news_poster;
	@ExtractBy("//div[@id='news_info']/span[1]/a/@href")
	private String news_poster_url;
	@ExtractBy("//div[@id='news_title']/a/text()")
	private String news_title;
	@ExtractBy("//div[@id='news_body']/allText()")
	private String news_body;
	@ExtractBy("//div[@id='news_info']/span[2]/text()")
	private String relative_time;
	private String extractTime;
	@ExtractBy("//div[@id='news_info']/span[4]/text()")
	private String strViewNum = "";
	private int view_num = 0;
	@ExtractBy("//div[@id='news_info']/span[3]/a/text()")
	private String strCommentNum = "";
	private int comment_num = 0;

	private String come_from;
	@ExtractBy("//div[@class='diggit']/span/text()")
	private String diggnum = "0";
	@ExtractBy("//div[@class='buryit']/span/text()")
	private String burynum = "0";
	private String url;
	private String pageMD5;
	private String urlMD5;
	private String history = "0";
	private String tag;
	@ExtractBy("//div[@id='news_more_info']/div[2]/allText()")
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
		// this.comment_num= StringUtils.getNumber(comment_num);
		this.comment_num = StringHandler.extractIntFromString(strCommentNum);
		this.view_num = StringHandler.extractIntFromString(strViewNum);
		// 对tags进行处理
		if (tags != "") {
			this.tags = tags.substring("标签：".length());
			String s = "<" + tags.trim() + ">";
			s = s.replace(" ", "><");
			this.tags = s;
		}

		// url、urlMD5、tags、extractTime、ralative_time、comment_num、 view_num
	}

}
