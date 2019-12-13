package br.com.codersistemas.libs;

import static org.junit.Assert.assertTrue;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import org.junit.Test;

import br.com.codersistemas.libs.dto.MudancaConteudoDTO;
import br.com.codersistemas.libs.utils.ReflectionUtils;
import br.com.codersistemas.libs.utils.teste.Carro;
import br.com.codersistemas.libs.utils.teste.Funcionario;
import br.com.codersistemas.libs.utils.teste.Genero;
import br.com.codersistemas.libs.utils.teste.MeuEnum;
import br.com.codersistemas.libs.utils.teste.Pessoa;
import br.com.codersistemas.libs.utils.teste.Tipos;

public class ReflectionUtilsTest {

	@Test
	public void testPrintGettersSetters() {
		ReflectionUtils.printGettersSetters(Pessoa.class);
	}

	@Test
	public void testIsGetter() {
		Method method = ReflectionUtils.getMethod(Pessoa.class, "getNome");
		assertTrue(ReflectionUtils.isGetter(method));
	}

	@Test
	public void testIsSetter() {
		Method method = ReflectionUtils.getMethod(Pessoa.class, "setNome");
		assertTrue(ReflectionUtils.isSetter(method));
	}

	@Test
	public void testGetField() {
		Pessoa build = Pessoa.builder().nome("oi").build();
		Field field = ReflectionUtils.getField(build, "nome");
		assertTrue(field.getName().equals("nome"));
	}

	@Test
	public void testGetFieldsClass() {
		Pessoa build = Pessoa.builder().nome("oi").build();
		Field field = ReflectionUtils.getField(build, "nome");
		assertTrue(field.getName().equals("nome"));
	}

	@Test
	public void testGetFieldsObject() {
		Pessoa build = Pessoa.builder().nome("oi").build();
		Field[] fields = ReflectionUtils.getFields(build);
		for (Field field : fields) {
			System.out.println(field);
		}
	}

	@Test
	public void testGetAnnotations() {
		Pessoa build = Pessoa.builder().nome("oi").build();
		Field[] fields = ReflectionUtils.getFields(build);
		for (Field field : fields) {
			Annotation[] annotations = ReflectionUtils.getAnnotation(build.getClass(), field);
			for (Annotation annotation : annotations) {
				System.out.println("> "+annotation.toString());
			}
		}
	}

	@Test
	public void testGetMethod() {
		Pessoa build = Pessoa.builder().nome("oi").build();
		Method method = ReflectionUtils.getMethod(build.getClass(), "getNome");
		System.out.println(method);
		method = ReflectionUtils.getMethod(build.getClass(), "setNome");
		System.out.println(method);
	}

	@Test
	public void testGetMethods() {
		Method[] methods = ReflectionUtils.getMethods(Pessoa.class);
		for (Method method : methods) {
			System.out.println(method);
		}
	}

	@Test
	public void testGetAnnotationClassField() {
		Pessoa build = Pessoa.builder().nome("oi").build();
		Field[] fields = ReflectionUtils.getFields(build);
		for (Field field : fields) {
			Annotation[] annotations = ReflectionUtils.getAnnotation(build.getClass(), field);
			for (Annotation annotation : annotations) {
				System.out.println("> "+annotation.toString());
			}
		}
	}

	@Test
	public void testGetGeterName() {
		String seterName = ReflectionUtils.getGeterName("nome");
		assertTrue("getNome".equals(seterName));
	}

	@Test
	public void testGetSeterName() {
		String seterName = ReflectionUtils.getSeterName("nome");
		assertTrue("setNome".equals(seterName));
	}

	@Test
	public void testGetAnnotationClassFieldClassOfQextendsAnnotation() {
		Field field = ReflectionUtils.getField(Pessoa.builder().build(), "nome");
		Annotation annotation = ReflectionUtils.getAnnotation(Pessoa.class, field, Column.class);
		System.out.println("> "+annotation.toString());
	}

	@Test
	public void testGetValorLiteral() {
		
		Tipos t = new Tipos();
		t.setTipoDate(new Date());
		t.setTipoDouble(1.0);
		t.setTipoDouble2(1.0);
		t.setTipoEnum(MeuEnum.SIM);
		t.setTipoFloat(1.5F);
		t.setTipoFloat2(1.6F);
		t.setTipoInt(1);
		t.setTipoInt2(2);
		t.setTipoLong2(1L);
		t.setTipoLong(2L);
		t.setTipoShort((short) 1);
		t.setTipoShort2((short) 2);
		t.setTipoString("um texto");
		
		System.out.println(ReflectionUtils.getValorLiteral(t, "tipoDate"));
		System.out.println(ReflectionUtils.getValorLiteral(t, "tipoDouble"));
		System.out.println(ReflectionUtils.getValorLiteral(t, "tipoDouble2"));
		System.out.println(ReflectionUtils.getValorLiteral(t, "tipoEnum"));
		System.out.println(ReflectionUtils.getValorLiteral(t, "tipoFloat"));
		System.out.println(ReflectionUtils.getValorLiteral(t, "tipoFloat2"));
		System.out.println(ReflectionUtils.getValorLiteral(t, "tipoLong"));
		System.out.println(ReflectionUtils.getValorLiteral(t, "tipoLong2"));
		System.out.println(ReflectionUtils.getValorLiteral(t, "tipoShort"));
		System.out.println(ReflectionUtils.getValorLiteral(t, "tipoShort2"));
		System.out.println(ReflectionUtils.getValorLiteral(t, "tipoString"));
		
	}
	
