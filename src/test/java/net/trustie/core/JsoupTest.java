package net.trustie.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class JsoupTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filepath = "C:/Users/kg/Desktop/pages/firefox.html";
		InputStreamReader inputReader = null;
		BufferedReader bufferReader = null;
		StringBuffer strBuffer = new StringBuffer();
		try {
			InputStream inputStream = new FileInputStream(filepath);
			inputReader = new InputStreamReader(inputStream);
			bufferReader = new BufferedReader(inputReader);
			// 读取一行
			String line = null;
			while ((line = bufferReader.readLine()) != null)
				strBuffer.append(line);
		} catch (IOException e) {
			System.out.println(e);
		}
		//System.out.println(strBuffer); 
		String part = strBuffer.toString();
		//System.out.println(part);
		Document doc = Jsoup.parse(part);
		Elements eles = doc.select("div.span6 div.well dl dd");
		System.out.println(eles.size());
		System.out.println(eles.get(4).html());
		//Elements ele = eles.get(4).select("");
		//System.out.println(eles.get(0).html());
	}

}
