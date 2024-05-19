package br.pro.software.eleicoes2020.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.pro.software.eleicoes2020.model.Login;
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
		mv.addObject("login", new Login());
		return mv;
	}

	@PostMapping("/fazerLogin")
	public String fazerLogin(HttpServletRequest request, Login login) {
		if (login.getLogin().startsWith("master") && loginService.logarMaster(login)) {
			request.getSession().setAttribute("login", login);
			return "redirect:/master/painelDeControleApto";
		}
		if (loginService.logar(login)) {			  
			request.getSession().setAttribute("login", login);
			if (votoService.jaVotou(Pessoa.of(login))) {
				return "redirect:/comprovante";
			}
			if (eleicaoService.noPrazo(Pessoa.of(login))) {
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
		request.getSession().setAttribute("login", null);
		return "redirect:login";
	}

}
