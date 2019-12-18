package br.com.codersistemas.libs.utils.mock;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Funcionario {
	
	@Id
	private Long id;
	
	@Column
	private String nome;
	
	private Carro carro;
	
}
