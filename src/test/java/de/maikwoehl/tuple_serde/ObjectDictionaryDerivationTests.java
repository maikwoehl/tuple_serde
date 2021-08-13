package de.maikwoehl.tuple_serde;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ObjectDictionaryDerivationTests 
{
    @Test
    public void canCreateWithOneItem()
    {
        MessageStructure msg = new MessageStructure(2020, null, null, null, null, null, null);
        assertEquals((Integer)2020, msg.getMessageType());
    }
}
