package br.pro.software.eleicoes2020.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pro.software.eleicoes2020.model.Candidato;
import br.pro.software.eleicoes2020.repository.CandidatoRepository;

@Service
public class CandidatoService {
	@Autowired
	CandidatoRepository candidatoRepo;
	
	public Candidato obter(Long id) {
		return candidatoRepo.getOne(id);
	}
}
