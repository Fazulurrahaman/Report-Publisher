package com.demo.ExcelProject.sender;

import java.io.BufferedReader;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

import com.demo.ExcelProject.Student;

public class SMSSender {

	private Properties props;
	private Set<Student> studentEntries;

	public SMSSender() throws IOException {
		props = new Properties();
		props.load(new FileInputStream("config.properties"));
	}

	public void sendMessage(Set<Student> studentEntries) throws IOException {
		this.studentEntries = studentEntries;

		String sendingLink = "https://www.fast2sms.com/dev/bulkV2?authorization=" + props.getProperty("MY_API_KEY");

		System.out.println("Sending SMS to parent...\t\t");

		Iterator<Student> iter = this.studentEntries.iterator();

		while (iter.hasNext()) {

			Student s = iter.next();

			String ID = "&sender_id=TXTIND";
			String message = "&message=" + URLEncoder.encode(MessageBody.getSMSBody(s), "UTF-8");

			String language = "&language=english";
			String route = "&route=v3";
			String number = "&numbers=" + s.getParentMobile();

			sendWebRequest(sendingLink + ID + message + language + route + number);
		}
		System.out.println("Sent Successfully!");
	}

	public void checkBalance() throws IOException {

		final String balanceLink = "https://www.fast2sms.com/dev/wallet?authorization=" + props.getProperty("MY_API_KEY");

		System.out.println(sendWebRequest(balanceLink));
	}

	private StringBuffer sendWebRequest(String link) throws IOException {
		URL url = new URL(link);

		HttpsURLConnection con =  (HttpsURLConnection) url.openConnection();

		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		con.setRequestProperty("cache-control", "no-cache");

		StringBuffer response = new StringBuffer();

		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

		while (true) {
			String line = br.readLine();
			if (line == null) {
				break;
			}
			response.append(line);

		}
		System.out.println(response);

		return response;
	}
}
