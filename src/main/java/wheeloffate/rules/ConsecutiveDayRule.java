package wheeloffate.rules;

import java.util.List;

import wheeloffate.models.Shift;

public class ConsecutiveDayRule implements IBauRule {

	@Override
	public boolean isValid(int shiftId, long employeeId, List<Shift> shifts) {
		boolean isValid = true;
		
		if (shiftId  < 2) {
			isValid = true;
		} else  {
			if (shiftId % 2 == 0) {
				if (checkIfShiftEmployee(shifts.get(shiftId - 1), employeeId) || checkIfShiftEmployee(shifts.get(shiftId - 2), employeeId)) {
					isValid = false;
				}
			} else {
				if (checkIfShiftEmployee(shifts.get(shiftId - 2), employeeId) || checkIfShiftEmployee(shifts.get(shiftId - 3), employeeId)) {
					isValid = false;
				}
			}
		}
		
		return isValid;
	}
	
	private boolean checkIfShiftEmployee(Shift shift, long employeeId) {
		return shift.getEngineer() != null ? shift.getEngineer().getId() == employeeId : false;
	}
}
