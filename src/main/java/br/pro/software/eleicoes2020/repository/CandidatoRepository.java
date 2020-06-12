package br.pro.software.eleicoes2020.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.pro.software.eleicoes2020.model.Candidato;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {

}
