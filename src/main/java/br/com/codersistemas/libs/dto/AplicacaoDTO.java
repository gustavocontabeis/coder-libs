package br.com.codersistemas.libs.dto;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.codersistemas.libs.utils.JPAUtil;
import br.com.codersistemas.libs.utils.ReflectionUtils;
import br.com.codersistemas.libs.utils.StringUtil;
import lombok.Data;

@Data
public class AplicacaoDTO {
	
	private String nome, pacoteBackend;
	private List<EntidadeDTO> entidades;
	
	public AplicacaoDTO(String nomeAplicacao, Class<?>... classes) {
		
		nome = nomeAplicacao;
		entidades = new ArrayList<EntidadeDTO>();

		for (Class<?> classe : classes) {

			EntidadeDTO entidade = new EntidadeDTO();
			entidade.setClasse(classe);
			entidade.setAtributos(new ArrayList<>());
			entidade.setNome(classe.getSimpleName());
			entidade.setNomeClasse(classe.getName());
			entidade.setNomeInstancia(StringUtil.uncapitalize(classe.getSimpleName()));
			entidade.setNomeCapitalizado(classe.getSimpleName());
			entidade.setRotulo(StringUtil.label(classe.getSimpleName()));
			entidade.setTabela(StringUtil.toUnderlineCase(classe.getSimpleName()).toLowerCase());
			entidade.setAplicacao(this);
			entidade.setRestURI("/" + StringUtil.uncaplitalizePlural(entidade.getTabela().replace("_", "-")));
			getEntidades().add(entidade);

			Field[] fields = ReflectionUtils.getFields(classe);
			for (Field field : fields) {
				AtributoDTO atributo = new AtributoDTO();
				if (field.getType().isEnum()) {
					List<String> enumValues = new ArrayList<>();
					Class enummClass = field.getType();
					Object[] enumConstants = enummClass.getEnumConstants();
					for (Object object : enumConstants) {
						Enum e = (Enum) object;
						enumValues.add(e.name());
					}
					atributo.setEnum(true);
					atributo.setEnumaracao(enumValues.toArray(new String[enumValues.size()]));
				}

				atributo.setTipoClasse(field.getType().getName());
				atributo.setNome(field.getName());
				atributo.setNomeInstancia(StringUtil.uncapitalize(field.getName()));
				atributo.setNomeLista(StringUtil.uncaplitalizePlural(field.getName()));
				atributo.setRotulo(StringUtil.label(field.getName()));
				atributo.setClasse(field.getType());
				atributo.setCollection(field.getType().isArray() || field.getType() == List.class
						|| field.getType() == Set.class || field.getType() == Map.class);
				atributo.setFk((!atributo.getTipoClasse().startsWith("java.") && !atributo.isEnum())
						|| atributo.isCollection());
				if(atributo.isFk() && !atributo.isCollection()) {
					Field[] fields2 = ReflectionUtils.getFields(field.getType());
					for (Field field2 : fields2) {
						Annotation annotation = ReflectionUtils.getAnnotation(field.getType(), field2, br.com.codersistemas.libs.annotations.ToString.class);
						if(annotation != null) {
							atributo.setFkField(atributo.getNomeInstancia()+"."+field2.getName());
						}
					}
				}
				ColumnDTO columnDTO = JPAUtil.getDto(classe, field);

				if (StringUtil.isNotBlank(columnDTO.getName())) {
					atributo.setColuna(columnDTO.getName());
				} else {
					atributo.setColuna(field.getName());
				}

				atributo.setPk(columnDTO.isPk());

				if (atributo.isPk() && !atributo.getColuna().startsWith("id_")) {
					atributo.setColuna("id_" + atributo.getColuna());
				}

				atributo.setObrigatorio(!columnDTO.isNullable());
				atributo.setPrecisao(columnDTO.getPrecision());
				atributo.setTamanho(columnDTO.getLength());
				atributo.setTipo(field.getType().getSimpleName().toUpperCase());
				atributo.setEntidade(entidade);

				Method getter = ReflectionUtils.getGetter(classe, field);
				if (getter != null) {
					Class tipoGenericoRetorno = ReflectionUtils.getTipoGenericoRetorno(getter);
					if (tipoGenericoRetorno != null) {
						atributo.setTipoClasseGenerica(tipoGenericoRetorno.getName());
						atributo.setTipoClasseGenericaNome(tipoGenericoRetorno.getSimpleName());
					}
				}

				if (atributo.isCollection()) {
					atributo.setColuna(null);
					atributo.setObrigatorio(false);
				}

				entidade.getAtributos().add(atributo);
			}

		}

	}

}
