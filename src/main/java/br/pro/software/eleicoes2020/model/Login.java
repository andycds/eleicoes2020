package br.pro.software.eleicoes2020.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Login {
	private String login;
	private String senha;
}
