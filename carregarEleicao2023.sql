--eleicao

insert into eleicao (eleicao_id, cabecalho, descritivo_email, fim, inicio, nome, rodape, senha, shuffle) values
(1, 'Teste Eleicao 2023', 'Seguem seus dados eleição Teste.', now() + interval '2 days', now(), 'Eleição Teste', 'Conre', 'master', false),
(2, 'Comprovante de Votação CONRE-2', 'Seguem seus dados na eleição CONRE-2.', now() + interval '2 days', now(), 'Eleição CONRE-2', 'CONRE-2', 'master2', false),
(3, 'Comprovante de Votação CONRE-3', 'Seguem seus dados na eleição CONRE-3.', now() + interval '2 days', now(), 'Eleição CONRE-3', 'CONRE-3', 'master3', true),
(4, 'Comprovante de Votação CONRE-4', 'Seguem seus dados na eleição CONRE-4.', now() + interval '2 days', now(), 'Eleição CONRE-4', 'CONRE-4', 'master4', false);

--insert into eleicao (id, nome, cabecalho, rodape, inicio, fim, senha, descritivo_email) values (1, 'Processo Eleitoral de 2023', 'Votação para:'||chr(10)||' - Presidente e Vice-Presidente;', 'Parabéns pelo exercício do seu direito a votar.', now(), now()+ interval '2 days', 'senhamasterconfusao', 'Vote em https://vote.extremodev.com/');
--insert into eleicao (id, nome, cabecalho, rodape, inicio, fim, senha, descritivo_email) values (2, 'Processo Eleitoral de 2023', 'Votação para:'||chr(10)||' - Presidente e Vice-Presidente;', 'Parabéns pelo exercício do seu direito a votar.', now(), now()+ interval '2 days', 'senhamasterconfusao', 'Vote em https://vote.extremodev.com/');
--insert into eleicao (id, nome, cabecalho, rodape, inicio, fim, senha, descritivo_email) values (3, 'Processo Eleitoral de 2023', 'Votação para:'||chr(10)||' - Presidente e Vice-Presidente;', 'Parabéns pelo exercício do seu direito a votar.', now(), now()+ interval '2 days', 'senhamasterconfusao', 'Vote em https://vote.extremodev.com/');
--insert into eleicao (id, nome, cabecalho, rodape, inicio, fim, senha, descritivo_email) values (4, 'Processo Eleitoral de 2023', 'Votação para:'||chr(10)||' - Presidente e Vice-Presidente;', 'Parabéns pelo exercício do seu direito a votar.', now(), now()+ interval '2 days', 'senhamasterconfusao', 'Vote em https://vote.extremodev.com/');

--candidato
insert into candidato (id, nome, eleicao_id, cargo) values (1, 'Voto em Branco', 1, 1);
insert into candidato (id, nome, eleicao_id, cargo) values (2, 'Voto Nulo', 1, 1);
insert into candidato (id, nome, eleicao_id, cargo) values (3, 'Teste 1', 1, 1);
insert into candidato (id, nome, eleicao_id, cargo) values (4, 'Teste 2', 1, 1);
insert into candidato (id, nome, eleicao_id, cargo) values (5, 'Teste 3', 1, 1);

insert into candidato (id, nome, eleicao_id, cargo) values (6, 'Voto em Branco', 2, 1);
insert into candidato (id, nome, eleicao_id, cargo) values (7, 'Voto Nulo', 2, 1);
insert into candidato (id, nome, eleicao_id, cargo) values (8, 'Hélio Otsuka', 2, 1);

insert into candidato (id, nome, eleicao_id, cargo) values (9, 'Voto em Branco', 3, 1);
insert into candidato (id, nome, eleicao_id, cargo) values (10, 'Voto Nulo', 3, 1);
insert into candidato (id, nome, eleicao_id, cargo) values (11, 'Mauro Correia Alves', 3, 1);
insert into candidato (id, nome, eleicao_id, cargo) values (12, 'Antonio Carlos Fonseca Pontes', 3, 1);
insert into candidato (id, nome, eleicao_id, cargo) values (13, 'Gizelton Pereira Alencar', 3, 1);
insert into candidato (id, nome, eleicao_id, cargo) values (14, 'Inês Nobuko Nishimoto', 3, 1);
insert into candidato (id, nome, eleicao_id, cargo) values (15, 'Tamara Cristina Mármore', 3, 1);

