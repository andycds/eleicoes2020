package br.pro.software.eleicoes2020.model;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor
public class ConfirmacaoSMS {
	@Id
	String id;
	String celular;
	ZonedDateTime data;
	
	public ConfirmacaoSMS(String id, String celular) {
		this.id = id;
		this.celular = celular;
		this.data = ZonedDateTime.now();
	}
}
