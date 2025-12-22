package br.pro.software.eleicoes2020.model;

import java.io.Serializable;
import java.time.ZonedDateTime;

import jakarta.persistence.*;

//import org.hibernate.annotations.Type;
//import org.hibernate.annotations.TypeDef;
//import org.hibernate.annotations.TypeDefs;

//import com.vladmihalcea.hibernate.type.array.LongArrayType;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import lombok.Data;
import lombok.NoArgsConstructor;

//@TypeDefs({
//    @TypeDef(
//        name = "list-array",
//        typeClass = LongArrayType.class
//    )
//})
@Entity @Data @NoArgsConstructor
public class Voto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue private Long id;

	@ManyToOne(cascade = CascadeType.ALL) private Pessoa pessoa;


	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "eleicao_id", nullable = false)
	private Eleicao eleicao;

	@Column(
        name = "candidatos_id",
        columnDefinition = "bigint[]"
    )
	@JdbcTypeCode(SqlTypes.ARRAY)
	private Long[] candidatos_id;

	private ZonedDateTime criado;

	@Column(length = 128) private String ip;

	public Voto(Pessoa pessoa, Long[] candidatos_id, Eleicao eleicao, String ip) {
		this.pessoa = pessoa;
		this.candidatos_id = candidatos_id;
		this.eleicao = eleicao;
		this.criado = ZonedDateTime.now();
		this.ip = ip;
	}
}
