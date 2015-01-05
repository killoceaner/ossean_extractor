package net.trustie.core;

import java.util.ArrayList;
import java.util.List;

import extension.StringHandler;

public class StringHanderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		list1.add("sf");
		list1.add("github");
		list2.add("sf_value");
		list2.add("github_value");
		System.out.println(StringHandler.assemblyOSSEANMap(list1, list2));
	}

}
