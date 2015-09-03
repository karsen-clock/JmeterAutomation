package com.ctrip.automation.common;

import com.ctrip.automation.common.SendMail;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String smtp = "smtp.126.com";
		String from = "testautomation@126.com";
		String to = "494344089@qq.com";
		String copyto = "494344089@qq.com";
		String subject = "Test Report";
		String content = "details please refer attachment";
		String username="testautomation@126.com";
		String password="*******";
	 	String filename = "D:\\test.txt";
		SendMail.sendAndCc(smtp, from, to, copyto, subject, content, username, password, filename);
		System.out.println("hello,welcome to use  automation test api");
	}

}
