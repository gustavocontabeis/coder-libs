package br.com.codersistemas.libs.dto;

import java.util.List;

import lombok.Data;

@Data
public class AplicacaoDTO {
	
	private String nome;
	private List<EntidadeDTO> entidades;

}
