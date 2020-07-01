package br.pro.software.eleicoes2020.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pro.software.eleicoes2020.model.Eleicao;
import br.pro.software.eleicoes2020.model.Login;
import br.pro.software.eleicoes2020.model.Pessoa;
import br.pro.software.eleicoes2020.repository.EleicaoRepository;
import br.pro.software.eleicoes2020.repository.PessoaRepository;

@Service
public class LoginService {
	@Autowired
	PessoaRepository pessoaRepo;

	@Autowired
	EleicaoRepository eleicaoRepo;

	public boolean logar(Login login) {
		return obterPessoaPorDados(Pessoa.of(login)) != null;
	}

	public Pessoa obterPessoaPorDados(Pessoa pessoa) {
		if (pessoa == null) {
			return null;
		}
		return pessoaRepo.findOneByDocumentoAndSenha(
				pessoa.getDocumento(), pessoa.getSenha());
	}

	public boolean logarMaster(Login login) {
		try {
			Optional<Eleicao> eleicao = eleicaoDoMaster(login);
			return eleicao.orElseThrow().getSenha().equals(login.getSenha());
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public Optional<Eleicao> eleicaoDoMaster(Login login) { 
		try {
			long idEleicao = Long.parseLong(login.getDocumento().substring("master".length()));
			return eleicaoRepo.findById(idEleicao);
		} catch (NumberFormatException e) {
			return Optional.empty();
		}
	}
}