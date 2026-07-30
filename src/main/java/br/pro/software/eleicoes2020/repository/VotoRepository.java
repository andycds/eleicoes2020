package br.pro.software.eleicoes2020.repository;

import java.util.List;
import java.util.stream.Stream;

import br.pro.software.eleicoes2020.model.Eleicao;
import br.pro.software.eleicoes2020.repository.projection.ResultadoProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import br.pro.software.eleicoes2020.model.Voto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VotoRepository extends JpaRepository<Voto, Long> {
	boolean existsByPessoaId(Long pessoaId);
	List<Voto> findAllByPessoaId(Long pessoaId);
	@Query(value = """
SELECT 
			c.nome,
			COUNT(*) AS votos
			FROM voto v
			CROSS JOIN LATERAL unnest(v.candidatos_id) AS u(candidato_id)
			JOIN candidato c
			ON c.id = u.candidato_id
			where v.eleicao_id = :eleicaoId
			GROUP BY c.nome\n
			ORDER BY total_votos DESC, c.nome;"""
)
    Stream<ResultadoProjection> resultado(@Param("eleicaoId") long eleicaoId);
}
