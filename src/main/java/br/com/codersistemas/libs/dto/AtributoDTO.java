package br.com.codersistemas.libs.dto;

import lombok.Data;

@Data
public class AtributoDTO {
	private String nome;
	private String classeInstancia;
	private String coluna;
	private String rotulo;
	private boolean obrigatorio;
	private String tipo;
	private int tamanho;
	private String[] enumaracao;
}
