package wheeloffate.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import wheeloffate.models.Shift;
import wheeloffate.rules.IBauRule;
import wheeloffate.rules.RulesManager;

@Component
public class RulesService {
	
	@Autowired
	RulesManager rulesManager;
	
	public boolean isValid(int shiftId, long employeeId, List<Shift> shifts) {
		boolean valid = true;
		
		for (IBauRule rule : rulesManager.getRules()) {
			valid = rule.isValid(shiftId, employeeId, shifts);
			if (!valid) {
				break;
			}
		}
		
		return valid;
	}
}
