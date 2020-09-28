SELECT id_aplicacao, nome FROM public.aplicacao;

SELECT id_entidade, nome, rotulo, tabela, id_aplicacao FROM public.entidade;

SELECT id_atributo, coluna, nome, obritatorio, rotulo, tamanho_maximo, tamanho_minimo, tipo, id_entidade
FROM public.atributo;


select 

INSERT INTO public.atributo (id_atributo, coluna, nome, obritatorio, rotulo, tamanho_maximo, tamanho_minimo, tipo, id_entidade) values (nextval('public.seq_atributo'), 'nome', 'nome', true, 'Nome', 100, 3, 'STRING', (SELECT id_entidade FROM public.entidade WHERE nome='PessoaFisica'));


;
