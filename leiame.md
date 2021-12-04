# Comandos

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
select count(*) from voto where candidatos_id::int[] @> array[3];

## Fazer o dump do banco pós eleição
heroku pg:backups:capture
heroku pg:backups:download

## Restaurá-lo
 pg_restore --verbose --clean --no-acl --no-owner -h localhost -U anderson -d eleicoes2020 latest.dump.1
 anderson