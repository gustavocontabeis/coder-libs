package br.com.codersistemas.libs.dto;

import java.util.List;

import lombok.Data;

@Data 
public class EntidadeDTO {
	private String nome, nomeInstancia, nomeClasse, rotulo, tabela;
	private AplicacaoDTO aplicacao;
	private List<AtributoDTO> atributos;
}
