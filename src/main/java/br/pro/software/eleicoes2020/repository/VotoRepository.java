package br.pro.software.eleicoes2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.pro.software.eleicoes2020.model.Voto;

public interface VotoRepository extends JpaRepository<Voto, Long>{
	boolean existsByPessoaId(Long pessoaId);
	List<Voto> findAllByPessoaId(Long pessoaId);
}
