package br.pro.software.eleicoes2020.service;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import br.pro.software.eleicoes2020.model.Pessoa;

public class EmailHelper {
	private static final int HTTP_OK = 200;
	private static final int HTTP_NOT_FOUND = 404;

	private static SendGrid sg = new SendGrid(System.getenv("SG_API_KN"));

	public static void send(Pessoa pessoa) {
		Email from = new Email("no-reply@extremodev.com");
		String subject = "Login e senha " + pessoa.getEleicao().getNome();
		Email to = new Email(pessoa.getEmail().trim());
		Content content = new Content("text/html", conteudo(pessoa));
		Mail mail = new Mail(from, subject, to, content);
		//		SendGrid sg = new SendGrid(System.getenv("SG_API_KN"));
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			System.out.println(response.getStatusCode());
			System.out.println(response.getHeaders());
		} catch (IOException ex) {

		}
	}

	private static String conteudo(Pessoa pessoa) {
		StringBuilder sb = new StringBuilder();
		sb.append("<HTML>");
		sb.append(pessoa.getEleicao().getNome()).append("<br>");
		sb.append("<br>");
		sb.append("Nome: " + pessoa.getNome()).append("<br>");
		sb.append("Login/Documento: " + pessoa.getDocumento()).append("<br>");
		sb.append("Senha: " + pessoa.getSenha()).append("<br>");
		sb.append("<br>");
		sb.append(adicionarComQuebra(pessoa.getEleicao().getDescritivoEmail()));
		sb.append("</HTML>");
		return sb.toString();
	}
	
	private static String adicionarComQuebra(String texto) {
		return texto.replace("\\n", "<br>");
	}

	public static void main(String[] args) {
		//		stats();
		//		separador();
//		bounces();
//		separador();
//		System.out.println(bounceEmail(""));
//		separador();
		//		invalid();
		//		separador();
//		System.out.println(invalidEmail(""));
//		separador();
		//		spams();
//		System.out.println(spamEmail(""));
//		separador();
//		System.out.println(blockEmail(""));
		System.out.println(checkEmail(""));
	}

	public static String checkEmail(String email) {
//		return String.join(", ", ((bounceEmail(email)).length() > 2 ? "bounce" : ""),
//			   ((invalidEmail(email)).length() > 2 ? "invalid" : ""),
//			   ((spamEmail(email)).length() > 2 ? "spam" : ""),
//			   ((blockEmail(email)).length() > 2 ? "block" : ""));
		
		return Stream.of((bounceEmail(email)).length() > 2 ? "bounce" : "",
				(invalidEmail(email)).length() > 2 ? "invalid" : "",
				(spamEmail(email)).length() > 2 ? "spam" : "",
				(blockEmail(email)).length() > 2 ? "block" : "")
			          .filter(s -> s != null && !s.isEmpty())
			          .collect(Collectors.joining(","));
	}
	
	public static void separador() {
		System.out.println("\n==================================================");
	}

	public static String bounceEmail(String email) {
		return makeApiRequest("suppression/bounces/", email);
	}

	public static String invalidEmail(String email) {
		return makeApiRequest("suppression/invalid_emails/", email);
	}

	public static String spamEmail(String email) {
		return makeApiRequest("suppression/spam_report/", email);
	}
	
	public static String blockEmail(String email) {
		return makeApiRequest("suppression/blocks/", email);
	}
	
	private static String makeApiRequest(String endPoint, String email) {
		try {
			Request request = new Request();
			request.setMethod(Method.GET);
			request.setEndpoint(endPoint + email);
			Response response = sg.api(request);
			switch (response.getStatusCode()) {
				case HTTP_OK: return response.getBody();
				case HTTP_NOT_FOUND: return "";
				default: 
					return "INFORMAR DESENVOLVEDOR: " + response.getStatusCode() + response.getBody();
			}
		} catch (IOException ex) {
			return ex.toString();
		}
	}

}