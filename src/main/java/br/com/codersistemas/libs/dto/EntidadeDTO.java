package br.com.codersistemas.libs.dto;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data 
public class EntidadeDTO {
	
	private String nome, nomeInstancia, nomeClasse, rotulo, tabela, restURI;
	
	private AplicacaoDTO aplicacao;
	private List<AtributoDTO> atributos;
	
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
