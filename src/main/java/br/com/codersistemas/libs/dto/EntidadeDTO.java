package br.com.codersistemas.libs.dto;

import java.util.List;

import lombok.Data;

@Data 
public class EntidadeDTO {
	private String nome, rotulo, tabela;
	private List<AtributoDTO> atributos;
}
