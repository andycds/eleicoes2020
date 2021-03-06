package br.pro.software.eleicoes2020.model;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor
public class Eleicao implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
    private String nome;
    private String cabecalho;
    private String rodape;
    private ZonedDateTime inicio;
    private ZonedDateTime fim;
	private String senha;
	private String descritivoEmail;
	@OneToMany(mappedBy = "eleicao")
	private List<Candidato> candidatos;
	
	public List<Candidato> candidatosPorCargo(Integer cargo) {
		return candidatos.stream().filter(c -> c.getCargo().equals(cargo)).collect(Collectors.toList());
	}
}