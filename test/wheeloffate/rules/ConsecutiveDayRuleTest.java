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

public class ConsecutiveDayRuleTest {
	
	protected ConsecutiveDayRule underTest;

	@Rule
	public ErrorCollector collector = new ErrorCollector();
	
	List<Shift> shifts = new ArrayList<Shift>();

	@Before
	public void setup() {
		underTest = new ConsecutiveDayRule();
		
		shifts.add(new Shift(0, new Engineer(1L, "Test", "Name", "Position")));
		shifts.add(new Shift(1, new Engineer(2L, "Test", "Name", "Position")));
		shifts.add(new Shift(2, new Engineer(3L, "Test", "Name", "Position")));
		shifts.add(new Shift(3, new Engineer(4L, "Test", "Name", "Position")));
	}

	@Test
	public void shouldReturnValidIfShiftIdIsLessThan2() {
		collector.checkThat(underTest.isValid(0, 1, shifts), equalTo(true));
		collector.checkThat(underTest.isValid(1, 1, shifts), equalTo(true));
	}
	
	@Test
	public void shouldReturnValidIfEngineerIdDoesNotExistInCurrentShifts() {
		collector.checkThat(underTest.isValid(3, 4L, shifts), equalTo(true));
		collector.checkThat(underTest.isValid(2, 3L, shifts), equalTo(true));
	}
	
	@Test
	public void shouldReturnInvalidIfEngineerIdDoesExistInCurrentShifts() {
		collector.checkThat(underTest.isValid(3, 1L, shifts), equalTo(false));
		collector.checkThat(underTest.isValid(2, 2L, shifts), equalTo(false));
	}
}