	@Test
	public void testBuscarDiferencas() {
		
		Pessoa obj1 = new Pessoa();
		obj1.setAltura(1.7F);
		obj1.setAtivo(true);
		obj1.setFilhos(new ArrayList<Pessoa>());
		obj1.setGenero(Genero.MASCULINO);
		obj1.setId(1L);
		obj1.setMae(null);
		obj1.setNome("Pessoa 1");
		obj1.setSalario(1000.0);
		
		Pessoa obj2 = new Pessoa();
		obj2.setAltura(1.7F);
		obj2.setAtivo(true);
		obj2.setFilhos(new ArrayList<Pessoa>());
		obj2.setGenero(Genero.MASCULINO);
		obj2.setId(1L);
		obj2.setMae(null);
		obj2.setNome("Pessoa 1");
		obj2.setSalario(1000.0);
		
		MudancaConteudoDTO[] buscarDiferencas = ReflectionUtils.buscarDiferencas(obj1, obj2);
		System.out.println("Encontradas "+buscarDiferencas.length+" diferencas");
		for (MudancaConteudoDTO mudancaConteudoDTO : buscarDiferencas) {
			System.out.println("\t" + mudancaConteudoDTO);
		}
		
		obj2.setAltura(1.8F);
		buscarDiferencas = ReflectionUtils.buscarDiferencas(obj1, obj2);
		System.out.println("Encontradas "+buscarDiferencas.length+" diferencas");
		for (MudancaConteudoDTO mudancaConteudoDTO : buscarDiferencas) {
			System.out.println("\t" + mudancaConteudoDTO);
		}
		
		obj2.setAtivo(false);
		buscarDiferencas = ReflectionUtils.buscarDiferencas(obj1, obj2);
		System.out.println("Encontradas "+buscarDiferencas.length+" diferencas");
		for (MudancaConteudoDTO mudancaConteudoDTO : buscarDiferencas) {
			System.out.println("\t" + mudancaConteudoDTO);
		}
		
		obj2.getFilhos().add(obj1);
		buscarDiferencas = ReflectionUtils.buscarDiferencas(obj1, obj2);
		System.out.println("Encontradas "+buscarDiferencas.length+" diferencas");
		for (MudancaConteudoDTO mudancaConteudoDTO : buscarDiferencas) {
			System.out.println("\t" + mudancaConteudoDTO);
		}
		
		obj2.setGenero(Genero.FEMININO);
		buscarDiferencas = ReflectionUtils.buscarDiferencas(obj1, obj2);
		System.out.println("Encontradas "+buscarDiferencas.length+" diferencas");
		for (MudancaConteudoDTO mudancaConteudoDTO : buscarDiferencas) {
			System.out.println("\t" + mudancaConteudoDTO);
		}
		
		obj2.setId(2L);
		buscarDiferencas = ReflectionUtils.buscarDiferencas(obj1, obj2);
		System.out.println("Encontradas "+buscarDiferencas.length+" diferencas");
		for (MudancaConteudoDTO mudancaConteudoDTO : buscarDiferencas) {
			System.out.println("\t" + mudancaConteudoDTO);
		}
		
		obj2.setMae(obj1);
		buscarDiferencas = ReflectionUtils.buscarDiferencas(obj1, obj2);
		System.out.println("Encontradas "+buscarDiferencas.length+" diferencas");
		for (MudancaConteudoDTO mudancaConteudoDTO : buscarDiferencas) {
			System.out.println("\t" + mudancaConteudoDTO);
		}
		
		obj2.setNome("Pessoa 2");
		buscarDiferencas = ReflectionUtils.buscarDiferencas(obj1, obj2);
		System.out.println("Encontradas "+buscarDiferencas.length+" diferencas");
		for (MudancaConteudoDTO mudancaConteudoDTO : buscarDiferencas) {
			System.out.println("\t" + mudancaConteudoDTO);
		}
		
		obj2.setSalario(2000.0);
		buscarDiferencas = ReflectionUtils.buscarDiferencas(obj1, obj2);
		System.out.println("Encontradas "+buscarDiferencas.length+" diferencas");
		for (MudancaConteudoDTO mudancaConteudoDTO : buscarDiferencas) {
			System.out.println("\t" + mudancaConteudoDTO);
		}
		
		obj1.setMae(Pessoa.builder().id(1L).build());
		obj2.setMae(Pessoa.builder().id(1L).build());
		buscarDiferencas = ReflectionUtils.buscarDiferencas(obj1, obj2);
		System.out.println("Encontradas "+buscarDiferencas.length+" diferencas");
		for (MudancaConteudoDTO mudancaConteudoDTO : buscarDiferencas) {
			System.out.println("\t" + mudancaConteudoDTO);
		}
		
		obj1.setMae(Pessoa.builder().id(1L).build());
		obj2.setMae(Pessoa.builder().id(2L).build());
		buscarDiferencas = ReflectionUtils.buscarDiferencas(obj1, obj2);
		System.out.println("Encontradas "+buscarDiferencas.length+" diferencas");
		for (MudancaConteudoDTO mudancaConteudoDTO : buscarDiferencas) {
			System.out.println("\t" + mudancaConteudoDTO);
		}
		
	}
	
