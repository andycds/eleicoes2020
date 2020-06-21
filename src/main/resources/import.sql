insert into eleicao (id, nome, cabecalho, rodape, inicio, fim, senha) values (1, 'Eleição Teste 1', 'Eleição 1', 'Obrigado por votar', now(), '2021-06-11T23:30', 'master');
insert into candidato (id, nome, eleicao_id) values (1, 'Anderson', 1);
insert into candidato (id, nome, eleicao_id) values (2, 'Lili', 1);
insert into pessoa (id, nome, documento, senha, email, apto, eleicao_id) values (1, 'admin', 'admin', 'admin', 'andycds@gmail.com', true, 1);