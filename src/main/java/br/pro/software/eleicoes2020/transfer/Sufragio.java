package br.pro.software.eleicoes2020.transfer;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Sufragio {
	List<Long> candidatosId; 
}
