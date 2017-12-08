package wheeloffate.rules;

import java.util.List;

import wheeloffate.models.Shift;

public interface IBauRule {
	boolean isValid(int shiftId, long employeeId, List<Shift> shifts);
}
