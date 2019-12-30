package br.com.codersistemas.libs.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang3.StringUtils;

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
		sb.append("import javax.persistence.*;\n");
		sb.append("\n");
		sb.append("@Data\n");
		sb.append("@NoArgsConstructor\n");
		sb.append("@AllArgsConstructor\n");
		sb.append("@Builder\n");
		sb.append("@Entity\n");
		sb.append("public class " + classe.getSimpleName() + " implements Serializable {\n");
		sb.append("	\n");
		sb.append("	private static final long serialVersionUID = 1L;");
		return sb.toString();
	}

	@SuppressWarnings("rawtypes")
	public static String gerarAtributos(Class classe) {
		
		Field[] fields = classe.getDeclaredFields();
		
		boolean contemId = false;
		for (Field field : fields) 
			if("id".equals(field.getName()))
				contemId = true;
		if(!contemId)
			throw new RuntimeException("A classe "+classe.getName()+" n�o cont�m id.");
		
		StringBuilder sb = new StringBuilder();
		
		for (Field field : fields) {
			
			String name = field.getName();
			String nomeColuna = JPAUtil.nomeColuna(field);
			String anotacoes = "\t";
			if (!"serialVersionUID".equals(name)) {
				sb.append("\n");
				
				if(field.getType().isPrimitive())
					throw new RuntimeException("N�o utilize tipos primitivos");
				
				if (field.getType().isEnum()) {
					anotacoes += "@Enumerated(EnumType.STRING) @Column(length=255, nullable=false)";
				} else if (field.getType() == Long.class) {
					if ("id".equals(field.getName())){
						String colunaId = StringUtil.toUnderlineCase( JPAUtil.gerarColunaId(classe) );
						String unCapitalize = StringUtil.toUnderlineCase( StringUtil.uncapitalize(classe.getSimpleName() ) );
						anotacoes += ("@Id @GeneratedValue(generator=\"seq_:name\", strategy=GenerationType.SEQUENCE) @SequenceGenerator(name=\"seq_:name\") @Column(name=\""+colunaId+"\") ").replace(":name", unCapitalize);
					}
				} else if (field.getType() == Date.class) {
					anotacoes += "@Temporal(TemporalType.DATE) \n";
					anotacoes += "\t@Column(name=\""+nomeColuna+"\", length=255, nullable=false)";
				} else if (field.getType() == String.class) {
					anotacoes += "@Column(name=\""+nomeColuna+"\", length=255, nullable=false)";
				} else if (field.getType() == Integer.class) {
					anotacoes += "@Column(name=\""+nomeColuna+"\", nullable=true)";
				} else if (field.getType() == Float.class) {
					anotacoes += "@Column(name=\""+nomeColuna+"\", length=10, precision=10, scale=2, nullable=false)";
				} else if (field.getType() == BigDecimal.class) {
					anotacoes += "@Column(name=\""+nomeColuna+"\", length=10, precision=10, scale=2, nullable=false)";
				} else if (field.getType() == Double.class) {
					anotacoes += "@Column(name=\""+nomeColuna+"\", length=10, precision=10, scale=2, nullable=false)";
				} else if (field.getType() == Boolean.class) {
					anotacoes += "@Column(name=\""+nomeColuna+"\", length=1, nullable=false)";
				} else if (field.getType() == List.class || field.getType() == Set.class) {
					Class genericType = (Class) ReflectionUtils.getGenericType(field);
					Field[] f = ReflectionUtils.getAttributesOffType(genericType, classe);
					if(f.length==1)
					anotacoes += "@OneToMany(mappedBy=\""+(f[0]).getName()+"\")";
					
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
}
