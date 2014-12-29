package net.trustie.core;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import core.Page;


public class GetPage {
	/**
	 * 从文件读取单个页面
	 * 
	 * @param filePath
	 * @return
	 */
	public static Page getPageFromFile(String url,String filePath) {

		InputStreamReader inputReader = null;
		BufferedReader bufferReader = null;
		StringBuffer strBuffer = new StringBuffer();
		try {
			InputStream inputStream = new FileInputStream(filePath);
			inputReader = new InputStreamReader(inputStream);
			bufferReader = new BufferedReader(inputReader);
			// 读取一行
			String line = null;
			while ((line = bufferReader.readLine()) != null)
				strBuffer.append(line);
		} catch (IOException e) {
			System.out.println(e);
			return null;
		}
		return GeneratePage.createPage(url, strBuffer.toString());
	}

	/**
	 * 从多个文件中读取页面
	 * 
	 * @param files
	 * @return
	 */
	public static List<Page> getPagesFromFiles(Map<String, String> files) {
		List<Page> pageList = new ArrayList<Page>();
		Set<String> keySet = files.keySet();
		for (String key : keySet) {
			Page page = getPageFromFile(key, files.get(key));
			pageList.add(page);
		}
		return pageList;
	}

	/**
	 * 从网上下载单个页面
	 * 
	 * @param url
	 * @return
	 */
	public static Page getPageFromInternet(String url) {
		try {
			Document document = Jsoup.connect(url).get();
			String rawText = document.toString();
			Page page = GeneratePage.createPage(url, rawText);
			if (page != null)
				return page;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * 从网上下载多个页面
	 * @param urls
	 * @return
	 */
	public static List<Page> getPagesFromInternet(List<String> urls) {
		List<Page> pageList = new ArrayList<Page>();
		for (String url : urls) {
			Page page = getPageFromInternet(url);
			pageList.add(page);
		}
		return pageList;
	}
}
