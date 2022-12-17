package br.pro.software.eleicoes2020.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.pro.software.eleicoes2020.model.ConfirmacaoSMS;
import br.pro.software.eleicoes2020.model.Eleicao;
import br.pro.software.eleicoes2020.model.Login;
import br.pro.software.eleicoes2020.model.Pessoa;
import br.pro.software.eleicoes2020.repository.ConfirmacaoSMSRepository;
import br.pro.software.eleicoes2020.repository.PessoaRepository;
import br.pro.software.eleicoes2020.repository.VotoRepository;
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
	
	@Autowired
	VotoRepository votoRepo;
	
	@Autowired
	private ConfirmacaoSMSRepository confirmacaoSMSRepo;
	
	@ModelAttribute
	public void addAttributes(HttpServletRequest request, Model model) {
		Login login = (Login) request.getSession().getAttribute("login");
		if (loginService.logarMaster(login)) {
			model.addAttribute("login", login);
			model.addAttribute("eleicao", loginService.eleicaoDoMaster(login).orElse(null));
		}
	}

	@GetMapping("/painelDeControleLento")
	public ModelAndView painelDeControle(@ModelAttribute("pessoa") Login login,
			@ModelAttribute("eleicao") Eleicao eleicao) {
		ModelAndView mv = new ModelAndView("painelDeControle");
		mv.addObject("eleicao", eleicao);
		mv.addObject("pessoas", pessoaRepo.findAllByEleicaoOrderByIdAsc(eleicao));
		mv.addObject("votoService", votoService);
		mv.addObject("pesEdit", new Pessoa());
		return mv;
	}

	@GetMapping("/painelDeControleApto")
	public ModelAndView painelDeControleApto(@ModelAttribute("pessoa") Login login,
			@ModelAttribute("eleicao") Eleicao eleicao) {
		List<Pessoa> todas = pessoaRepo.findAllByEleicaoAndApto(eleicao, true);
		List<Pessoa> jaVotaram = votoRepo.findAll().stream().map(v -> v.getPessoa()).collect(Collectors.toList());
		List<Pessoa> pessoas  = todas.stream().filter(p -> !jaVotaram.contains(p)).collect(Collectors.toList());
		ModelAndView mv = new ModelAndView("painelDeControle");
		pessoas.sort(Comparator.comparing(Pessoa::getNome));
		mv.addObject("eleicao", eleicao);
		mv.addObject("pessoas", pessoas);
		mv.addObject("votoService", votoService);
		mv.addObject("pesEdit", new Pessoa());
		return mv;
	}
	
	@GetMapping("/painelDeControleVotou")
	public ModelAndView painelDeControleVotou(@ModelAttribute("pessoa") Login login,
			@ModelAttribute("eleicao") Eleicao eleicao) {
		List<Pessoa> pessoas = votoRepo.findAll().stream().map(v -> v.getPessoa()).collect(Collectors.toList());
		ModelAndView mv = new ModelAndView("painelDeControle");
		pessoas.sort(Comparator.comparing(Pessoa::getNome));
		mv.addObject("eleicao", eleicao);
		mv.addObject("pessoas", pessoas);
		mv.addObject("votoService", votoService);
		mv.addObject("pesEdit", new Pessoa());
		return mv;
	}

	@GetMapping("/painelDeControleTotal")
	public ModelAndView painelDeControleVotouTotal(@ModelAttribute("pessoa") Login login,
			@ModelAttribute("eleicao") Eleicao eleicao) {
		List<Pessoa> todas = pessoaRepo.findAllByEleicaoAndApto(eleicao, true);
		List<Pessoa> jaVotaram = votoRepo.findAll().stream().map(v -> v.getPessoa()).collect(Collectors.toList());
		List<Pessoa> pessoas  = todas.stream().filter(p -> !jaVotaram.contains(p)).collect(Collectors.toList());
		ModelAndView mv = new ModelAndView("painelDeControleTotal");
		pessoas.sort(Comparator.comparing(Pessoa::getNome));
		mv.addObject("eleicao", eleicao);
		mv.addObject("pessoas", pessoas);
		mv.addObject("votoService", votoService);
		mv.addObject("pesEdit", new Pessoa());
		return mv;
	}
	
	@GetMapping("/painelDeControleNaoApto")
	public ModelAndView painelDeControleNaoApto(@ModelAttribute("pessoa") Login login,
			@ModelAttribute("eleicao") Eleicao eleicao) {
		ModelAndView mv = new ModelAndView("painelDeControle");
		List<Pessoa> pessoas = pessoaRepo.findAllByEleicaoAndApto(eleicao, false);
		pessoas.sort(Comparator.comparing(Pessoa::getNome));
		mv.addObject("eleicao", eleicao);
		mv.addObject("pessoas", pessoas);
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
		Pessoa pessoa = pessoaRepo.getReferenceById(id);
	    pessoa.setApto(!pessoa.getApto().booleanValue());
		pessoaRepo.save(pessoa);
	    return "redirect:/painelDeControleApto";
	}
	
	@PostMapping("/email/{id}")
	public String updateEmail(@PathVariable("id") long id, @ModelAttribute("login") Login login,
			Pessoa pessoa) {
		Pessoa p = pessoaRepo.findById(id).get();
		p.setEmail(pessoa.getEmail());
		pessoaRepo.save(p);
		EmailHelper.send(p);
		return "redirect:/painelDeControleApto";
	}
	
	@PostMapping("/celular/{id}")
	public String updateCelular(@PathVariable("id") long id, @ModelAttribute("login") Login login,
			Pessoa pessoa) {
		Pessoa p = pessoaRepo.findById(id).get();
		p.setCelular(pessoa.getCelular());
		pessoaRepo.save(p);
		return "redirect:/painelDeControleTotal";
	}

	@GetMapping("/sendEmail/{id}")
	public String sendEmail(@PathVariable("id") long id, @ModelAttribute("login") Login login,
			Pessoa pessoa) {
		Pessoa p = pessoaRepo.findById(id).get();
		EmailHelper.send(p);
		return "redirect:/painelDeControleApto";
	}
	
	@GetMapping("/sendAllEmail")
	public String sendAllEmail(@ModelAttribute("login") Login login, Pessoa pessoa) {
		List<Pessoa> todas = pessoaRepo.findAllByApto(true);
		List<Pessoa> jaVotaram = votoRepo.findAll().stream().map(v -> v.getPessoa()).collect(Collectors.toList());
		List<Pessoa> pessoas  = todas.stream().filter(p -> !jaVotaram.contains(p)).collect(Collectors.toList());
		pessoas.forEach(p -> {
			if (p.getEmail() != null && p.getEmail().contains("@")) {
				EmailHelper.send(p);
			}
		});
		return "redirect:/painelDeControleApto";
	}

	
	@GetMapping("/sendAllSMS")
	public String sendAllSMS(@ModelAttribute("login") Login login, Pessoa pessoa) {
		List<Pessoa> todas = pessoaRepo.findAllByApto(true);
		List<Pessoa> jaVotaram = votoRepo.findAll().stream().map(v -> v.getPessoa()).collect(Collectors.toList());
		List<Pessoa> pessoas  = todas.stream().filter(p -> !jaVotaram.contains(p)).collect(Collectors.toList());
		pessoas.forEach(p -> {
			sendSMS(p);
		});
		return "redirect:/painelDeControleApto";
	}
	@GetMapping("/sendSMS/{id}")
	public String sendSMS(@PathVariable("id") long id, @ModelAttribute("login") Login login,
			Pessoa pessoa) {
		Pessoa p = pessoaRepo.findById(id).get();
		sendSMS(p);
		return "redirect:/painelDeControleTotal";
	}
	
	private void sendSMS(Pessoa p) {
		String idSms = SMSHelper.send(p);
		ConfirmacaoSMS confirmacaoSMS = new ConfirmacaoSMS(idSms, p.getCelular());
		confirmacaoSMSRepo.save(confirmacaoSMS);
	}
}
