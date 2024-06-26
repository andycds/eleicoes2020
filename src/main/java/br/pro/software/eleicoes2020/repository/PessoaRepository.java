package br.pro.software.eleicoes2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.pro.software.eleicoes2020.model.Eleicao;
import br.pro.software.eleicoes2020.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	public Pessoa findOneByLoginAndSenha(String login, String senha);
	public Pessoa findOneByLogin(String login);
	public List<Pessoa> findAllByEleicao(Eleicao eleicao);
	public List<Pessoa> findAllByEleicaoOrderByIdAsc(Eleicao eleicao);
	public List<Pessoa> findAllByEleicaoAndApto(Eleicao eleicao, Boolean bool);
	public List<Pessoa> findAllByApto(Boolean apto);
}
