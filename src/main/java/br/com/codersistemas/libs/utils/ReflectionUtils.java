/**
 * 
 */
package br.com.codersistemas.libs.utils;

import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import br.com.codersistemas.libs.annotations.Label;
import br.com.codersistemas.libs.dto.AtributoDTO;
import br.com.codersistemas.libs.dto.MudancaConteudoDTO;
import br.com.codersistemas.libs.exceptions.ReflectionUtilsException;

public class ReflectionUtils {

	private ReflectionUtils() {

	}

	public static void printGettersSetters(Class aClass) {
		Method[] methods = aClass.getMethods();
		for (Method method : methods) {
			if (method.getName().equals("equals") || method.getName().equals("getClass")) {
				continue;
			}
			if (isGetter(method))
				System.out.println("getter: " + method);
			if (isSetter(method))
				System.out.println("setter: " + method);
		}
	}

	public static boolean isGetter(Method method) {
		if (!method.getName().startsWith("get")) {
			return false;
		}
		if (method.getParameterTypes().length != 0) {
			return false;
		}
		if (void.class.equals(method.getReturnType())) {
			return false;
		}
		return true;
	}

	public static boolean isSetter(Method method) {
		if (!method.getName().startsWith("set")) {
			return false;
		}
		if (method.getParameterTypes().length != 1) {
			return false;
		}
		return true;
	}

	/**
	 * Retorna o Field mesmo com herança
	 * 
	 * @param obj
	 *            obj
	 * @param nome
	 *            nome
	 * @return field field
	 */
	public static Field getField(Object obj, String nome) {
		Class classe = obj.getClass();
		do {
			Field[] declaredFields = classe.getDeclaredFields();
			for (Field field : declaredFields) {
				if (field.getName().equals(nome)) {
					return field;
				}
			}
			classe = classe.getSuperclass();
		} while (classe != Object.class);
		return null;
	}

	public static Field[] getFields(Class classe) {
		List<Field> list = new ArrayList<Field>();
		do {
			Field[] declaredFields = classe.getDeclaredFields();
			for (Field field : declaredFields) {
				if (!field.getName().equals("serialVersionUID"))
					list.add(field);
			}
			classe = classe.getSuperclass();
		} while (classe != Object.class);
		return list.toArray(new Field[list.size()]);
	}

	public static Field[] getFields(Object obj) {
		return getFields(obj.getClass());
	}

	@Deprecated
	public static Annotation[] getAnnotations(Object obj, String name) {
		List<Annotation> list = new ArrayList<>();
		Field field = getField(obj, name);
		for (Annotation a : field.getDeclaredAnnotations()) {
			list.add(a);
		}
		Method getter = getGetter(obj.getClass(), field);// XXXXXXXXXXXXXXXXXXX
		for (Annotation a : getter.getDeclaredAnnotations()) {
			list.add(a);
		}
		Method setter = getSetter(obj.getClass(), field);// XXXXXXXXXXXXXXXXXXX
		for (Annotation a : setter.getDeclaredAnnotations()) {
			list.add(a);
		}
		return list.toArray(new Annotation[list.size()]);
	}

	private static Method getSetter(Class classe, Field field) {
		String seterName = getSeterName(field.getName());
		Method[] methods = getMethods(classe);
		for (Method method : methods) {
			if (seterName.equals(method.getName())) {
				return method;
			}
		}
		return null;
	}

	private static Method getGetter(Class obj, Field field) {
		String geterName = getGeterName(field.getName());
		Method[] methods = getMethods(obj);
		for (Method method : methods) {
			if (geterName.equals(method.getName())) {
				return method;
			}
		}

		geterName = getGeterNameBoolean(field.getName());
		for (Method method : methods) {
			if (geterName.equals(method.getName())) {
				return method;
			}
		}

		return null;
	}

	public static Method getMethod(Class classe, String name) {
		do {
			Method[] methodFields = classe.getDeclaredMethods();
			for (Method method : methodFields) {
				if (method.getName().equals(name)) {
					return method;
				}
			}
			classe = classe.getSuperclass();
		} while (classe != Object.class);
		return null;
	}

	public static Method[] getMethods(Class classe) {
		Class classe1 = classe;
		List<Method> list = new ArrayList<Method>();
		do {
			for (Method method : classe1.getDeclaredMethods()) {
				list.add(method);
			}
			classe1 = classe1.getSuperclass();
		} while (classe1 != Object.class);
		return list.toArray(new Method[list.size()]);
	}

