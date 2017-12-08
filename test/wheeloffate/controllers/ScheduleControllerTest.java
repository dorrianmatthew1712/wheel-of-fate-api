package wheeloffate.controllers;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import wheeloffate.data.Engineer;
import wheeloffate.models.Schedule;
import wheeloffate.models.Shift;
import wheeloffate.services.ScheduleService;

public class ScheduleControllerTest {
	@InjectMocks
	protected ScheduleController underTest;

	@Mock
	ScheduleService mockScheduleService;

	List<Engineer> engineers = new ArrayList<Engineer>();

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		when(mockScheduleService.createSchedule(any(int.class), any(int.class))).thenReturn(new Schedule(new ArrayList<Shift>()));
		
		ReflectionTestUtils.setField(underTest, "shiftsPerPeriod", 10);
		ReflectionTestUtils.setField(underTest, "maxShiftsPerEngineer", 2);
	}
	
	@Test
	public void shouldCallEngineerServiceWhenCalled() {
		underTest.createSchedule();
		verify(mockScheduleService, times(1)).createSchedule(10, 2);
	}

}
