package fr.insa.soa.scenarioManagement.ressources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
				for (int i=0; i<15;i++){
					for (Classroom c:lc) {
						if (DayHour.isItWorkDay() && DayHour.isItWorkHours()){
							sr.setHeating(c); 
							System.out.println("Heating handled");
						
							sr.openWindows(c);
							System.out.println("Windows handled");
						}
						
						if(DayHour.isItWorkDay() && DayHour.isItDark()){
							sr.PresenceDetectionON(c);
							System.out.println("Presence turn lights ON handled");
						}
						
						if(DayHour.isItForbidden() || !DayHour.isItWorkDay()){
							sr.setAlarmIfPresence(c);
							System.out.println("Alarm handled");
						}
					}
				TimeUnit.SECONDS.sleep(20);
				}
				for (Classroom c:lc) {
					if(!DayHour.isItWorkDay() || !DayHour.isItWorkHours()){
						sr.TurnOffEverything(c);
						System.out.println("Turning everything off handled");
					}
					if(DayHour.isItWorkDay() && DayHour.isItDark()){
						sr.PresenceDetectionOFF(c);
						System.out.println("Presence turn lights OFF handled");
					}
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