	public static Annotation[] getAnnotation(Class classe, Field field) {
		List<Annotation> list = new ArrayList<Annotation>();
		for (Annotation annotation : field.getDeclaredAnnotations()) {
			list.add(annotation);
		}
		Method getter = getGetter(classe, field);
		if (getter != null) {
			for (Annotation annotation : getter.getDeclaredAnnotations()) {
				list.add(annotation);
			}
		}

		Method setter = getSetter(classe, field);
		if (setter != null) {
			for (Annotation annotation : setter.getDeclaredAnnotations()) {
				list.add(annotation);
			}
		}
		return list.toArray(new Annotation[list.size()]);
	}

	/**
	 * id - getId, nome - getNome
	 * 
	 * @param name
	 *            name
	 * @return String String
	 */
	public static String getGeterName(String name) {
		if (name.length() >= 2) {
			return "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
		} else {
			return name;
		}
	}

	public static String getGeterNameBoolean(String name) {
		if (name.length() >= 2) {
			return "is" + name.substring(0, 1).toUpperCase() + name.substring(1);
		} else {
			return name;
		}
	}

	/**
	 * id - setId, nome - setNome
	 * 
	 * @param name
	 *            name
	 * @return String String
	 */
	public static String getSeterName(String name) {
		if (name.length() >= 2) {
			return "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
		} else {
			return name;
		}
	}

	public static Annotation getAnnotation(Class classe, Field field, Class<? extends Annotation> annotationClass) {
		for (Annotation annotation : getAnnotation(classe, field)) {
			if (annotation.annotationType() == annotationClass) {
				return annotation;
			}
		}
		return null;
	}

	public static Object getValor(Object obj, String atributo) {

		Field field = getField(obj, atributo);
		if (field == null)
			throw new ReflectionUtilsException(
					"A classe " + obj.getClass().getName() + " não possui o atributo " + atributo);

		Method getter = getGetter(obj.getClass(), field);
		if (getter == null)
			return null;

		Object invoke = null;
		try {
			invoke = getter.invoke(obj, new Class[] {});
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return invoke;
	}

	public static MudancaConteudoDTO[] buscarDiferencas(Object obj1, Object obj2) {

		List<MudancaConteudoDTO> list = new ArrayList<>();
		Field[] fields = getFields(obj1.getClass());

		for (Field field : fields) {

			Object valor1 = getValor(obj1, field.getName());
			Object valor2 = getValor(obj2, field.getName());

			if (valor1 == null && valor2 == null) {
				continue;
			}

			if (valor1 != null && valor1 instanceof Date) {
				valor1 = DateUtils.dateTimeToString((Date) valor1);
			}

			if (valor2 != null && valor2 instanceof Date) {
				valor2 = DateUtils.dateTimeToString((Date) valor2);
			}

			String label = field.getName();

			Annotation annotation = getAnnotation(obj1.getClass(), field, Label.class);

			if (annotation != null) {
				Label annotationLabel = (Label) annotation;
				label = annotationLabel.name();
			}

			Class classe = valor1 != null ? valor1.getClass() : valor2.getClass();
			if (!classe.isEnum() && !classe.getName().startsWith("java.")) {
				Field[] fields2 = getFields(classe);

				// Buscar valor pela descricao
				boolean ok = false;
				for (Field field2 : fields2) {
					Annotation label2 = getAnnotation(classe, field2, Label.class);
					if (label2 != null) {
						Label xxx = (Label) label2;
						if (xxx.descricao()) {

							if (valor1 != null)
								valor1 = getValor(valor1, field2.getName());

							if (valor2 != null)
								valor2 = getValor(valor2, field2.getName());

							ok = true;
							break;

						}
					}
				}
				if (!ok) {
					for (Field field2 : fields2) {
						Annotation annotationId = getAnnotation(classe, field2, Id.class);
						if (annotationId != null) {

							if (valor1 != null)
								valor1 = getValor(valor1, field2.getName());

							if (valor2 != null)
								valor2 = getValor(valor2, field2.getName());

							break;
						}
					}
				}
			}

			if (valor1 != null) {
				if (!valor1.equals(valor2)) {
					list.add(MudancaConteudoDTO.builder().noCampo(label).coAntes(valor1.toString())
							.coDepois(valor2 != null ? valor2.toString() : null).build());
				}
			} else {
				if (valor2 != null) {
					list.add(MudancaConteudoDTO.builder().noCampo(label)
							.coAntes((valor1 != null ? valor1.toString() : null))
							.coDepois(valor2 != null ? valor2.toString() : null).build());
				}
			}
		}

		return list.toArray(new MudancaConteudoDTO[list.size()]);
	}

	public static void inject(Object action, String nomeSAtributo, Object value)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		setValue(action, nomeSAtributo, value);
	}

