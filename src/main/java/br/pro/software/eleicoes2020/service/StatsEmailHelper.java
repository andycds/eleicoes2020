package br.pro.software.eleicoes2020.service;

import java.io.IOException;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

public class StatsEmailHelper {
	private static SendGrid sg = new SendGrid(System.getenv("SG_API_KN"));

	public static void openTrackerStatusOn() {
		try {
			SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
			Request request = new Request();
			request.setMethod(Method.PATCH);
			request.setEndpoint("tracking_settings/open");
			request.setBody("{\"enabled\":true}");
			Response response = sg.api(request);
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}


	public static void stats() {
		try {
			Request request = new Request();
			request.setMethod(Method.GET);
			request.setEndpoint("stats");
			request.addQueryParam("aggregated_by", "day");
			request.addQueryParam("limit", "1");
			request.addQueryParam("start_date", "2020-07-01");
			request.addQueryParam("end_date", "2020-08-05");
			request.addQueryParam("offset", "1");
			Response response = sg.api(request);
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

	public static void invalid() {
		try {
			Request request = new Request();
			request.setMethod(Method.GET);
			request.setEndpoint("suppression/invalid_emails");
			request.addQueryParam("start_time", "1");
			request.addQueryParam("limit", "1");
			request.addQueryParam("end_time", "1");
			request.addQueryParam("offset", "1");
			Response response = sg.api(request);
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

	public static void spams() {
		try {
			Request request = new Request();
			request.setMethod(Method.GET);
			request.setEndpoint("suppression/spam_reports");
			request.addQueryParam("start_time", "1");
			request.addQueryParam("limit", "1");
			request.addQueryParam("end_time", "1");
			request.addQueryParam("offset", "1");
			Response response = sg.api(request);
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

	public static void bounces() {
		try {
			Request request = new Request();
			request.setMethod(Method.GET);
			request.setEndpoint("suppression/bounces");
			request.addQueryParam("start_time", "1");
			request.addQueryParam("end_time", "1");
			Response response = sg.api(request);
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}
}
