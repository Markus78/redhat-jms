package jms;

import java.io.Serializable;

public class MyMessage implements Serializable {

	final String description;
	final int id;

	public MyMessage(String description, int id) {
		this.description = description;
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public int getId() {
		return id;
	}

}
