package wheeloffate.rules;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class RulesManager {
	
	public List<IBauRule> getRules() {
		List<IBauRule> rules = new ArrayList<IBauRule>();
		rules.add(new ConsecutiveDayRule());
		rules.add(new MaxShiftsPerDayRule());
		return rules;
	}
	
}
