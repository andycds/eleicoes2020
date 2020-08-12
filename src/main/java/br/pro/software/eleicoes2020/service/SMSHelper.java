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
		HttpResponse<String> response = Unirest.get(ENDERECO + KEY 
				+ "&type=9&number=" + pessoa.getCelular() + "&msg="
				+ "Vote na eleicao IBA. www.eleicaoiba2020.com.br Usuario: " + pessoa.getDocumento()
				+ " Senha: " + pessoa.getSenha()).asString();
		Gson gson = new Gson();
		Map map = gson.fromJson(response.getBody(), Map.class);
//			{
//			  "situacao": "OK",
//			  "codigo": "1",
//			  "id": "637849052",
//			  "descricao": "MENSAGEM NA FILA"
//			}
		return map.get("situacao").toString();
	}
	
}
