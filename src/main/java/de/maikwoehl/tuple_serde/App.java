/**
 * @author Maik Wöhl
 * @date 2021-08-12
 */
package de.maikwoehl.tuple_serde;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        MessageStructure message = new MessageStructure(2020, null, null, null, null, null, null);
        System.out.println(message.getMessageType().toString());
    }
}
