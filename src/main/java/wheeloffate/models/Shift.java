package wheeloffate.models;

import wheeloffate.data.Engineer;

public class Shift {
	
	private int id;
	private Engineer engineer;

	public Shift(int id) {
		this.id = id;
	}
	
	public Shift(int id, Engineer engineer) {
		this.id = id;
		this.engineer = engineer;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Engineer getEngineer() {
		return this.engineer;
	}

	public void setEngineer(Engineer engineer) {
		this.engineer = engineer;
	}
}
