/**
 * @author Maik Wöhl
 * @date 2021-08-12
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
 * @brief This annotation makes the MemberName available to internal use of the method.
 * @author mwoehl
 * @date 2021-08-12
 */
public @interface MemberName {
	String[] value(); /**< @brief A string value passed as member field name reference. */
}
