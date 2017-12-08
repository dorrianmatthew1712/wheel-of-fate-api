package wheeloffate.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import wheeloffate.data.Engineer;
import wheeloffate.data.EngineerRepository;

@Component
public class EngineerService {

	@Autowired
	EngineerRepository engineerRepository;

	public List<Engineer> getEngineers() {
		return engineerRepository.findAll();
	}
}
