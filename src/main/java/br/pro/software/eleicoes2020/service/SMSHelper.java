package br.pro.software.eleicoes2020.service;


import java.util.Map;

import com.google.gson.Gson;

import br.pro.software.eleicoes2020.model.Pessoa;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class SMSHelper {
	
	private static final String ENDERECO = "https://api.smsdev.com.br/send?key=";
	private static final String KEY = System.getenv("SMS_KEY");
	
	public static String send(Pessoa pessoa) {
		String celular = pessoa.getCelular().trim();
		if (celular == null || celular.length() != 11 || !celular.substring(2, 3).equals("9") || !celular.matches("[0-9]+")) {
			return "";
		}
		HttpResponse<String> response = Unirest.get(ENDERECO + KEY 
				+ "&type=9&number=" + celular + "&msg="
				+ "Vote na eleicao Conre. https://vote.extremodev.com Documento: " + pessoa.getDocumento()
				+ " Senha: " + pessoa.getSenha()).asString();
		Gson gson = new Gson();
		Map<String, String> map = gson.fromJson(response.getBody(), Map.class);
//			{
//			  "situacao": "OK",
//			  "codigo": "1",
//			  "id": "637849052",
//			  "descricao": "MENSAGEM NA FILA"
//			}
		String situacao = map.get("situacao");
		String id = map.get("id"); 
		return situacao.equals("OK") ? id : situacao;
	}
}
