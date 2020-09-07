package br.pro.software.eleicoes2020.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.pro.software.eleicoes2020.service.VotoService;

@Controller
public class NoLoginController {
	@Autowired
	VotoService votoService;

	@GetMapping(value = "valid/{id}/{zdt}")
	public ResponseEntity<String> validarVoto(@PathVariable("id") long id, 
			@PathVariable("zdt") long zdt) {
		if (votoService.validar(id, zdt)) {
			return new ResponseEntity<>("<h1>Voto válido.</h1>", HttpStatus.OK);
		}
		return new ResponseEntity<>("<h1>Não reconhecido.</h2>", HttpStatus.NOT_FOUND);
	}
}