insert into candidato (id, nome, eleicao_id, cargo) values (16, 'Voto em Branco', 4, 1);
insert into candidato (id, nome, eleicao_id, cargo) values (17, 'Voto Nulo', 4, 1);
insert into candidato (id, nome, eleicao_id, cargo) values (18, 'Eduardo Schindler', 4, 1);
insert into candidato (id, nome, eleicao_id, cargo) values (19, 'Gabriel Afonso Marchesi Lopes', 4, 1);
--pessoa
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (1, true, '100001', 'anderson@extremodev.com', 'Pessoa 000001', '100001', 1, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (2, true, '100002', 'anderson@extremodev.com', 'Pessoa 000002', '100002', 1, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (3, true, '100003', 'anderson@extremodev.com', 'Pessoa 000003', '100003', 1, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (4, true, '100004', 'anderson@extremodev.com', 'Pessoa 000004', '100004', 1, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (5, true, '100005', 'anderson@extremodev.com', 'Pessoa 000005', '100005', 1, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (6, true, '100006', 'anderson@extremodev.com', 'Pessoa 000006', '100006', 1, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (7, true, '100007', 'anderson@extremodev.com', 'Pessoa 000007', '100007', 1, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (8, true, '100008', 'anderson@extremodev.com', 'Pessoa 000008', '100008', 1, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (9, true, '100009', 'anderson@extremodev.com', 'Pessoa 000009', '100009', 1, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (10, true, '100010', 'anderson@extremodev.com', 'Pessoa 000010', '100010', 1, 11987654321);

insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (11, true, '200001', 'anderson@extremodev.com', 'Pessoa 200001', '200001', 2, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (12, true, '200002', 'anderson@extremodev.com', 'Pessoa 200002', '200002', 2, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (13, true, '200003', 'anderson@extremodev.com', 'Pessoa 200003', '200003', 2, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (14, true, '200004', 'anderson@extremodev.com', 'Pessoa 200004', '200004', 2, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (15, true, '200005', 'anderson@extremodev.com', 'Pessoa 200005', '200005', 2, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (16, true, '200006', 'anderson@extremodev.com', 'Pessoa 200006', '200006', 2, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (17, true, '200007', 'anderson@extremodev.com', 'Pessoa 200007', '200007', 2, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (18, true, '200008', 'anderson@extremodev.com', 'Pessoa 200008', '200008', 2, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (19, true, '200009', 'anderson@extremodev.com', 'Pessoa 200009', '200009', 2, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (20, true, '200010', 'anderson@extremodev.com', 'Pessoa 200010', '200010', 2, 11987654321);

insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (21, true, '300001', 'anderson@extremodev.com', 'Pessoa 300001', '300001', 3, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (22, true, '300002', 'anderson@extremodev.com', 'Pessoa 300002', '300002', 3, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (23, true, '300003', 'anderson@extremodev.com', 'Pessoa 300003', '300003', 3, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (24, true, '300004', 'anderson@extremodev.com', 'Pessoa 300004', '300004', 3, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (25, true, '300005', 'anderson@extremodev.com', 'Pessoa 300005', '300005', 3, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (26, true, '300006', 'anderson@extremodev.com', 'Pessoa 300006', '300006', 3, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (27, true, '300007', 'anderson@extremodev.com', 'Pessoa 300007', '300007', 3, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (28, true, '300008', 'anderson@extremodev.com', 'Pessoa 300008', '300008', 3, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (29, true, '300009', 'anderson@extremodev.com', 'Pessoa 300009', '300009', 3, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (30, true, '300010', 'anderson@extremodev.com', 'Pessoa 300010', '300010', 3, 11987654321);

insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (31, true, '400001', 'anderson@extremodev.com', 'Pessoa 400001', '400001', 4, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (32, true, '400002', 'anderson@extremodev.com', 'Pessoa 400002', '400002', 4, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (33, true, '400003', 'anderson@extremodev.com', 'Pessoa 400003', '400003', 4, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (34, true, '400004', 'anderson@extremodev.com', 'Pessoa 400004', '400004', 4, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (35, true, '400005', 'anderson@extremodev.com', 'Pessoa 400005', '400005', 4, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (36, true, '400006', 'anderson@extremodev.com', 'Pessoa 400006', '400006', 4, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (37, true, '400007', 'anderson@extremodev.com', 'Pessoa 400007', '400007', 4, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (38, true, '400008', 'anderson@extremodev.com', 'Pessoa 400008', '400008', 4, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (39, true, '400009', 'anderson@extremodev.com', 'Pessoa 400009', '400009', 4, 11987654321);
insert into pessoa (id, apto, documento, email, nome, senha, eleicao_id, celular) values (40, true, '400010', 'anderson@extremodev.com', 'Pessoa 400010', '400010', 4, 11987654321);
