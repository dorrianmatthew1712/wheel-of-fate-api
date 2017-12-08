package wheeloffate;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import wheeloffate.data.Engineer;
import wheeloffate.data.EngineerRepository;

/**
 	@author dorrianmatthew
 **/
@SpringBootApplication
@EntityScan("wheeloffate.data")
public class WheelOfFateApplication {

	@Autowired
	EngineerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(WheelOfFateApplication.class, args);
	}

	// For the purpose of the task, clearing and adding 10 engineers to the repository 
	// when the application starts
	@PostConstruct
	public void populateEngineers() {
		repository.deleteAll();
		
		List<Engineer> engineers = new ArrayList<Engineer>();
		engineers.add(new Engineer("Matthew", "Dorrian", "Senior Software Engineer"));
		engineers.add(new Engineer("Stephen", "Dorrian", "Licensing Manager"));
		engineers.add(new Engineer("Ryan", "Dorrian", "Senior Software Engineer"));
		engineers.add(new Engineer("Chris", "Murray", "Principal Software Engineer"));
		engineers.add(new Engineer("Angela", "Dorrian", "Delivery Lead"));
		engineers.add(new Engineer("Colm", "McKevitt", "Senior Software Engineer"));
		engineers.add(new Engineer("Graham", "Kelly", "Software Engineer"));
		engineers.add(new Engineer("Sarah", "Murray", "Product Manager"));
		engineers.add(new Engineer("David", "Best", "UX Designer"));
		engineers.add(new Engineer("Marc", "Barbour", "Principal Software Engineer"));
		
		repository.save(engineers);
	}

}
