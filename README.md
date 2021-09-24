# tuple_serde

You can use the ObjectContainer to create your own tuple-like object.

To show how to use it, some boilerplate code is necessary to do some checks:

```java
import java.io.Serializable;
import java.util.Objects;
 
import de.maikwoehl.tuple_serde;
 
public class MessageStructure extends ObjectContainer implements Serializable {
   private static final long serialVersionUID = 0L;
   
   @Item
   private Integer messageType;
   
   @Item
   private String messageSource;
   
   @Item 
   private String messagePayload;
   
   public MessageStructure(Integer messageType, String messageSource, String messagePayload) {
       super(messageType, messageSource, messagePayload, null, null, null, null, null, null, null);
   }
   
   @MemberName("messageType")
   public Integer getMessageType() {
       return Objects.isNull(messageType) ? (Integer) invokeMethod() : messageType;
   }
   
   @MemberName("messageSource")
   public String getMessageSource() {
       return Objects.isNull(messageSource) ? (String) invokeMethod() : messageSource;
   }
   
   @MemberName("messagePayload")
   public String getMessagePayload() {
       return Objects.isNull(messagePayload) ? (String) invokeMethod() : messagePayload;
   }
 }
 ```
 
 So you can use this as a template for code generation:
 
```java
import java.io.Serializable;
import java.util.Objects;
 
import de.maikwoehl.tuple_serde;

/* template <T..N> */
public class [[SchemaName]] extends ObjectContainer implements Serializable {
    private static final long serialVersionUID = 0L;
       
    /* [ for T, member in member_fields ] */
    @Item
    private T member;
    /* [ end for] */
       
    public [[SchemaName]](
        /* [ for T, member in member_fields ] */
        T member, 
        /* [ end for] */
    ) {
        super(
            /* [ for i in range(1..len(member_fields)) ] */        
                /* [ try; T, member = member_fields.at(i) ] */
                member,
                /* [ catch IndexOutOfBounds ] */
                null,
                /* [ end try ] */
            /* [ end for] */
        );
    }
       
    /* [ for T, member in member_fields ] */
    @MemberName(member)
    public Integer get[[ member | uppercase ]]() {
        return Objects.isNull(member) ? (T) invokeMethod() : member;
    }
    /* [ end for] */
}
```