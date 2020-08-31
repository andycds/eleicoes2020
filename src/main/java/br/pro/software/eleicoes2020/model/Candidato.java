package br.pro.software.eleicoes2020.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor
public class Candidato implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	@ManyToOne
	@JoinColumn(name = "eleicao_id")
    private Eleicao eleicao;
	private Integer cargo; //TODO: Criar Cargo
	private transient Boolean selecionado;

	@Override
	public String toString() {
		return "candidato: [id: " + id + ", nome: " + nome + ", eleição: " + eleicao.getId() + "]";
	}
}
