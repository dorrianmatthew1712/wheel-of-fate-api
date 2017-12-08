package wheeloffate.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import wheeloffate.data.Engineer;
import wheeloffate.models.Shift;

public class ShiftServiceTest {

	@InjectMocks
	protected ShiftService underTest;

	@Mock
	RulesService mockRulesService;

	List<Engineer> engineers = new ArrayList<Engineer>();

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		engineers.add(new Engineer(1L, "Test 1", "Name 1", "Test Position"));
		engineers.add(new Engineer(2L, "Test 2", "Name 2", "Test Position"));
		engineers.add(new Engineer(3L, "Test 3", "Name 3", "Test Position"));
	}

	@Rule
	public ErrorCollector collector = new ErrorCollector();

	@Test
	public void shouldCreateAShiftListOfTheCorrectSize() {
		EngineerPool engineerPool = new EngineerPool();
		List<Shift> result = underTest.createShifts(engineerPool, 10);
		collector.checkThat(result.size(), equalTo(10));
	}

	@Test
	public void shouldCreateAShiftListWithCorrectIDs() {
		EngineerPool engineerPool = new EngineerPool();
		List<Shift> result = underTest.createShifts(engineerPool, 10);
		collector.checkThat(result.size(), equalTo(10));

		for (int i = 0; i < result.size(); i++) {
			collector.checkThat(result.get(i).getId(), equalTo(i));
		}
	}

	@Test
	public void shouldCreateAShiftListOfEmptyEngineersIfNoAvailableEngineers() {
		EngineerPool engineerPool = new EngineerPool();
		List<Shift> result = underTest.createShifts(engineerPool, 10);
		collector.checkThat(result.size(), equalTo(10));

		for (Shift shift : result) {
			collector.checkThat(shift.getEngineer(), nullValue());
		}
	}

	@Test
	public void shouldCreateAShiftListWithAllEngineers() {
		when(mockRulesService.isValid(any(int.class), any(long.class), any())).thenReturn(true);

		EngineerPool engineerPool = new EngineerPool();
		engineerPool.addEngineers(engineers);
		List<Shift> result = underTest.createShifts(engineerPool, 3);
		verify(mockRulesService, times(3)).isValid(any(int.class), any(long.class), any());

		collector.checkThat(result.size(), equalTo(3));

		for (Shift shift : result) {
			collector.checkThat(shift.getEngineer(), notNullValue());
		}
	}

	@Test
	public void shouldCreateAShiftListOfEmptyEngineersIfRulesFlaggedInvalid() {
		when(mockRulesService.isValid(any(int.class), any(long.class), any())).thenReturn(false);

		EngineerPool engineerPool = new EngineerPool();
		engineerPool.addEngineers(engineers);

		List<Shift> result = underTest.createShifts(engineerPool, 3);
		collector.checkThat(result.size(), equalTo(3));

		for (Shift shift : result) {
			collector.checkThat(shift.getEngineer(), nullValue());
		}
	}

}
