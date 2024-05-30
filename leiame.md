# Comandos

Estamos usando a stack heroku-22.

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

## Exportação

\copy (WITH expanded AS (
SELECT unnest(candidatos_id) AS number
FROM voto
)
SELECT number, COUNT(*)
FROM expanded
WHERE number BETWEEN 6 AND 12
GROUP BY number
ORDER BY number) TO 'resultado.tsv' WITH (FORMAT CSV, DELIMITER E'\t', HEADER);

## Cópia bruta total

\copy (select * from candidato) TO 'FTcandidato.tsv' WITH (FORMAT CSV, DELIMITER E'\t', HEADER);
\copy (select * from eleicao) TO 'FTeleicao.tsv' WITH (FORMAT CSV, DELIMITER E'\t', HEADER);
\copy (select * from pessoa) TO 'FTpessoa.tsv' WITH (FORMAT CSV, DELIMITER E'\t', HEADER);
\copy (select * from voto) TO 'FTvoto.tsv' WITH (FORMAT CSV, DELIMITER E'\t', HEADER);
\copy (select * from confirmacaosms) TO 'FTconfirmacaosms.tsv' WITH (FORMAT CSV, DELIMITER E'\t', HEADER);
