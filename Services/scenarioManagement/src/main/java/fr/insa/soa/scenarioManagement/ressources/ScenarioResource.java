package fr.insa.soa.scenarioManagement.ressources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.insa.soa.scenarioManagement.model.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/scenario")
public class ScenarioResource {
	
	// SCENARIO entre 7h et 20h TEMP < 15°C => allumer les radiateurs d'une pièce 
	//Implémentation des horaires de fonctionnement de chaque scénario dans le service global 
	//Récupere une les infos d'une salle et si la temp<15, allume les chauffages avec un POST de 1
	public void setHeating(Classroom classroom) {
		Sensor t = classroom.getTemp();
		ArrayList<Actuator> h_list= classroom.getHeating();
		if (t.getValue()<15){
			for (int i=0; i< h_list.size();i++){ 
				h_list.get(i).postDataOM2M(1);
			}
		}
	}
}
