package in.koyad.piston.common.util;

import lombok.Data;
import lombok.Getter;

@Data
public class Property {
	@Getter
	private String key;
	
	@Getter
	private String value;
	
	public Property(String key, String value) {
		this.key = key;
		this.value = value;
	}
}
