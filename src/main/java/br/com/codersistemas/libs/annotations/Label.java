/**
 * 
 */
package br.com.codersistemas.libs.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Anotação utilitária para defirnir nome a um Atributo ou método.
 *  
 * @author gustavo.dasilva@castgroup.com.br
 *
 */
@Target(value={FIELD, METHOD})
@Retention(value=RUNTIME)
public @interface Label {

	String name();
	boolean descricao() default false;

}
