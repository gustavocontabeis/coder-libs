package br.com.codersistemas.libs.dto;

import java.util.List;

import lombok.Data;

@Data
public class AplicacaoDTO {
	
	private String nome, pacoteBackend;
	private List<EntidadeDTO> entidades;

}
