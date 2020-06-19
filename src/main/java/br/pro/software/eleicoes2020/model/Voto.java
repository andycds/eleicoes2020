package br.pro.software.eleicoes2020.model;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor
public class Voto implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne(cascade = CascadeType.ALL)
	private Pessoa pessoa;
	@ManyToOne(cascade = CascadeType.ALL)
	private Candidato candidato;
	@ManyToOne(cascade = CascadeType.ALL)
	private Eleicao eleicao;
	private ZonedDateTime criado;

	public Voto(Pessoa pessoa, Candidato candidato, Eleicao eleicao) {
		this.pessoa = pessoa;
		this.candidato = candidato;
		this.eleicao = eleicao;
		this.criado = ZonedDateTime.now();
	}
}
