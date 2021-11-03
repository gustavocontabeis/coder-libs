package br.com.codersistemas.libs.utils.mock;

import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
public class Condominio{
	private Long id;
	
	@OneToMany(mappedBy="condominio", fetch = FetchType.LAZY)
	private List<Bloco>blocos;
}
