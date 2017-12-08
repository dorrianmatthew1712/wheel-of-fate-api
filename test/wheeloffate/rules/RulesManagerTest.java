package wheeloffate.rules;

import static org.hamcrest.CoreMatchers.equalTo;

import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class RulesManagerTest {
	
	@InjectMocks
	protected RulesManager underTest;

	@Rule
	public ErrorCollector collector = new ErrorCollector();

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldReturnCorrectBAURules() {
		List<IBauRule> rules = underTest.getRules();
		collector.checkThat(rules.size(), equalTo(2));
		
		collector.checkThat(rules.get(0).getClass(), equalTo(ConsecutiveDayRule.class));
		collector.checkThat(rules.get(1).getClass(), equalTo(MaxShiftsPerDayRule.class));
	}

}
