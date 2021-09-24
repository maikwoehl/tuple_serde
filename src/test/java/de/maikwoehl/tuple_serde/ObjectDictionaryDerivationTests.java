package de.maikwoehl.tuple_serde;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

import org.junit.Test;

public class ObjectDictionaryDerivationTests 
{
    @Test
    public void canCreateWithOneItem()
    {
        MessageStructure msg = new MessageStructure(2020, null, null, null, null, null, null);
        assertEquals((Integer)2020, msg.getMessageType());
        
		try {
			Field messageType = msg.getClass().getDeclaredField("messageType");
	        messageType.setAccessible(true);
	        assertEquals((Integer)2020, (Integer)messageType.get(msg));
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Test
    public void itemContentIsPulledIntoChild() {
    	MessageStructure msg = new MessageStructure(2020, null, null, null, null, null, null);
        msg.getMessageType();
        try {
			Field messageType = msg.getClass().getDeclaredField("messageType");
	        messageType.setAccessible(true);
	        assertEquals((Integer)2020, (Integer)messageType.get(msg));
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        assertEquals((Integer)2020, (Integer)msg.getMessageType());
    }
}