	@Test
	public void testBuscarDiferencas2() {
		
		Funcionario obj1 = new Funcionario();
		obj1.setId(1L);
		obj1.setNome("Teste 1");
		obj1.setCarro(Carro.builder().id(1L).placa("ABC-1234").build());
		
		Funcionario obj2 = new Funcionario();
		obj2.setId(1L);
		obj2.setNome("Teste 1");
		obj2.setCarro(Carro.builder().id(2L).placa("ABC-5678").build());
		
		MudancaConteudoDTO[] buscarDiferencas = ReflectionUtils.buscarDiferencas(obj1, obj2);
		System.out.println("Encontradas "+buscarDiferencas.length+" diferencas");
		for (MudancaConteudoDTO mudancaConteudoDTO : buscarDiferencas) {
			System.out.println("\t" + mudancaConteudoDTO);
		}
		
	}

	@Test
	public void testInject() {
		Pessoa build = Pessoa.builder().build();
		try {
			ReflectionUtils.inject(build, "nome", "João");
			assertTrue(ReflectionUtils.getValor(build, "nome").equals("João"));
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSetValue() {
		Pessoa build = Pessoa.builder().build();
		try {
			ReflectionUtils.setValue(build, "nome", "João");
			assertTrue(build.getNome().equals("João"));
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSetValues() {
		try {
			Pessoa mae = Pessoa.builder().build();
			List<Pessoa> asList = Arrays.asList(Pessoa.builder().build());
			Pessoa obj = ReflectionUtils.setValues(Pessoa.class, "id", 1L, "nome", "João", "altura", 1.2F, "salario", 1.0, "mae", mae, "filhos", asList);
			System.out.println(obj);
		} catch (InstantiationException | IllegalAccessException | NoSuchFieldException | SecurityException
				| IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetTipoGenericoRetorno() {
		Method method = ReflectionUtils.getMethod(Pessoa.class, "getFilhos");
		Class tipoGenericoRetorno = ReflectionUtils.getTipoGenericoRetorno(method);
		assertTrue(tipoGenericoRetorno == Pessoa.class);
	}

	@Test
	public void testGetTipoGenericoRetornoString() {
		Method method = ReflectionUtils.getMethod(Pessoa.class, "getFilhos");
		String tipoGenericoRetorno = ReflectionUtils.getTipoGenericoRetornoString(method);
		assertTrue(tipoGenericoRetorno.equals("List<Pessoa>"));
	}

	@Test
	public void testGetContemRetorno() {
		Method method = ReflectionUtils.getMethod(Pessoa.class, "getFilhos");
		boolean contemRetorno = ReflectionUtils.getContemRetorno(method);
		assertTrue(contemRetorno);
	}

	@Test
	public void testClone() {
		try {
			Pessoa obj1 = Pessoa.builder().altura(1.7f).ativo(true).filhos(new ArrayList<Pessoa>()).genero(Genero.MASCULINO).id(1L).mae(null).nome("Pessoa 1").salario(1000.0).build();
			Pessoa obj2 = ReflectionUtils.clone(obj1);
			assertTrue(obj1 != obj2);
			assertTrue(obj1.equals(obj2));
			assertTrue(ReflectionUtils.buscarDiferencas(obj1, obj2).length == 0);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testToString() {
		Pessoa obj1 = Pessoa.builder().altura(1.7f).ativo(true).filhos(new ArrayList<Pessoa>()).genero(Genero.MASCULINO).id(1L).mae(null).nome("Pessoa 1").salario(1000.0).build();
		String str = ReflectionUtils.toString(obj1);
		System.out.println(str);
	}
	
}
