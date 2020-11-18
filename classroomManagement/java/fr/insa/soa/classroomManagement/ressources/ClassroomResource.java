package fr.insa.soa.classroomManagement.ressources;
import fr.insa.soa.classroomManagement.model.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import org.springframework.http.MediaType;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/GEI")
public class ClassroomResource {
	ArrayList<Classroom> classrooms = new ArrayList<Classroom>(); 
	String[] ports = {"8181","8282"};
	String url="";
	
	
	public ArrayList<Integer> getData(RestTemplate rest, HttpEntity<String> entity, String ports, String Type){
		ArrayList<Integer> list=new ArrayList<Integer>();
		String url="http://localhost:"+ports+"?fu=1&lbl=Type/"+Type;
		ResponseEntity<String> response = rest.exchange(
						url, HttpMethod.GET, entity, String.class);
		String[] arrOfStr = response.getBody().split("\"",0);
		String[] arrOfPath = arrOfStr[3].split(" ",0); //list of the path to each *type* of a room
		
		for (int l=0; l<arrOfPath.length;l++){
			ResponseEntity<String> Data = rest.exchange(
					"http://localhost:8080/~/"+arrOfPath[l]+"/DATA/la", HttpMethod.GET, entity, String.class);
			arrOfStr = Data.getBody().split("\"con\" : \"",0);
			list.add(Integer.parseInt(String.valueOf(arrOfStr[1].charAt(0))));
			
		}
		System.out.println(Type+ " : "+list);
		return list;
		
	}
	
	
	@GetMapping("/classrooms")
	public ArrayList<Classroom> getClassroom(){
		
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("x-m2m-origin", "admin:admin");
		
		String url_base = "http://localhost:";
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		for(int i=0; i< ports.length;i++){
			int temperature=-1;
			int heating=-1;
			int presence =-1;
			ArrayList<Integer> light=new ArrayList<Integer>();
			ArrayList<Integer> door=new ArrayList<Integer>();
			ArrayList<Integer> window=new ArrayList<Integer>();
			String[] arrOfStr; 
			String[] arrOfPath; 
			
			System.out.println("INFORMATION ROOM "+ (i+1));
			
			light = getData(restTemplate , entity, ports[i], "light");
			//////// GET LIGHTS OF A ROOM //////////
			/*url=url_base+ports[i]+"?fu=1&lbl=Type/light";
			ResponseEntity<String> responseLight = restTemplate.exchange(
							url, HttpMethod.GET, entity, String.class);
			arrOfStr = responseLight.getBody().split("\"",0);
			arrOfPath = arrOfStr[3].split(" ",0); //list of the path to each light of a room
			
			for (int l=0; l<arrOfPath.length;l++){
				ResponseEntity<String> Data = restTemplate.exchange(
						url_base+"8080/~/"+arrOfPath[l]+"/DATA/la", HttpMethod.GET, entity, String.class);
				arrOfStr = Data.getBody().split("\"con\" : \"",0);
				light.add(Integer.parseInt(String.valueOf(arrOfStr[1].charAt(0))));
				
			}
			
			System.out.println("Lights : "+light);*/
			
			//////// GET DOORS OF A ROOM //////////
			door = getData(restTemplate , entity, ports[i], "door");
			/*url=url_base+ports[i]+"?fu=1&lbl=Type/door";
			ResponseEntity<String> responseDoor = restTemplate.exchange(
							url, HttpMethod.GET, entity, String.class);
			arrOfStr = responseDoor.getBody().split("\"",0);
			arrOfPath = arrOfStr[3].split(" ",0); //list of the path to each door of a room
			
			for (int l=0; l<arrOfPath.length;l++){
				ResponseEntity<String> Data = restTemplate.exchange(
						url_base+"8080/~/"+arrOfPath[l]+"/DATA/la", HttpMethod.GET, entity, String.class);
				arrOfStr = Data.getBody().split("\"con\" : \"",0);				
				door.add(Integer.parseInt(String.valueOf(arrOfStr[1].charAt(0))));	
			}
			System.out.println("Doors : "+ door);*/
			
			
			//////// GET WINDOWS OF A ROOM //////////
			window = getData(restTemplate , entity, ports[i], "window");
			/*url=url_base+ports[i]+"?fu=1&lbl=Type/window";
			ResponseEntity<String> responseWindow = restTemplate.exchange(
							url, HttpMethod.GET, entity, String.class);
			arrOfStr = responseWindow.getBody().split("\"",0);
			arrOfPath = arrOfStr[3].split(" ",0); //list of the path to each door of a room
			
			for (int l=0; l<arrOfPath.length;l++){
				ResponseEntity<String> Data = restTemplate.exchange(
						url_base+"8080/~/"+arrOfPath[l]+"/DATA/la", HttpMethod.GET, entity, String.class);
				arrOfStr = Data.getBody().split("\"con\" : \"",0);
				window.add(Integer.parseInt(String.valueOf(arrOfStr[1].charAt(0))));	
			}
			System.out.println("Windows : "+window);*/
			
			//////// GET TEMPERATURE OF A ROOM //////////
			temperature = (getData(restTemplate , entity, ports[i], "temperature")).get(0);
			/*url=url_base+ports[i]+"?fu=1&lbl=Type/temperature";
			ResponseEntity<String> responseTemperature = restTemplate.exchange(
							url, HttpMethod.GET, entity, String.class);
			arrOfStr = responseTemperature.getBody().split("\"",0);
			arrOfPath = arrOfStr[3].split(" ",0); //list of the path to each door of a room
			
			for (int l=0; l<arrOfPath.length;l++){
				ResponseEntity<String> Data = restTemplate.exchange(
						url_base+"8080/~/"+arrOfPath[l]+"/DATA/la", HttpMethod.GET, entity, String.class);
				arrOfStr = Data.getBody().split("\"con\" : \"",0);
				arrOfStr = arrOfStr[1].split("\"",0);
				temperature = Integer.parseInt(arrOfStr[0]);	
			}
			System.out.println("Temperature : "+ temperature);*/
			
			heating = (getData(restTemplate , entity, ports[i], "heating")).get(0);
			presence = (getData(restTemplate , entity, ports[i], "presence")).get(0);
			
			classrooms.add(new Classroom("Room "+(i+1),temperature, presence, heating, light, door, window));

		}
		
		
		return classrooms;		

	}
}
