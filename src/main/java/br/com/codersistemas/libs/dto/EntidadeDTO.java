package br.com.codersistemas.libs.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.codersistemas.libs.utils.StringUtil;
import lombok.Data;

@Data 
public class EntidadeDTO {
	
	private Class classe;
	private String nome, nomeInstancia, nomeClasse, nomeCapitalizado, rotulo, tabela, restURI;
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

	public List<AtributoDTO> getAtributosFKs() {
		return atributos.stream()
		.filter(atributo -> atributo.isFk() && !atributo.isEnum() && !atributo.isCollection())
		.collect(Collectors.toList());
	}

	public String getNomeHyphenCase() {
		return StringUtil.toHiphenCase(nome);
	}
}
