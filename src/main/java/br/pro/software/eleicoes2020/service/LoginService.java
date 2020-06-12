package br.pro.software.eleicoes2020.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pro.software.eleicoes2020.model.Pessoa;
import br.pro.software.eleicoes2020.repository.PessoaRepository;

@Service
public class LoginService {
	@Autowired
	PessoaRepository pessoaRepo;
	
	public boolean logar(Pessoa pessoa) {
		return obterPessoaPorDados(pessoa) != null;
	}
	
	public Pessoa obterPessoaPorDados(Pessoa pessoa) {
		return pessoaRepo.findOneByDocumentoAndSenha(
				pessoa.getDocumento(), pessoa.getSenha());
	}
}