package br.com.codersistemas.libs.utils.mock;

import java.util.List;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
public class Bloco {
	private Long id;
	
	@ManyToOne
	private Condominio condominio;
	
	@OneToMany
	private List<Apartamento>apartamentos;
}
