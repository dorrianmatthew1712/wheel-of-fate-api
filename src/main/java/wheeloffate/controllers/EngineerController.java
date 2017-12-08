package wheeloffate.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wheeloffate.data.Engineer;
import wheeloffate.services.EngineerService;

@RestController
public class EngineerController {

	@Autowired
	EngineerService engineerService;
    
    @RequestMapping("/engineers")
    public List<Engineer> getEngineers() {
        return engineerService.getEngineers();
    }
}
