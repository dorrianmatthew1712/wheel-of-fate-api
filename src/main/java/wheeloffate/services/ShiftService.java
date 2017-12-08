package wheeloffate.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import wheeloffate.data.Engineer;
import wheeloffate.models.Shift;

@Component
public class ShiftService {
	
	@Autowired
	RulesService rulesService;
	
	public ArrayList<Shift> createShifts(EngineerPool engineerPool, int shiftsPerPeriod) {
		ArrayList<Shift> shifts = new ArrayList<Shift>();
		
        for(int i = 0; i < shiftsPerPeriod; i++) {
            shifts.add(new Shift(i));
        }

        Engineer engineer;
        while((engineer = engineerPool.getRandomEngineer()) != null)
        {
            for (int i = 0; i < shiftsPerPeriod; i++)
            {
                if (shifts.get(i).getEngineer() == null)
                {
                    boolean foundSuitableSlot = rulesService.isValid(i, engineer.getId(), shifts);
                    if (foundSuitableSlot)
                    {
                        shifts.get(i).setEngineer(engineer);
                        engineerPool.remove(engineer);
                        break;
                    }
                }
            }
        }
		

		return shifts;
	}
}
