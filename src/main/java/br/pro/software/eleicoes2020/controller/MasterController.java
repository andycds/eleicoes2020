package br.pro.software.eleicoes2020.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.pro.software.eleicoes2020.model.Eleicao;
import br.pro.software.eleicoes2020.model.Login;
import br.pro.software.eleicoes2020.model.Pessoa;
import br.pro.software.eleicoes2020.repository.PessoaRepository;
import br.pro.software.eleicoes2020.service.EmailHelper;
import br.pro.software.eleicoes2020.service.LoginService;
import br.pro.software.eleicoes2020.service.SMSHelper;
import br.pro.software.eleicoes2020.service.VotoService;

@Controller
public class MasterController {
	@Autowired
	LoginService loginService;
	
	@Autowired 
	PessoaRepository pessoaRepo;
	
	@Autowired
	VotoService votoService;
	
	@ModelAttribute
	public void addAttributes(HttpServletRequest request, Model model) {
		Login login = (Login) request.getSession().getAttribute("login");
		if (loginService.logarMaster(login)) {
			model.addAttribute("login", login);
			model.addAttribute("eleicao", loginService.eleicaoDoMaster(login).orElse(null));
		}
	}

	@GetMapping("/painelDeControle")
	public ModelAndView painelDeControle(@ModelAttribute("pessoa") Login login,
			@ModelAttribute("eleicao") Eleicao eleicao) {
		ModelAndView mv = new ModelAndView("painelDeControle");
		mv.addObject("eleicao", eleicao);
		mv.addObject("pessoas", pessoaRepo.findAllByEleicaoOrderByIdAsc(eleicao));
		mv.addObject("votoService", votoService);
		mv.addObject("pesEdit", new Pessoa());
		return mv;
	}
	
	@GetMapping("/apto/{id}")
	public String updateUser(@PathVariable("id") long id, @ModelAttribute("login") Login login 
	  /*,BindingResult result, Model model*/) {
		if (login == null) {
			return "/sair";
		}
		Pessoa pessoa = pessoaRepo.getOne(id);
	    pessoa.setApto(!pessoa.getApto().booleanValue());
		pessoaRepo.save(pessoa);
	    return "redirect:/painelDeControle";
	}
	
	@PostMapping("/email/{id}")
	public String updateEmail(@PathVariable("id") long id, @ModelAttribute("login") Login login,
			Pessoa pessoa) {
		Pessoa p = pessoaRepo.findById(id).get();
		p.setEmail(pessoa.getEmail());
		pessoaRepo.save(p);
		return "redirect:/painelDeControle";
	}
	
	@PostMapping("/celular/{id}")
	public String updateCelular(@PathVariable("id") long id, @ModelAttribute("login") Login login,
			Pessoa pessoa) {
		Pessoa p = pessoaRepo.findById(id).get();
		p.setCelular(pessoa.getCelular());
		pessoaRepo.save(p);
		return "redirect:/painelDeControle";
	}

	@GetMapping("/sendEmail/{id}")
	public String sendEmail(@PathVariable("id") long id, @ModelAttribute("login") Login login,
			Pessoa pessoa) {
		Pessoa p = pessoaRepo.findById(id).get();
		EmailHelper.send(p);
		return "redirect:/painelDeControle";
	}
	
	@GetMapping("/sendSMS/{id}")
	public String sendSMS(@PathVariable("id") long id, @ModelAttribute("login") Login login,
			Pessoa pessoa) {
		Pessoa p = pessoaRepo.findById(id).get();
		SMSHelper.send(p);
		return "redirect:/painelDeControle";
	}
}
