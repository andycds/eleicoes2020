package br.pro.software.eleicoes2020.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
    private String documento;
    private String senha;
    private String email;
    private Boolean apto;
    @ManyToOne(cascade = CascadeType.ALL)
    private Eleicao eleicao;
}
