package wheeloffate.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import wheeloffate.data.Engineer;

@Component
public class EngineerPool {

	private List<Engineer> _engineers;
	private List<Engineer> _availableEngineers;
	private Random _random;
	
	public EngineerPool() {
		_engineers = new ArrayList<Engineer>();
		_availableEngineers = new ArrayList<Engineer>();
		_random = new Random();
	}
	
	public void addEngineers(List<Engineer> engineers) {
		_engineers.addAll(engineers);
		_availableEngineers.addAll(engineers);
	}
	
	public Engineer getRandomEngineer() {
		Engineer engineer = null;
		
		if (!_availableEngineers.isEmpty()) {
			engineer = _availableEngineers.get(_random.nextInt(_availableEngineers.size()));
			_availableEngineers.remove(engineer);
		} 
		
		return engineer;
	}
	
	public void remove(Engineer engineer) {
		_engineers.remove(engineer);
    }
}
