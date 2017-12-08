package wheeloffate.rules;

import java.util.List;

import wheeloffate.models.Shift;

public class MaxShiftsPerDayRule implements IBauRule {

	@Override
	public boolean isValid(int shiftId, long employeeId, List<Shift> shifts) {
		boolean isValid = true;
		
		if (shifts.stream().filter(engineer -> engineer.getEngineer() != null).count() == 0) {
			isValid = true;
		} else if (shiftId % 2 == 1) {
			isValid = shifts.get(shiftId - 1).getEngineer() != null ? shifts.get(shiftId - 1).getEngineer().getId() != employeeId : false;
		} 
		
		return isValid;
	}

}
