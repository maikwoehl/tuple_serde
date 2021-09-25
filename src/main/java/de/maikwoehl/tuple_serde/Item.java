/**
 * @author Maik Wöhl
 * @date 2021-08-12
 */
package de.maikwoehl.tuple_serde;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @brief This annotation helps finding the items index.
 * 
 * @author Maik Wöhl
 * @date 2021-08-12
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface Item {

}
