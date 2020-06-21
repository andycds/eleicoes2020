package br.pro.software.eleicoes2020.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.pro.software.eleicoes2020.model.Pessoa;
import br.pro.software.eleicoes2020.service.EleicaoService;
import br.pro.software.eleicoes2020.service.LoginService;
import br.pro.software.eleicoes2020.service.VotoService;

@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private VotoService votoService;
	
	@Autowired
	private EleicaoService eleicaoService;

	@GetMapping(value = {"/login", "/"})
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("pessoa", new Pessoa());
		return mv;
	}

	@PostMapping("/fazerLogin")
	public String fazerLogin(HttpServletRequest request, Pessoa pessoa) {
		if (loginService.logar(pessoa)) {			  
			request.getSession().setAttribute("pessoa", pessoa);
			if (votoService.jaVotou(pessoa)) {
				return "redirect:/comprovante";
			}
			if (eleicaoService.noPrazo(pessoa)) {
				return "redirect:/votar";
			} else {
				return "redirect:/login?prazo";
			}
		} else {
			return "redirect:/login?senha";
		}
	}
	

	@GetMapping(value = {"/logout", "/sair"})
	public String logout(HttpServletRequest request) {
		request.getSession().setAttribute("pessoa", null);
		return "redirect:login";
	}

}
