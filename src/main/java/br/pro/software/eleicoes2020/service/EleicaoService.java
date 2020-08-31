package br.pro.software.eleicoes2020.service;

import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pro.software.eleicoes2020.model.Eleicao;
import br.pro.software.eleicoes2020.model.Pessoa;

@Service
public class EleicaoService {
	@Autowired
	LoginService loginService;
	
	public boolean noPrazo(Pessoa pessoa) {
		pessoa = loginService.obterPessoaPorDados(pessoa);
		Eleicao eleicao = pessoa.getEleicao();
		ZonedDateTime agora = ZonedDateTime.now();
		return eleicao.getInicio().isBefore(agora) &&
				agora.isBefore(eleicao.getFim());
	}
}
