package br.com.codersistemas.libs.utils.mock;

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
public class Carro {
	
	@Id
	private Long id;
	
	@Label(name="Placa", descricao=true)
	@Column
	private String placa;
	
}
