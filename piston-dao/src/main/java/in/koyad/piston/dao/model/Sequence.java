package in.koyad.piston.dao.model;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Sequence extends AbstractEntity {

	private String name;
	private int value;

	//required for JPA
	private Sequence() {
		
	}
	
	public Sequence(String name, int value) {
		this.name = name;
		this.value = value;
	}
}
