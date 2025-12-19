package br.pro.software.eleicoes2020.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor
public class Candidato implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String nome;
	//@ManyToOne
	//@JoinColumn(name = "eleicao_id")
	@ManyToOne
	@JoinColumn(name = "eleicao_id", nullable = false)
	private Eleicao eleicao;

	private Integer cargo; //TODO: Criar Cargo

	private transient Boolean selecionado;

	@Override
	public String toString() {
		return "candidato: [id: " + id + ", nome: " + nome + ", eleição: " + eleicao.getId() + "]";
	}
}
