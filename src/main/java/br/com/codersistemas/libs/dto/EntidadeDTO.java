package br.com.codersistemas.libs.dto;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data 
public class EntidadeDTO {
<<<<<<< HEAD
	
	private String nome, nomeInstancia, nomeClasse, rotulo, tabela, restURI;
	
=======
	private String nome, nomeInstancia, nomeClasse, rotulo, tabela, restURI;
>>>>>>> f54e367599603f4e44244baf6a2a81905ccfe442
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
