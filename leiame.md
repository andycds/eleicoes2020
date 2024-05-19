# Comandos

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