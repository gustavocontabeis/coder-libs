package br.com.codersistemas.libs.utils.mock;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;

import br.com.codersistemas.libs.annotations.Label;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PessoaPrimitive {
	
	@Id
	private long id;
	
	@Column
	private String nome;
	
	@Label(name="Gênero")
	private Genero genero;
	
	@Label(name="Altura")
	private float altura;
	
	@Label(name="Salário")
	private double salario;
	
	private boolean ativo;
	
	private PessoaPrimitive mae;
	
	private List<PessoaPrimitive>filhos;
	
}
