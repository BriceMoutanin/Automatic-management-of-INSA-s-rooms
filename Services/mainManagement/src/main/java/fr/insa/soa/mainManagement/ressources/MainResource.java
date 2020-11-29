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
	public void getDay(){
		Calendar cal = Calendar.getInstance();
		int dayOfWeek= cal.get(Calendar.DAY_OF_WEEK);
		System.out.println(dayOfWeek);
		int hourOfDay= cal.get(Calendar.HOUR_OF_DAY);
		System.out.println(hourOfDay);
		
		// HORAIRES OU LE GEI EST OUVERT 
		if(dayOfWeek!=1 && dayOfWeek!=7){
			System.out.println("It's a week day youhou");
			if(hourOfDay>7 && hourOfDay<20){
				System.out.println("ouiiiiii");
				//Lancement des scenarios qui nous intéressent
			}
		}	
		
	}
}
