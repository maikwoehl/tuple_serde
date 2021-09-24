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
	
	private List<String> getMappings() {
		LinkedList<String> mappings = new LinkedList<String>();
		
		for (Field item : this.getClass().getDeclaredFields()) {
			if (item.isAnnotationPresent(Item.class)) {
				System.out.println("Insert " + item.getName() + " into mappings.");
				mappings.add(item.getName());
			}
		}
		
		return mappings;
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
		
		String memberName = null;
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
			memberName = (callerMethod.getAnnotation(MemberName.class)).value()[0];
			System.out.println("MemberName: " + memberName);
		}

		try {
			String getterName = getGetterNameOfMember(memberName);
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
