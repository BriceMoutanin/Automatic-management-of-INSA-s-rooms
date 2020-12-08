package fr.insa.soa.scenarioManagement.ressources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import fr.insa.soa.scenarioManagement.model.*;
import fr.insa.soa.scenarioManagement.ressources.ScenarioResource;

@RestController
@RequestMapping("/main")
public class MainResource {
	static ScenarioResource sr=new ScenarioResource();
	static String urlClassrooms = "http://localhost:8081/GEI/classrooms/info";
	static RestTemplate restTemplate= new RestTemplate();
	static DayHour time=new DayHour();
	public static void main(String[] args){
		while (true){
			try {
				ArrayList<Classroom> lc=restTemplate.getForObject(urlClassrooms, ListClassroom.class).getListCl();
				for (Classroom c:lc) {
					if (DayHour.isItWorkDay()){
					 sr.setHeating(c); 
					 System.out.println("hakunamatata");
					}
				}
				TimeUnit.SECONDS.sleep(20);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

