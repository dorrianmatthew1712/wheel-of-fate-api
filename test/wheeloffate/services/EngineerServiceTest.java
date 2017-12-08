package wheeloffate.services;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import wheeloffate.data.EngineerRepository;

public class EngineerServiceTest {
	@InjectMocks
	EngineerService underTest;

	@Mock
	EngineerRepository mockEngineerRepository;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldCallRulesManagerToGetRules() {
		underTest.getEngineers();
		verify(mockEngineerRepository, times(1)).findAll();
	}
}
