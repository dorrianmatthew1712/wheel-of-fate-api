package wheeloffate.services;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import wheeloffate.data.Engineer;

public class EngineerPoolTest {
	
	@InjectMocks
	EngineerPool underTest;

	@Rule
	public ErrorCollector collector = new ErrorCollector();

	List<Engineer> engineers = new ArrayList<Engineer>();
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldGetRandomEngineersFromListUntilEmpty() {
		engineers.add(new Engineer(1L, "Test 1", "Name 1", "Test Position"));
		engineers.add(new Engineer(2L, "Test 2", "Name 2", "Test Position"));
		engineers.add(new Engineer(3L, "Test 3", "Name 3", "Test Position"));
		
		// Set up engineers for test data
		underTest.addEngineers(engineers);
		
		for(int i = 0; i < engineers.size(); i++) {
			Engineer engineer = underTest.getRandomEngineer();
			collector.checkThat(engineer, notNullValue());
		}
	}
	
	@Test
	public void shouldGetNullEngineersFromListIfEmpty() {
		Engineer engineer = underTest.getRandomEngineer();
		collector.checkThat(engineer, nullValue());
	}
	
	@Test
	public void shouldRemoveEngineer() {
		engineers.clear();
		
		engineers.add(new Engineer(3L, "Test 3", "Name 3", "Test Position"));
		underTest.addEngineers(engineers);
		
		Engineer engineer = underTest.getRandomEngineer();
		collector.checkThat(engineer, notNullValue());
		
		underTest.remove(engineer);
		
		engineer = underTest.getRandomEngineer();
		collector.checkThat(engineer, nullValue());
	}
}
