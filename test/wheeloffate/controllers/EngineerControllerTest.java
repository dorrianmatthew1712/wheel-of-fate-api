package wheeloffate.controllers;

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

import wheeloffate.data.Engineer;
import wheeloffate.services.EngineerService;

public class EngineerControllerTest {
	@InjectMocks
	protected EngineerController underTest;

	@Mock
	EngineerService mockEngineerService;

	List<Engineer> engineers = new ArrayList<Engineer>();

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		when(mockEngineerService.getEngineers()).thenReturn(new ArrayList<Engineer>());
	}

	@Test
	public void shouldCallEngineerServiceWhenCalled() {
		underTest.getEngineers();
		verify(mockEngineerService, times(1)).getEngineers();
	}

}
