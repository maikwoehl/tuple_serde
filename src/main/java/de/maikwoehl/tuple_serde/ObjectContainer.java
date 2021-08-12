/**
 * 
 */
package de.maikwoehl.tuple_serde;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author mwoehl
 *
 */
public class ObjectContainer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4788686458506140083L;
	
	/**
	 * Method 2 on stack trace should be the real caller as we have a wrapper method for error handling.
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
	
	/** Mapping of items */
	protected final List<String> mappings;
	
	public ObjectContainer(List<String> mappings, Object item0, Object item1, Object item2, Object item3, Object item4, Object item5, Object item6)
	{
		this.item0 = item0;
		this.item1 = item1;
		this.item2 = item2;
		this.item3 = item3;
		this.item4 = item4;
		this.item5 = item5;
		this.item6 = item6;
		
		this.mappings = mappings;
	}
	
	
	private Integer getIndexNameFromMappings(String memberName) {
		return mappings.indexOf(memberName);
	}
	
	private String getGetterNameOfMember(String memberName) {
		return "getItem" + getIndexNameFromMappings(memberName).toString();
	}
	
	private Object invokeMethodUnsafe() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		/* Get method name of invoking method from stack trace. As this is the last one, and we have a
		 * wrapper method, it should be method 2 in the list.
		 */
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement e = stacktrace[METHOD_ON_STACK_TRACE];
		String methodName = e.getMethodName();
		System.out.println("MethodName: " + methodName);
		
		MemberName memberName = null;
		Method callerMethod = null;
		Method getterMethod = null;
		
		try {
			System.out.println("Scope class: " + this.getClass().getName());
			System.out.println("Parent class: " + this.getClass().getSuperclass().getName());
			callerMethod = this.getClass().getDeclaredMethod(methodName, (Class<?>[]) null);
			System.out.println("Caller: " + callerMethod.getName());
		} catch (NoSuchMethodException | SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (callerMethod.isAnnotationPresent(MemberName.class)) {
			memberName = callerMethod.getAnnotation(MemberName.class);
			System.out.println("MemberName: " + memberName.value()[0]);
		}
		
		try {
			String getterName = getGetterNameOfMember(memberName.value()[0]);
			System.out.println("Getter: " + getterName);
			System.out.println("Methods in base: ");
			for (Method method : this.getClass().getSuperclass().getDeclaredMethods()) {
				System.out.println(" - " + method.getName());
			}
			getterMethod = this.getClass().getSuperclass().getDeclaredMethod(getterName, (Class<?>[]) null);
		} catch (NoSuchMethodException | SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("Execute: " + getterMethod.getName());
		return getterMethod.invoke(this, (Object[]) null);
	}

	protected Object invokeMethod() {
		Object value = null;
		try {
			value = invokeMethodUnsafe();
		} catch (IllegalAccessException e) {
			System.err.println("Can't access underlying item.");
		} catch (IllegalArgumentException e) {
			System.err.println("Can't invoke with zero objects.");
		} catch (InvocationTargetException e) {
			System.err.println("Can't invoke getter.");
		}
		return value;
	}
	
	/* Stub methods */
	
	protected Object getItem0() {
		return item0;
	}
}
