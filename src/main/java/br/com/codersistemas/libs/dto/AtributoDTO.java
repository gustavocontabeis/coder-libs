package br.com.codersistemas.libs.dto;

import br.com.codersistemas.libs.utils.StringUtil;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude="entidade")
public class AtributoDTO {
	private String nome;
	private String nomeCapitalizado;
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
	private String fkField;
	private boolean pk;
	private String[] enumaracao;
	private EntidadeDTO entidade;
	private Class classe;
	
	public String getNomeHyphenCase() {
		return StringUtil.toHiphenCase(nome);
	}
}
