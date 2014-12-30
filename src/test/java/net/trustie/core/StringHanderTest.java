package net.trustie.core;

import extension.StringHandler;

public class StringHanderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String tmp = "23 day";
		String rs =StringHandler.getUnit(tmp);
		System.out.println(rs);
	}

}
