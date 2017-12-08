package wheeloffate.models;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class Schedule {
	
	private ArrayList<Shift> shifts;
	private Date createdDate;
	
	protected Schedule() {
	}

	public Schedule(ArrayList<Shift> shifts) {
		this.setShifts(shifts);
		this.setCreatedDate(Date.from(Instant.now()));
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public ArrayList<Shift> getShifts() {
		return shifts;
	}

	public void setShifts(ArrayList<Shift> shifts) {
		this.shifts = shifts;
	}
}
