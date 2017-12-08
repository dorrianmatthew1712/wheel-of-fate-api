package wheeloffate.services;

import static org.hamcrest.CoreMatchers.equalTo;
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

import wheeloffate.models.Shift;
import wheeloffate.rules.IBauRule;
import wheeloffate.rules.RulesManager;

public class RulesServiceTest {

	@InjectMocks
	RulesService underTest;

	@Mock
	RulesManager mockRulesManager;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Rule
	public ErrorCollector collector = new ErrorCollector();

	@Test
	public void shouldCallRulesManagerToGetRules() {
		underTest.isValid(1, 1, new ArrayList<Shift>());
		verify(mockRulesManager, times(1)).getRules();
	}

	@Test
	public void shouldFlagValidIfNoRulesFound() {
		when(mockRulesManager.getRules()).thenReturn(new ArrayList<IBauRule>());
		boolean result = underTest.isValid(1, 1, new ArrayList<Shift>());
		collector.checkThat(result, equalTo(true));
	}
}
