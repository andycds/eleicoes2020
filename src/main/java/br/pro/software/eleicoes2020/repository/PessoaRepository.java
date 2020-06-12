package br.pro.software.eleicoes2020.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.pro.software.eleicoes2020.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	public Pessoa findOneByDocumentoAndSenha(String documento, String senha);
}
