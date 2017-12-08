package wheeloffate.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import wheeloffate.data.EngineerRepository;
import wheeloffate.models.Schedule;
import wheeloffate.models.Shift;

@Component
public class ScheduleService {

	@Autowired
	EngineerRepository engineerRepository;

	@Autowired
	ShiftService shiftService;

	public Schedule createSchedule(int shiftsPerPeriod, int maxShiftsPerEngineer) {
		ArrayList<Shift> shifts = new ArrayList<Shift>();

		while (shifts.stream().filter(engineer -> engineer.getEngineer() != null).count() < shiftsPerPeriod) {
			EngineerPool engineerPool = createEngineerPool(maxShiftsPerEngineer);		
			shifts = shiftService.createShifts(engineerPool, shiftsPerPeriod);
		}

		return new Schedule(shifts);
	}

	private EngineerPool createEngineerPool(int maxShiftsPerEngineer) {
		EngineerPool engineerPool = new EngineerPool();
		
		for (int i = 0; i < maxShiftsPerEngineer; i++) {
			engineerPool.addEngineers(engineerRepository.findAll());
		}
		
		return engineerPool;
	}
}
