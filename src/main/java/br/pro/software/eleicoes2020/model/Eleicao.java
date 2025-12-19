package br.pro.software.eleicoes2020.model;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor
public class Eleicao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name = "eleicao_id")
	private Long id;
	
    private String nome;
    
    private String cabecalho;
    
    private String rodape;
    
    private ZonedDateTime inicio;
    
    private ZonedDateTime fim;
	
    private String senha;
	
    @Column(length = 500)
	private String descritivoEmail;
	
    private boolean shuffle;
	
    @OneToMany
    @JoinColumn(name = "eleicao_id")
	private List<Candidato> candidatos;
	
	public List<Candidato> candidatosPorCargo(Integer cargo) {
		List<Candidato> candidatosPCargo = candidatos.stream()
				.filter(c -> c.getCargo().equals(cargo)).collect(Collectors.toList());
		if (shuffle) {
			Collections.shuffle(candidatosPCargo);
		}
		Candidato ponteiro;
		for (int i = 0; i < candidatosPCargo.size(); i++) {
			ponteiro = candidatosPCargo.get(i);
			if (ponteiro.getNome().equals("Voto em Branco")) {
				candidatosPCargo.remove(i);
				candidatosPCargo.add(ponteiro);
				break;
			}
		}

		for (int i = 0; i < candidatosPCargo.size(); i++) {
			ponteiro = candidatosPCargo.get(i);
			if (ponteiro.getNome().equals("Voto Nulo")) {
				candidatosPCargo.remove(i);
				candidatosPCargo.add(ponteiro);
				break;
			}
		}
		return candidatosPCargo; 
	}
	
//	public Candidato candidatoBranco() {
//		Candidato c = new Candidato();
//		c.setNome("Voto em Branco");
//		c.setId(1L);
//		c.setCargo(1);
//		return c;
//	}
//
//	public Candidato candidatoNulo() {
//		Candidato c = new Candidato();
//		c.setNome("Voto Nulo");
//		c.setId(2L);
//		c.setCargo(1);
//		return c;
//	}
	
}