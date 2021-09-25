/**
 * @author Maik Wöhl
 * @date 2021-08-12
 */
package de.maikwoehl.tuple_serde;

import java.io.Serializable;
import java.util.Objects;

import de.vdv.vdv463.ProvideChargingInformationRequest;


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
	private ProvideChargingInformationRequest messagePayload;
	
	public MessageStructure(Integer messageType, String messageSource, String messageURI, String messageDateTime, String messageUUID, String messageAction,
			ProvideChargingInformationRequest messagePayload) {
		super(messageType, messageSource, messageURI, messageDateTime, messageUUID, messageAction, messagePayload, null, null, null);
	}

	@MemberName("messageType")
	public Integer getMessageType() {
		return Objects.isNull(messageType) ? (Integer) invokeMethod() : messageType;
	}
	
	@MemberName("messageSource")
	public String getMessageSource() {
		return Objects.isNull(messageSource) ? (String) invokeMethod() : messageSource;
	}
	
	@MemberName("messageURI")
	public String getMessageURI() {
		return Objects.isNull(messageURI) ? (String) invokeMethod() : messageURI;
	}
	
	@MemberName("messageDateTime")
	public String getMessageDateTime() {
		return Objects.isNull(messageDateTime) ? (String) invokeMethod() : messageDateTime;
	}
	
	@MemberName("messageUUID")
	public String getMessageUUID() {
		return Objects.isNull(messageUUID) ? (String) invokeMethod() : messageUUID;
	}
	
	@MemberName("messageAction")
	public String getMessageAction() {
		return Objects.isNull(messageAction) ? (String) invokeMethod() : messageAction;
	}
	
	@MemberName("messagePayload")
	public ProvideChargingInformationRequest getMessagePayload() {
		return Objects.isNull(messagePayload) ? (ProvideChargingInformationRequest) invokeMethod() : messagePayload;
	}
}
