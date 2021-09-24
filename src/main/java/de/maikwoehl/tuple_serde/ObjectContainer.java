/**
 * @author Maik Wöhl
 * @date 2021-08-12
 */
package de.maikwoehl.tuple_serde;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mwoehl
 *
 */
public class ObjectContainer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4788686458506140083L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ObjectContainer.class);
	
	/**
	 * Method 3 on stack trace should be the real caller as we have a wrapper method for error handling.
	 */
	private static final int METHOD_ON_STACK_TRACE = 3;
	
	/* Private generic values with correct types */
	private final Object item0;
	private final Object item1;
	private final Object item2;
	private final Object item3;
	private final Object item4;	
	private final Object item5;	
	private final Object item6;
	private final Object item7;
	private final Object item8;
	private final Object item9;
	
	/** Mapping of items */
	protected final List<String> mappings;
	
	public ObjectContainer(Object item0, Object item1, Object item2, Object item3, Object item4, Object item5, Object item6, Object item7, Object item8, Object item9)
	{
		this.mappings = getMappings();
		
		this.item0 = item0;
		this.item1 = item1;
		this.item2 = item2;
		this.item3 = item3;
		this.item4 = item4;
		this.item5 = item5;
		this.item6 = item6;
		this.item7 = item7;
		this.item8 = item8;
		this.item9 = item9;
	}
	
	/**
	 * @brief Inserts mappings of index to member field name of derivating class.
	 * @return List of member field names.
	 */
	private List<String> getMappings() {
		LinkedList<String> mappings = new LinkedList<String>();
		
		for (Field item : this.getClass().getDeclaredFields()) {
			if (item.isAnnotationPresent(Item.class)) {
				LOGGER.debug("Insert " + item.getName() + " into mappings.");
				mappings.add(item.getName());
			}
		}
		
		return mappings;
	}
	
	/**
	 * @brief Returns the index of given member field name.
	 * 
	 * @param memberName member field name
	 * @return index in internal mapping
	 */
	private Integer getIndexNameFromMappings(String memberName) {
		return mappings.indexOf(memberName);
	}
	
	/**
	 * @brief Returns internal getter name of given member field name.
	 * 
	 * @param memberName member field name to get value of
	 * @return internal get method name
	 */
	private String getGetterNameOfMember(String memberName) {
		return "getItem" + getIndexNameFromMappings(memberName).toString();
	}
	
	/**
	 * Invokes internal getter method for caller.
	 * 
	 * @return returned object of invoked getter method
	 * @throws IllegalAccessException 		Access to underlying method/field is not allowed.
	 * @throws IllegalArgumentException		No method/field for caller method found.
	 * @throws InvocationTargetException	Method can't be invoked on internal target.
	 */
	private Object invokeMethodUnsafe() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		/* Get method name of invoking method from stack trace. As this is the last one, and we have a
		 * wrapper method, it should be method 2 in the list.
		 */
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement e = stacktrace[METHOD_ON_STACK_TRACE];
		String methodName = e.getMethodName();
		LOGGER.info("MethodName: " + methodName);
		
		String memberName = null;
		Method callerMethod = null;
		Method getterMethod = null;
		
		try {
			LOGGER.debug("Scope class: " + this.getClass().getName());
			LOGGER.debug("Parent class: " + this.getClass().getSuperclass().getName());
			callerMethod = this.getClass().getDeclaredMethod(methodName, (Class<?>[]) null);
			LOGGER.debug("Caller: " + callerMethod.getName());
		} catch (NoSuchMethodException | SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (callerMethod.isAnnotationPresent(MemberName.class)) {
			memberName = (callerMethod.getAnnotation(MemberName.class)).value()[0];
			LOGGER.debug("MemberName: " + memberName);
		}

		try {
			String getterName = getGetterNameOfMember(memberName);
			LOGGER.debug("Getter: " + getterName);
			LOGGER.debug("Methods in base: ");
			for (Method method : this.getClass().getSuperclass().getDeclaredMethods()) {
				LOGGER.debug(" - " + method.getName());
			}
			getterMethod = this.getClass().getSuperclass().getDeclaredMethod(getterName, (Class<?>[]) null);
		} catch (NoSuchMethodException | SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		LOGGER.info("Execute: " + getterMethod.getName());
		Object returnValue = getterMethod.invoke(this, (Object[]) null);
		try {
			Field memberField = this.getClass().getDeclaredField(memberName);
			memberField.setAccessible(true);
			memberField.set(this, returnValue);
		} catch (NoSuchFieldException | SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return returnValue;
	}

	/**
	 * @brief Invokes method of decorated MemberName of caller method.
	 * 
	 * @return object from internal data model
	 */
	protected Object invokeMethod() {
		Object value = null;
		try {
			value = invokeMethodUnsafe();
		} catch (IllegalAccessException e) {
			LOGGER.error("Can't access underlying item.");
		} catch (IllegalArgumentException e) {
			LOGGER.error("Can't invoke with zero objects.");
		} catch (InvocationTargetException e) {
			LOGGER.error("Can't invoke getter.");
		}
		return value;
	}
	
	/* Stub methods */
	
	@SuppressWarnings("unused")
	private Object getItem0() {
		return item0;
	}
	
	@SuppressWarnings("unused")
	private Object getItem1() {
		return item1;
	}
	
	@SuppressWarnings("unused")
	private Object getItem2() {
		return item2;
	}
	
	@SuppressWarnings("unused")
	private Object getItem3() {
		return item3;
	}
	
	@SuppressWarnings("unused")
	private Object getItem4() {
		return item4;
	}
	
	@SuppressWarnings("unused")
	private Object getItem5() {
		return item5;
	}
	
	@SuppressWarnings("unused")
	private Object getItem6() {
		return item6;
	}
	
	@SuppressWarnings("unused")
	private Object getItem7() {
		return item7;
	}
	
	@SuppressWarnings("unused")
	private Object getItem8() {
		return item8;
	}
	
	@SuppressWarnings("unused")
	private Object getItem9() {
		return item9;
	}
}
