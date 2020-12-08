package fr.insa.soa.scenarioManagement.ressources;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.insa.soa.scenarioManagement.model.*;

import java.util.ArrayList;
import java.util.Calendar;

@RestController
@RequestMapping("/scenario")
public class ScenarioResource {
	
	// SCENARIO entre 7h et 20h TEMP < 15°C => allumer les radiateurs d'une pièce 
	//Implémentation des horaires de fonctionnement de chaque scénario dans le service global 
	//Récupere une les infos d'une salle et si la temp<15, allume les chauffages avec un POST de 1
	
	@GetMapping("/heatingON")
	public int setHeating(Classroom classroom) {
		Sensor t = classroom.getTemp();
		ArrayList<Actuator> h_list= classroom.getHeating();
		if (t.getValue()<15 && h_list.get(0).getValue()!=1){
			for (int i=0; i< h_list.size();i++){ 
				h_list.get(i).postDataOM2M(1);
			}
		}else if (t.getValue()>20 && h_list.get(0).getValue()!=0){
			for (int i=0; i< h_list.size();i++){ 
				h_list.get(i).postDataOM2M(0);
			}
		}
		return 0;
	}
	
/*	
	// SCENARIO entre 7h et 20h TEMP > 20 °C => éteindre les radiateurs d'une pièce 
	@GetMapping("/heatingOFF")
	public static void turnoffHeating(Classroom classroom) {
		Sensor t = classroom.getTemp();
		ArrayList<Actuator> h_list= classroom.getHeating();
		if (t.getValue()>20){
			for (int i=0; i< h_list.size();i++){ 
				h_list.get(i).postDataOM2M(0);
			}
		}
	}
*/	
	// SCENARIO entre 7h et 20h 18°C<TEMP<35°C => ouvrture des fenetres
	@GetMapping("/windowsON")
	public void openWindows(Classroom classroom) {
		Sensor t = classroom.getTemp();
		ArrayList<Actuator> w_list= classroom.getWindows();
		if (t.getValue()<35 && t.getValue()>18 && w_list.get(0).getValue()!=1){
			for (int i=0; i< w_list.size();i++){ 
				w_list.get(i).postDataOM2M(1);
			}
		}
	}
	


	// SCENARIO entre 18h et 9h Présence --> Allumer les lumières
	@GetMapping("/presence")
	public void PresenceDetection(Classroom classroom) {
		Sensor p; 
		ArrayList<Actuator> l_list= classroom.getLights();
		//while(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)>18 && Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<9){
		p = classroom.getPresence();
		if (p.getValue()==1 && l_list.get(0).getValue()!=1){
			for (int i=0; i< l_list.size();i++){ 
				l_list.get(i).postDataOM2M(1);
			}
		}else if(p.getValue()==0 && l_list.get(0).getValue()!=0){
			for (int i=0; i< l_list.size();i++){ 
					l_list.get(i).postDataOM2M(0);
			}
		}
		
	}
	
	//SCENARIO entre 22h et 6h Présence --> Allumer l'alarme
	@GetMapping("/alarm")
	public void setAlarmIfPresence(Classroom classroom){
		Actuator alarm = new Actuator("http://localhost:8080/in-cse/Alarme/DATA","Alarme",0);
		// ici j'ai définis l'alarme je sais pas si c'était nécessaire;
		Sensor p;
		//while(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)>22 && Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<6){
		p = classroom.getPresence();
		if (p.getValue()==1 && alarm.getValue()!=1){
			alarm.postDataOM2M(1);
		}
		//}
	}
	
	
	// ACTION Jours non travail et hors horaires travail + Non présence --> Eteindre Chauffage, Lumières,
	// Portes et Fenetres
	@GetMapping("/OFF")
	public void TurnOffEverything(Classroom classroom){
		//Sensor p = classroom.getPresence();
		ArrayList<Actuator> l_list= classroom.getLights();
		ArrayList<Actuator> w_list= classroom.getWindows();
		ArrayList<Actuator> d_list= classroom.getDoors();
		ArrayList<Actuator> h_list= classroom.getHeating();
		//if ((!DayHour.isItWorkHours() || DayHour.isItWorkDay()) && p.getValue()==0){
		if(l_list.get(0).getValue()!=0){	
			for (int i=0; i< l_list.size();i++){ 
					l_list.get(i).postDataOM2M(0);
				}
		}
		if(w_list.get(0).getValue()!=0){	
			for (int i=0; i< w_list.size();i++){ 
				w_list.get(i).postDataOM2M(0);
			}
		}
		if(d_list.get(0).getValue()!=0){
			for (int i=0; i< d_list.size();i++){ 
				d_list.get(i).postDataOM2M(0);
			}
		}
		if(h_list.get(0).getValue()!=0){
			for (int i=0; i< h_list.size();i++){ 
				h_list.get(i).postDataOM2M(0);
			}
		}	
	}
}
