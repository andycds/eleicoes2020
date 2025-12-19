package br.pro.software.eleicoes2020.model;

import java.io.Serializable;
import java.time.ZonedDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

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
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne(cascade = CascadeType.ALL)
	private Pessoa pessoa;
//	@ManyToOne(cascade = CascadeType.ALL)
//	private Candidato candidato;
	@ManyToOne(cascade = CascadeType.ALL)
	private Eleicao eleicao;
    //@Type(type = "list-array")
    @Column(
        name = "candidatos_id",
        columnDefinition = "bigint[]"
    )
	@JdbcTypeCode(SqlTypes.ARRAY)
	private Long[] candidatos_id;
	private ZonedDateTime criado;

	public Voto(Pessoa pessoa, Long[] candidatos_id, Eleicao eleicao) {
		this.pessoa = pessoa;
		this.candidatos_id = candidatos_id;
		this.eleicao = eleicao;
		this.criado = ZonedDateTime.now();
	}
}
