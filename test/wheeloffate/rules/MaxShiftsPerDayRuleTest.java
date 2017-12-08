package wheeloffate.rules;

import static org.hamcrest.CoreMatchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import wheeloffate.data.Engineer;
import wheeloffate.models.Shift;

public class MaxShiftsPerDayRuleTest {

	protected MaxShiftsPerDayRule underTest;

	@Rule
	public ErrorCollector collector = new ErrorCollector();
	
	List<Shift> shifts = new ArrayList<Shift>();

	@Before
	public void setup() {
		underTest = new MaxShiftsPerDayRule();
		
		shifts.add(new Shift(0, new Engineer(1L, "Test", "Name", "Position")));
		shifts.add(new Shift(1, new Engineer(2L, "Test", "Name", "Position")));
		shifts.add(new Shift(2, new Engineer(3L, "Test", "Name", "Position")));
		shifts.add(new Shift(3, new Engineer(4L, "Test", "Name", "Position")));
	}
	
	@Test
	public void shouldReturnValidIfNoEngineersExist() {
		collector.checkThat(underTest.isValid(0, 1L, new ArrayList<Shift>()), equalTo(true));
	}
	
	@Test
	public void shouldReturnValidIfPreviousShiftIdIsNotTheSame() {
		collector.checkThat(underTest.isValid(3, 1L, new ArrayList<Shift>()), equalTo(true));
	}
	
	@Test
	public void shouldReturnInvalidIfPreviousShiftIdIsTheSame() {
		collector.checkThat(underTest.isValid(3, 3L, shifts), equalTo(false));
	}
}
