package br.pro.software.eleicoes2020.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.pro.software.eleicoes2020.model.Login;
import br.pro.software.eleicoes2020.model.Pessoa;
import br.pro.software.eleicoes2020.model.Voto;
import br.pro.software.eleicoes2020.service.CandidatoService;
import br.pro.software.eleicoes2020.service.LoginService;
import br.pro.software.eleicoes2020.service.VotoService;
import br.pro.software.eleicoes2020.transfer.Sufragio;


@Controller
public class VotoController {
	@Autowired
	LoginService loginService;

	@Autowired
	CandidatoService candidatoService;

	@Autowired
	VotoService votoService;

	@ModelAttribute
	public void addAttributes(HttpServletRequest request, Model model) {
		Login login = (Login) request.getSession().getAttribute("login");
		Pessoa pessoa = loginService.obterPessoaPorDados(Pessoa.of(login));
		model.addAttribute("pessoa", pessoa);
		model.addAttribute("jaVotou", votoService.jaVotou(pessoa));
	}

	@GetMapping("/votar")
	public ModelAndView votar(@ModelAttribute("pessoa") Pessoa pessoa,
			@ModelAttribute("jaVotou") boolean jaVotou) {
		if (jaVotou) {
			return new ModelAndView( "redirect:/comprovante");
		}
		ModelAndView mv = new ModelAndView("votar");
		mv.addObject("eleicao", pessoa.getEleicao());
		Sufragio sufragio = new Sufragio();
		List<Long> candidatosId = pessoa.getEleicao().getCandidatos()
				.stream().map(c -> c.getId()).collect(Collectors.toList()); 
		sufragio.setCandidatosId(candidatosId);
		mv.addObject("sufragio", sufragio);
		return mv;
	}

	@PostMapping(value = "/votando")
	public String votando(@ModelAttribute("pessoa") Pessoa pessoa,
			@ModelAttribute("jaVotou") boolean jaVotou, Sufragio sufragio) {
		if (jaVotou) {
			return "redirect:/comprovante";
		}
//		for (Long candidatoId : sufragio.getCandidatosId()) {
//			Candidato candidato = candidatoService.obter(candidatoId); 
//			votoService.salvar(new Voto(pessoa, candidato, pessoa.getEleicao()));
//		}
		if (verificarCandidatosEscolhidos(sufragio.getCandidatosId())) {
			votoService.salvar(new Voto(pessoa, sufragio.getCandidatosId().toArray(Long[]::new), pessoa.getEleicao()));
			return "redirect:/comprovante";
		}
		return "redirect:/votar";
	}

	private boolean verificarCandidatosEscolhidos(List<Long> candidatosId) {
		if (candidatosId.contains(1L) && candidatosId.contains(2L)) {
			return false;
		}
		if (candidatosId.contains(1L) || candidatosId.contains(2L)) {
			if (candidatosId.size() >= 7 && candidatosId.size() <= 13) {
				return true;
			}
		}
		return false;
	}

	@RequestMapping(value = "/comprovante", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> comprovante(HttpServletResponse response,
			@ModelAttribute("pessoa") Pessoa pessoa,
			@ModelAttribute("jaVotou") boolean jaVotou) throws IOException {
		if (!jaVotou) {
			response.sendRedirect("/votar");
			return null;
		}
		ByteArrayInputStream bis = votoService.gerarPdf(pessoa, 
				ServletUriComponentsBuilder.fromCurrentContextPath().toUriString());
		
		var headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=comprovante.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));

	}
	


	//	public ResponseEntity<InputStreamResource> comprovante(HttpServletRequest request) {
	//		Pessoa pessoa = loginService.obterPessoaPorDados(
	//				(Pessoa) request.getSession().getAttribute("login"));
	//		if (!votoService.jaVotou(pessoa)) {
	//			return null;
	//		}
	//		ByteArrayInputStream bis = votoService.gerarPdf(pessoa);
	//
	//        var headers = new HttpHeaders();
	//        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");
	//
	//        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
	//                .body(new InputStreamResource(bis));
	//	}
}