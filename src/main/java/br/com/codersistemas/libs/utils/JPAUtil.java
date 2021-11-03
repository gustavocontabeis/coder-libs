package br.com.codersistemas.libs.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.apache.commons.lang3.StringUtils;

import br.com.codersistemas.libs.dto.AtributoDTO;
import br.com.codersistemas.libs.dto.ColumnDTO;

public class JPAUtil {

	public static String gerarColunaId(Class classe) {
		return "id_"+ StringUtil.toUnderlineCase( StringUtils.uncapitalize( classe.getSimpleName() ) );
	}

	public static String nomeTabela(Class classe) {
		Annotation[] annotations = classe.getAnnotations();
		for (Annotation annotation : annotations) {
			if (annotation instanceof Entity) {
				Entity entity = (Entity) annotation;
				String name = entity.name();
				if (StringUtil.isBlank(name)) {
					return classe.getSimpleName();
				} else {
					return name;
				}
			}
		}
		return null;
	}

	public static boolean isId(Class classe) {
		Annotation[] annotations = classe.getAnnotations();
		for (Annotation annotation : annotations)
			if (annotation instanceof Id)
				return true;
		return false;
	}

	public static String nomeColuna(Field field) {
		return StringUtil.toUnderlineCase(field.getName());
	}

	@SuppressWarnings("rawtypes")
	public static String gerarClasse(Class classe) {
		StringBuilder sb = new StringBuilder();
		sb.append(classe.getPackage() + ";\n");
		sb.append("\n");
		sb.append("import java.io.Serializable;\n");
		sb.append("import java.util.*;\n");
		sb.append("import lombok.*;\n");
		sb.append("import javax.persistence.*;\n");
		sb.append("import javax.validation.constraints.*;\n");
		sb.append("\n");
		sb.append("@Data\n");
		sb.append(toString(classe));
		
		sb.append("@NoArgsConstructor\n");
		sb.append("@AllArgsConstructor\n");
		sb.append("@Builder\n");
		sb.append("@Entity\n");
		sb.append("@Table(name=\""+StringUtil.toUnderlineCase(classe.getSimpleName()) +"\")\n");
		sb.append("public class " + classe.getSimpleName() + " implements Serializable {\n");
		sb.append("	\n");
		sb.append("	private static final long serialVersionUID = 1L;");
		return sb.toString();
	}

	private static String toString(Class classe) {
		return new LombokToStringUtil(classe).print();
	}

	@SuppressWarnings("rawtypes")
	public static String gerarAtributos(Class classe) {

		Field[] fields = classe.getDeclaredFields();

		boolean contemId = !Stream.of(fields).filter(f->"id".equals(f.getName())).collect(Collectors.toList()).isEmpty();

		if(!contemId)
			throw new RuntimeException("A classe "+classe.getName()+" nao contem id.");

		StringBuilder sb = new StringBuilder();

		for (Field field : fields) {

			String name = field.getName();
			String nomeColuna = JPAUtil.nomeColuna(field);
			String label = StringUtil.label(name);
			String anotacoes = "\t";
			if (!"serialVersionUID".equals(name)) {
				sb.append("\n");

				if(field.getType().isPrimitive())
					throw new RuntimeException("Nao utilize tipos primitivos");
				if(isBidirectionalRelationship(classe, field)) {
					anotacoes += new JsonIgnorePropertiesUtil(classe, field).print();
				}
				anotacoes += new JsonIgnoreUtil(classe, field).print();
				if (field.getType().isEnum()) {
					anotacoes += "\t@NotNull(message = \""+label+" deve ser preenchido.\")\n";
					anotacoes += "\t@Enumerated(EnumType.STRING)\n";
					anotacoes += "\t@Column(length=255, nullable=false)";
				} else if (field.getType() == Long.class) {
					if ("id".equals(field.getName())){
						String colunaId = StringUtil.toUnderlineCase( JPAUtil.gerarColunaId(classe) );
						String unCapitalize = StringUtil.toUnderlineCase( StringUtil.uncapitalize(classe.getSimpleName() ) );
						anotacoes += ("@Id @GeneratedValue(generator=\"seq_:name\", strategy=GenerationType.SEQUENCE) @SequenceGenerator(name=\"seq_:name\", initialValue=1000, allocationSize=1) @Column(name=\""+colunaId+"\", nullable=false) ").replace(":name", unCapitalize);
					} else {
						anotacoes += "@Column(name=\""+nomeColuna+"\", nullable=false)";
					}
				} else if (field.getType() == Date.class || field.getType() == LocalDate.class) {
					anotacoes += "\n@NotNull(message = \""+label+" deve ser preenchido.\")\n ";
					anotacoes += "\t@JsonFormat(pattern=\"dd/MM/yyyy\")\n";
					anotacoes += "\t@Temporal(TemporalType.DATE) \n";
					anotacoes += "\t@Column(name=\""+nomeColuna+"\", length=255, nullable=false)";
				} else if (field.getType() == LocalDateTime.class) {
					anotacoes += "\t@NotNull(message = \""+label+" deve ser preenchido.\")";
					anotacoes += "\t@JsonFormat(pattern=\"dd/MM/yyyy HH:mm\")\n";
					anotacoes += "\t@Temporal(TemporalType.TIMESTAMP) \n";
					anotacoes += "\t@Column(name=\""+nomeColuna+"\", length=255, nullable=false)";
				} else if (field.getType() == String.class) {
					anotacoes += "@NotEmpty(message = \""+label+" deve ser preenchido.\")\n";
					anotacoes += "\t@Column(name=\""+nomeColuna+"\", length=255, nullable=false)";
				} else if (field.getType() == Integer.class) {
					anotacoes += "@NotNull @Column(name=\""+label+"\", nullable=true)";
				} else if (field.getType() == Float.class) {
					anotacoes += "@NotNull(message = \""+label+" deve ser preenchido.\")";
					anotacoes += "@Column(name=\""+nomeColuna+"\", precision=10, scale=2, nullable=false)";
				} else if (field.getType() == BigDecimal.class) {
					anotacoes += "@NotNull(message = \""+label+" deve ser preenchido.\")";
					anotacoes += "@Column(name=\""+nomeColuna+"\", precision=10, scale=2, nullable=false)";
				} else if (field.getType() == Double.class) {
					anotacoes += "@NotNull(message = \""+label+" deve ser preenchido.\")";
					anotacoes += "@Column(name=\""+nomeColuna+"\", precision=10, scale=2, nullable=false)";
				} else if (field.getType() == Boolean.class) {
					anotacoes += "@NotNull(message = \""+label+" deve ser preenchido.\") \n";
					anotacoes += "\t@Column(name=\""+nomeColuna+"\", length=1, nullable=false)";
				} else if (field.getType() == List.class || field.getType() == Set.class) {
					Class genericType = (Class) ReflectionUtils.getGenericType(field);
					Field[] f = ReflectionUtils.getAttributesOffType(genericType, classe);
					boolean isMappedBy = f.length == 1;
					if(isMappedBy) {
						anotacoes += "@OneToMany(mappedBy=\""+(f[0]).getName()+"\")";
					} else {
						anotacoes += "//@ManyToMany()";
					}

				} else if (!"java".startsWith(field.getType().getName())) {
					boolean isEntity = JPAUtil.isEntity(field);
					if(isEntity){
						Field id = getId(field);
						String fk_name = classe.getSimpleName()+"_"+field.getType().getSimpleName()+"_fk";
						anotacoes += "@ManyToOne @JoinColumn(name=\""+ JPAUtil.gerarColunaId(field.getType())+"\", nullable=false) @ForeignKey(name=\""+fk_name+"\")";
					}
				} else {
					throw new RuntimeException("Erro ao gerar atributo de " + field.getType().getName());
				}
				sb.append(anotacoes + "\n");
				sb.append(String.format("\tprivate %s %s;\n", ReflectionUtils.getTipo2(field), field.getName()));
			}
		}
		return sb.toString();
	}

