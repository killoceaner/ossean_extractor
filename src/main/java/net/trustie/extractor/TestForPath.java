package net.trustie.extractor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.LoggerFactory;

import us.codecraft.xsoup.Xsoup;

public class TestForPath {
	public static org.slf4j.Logger logger = LoggerFactory
			.getLogger(TestForPath.class);

	private static String getContent(String rawurl) {
		URL url = null;
		try {
			url = new URL(rawurl);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		StringBuffer builder = new StringBuffer();
		int responseCode = -1;
		HttpURLConnection con = null;
		try {
			con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");// IE代理进行下载
			con.setConnectTimeout(60000);
			con.setReadTimeout(60000);

			// 获得网页返回信息码
			responseCode = con.getResponseCode();

			if (responseCode == -1) {
				System.out.println("error");
				return null;
			}

			if (responseCode >= 400) {
				System.out.println("error");
				return null;
			}

			InputStream is = con.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String str = null;
			while ((str = br.readLine()) != null)
				builder.append(str);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			con.disconnect();
		}
		return builder.toString();
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Document document = Jsoup.connect(
				"http://blog.csdn.net/a2983913477/article/details/42005703")
				.get();
		// String result = Xsoup
		// .compile(
		// "//*[@id='main']/div[@class='main']/div[@id='article_details']")
		// .evaluate(document).get();
		String result;// =TestForPath.getContent("http://blog.csdn.net/a2983913477/article/details/42005703");
		// document= Jsoup.parse(result);
		// System.out.println(result);
		String str = Xsoup
				.compile(
						"//*[@id='main']/div[@class='main']/div[@id='article_details']")
				.evaluate(document).get();
		document = Jsoup.parse(str);
		System.out.println(str);
		result = Xsoup.compile("//div[@id='digg']/html()").evaluate(document)
				.get();
		// document=Jsoup.parse(result);
		// // Elements eles = document.select("h1 span.title").select(
		// // ".text_overflow");
		// // Element a = eles.first();
		// Pattern pattern=Pattern.compile("[0-9]{4,}-.*");
		// Matcher matcher=pattern.matcher(str);
		// if(matcher.find()){
		// result=matcher.group().trim();
		// //result=result.substring(result.indexOf(':')+1,
		// result.indexOf('次')).trim();
		// if(StringUtils.isNotBlank(result))
		// str=result;
		// }
		// str=str.substring(str.indexOf("提问于：")+1).trim();

		System.out.println(result);
	}
}
