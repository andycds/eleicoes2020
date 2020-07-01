package br.pro.software.eleicoes2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.pro.software.eleicoes2020.model.Eleicao;
import br.pro.software.eleicoes2020.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	public Pessoa findOneByDocumentoAndSenha(String documento, String senha);
	public List<Pessoa> findAllByEleicao(Eleicao eleicao);
	public List<Pessoa> findAllByEleicaoOrderByIdAsc(Eleicao eleicao);
}
