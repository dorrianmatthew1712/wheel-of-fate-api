package wheeloffate.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import wheeloffate.data.Engineer;
import wheeloffate.data.EngineerRepository;
import wheeloffate.models.Schedule;
import wheeloffate.models.Shift;

public class ScheduleServiceTest {

	@InjectMocks
	ScheduleService underTest;

	@Mock
	EngineerRepository mockEngineerRepository;
	
	@Mock
	ShiftService mockShiftService;
	
	ArrayList<Shift> shifts = new ArrayList<Shift>();
	
	int numShifts = 10;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		when(mockEngineerRepository.findAll()).thenReturn(new ArrayList<Engineer>());
		
		for (int i = 0; i < numShifts; i++) {
			shifts.add(new Shift(i, new Engineer("Test", "Name", "Position")));
		}
		
		when(mockShiftService.createShifts(any(EngineerPool.class), any(int.class))).thenReturn(shifts);
	}

	@Rule
	public ErrorCollector collector = new ErrorCollector();
	
	@Test
	public void shouldCallRepositoryCorrectNumberOfTimes() {
		underTest.createSchedule(numShifts, 2);
		verify(mockEngineerRepository, times(2)).findAll();
	}
	
	@Test
	public void shouldReturnAShiftList() {
		Schedule schedule = underTest.createSchedule(numShifts, 2);
		collector.checkThat(schedule.getCreatedDate(), notNullValue());
		collector.checkThat(schedule.getShifts().size(), equalTo(numShifts));
	}

}
