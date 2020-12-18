package fr.insa.soa.scenarioManagement.ressources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.soa.scenarioManagement.model.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/scenario")
public class ScenarioResource {
	
	// SCENARIO TEMP < 15°C => allumer les radiateurs d'une pièce 
	//Implémentation des horaires de fonctionnement de chaque scénario dans le service global
	
	//Récupere une les infos d'une salle et si la temp<15, allume les chauffages avec un POST de 1
	@GetMapping("/heating")
	public int setHeating(Classroom classroom) {
		Sensor t = classroom.getTemp();
		ArrayList<Actuator> h_list= classroom.getHeating();
		if (t.getValue()<15){
			for (int i=0;i<h_list.size();i++){
				if(h_list.get(i).getValue()!=1){
					h_list.get(i).postDataOM2M(1);
				}
			}
		}else if (t.getValue()>20){
			for (int j=0;j<h_list.size();j++){
				if(h_list.get(j).getValue()!=0){ 
					h_list.get(j).postDataOM2M(0);
				}
			}
		}
		return 0;
	}

	// SCENARIO 18°C<TEMP<35°C => ouvrture des fenetres
	@GetMapping("/windows")
	public void openWindows(Classroom classroom) {
		Sensor t = classroom.getTemp();
		ArrayList<Actuator> w_list= classroom.getWindows();
		if (t.getValue()<30 && t.getValue()>18){
			for (int i=0;i<w_list.size();i++){
				if(w_list.get(i).getValue()!=1){
					w_list.get(i).postDataOM2M(1);
				}
			}
		}else if (t.getValue()<15 || t.getValue()>35){
			for (int i=0;i<w_list.size();i++){
				if(w_list.get(i).getValue()!=0){
					w_list.get(i).postDataOM2M(0);
				}
			}
		}
	}
	

	// SCENARIO Présence --> Allumer les lumières
	@GetMapping("/presenceON")
	public void PresenceDetectionON(Classroom classroom) {
		Sensor p; 
		ArrayList<Actuator> l_list= classroom.getLights();
		p = classroom.getPresence();
		if (p.getValue()==1){
			for (int i=0;i<l_list.size();i++){
				if(l_list.get(i).getValue()!=1){
					l_list.get(i).postDataOM2M(1);
				}
			}
		}	
	}
	@GetMapping("/presenceOFF")
	public void PresenceDetectionOFF(Classroom classroom) {
		Sensor p; 
		ArrayList<Actuator> l_list= classroom.getLights();
		p = classroom.getPresence();
		if (p.getValue()==0){
			for (int i=0;i<l_list.size();i++){
				if(l_list.get(i).getValue()!=0){
					l_list.get(i).postDataOM2M(0);
				}
			}
		}
	}
	
	//SCENARIO Présence --> Allumer l'alarme
	@GetMapping("/alarm")
	public void setAlarmIfPresence(Classroom classroom){
		Actuator alarm = new Actuator("http://localhost:8080/in-cse/Alarme/DATA","Alarme",0);
		Sensor p = classroom.getPresence();
		if (p.getValue()==1 && alarm.getValue()!=1){
			alarm.postDataOM2M(1);
		}
	}
	
	
	// Non présence --> Eteindre Chauffage, Lumières, Portes et Fenetres
	@GetMapping("/OFF")
	public void TurnOffEverything(Classroom classroom){
		Sensor p = classroom.getPresence();
		ArrayList<Actuator> l_list= classroom.getLights();
		ArrayList<Actuator> w_list= classroom.getWindows();
		ArrayList<Actuator> d_list= classroom.getDoors();
		ArrayList<Actuator> h_list= classroom.getHeating();
		if (p.getValue()==0){
			for (int i=0;i<l_list.size();i++){
				if(l_list.get(i).getValue()!=0){	
					l_list.get(i).postDataOM2M(0);
				}
			}
			for (int i=0;i<w_list.size();i++){
				if(w_list.get(i).getValue()!=0){	
					w_list.get(i).postDataOM2M(0);
				}
			}
			for (int i=0;i<d_list.size();i++){
				if(d_list.get(i).getValue()!=0){
					d_list.get(i).postDataOM2M(0);
				}
			}
			for (int i=0;i<h_list.size();i++){
				if(h_list.get(i).getValue()!=0){
					h_list.get(i).postDataOM2M(0);
				}
			}
		}
	}
}
