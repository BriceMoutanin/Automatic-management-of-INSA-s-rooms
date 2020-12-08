package fr.insa.soa.mainManagement.ressources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Calendar;

import fr.insa.soa.mainManagement.model.*;
 
@RestController
@RequestMapping("/main")
public class MainResource {
	
	@GetMapping("/b")
	public void ScenarioLaunch(){
		String portScenarios = "http://localhost:8082/scenario/";
		RestTemplate restTemplate= new RestTemplate();
		
		if (DayHour.isItWorkDay()){
			restTemplate.getForObject(portScenarios+"heatingON", Integer.class);
		}
	}
}
