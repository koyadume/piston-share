package in.koyad.piston.common.util;

import in.koyad.piston.common.constants.MsgType;
import lombok.Getter;


public class Message {
	public MsgType type;
	
	@Getter
	public String details;
	
	public Message(MsgType type, String details) {
		this.type = type;
		this.details = details;
	}
	
	public String getType() {
		return type.toString();
	}
}