	private static boolean isBidirectionalRelationship(Class classe, Field field) {
		boolean containsCircularReference = ReflectionUtils.containsCircularReference(classe, field);
		return containsCircularReference;
		/*
		//System.out.println(classe.getName());
		//System.out.println("  "+type.getName());
		//System.out.println(">>>");
		Field[] fields = ReflectionUtils.getFields(type);
		for (Field field : fields) {
			Type genericType = ReflectionUtils.getGenericType(field);
			if(genericType != null) {
				if(genericType.getTypeName().equals(classe.getName())) {
					//System.out.println(">>>!!!");
					return true;
				}
			}
			if(field.getType().getName().equals(classe.getName())) {
				return true;
			}
		}
		return false;
		*/
	}

	/**
	 * Verifica se a classe deste atributo cont�m @Entity.
	 * @param field
	 * @return
	 */
	public static boolean isEntity(Field field) {
		Annotation[] annotations = field.getType().getAnnotations();
		for (Annotation annotation : annotations) {
			if (annotation instanceof Entity)
				return true;
		}
		return false;
	}

	/**
	 * Retorna o atributo de nome "id".
	 * @param field
	 * @return
	 */
	public static Field getId(Field field) {
		return ReflectionUtils.getField(field.getType(), "id");
	}

	public static ColumnDTO getDto(Class classe, Field field) {
		ColumnDTO dto = new ColumnDTO();
		Annotation annotation = ReflectionUtils.getAnnotation(classe, field, Column.class);
		if(annotation != null) {
			Column column = (Column) annotation;
			dto.setFk(false);
			dto.setName(column.name());
			dto.setLength(column.length());
			dto.setNullable(column.nullable());
			dto.setPrecision(column.precision());
			dto.setScale(column.scale());
			dto.setTable(column.table());
			dto.setUnique(column.unique());
			dto.setUpdatable(column.updatable());
			dto.setInsertable(column.insertable());
		} else {
			annotation = ReflectionUtils.getAnnotation(classe, field, JoinColumn.class);
			if(annotation != null) {
				JoinColumn column = (JoinColumn) annotation;
				dto.setFk(true);
				dto.setName(column.name());
				dto.setLength(0);
				dto.setNullable(column.nullable());
				dto.setPrecision(0);
				dto.setScale(0);
				dto.setTable(column.table());
				dto.setUnique(column.unique());
				dto.setUpdatable(column.updatable());
				dto.setInsertable(column.insertable());
			}
		}

		annotation = ReflectionUtils.getAnnotation(classe, field, Id.class);
		if(annotation != null) {
			Id id = (Id) annotation;
			dto.setPk(true);
		}

		return dto;
	}
}