	public static void setValue(Object action, String nomeSAtributo, Object value)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field fieldGitecsPermitidas = null;
		;
		Field[] fields = getFields(action.getClass());
		for (Field field : fields) {
			if (field.getName().equals(nomeSAtributo)) {
				fieldGitecsPermitidas = field;
				break;
			}
		}
		if (fieldGitecsPermitidas == null)
			throw new ReflectionUtilsException("O atribito " + nomeSAtributo + " não existe.");
		fieldGitecsPermitidas.setAccessible(true);
		fieldGitecsPermitidas.set(action, value);
	}

	public static <T> T setValues(Class<T> classe, Object... args) throws InstantiationException,
			IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {

		T obj = classe.newInstance();

		String atribute = null;
		Object valor;

		for (int i = 0; i < args.length; i++) {
			if (i % 2 == 0) {
				atribute = (String) args[i];
			} else {
				valor = args[i];
				setValue(obj, atribute, valor);
			}
		}

		return obj;
	}

	public static Class getTipoGenericoRetorno(Method method) {
		String returnType = method.getReturnType().getSimpleName();
		if (method.getReturnType() == List.class) {
			String typeGeneric = "";
			if (method.getGenericReturnType() instanceof ParameterizedType) {
				ParameterizedType type = (ParameterizedType) method.getGenericReturnType();
				Type[] actualTypeArguments = type.getActualTypeArguments();
				for (Type type2 : actualTypeArguments) {
					Class c = (Class) type2;
					typeGeneric = c.getSimpleName();
					return c;
				}
			}
		}
		return null;
	}

	public static String getTipoGenericoRetornoString(Method method) {
		String returnType = method.getReturnType().getSimpleName();
		if (method.getReturnType() == List.class) {
			String typeGeneric = "";
			if (method.getGenericReturnType() instanceof ParameterizedType) {
				ParameterizedType type = (ParameterizedType) method.getGenericReturnType();
				Type[] actualTypeArguments = type.getActualTypeArguments();
				for (Type type2 : actualTypeArguments) {
					Class c = (Class) type2;
					typeGeneric = c.getSimpleName();
				}
			}
			returnType = method.getReturnType().getSimpleName() + "<" + typeGeneric + ">";
		}
		return returnType;
	}

	public static boolean getContemRetorno(Method method) {
		return method.getGenericReturnType() != void.class;
	}

	public static Object getValorLiteral(Object obj, String atributo) {
		Object valor = getValor(obj, atributo);

		if (valor != null) {
			if (valor instanceof String) {
				return "\"" + valor + "\"";
			}

			if (valor instanceof Integer) {
				return valor.toString();
			}

			if (valor instanceof Long) {
				return valor.toString() + "L";
			}

			if (valor instanceof Float) {
				return valor.toString() + "F";
			}

			if (valor instanceof Double) {
				return valor.toString();
			}

			if (valor instanceof Date) {
				return valor.toString();
			}

			if (valor instanceof Enum) {
				return valor.getClass().getSimpleName() + "." + valor.toString();
			}

			if (valor instanceof Short) {
				return "(short) " + valor.toString();
			}

			if (valor instanceof Boolean) {
				return valor.toString();
			}

		} else {
			return "null";
		}

		return null;
	}

	public static <T> T clone(T obj1) throws InstantiationException, IllegalAccessException, NoSuchFieldException,
			SecurityException, IllegalArgumentException {
		T obj2 = (T) obj1.getClass().newInstance();
		Field[] fields = ReflectionUtils.getFields(obj1.getClass());
		for (Field field : fields)
			ReflectionUtils.setValue(obj2, field.getName(), ReflectionUtils.getValor(obj1, field.getName()));
		return obj2;
	}

	public static String toString(Object obj1) {
		Field[] fields = getFields(obj1.getClass());

		StringBuilder sb = new StringBuilder();

		for (Field field : fields) {

			Annotation annotation = getAnnotation(obj1.getClass(), field, Label.class);
			String nome = null;
			if (annotation != null) {
				Label label = (Label) annotation;
				nome = label.name();
			} else {
				nome = field.getName();
			}

			sb.append(nome);
			sb.append("=");

			Object valor = getValor(obj1, field.getName());

			sb.append(valor != null ? valor : "");
			sb.append(",");

		}
		return sb.toString();
	}

	public static Object toStringNotNulls(Object obj1, String... ignores) {

		Field[] fields = getFields(obj1.getClass());

		StringBuilder sb = new StringBuilder();

		ignorarFiel: for (Field field : fields) {

			for (String ignore : ignores) {
				if (ignore.equals(field.getName())) {
					continue ignorarFiel;
				}
			}

			Annotation annotation = getAnnotation(obj1.getClass(), field, Label.class);
			String nome = null;
			if (annotation != null) {
				Label label = (Label) annotation;
				nome = label.name();
			} else {
				nome = field.getName();
			}

			Object valor = getValor(obj1, field.getName());

			if (valor != null && !valor.equals("")) {
				sb.append(nome);
				sb.append("=");
				sb.append(valor);
				sb.append(",");
			}

		}

		return sb.toString();
	}

	public static String printCreateObjectCode(Object instance, String var) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		BeanInfo beanInfo = Introspector.getBeanInfo(instance.getClass());

		BeanDescriptor bd = beanInfo.getBeanDescriptor();
		System.out.print(bd.getName());
		System.out.print(" ");
		System.out.print(var);
		System.out.println(" = new " + bd.getName() + "();");

		for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
			if(pd.getWriteMethod() == null || pd.getReadMethod() == null)
				continue;
			System.out.print(var);
			System.out.print(".");
			System.out.print(pd.getWriteMethod().getName());
			System.out.print("(");
			System.out.print(getValorLiteral(instance, pd.getName()));
			System.out.print(");");
			System.out.println("");
		}
		
		return sb.toString();
	}
	
	@SuppressWarnings("rawtypes")
	public static Type getGenericType(Field field) {
		Type genericFieldType = field.getGenericType();
		if (genericFieldType instanceof ParameterizedType) {
			ParameterizedType aType = (ParameterizedType) genericFieldType;
			Type[] fieldArgTypes = aType.getActualTypeArguments();
			for (Type fieldArgType : fieldArgTypes) {
				Class fieldArgClass = (Class) fieldArgType;
				return fieldArgClass;
			}
		}
		return null;
	}

	public static Field[] getAttributesOffType(Class classe, Class type) {
		List<Field>fields = new ArrayList<Field>();
		for (Field field : classe.getDeclaredFields()) {
			if(field.getType() == type){
				fields.add(field);
			}
		}
		Field[] array = new Field[fields.size()];
		return fields.toArray(array);
	}

	@SuppressWarnings("rawtypes")
	private static String getTipo(Field field) {
		return field.getType().getSimpleName() + (isGenericType(field) ? "<" + ((Class) getGenericType(field)).getSimpleName() + ">" : "");
	}
	@SuppressWarnings("rawtypes")
	public static Type getGenericType2(Field field) {
		Type genericFieldType = field.getGenericType();
		if (genericFieldType instanceof ParameterizedType) {
			ParameterizedType aType = (ParameterizedType) genericFieldType;
			Type[] fieldArgTypes = aType.getActualTypeArguments();
			for (Type fieldArgType : fieldArgTypes) {
				Class fieldArgClass = (Class) fieldArgType;
				return fieldArgClass;
			}
		}
		return null;
	}

	public static Type getGenericType(Method method) {
		Type returnType = method.getGenericReturnType();

		if(returnType instanceof ParameterizedType){
		    ParameterizedType type = (ParameterizedType) returnType;
		    Type[] typeArguments = type.getActualTypeArguments();
		    for(Type typeArgument : typeArguments){
		        Class typeArgClass = (Class) typeArgument;
		        return typeArgClass;
		    }
		}
		return null;
	}

	public static boolean isGenericType(Field field) {
		Type type = field.getGenericType();
		return type instanceof ParameterizedType;
	}
	
	public static boolean isGenericType(Method method) {
		Type returnType = method.getGenericReturnType();

		if(returnType instanceof ParameterizedType){
		    ParameterizedType type = (ParameterizedType) returnType;
		    Type[] typeArguments = type.getActualTypeArguments();
		    for(Type typeArgument : typeArguments){
		        Class typeArgClass = (Class) typeArgument;
		        return true;
		    }
		}
		return false;
	}
	

	/**
	 * Retorna o tipo com generics se nescessario.
	 * 
	 * @param field
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	public static String getTipo2(Field field) {
		return field.getType().getSimpleName() + (isGenericType(field) ? "<" + ((Class) getGenericType(field)).getSimpleName() + ">" : "");
	}

	public static Object createObjectWithValues() {
		// TODO Auto-generated method stub
		return null;
	}

	public static List<AtributoDTO> getAtributos(Class classe) {
		
		List<AtributoDTO> atributos = new ArrayList<>();
		
		Field[] fields = getFields(classe);
		
		for (Field field : fields) {
			
			String name = field.getName();
			System.out.println(name);
			if (!"serialVersionUID".equals(name)) {
			
				Column column = (Column) ReflectionUtils.getAnnotation(classe, field, Column.class);
				
				AtributoDTO atributo = new AtributoDTO();
				atributo.setNome(field.getName());
				atributo.setColuna(nomeColuna(classe, field));
				atributo.setObrigatorio(column != null && !column.nullable());
				atributo.setRotulo(StringUtil.label(field.getName()) );
				atributo.setTamanho(column != null ? column.length() : 0);
				atributo.setTipo(field.getType().getName());
				atributos.add(atributo);
				
				if(field.getType().isPrimitive())
					throw new RuntimeException("N�o utilize tipos primitivos");
				
				if (field.getType().isEnum()) {
					System.out.println("");
					Class classeEnum = field.getType();
					Object[] enumConstants = classeEnum.getEnumConstants();
					String[] enumConstantsStrings  = new String[enumConstants.length];
					for (int i = 0; i < enumConstants.length; i++) 
						enumConstantsStrings[i] = Arrays.asList(classeEnum.getEnumConstants()).get(i).toString();
					atributo.setEnumaracao(enumConstantsStrings);
				} else if (field.getType() == Long.class) {
//					if ("id".equals(field.getName())){
//						String colunaId = StringUtil.toUnderlineCase( JPAUtil.gerarColunaId(classe) );
//						String unCapitalize = StringUtil.toUnderlineCase( StringUtil.uncapitalize(classe.getSimpleName() ) );
//						//anotacoes += ("@Id @GeneratedValue(generator=\"seq_:name\", strategy=GenerationType.SEQUENCE) @SequenceGenerator(name=\"seq_:name\") @Column(name=\""+colunaId+"\") ").replace(":name", unCapitalize);
//					}
				} else if (field.getType() == Date.class) {
					//anotacoes += "@Temporal(TemporalType.DATE) \n";
					//anotacoes += "\t@Column(name=\""+nomeColuna+"\", length=255, nullable=false)";
				} else if (field.getType() == String.class) {
					//anotacoes += "@Column(name=\""+nomeColuna+"\", length=255, nullable=false)";
				} else if (field.getType() == Integer.class) {
					//anotacoes += "@Column(name=\""+nomeColuna+"\", nullable=true)";
				} else if (field.getType() == Float.class) {
					//anotacoes += "@Column(name=\""+nomeColuna+"\", length=10, precision=10, scale=2, nullable=false)";
				} else if (field.getType() == BigDecimal.class) {
					//anotacoes += "@Column(name=\""+nomeColuna+"\", length=10, precision=10, scale=2, nullable=false)";
				} else if (field.getType() == Double.class) {
					//anotacoes += "@Column(name=\""+nomeColuna+"\", length=10, precision=10, scale=2, nullable=false)";
				} else if (field.getType() == Boolean.class) {
					//anotacoes += "@Column(name=\""+nomeColuna+"\", length=1, nullable=false)";
				} else if (field.getType() == List.class || field.getType() == Set.class) {
					Class genericType = (Class) ReflectionUtils.getGenericType(field);
					Field[] f = ReflectionUtils.getAttributesOffType(genericType, classe);
					//if(f.length==1)
					//anotacoes += "@OneToMany(mappedBy=\""+(f[0]).getName()+"\")";
					
				} else if (!"java".startsWith(field.getType().getName())) {
					boolean isEntity = JPAUtil.isEntity(field);
					if(isEntity){
						Field id = JPAUtil.getId(field);
						String fk_name = classe.getSimpleName()+"_"+field.getType().getSimpleName()+"_fk";
						//anotacoes += "@ManyToOne @JoinColumn(name=\""+ JPAUtil.gerarColunaId(field.getType())+"\", nullable=false) @ForeignKey(name=\""+fk_name+"\")";
					}
				} else {
					throw new RuntimeException("Erro ao gerar atributo de " + field.getType().getName());
				}
			}
		}

		return atributos;
	}

	private static String nomeColuna(Class classe, Field field) {
		
		Column column = (Column) getAnnotation(classe, field, Column.class);
		if(column != null) {
			return column.name();
		}
		
		JoinColumn joinColumn = (JoinColumn) getAnnotation(classe, field, Column.class);
		if(joinColumn != null) {
			return joinColumn.name();
		}
		
		return JPAUtil.nomeColuna(field); 
	}

}
