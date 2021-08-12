/**
 * 
 */
package de.maikwoehl.tuple_serde;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target(METHOD)
@Inherited
/**
 * This annotation makes the MemberName available to internal use of the method.
 * @author mwoehl
 *
 */
public @interface MemberName {
	String[] value();
}
