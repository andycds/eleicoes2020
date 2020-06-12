package br.pro.software.eleicoes2020.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.pro.software.eleicoes2020.model.Pessoa;
import br.pro.software.eleicoes2020.service.LoginService;


@Controller
public class VotoController {
	@Autowired
	LoginService loginService;
	
	@GetMapping("/votar")
	public ModelAndView votar(HttpServletRequest request) {
		Pessoa pessoa = loginService.obterPessoaPorDados(
				(Pessoa) request.getSession().getAttribute("pessoa"));
		ModelAndView mv = new ModelAndView("votar");
		mv.addObject("eleicao", pessoa.getEleicao());
//		mv.addObject("candidatos", pessoa.getEleicao().getCandidatos());
		return mv;
	}
}