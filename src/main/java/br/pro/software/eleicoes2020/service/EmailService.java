package br.pro.software.eleicoes2020.service;

import java.io.IOException;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import br.pro.software.eleicoes2020.model.Pessoa;

public class EmailService {

	public static void send(Pessoa pessoa) {
		Email from = new Email("no-reply@eleicoesiba2020.com.br");
		String subject = "Login e senha da " + pessoa.getEleicao().getNome();
		Email to = new Email(pessoa.getEmail());
		Content content = new Content("text/plain", conteudo(pessoa));
		Mail mail = new Mail(from, subject, to, content);
		SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
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
		sb.append(pessoa.getEleicao().getNome()).append("\n");
		sb.append("\n");
		sb.append("Nome: " + pessoa.getNome()).append("\n");
		sb.append("Login/Documento: " + pessoa.getDocumento()).append("\n");
		sb.append("Senha: " + pessoa.getSenha()).append("\n");
		sb.append("\n");
		sb.append(pessoa.getEleicao().getDescritivoEmail());
		return sb.toString();
	}
}