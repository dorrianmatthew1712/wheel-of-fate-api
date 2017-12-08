package wheeloffate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wheeloffate.models.Schedule;
import wheeloffate.services.ScheduleService;

@RestController
public class ScheduleController {
	
	@Value("${shiftsPerPeriod}")
	private int shiftsPerPeriod;
	
	@Value("${maxShiftsPerEngineer}")
	private int maxShiftsPerEngineer;
	
	@Autowired
	ScheduleService scheduleService;
    
    @RequestMapping("/create-schedule")
    public Schedule createSchedule() {
        return scheduleService.createSchedule(shiftsPerPeriod, maxShiftsPerEngineer);
    }
}
