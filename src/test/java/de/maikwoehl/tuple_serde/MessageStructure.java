/**
 * @author Maik Wöhl
 * @date 2021-08-12
 */
package de.maikwoehl.tuple_serde;

import java.io.Serializable;
import java.util.Objects;


/**
 * This is a data class of the schema MessageStructure.
 * 
 * It's usage is to provide a custom list of members, used to populate the json array for serialization and 
 * for extracting the objects from a json array on deserialization.
 * 
 * @author Maik Wöhl
 * 
 *
 */
public class MessageStructure extends ObjectContainer implements Serializable {
	private static final long serialVersionUID = -2579921802313002387L;
	
	@Item
	private Integer messageType;
	@Item
	private String messageSource;
	@Item
	private String messageURI;
	@Item
	private String messageDateTime;
	@Item
	private String messageUUID;
	@Item
	private String messageAction;
	@Item
	private Object messagePayload;
	
	public MessageStructure(Integer messageType, String messageSource, String messageURI, String messageDateTime, String messageUUID, String messageAction,
			Object messagePayload) {
		super(messageType, messageSource, messageURI, messageDateTime, messageUUID, messageAction, messagePayload, null, null, null);
	}

	@MemberName("messageType")
	public Integer getMessageType() {
		return Objects.isNull(messageType) ? (Integer) invokeMethod() : messageType;
	}
}
