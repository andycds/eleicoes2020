# Comandos

Estamos usando a stack heroku-22.

## Iniciar com profile local dev

`mvn spring-boot:run -Dspring-boot.run.profiles=local,dev`

*Aplicativo em:*

http://localhost:8181/

https://vote.ext.......com

## DocOrigem

No campo docOrigem de `Pessoa` marque a inscrição do candidato junto ao Conre. Passe esse valor junto ao nome para eles, para identificarem quem votou.

## Banco local

psql eleicoes2020

## Conectar no pgsql do Heroku

heroku pg:psql

## Enviar para Heroku

git push heroku master

## Antigo conexão BD H2

http://localhost:8181/h2-console/login.do

## Arrumar e-mail do banco

update pessoa set email=regexp_replace(email, '[^[:ascii:]]', '', 'g') where email like 'lper%';

## Diferença de tempo

Banco Heroku é +3 do que aqui (conferir devido a horário de verão)

## Push conres branch to Heroku

git push heroku conres:master

## Contagem

select count(candidatos_id) from voto v where 16=any(candidatos_id);

SELECT
c.id,
c.nome,
COUNT(*) AS total_votos
FROM voto v
CROSS JOIN LATERAL unnest(v.candidatos_id) AS u(candidato_id)
JOIN candidato c
ON c.id = u.candidato_id
where v.eleicao_id = 7
GROUP BY c.id, c.nome
ORDER BY total_votos DESC, c.nome;

SELECT
c.nome,
COUNT(*) AS total_votos
FROM voto v
CROSS JOIN LATERAL unnest(v.candidatos_id) AS u(candidato_id)
JOIN candidato c
ON c.id = u.candidato_id
where v.eleicao_id = 7
GROUP BY c.nome
ORDER BY total_votos DESC, c.nome;


## Exportação

### Votantes

`\COPY (select p.login, p.doc_origem, p.nome, p.email, p.celular 
from pessoa p join voto v on p.id = v.pessoa_id 
where v.eleicao_id = 7 order by login) 
TO 'votantesConre7.tsv' WITH (FORMAT CSV, DELIMITER E'\t', HEADER);
`
### Não Votantes
\COPY (
SELECT p.login, p.doc_origem, p.apto, p.nome, p.email, p.celular
FROM pessoa p WHERE eleicao_id = 7 and NOT EXISTS (
SELECT 1 FROM voto v WHERE v.pessoa_id = p.id AND v.eleicao_id = 7)
ORDER BY p.apto desc, p.login
) TO 'NAOvotantesConre7.tsv' WITH (FORMAT CSV, DELIMITER E'\t', HEADER);


\copy (WITH expanded AS (
SELECT unnest(candidatos_id) AS number
FROM voto
)
SELECT number, COUNT(*)
FROM expanded
GROUP BY number
ORDER BY number) TO 'resultado.tsv' WITH (FORMAT CSV, DELIMITER E'\t', HEADER);

--WHERE number BETWEEN 6 AND 25


## Cópia bruta total

\copy (select * from candidato) TO 'FTcandidato.tsv' WITH (FORMAT CSV, DELIMITER E'\t', HEADER);
\copy (select * from eleicao) TO 'FTeleicao.tsv' WITH (FORMAT CSV, DELIMITER E'\t', HEADER);
\copy (select * from pessoa) TO 'FTpessoa.tsv' WITH (FORMAT CSV, DELIMITER E'\t', HEADER);
\copy (select * from voto) TO 'FTvoto.tsv' WITH (FORMAT CSV, DELIMITER E'\t', HEADER);
\copy (select * from confirmacaosms) TO 'FTconfirmacaosms.tsv' WITH (FORMAT CSV, DELIMITER E'\t', HEADER);



## Listagem por candidato_id
WITH expanded AS (
SELECT unnest(candidatos_id) AS number
FROM voto
)
SELECT eleicao_id, id, number, nome, COUNT(*)
FROM expanded 
 join candidato on id = number
--WHERE number BETWEEN 6 AND 25
GROUP BY number, id, nome, eleicao_id
ORDER BY number;

## Listagem por eleicão
WITH expanded AS (
SELECT unnest(candidatos_id) AS number
FROM voto
)
SELECT eleicao_id, nome, COUNT(*) as votos
FROM expanded
join candidato on id = number
--WHERE number BETWEEN 6 AND 25
GROUP BY id, nome, eleicao_id
ORDER BY eleicao_id, count(*) desc;

## Eleitores que votaram por eleição
\copy (select nome, doc_origem, email, celular from pessoa p join voto v on p.id = v.pessoa_id where eleicao_id = 2) TO 'VotantesConre2.tsv' WITH (FORMAT CSV, DELIMITER E'\t', HEADER);
\copy (select nome, doc_origem, email, celular from pessoa p join voto v on p.id = v.pessoa_id where eleicao_id = 3) TO 'VotantesConre3.tsv' WITH (FORMAT CSV, DELIMITER E'\t', HEADER);
\copy (select nome, doc_origem, email, celular from pessoa p join voto v on p.id = v.pessoa_id where eleicao_id = 4) TO 'VotantesConre4.tsv' WITH (FORMAT CSV, DELIMITER E'\t', HEADER);

## Eleitores que NÃO votaram por eleição
\copy (select nome, doc_origem, email, celular from pessoa p where p.id not in (select pessoa_id from voto) and p.eleicao_id = 2) TO 'NaoVotantesConre2.tsv' WITH (FORMAT CSV, DELIMITER E'\t', HEADER);
\copy (select nome, doc_origem, email, celular from pessoa p where p.id not in (select pessoa_id from voto) and p.eleicao_id = 3) TO 'NaoVotantesConre3.tsv' WITH (FORMAT CSV, DELIMITER E'\t', HEADER);
\copy (select nome, doc_origem, email, celular from pessoa p where p.id not in (select pessoa_id from voto) and p.eleicao_id = 4) TO 'NaoVotantesConre4.tsv' WITH (FORMAT CSV, DELIMITER E'\t', HEADER);

## Listagem Dóris
\copy (select * from candidato where eleicao_id = 3) TO 'DorisCandidatosConre3.tsv' WITH (FORMAT CSV, DELIMITER E'\t', HEADER);
\copy (select * from pessoa where eleicao_id = 3) TO 'DorisEleitoresConre3.tsv' WITH (FORMAT CSV, DELIMITER E'\t', HEADER);
\copy (select * from voto where eleicao_id = 3) TO 'DorisVotosConre3.tsv' WITH (FORMAT CSV, DELIMITER E'\t', HEADER);
