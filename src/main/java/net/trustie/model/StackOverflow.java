package net.trustie.model;

import java.util.List;

import core.AfterExtractor;
import core.Page;
import us.codecraft.webmagic.model.annotation.ExtractBy;

@ExtractBy("//*[@class='container']/div[@id='content']/div/div[@id='mainbar']/div[@id='question']/table")
public class StackOverflow implements AfterExtractor {

	@ExtractBy("//*[@id='question-header']/h1/a[@class='question-hyperlink']/text()")
	private String questionTitle;

	@ExtractBy("//*[@class='postcell']/div/div[@class='post-text']/allText()")
	private String questionContent;

	@ExtractBy("//*[@class='postcell']/div/div[@class='post-taglist']/a/allText()")
	private List<String> tags;

	@ExtractBy("//*div[@class='user-details']/a/@href")
	private String authorUrl;

	@ExtractBy("//*div[@class='user-details']/a/text()")
	private String author;

	@ExtractBy("//*div[@class='user-action-time']/span/text()")
	private String postTime;

	@ExtractBy("//*td[@class='votecell']/div[@class='vote']/span/text()")
	private String vote;

	@ExtractBy("//*td[@class='votecell']/div[@class='vote']/div[@class='favoritecount']/b/text()")
	private String likeNum;

	public void afterProcess(Page arg0) {
		// TODO Auto-generated method stub

	}

}
