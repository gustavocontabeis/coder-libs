package br.com.codersistemas.libs.dto;

import lombok.Data;

@Data
public class AtributoDTO {
	private String nome;
	private String nomeInstancia;
	private String nomeLista;
	private String coluna;
	private String rotulo;
	private boolean obrigatorio;
	private boolean isEnum;
	private boolean collection;
	private String tipo;
	private String tipoClasse;
	private String tipoClasseGenerica;
	private String tipoClasseGenericaNome;
	private int tamanho;
	private int precisao;
	private boolean fk;
	private boolean pk;
	private String[] enumaracao;
	private EntidadeDTO entidade;
}
