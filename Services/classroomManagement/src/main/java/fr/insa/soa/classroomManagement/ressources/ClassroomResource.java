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

import java.util.ArrayList;

@RestController
@RequestMapping("/GEI")
public class ClassroomResource {
	public static String[] ports = {"8181","8282"};
	public static String[][] portRoomAssociation= getAllRooms();
	ArrayList<Classroom> classrooms = new ArrayList<Classroom>();
	String url="";
	// Recuperer les noms des salles à partir des ports donnés
	@GetMapping("/rooms")
	static public String[][] getAllRooms(){
		String[][] result=new String[ports.length][2];
		String url="";

		RestTemplate rest = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("x-m2m-origin", "admin:admin");
		headers.set("Content-Type", "application/xml");
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		for (int i=0; i<ports.length;i++){
			result[i][0]=ports[i];
			url="http://localhost:"+ports[i];

			ResponseEntity<String> response = rest.exchange(
					url, HttpMethod.GET, entity, String.class);
			String responseBody= response.getBody().replaceAll("/", "");
			String[] arrOfStr = responseBody.split("<ri>",0);
			result[i][1] = arrOfStr[1];
		}
		System.out.println(result);
		return result;
	}

	@GetMapping("/{port}/{sensorType}")
	public String getPathBySensorType(@PathVariable("port") String port, @PathVariable("sensorType") String type){
		String url="http://localhost:"+port+"?fu=1&lbl=Type/"+type;
		RestTemplate rest = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("x-m2m-origin", "admin:admin");
		headers.set("Content-Type", "application/xml");
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> response = rest.exchange(
				url, HttpMethod.GET, entity, String.class);
		String[] arrOfStr = response.getBody().split(">", 0);
		arrOfStr = arrOfStr[2].split("<", 0);
		return arrOfStr[0];
	}


	@GetMapping("/{room}/info")
	public Classroom getClassroomInfo(@PathVariable String room){
		boolean find= false;
		String port="";
		int i=0;


		ArrayList<Actuator> heatings=new ArrayList<Actuator>();
		ArrayList<Actuator> lights=new ArrayList<Actuator>();
		ArrayList<Actuator> doors=new ArrayList<Actuator>();
		ArrayList<Actuator> windows=new ArrayList<Actuator>();

		while(find==false && i<ports.length){

			if ( portRoomAssociation[i][1].equals(room)){
				find = true;
				port = portRoomAssociation[i][0];
			}
			i++;
		}

			System.out.println(port);
			System.out.println("INFORMATION "+ room);

			//////// GET TEMPERATURE OF A ROOM //////////
			String path = getPathBySensorType(port,"temperature");
			Sensor temperature=new Sensor(path,"temperature");

			System.out.println(path);
			System.out.println("Temperature : ");
			System.out.println(temperature.getDataOM2M());

			//////// GET PRESENCE OF A ROOM //////////
			path = getPathBySensorType(port,"presence");
			Sensor presence =new Sensor(path, "presence");
			System.out.println("Présence : ");
			System.out.println(presence.getDataOM2M());

			//////// GET LIGHTS OF A ROOM //////////
			path = getPathBySensorType(port,"light");
			String[] arrOfPathL = path.split(" ");
			for (int j=0;j< arrOfPathL.length;j++){
				Actuator l= new Actuator(arrOfPathL[j],"light");
				l.getDataOM2M();
				lights.add(l);
			}
			//////// GET DOORS OF A ROOM //////////
			path = getPathBySensorType(port,"door");
			String[] arrOfPathD = path.split(" ");
			for (int j=0;j< arrOfPathD.length;j++){
				Actuator d= new Actuator(arrOfPathD[j], "door");
				d.getDataOM2M();
				doors.add(d);
			}
			//////// GET WINDOWS OF A ROOM //////////
			path = getPathBySensorType(port,"window");
			String[] arrOfPathW = path.split(" ");
			for (int j=0;j< arrOfPathW.length;j++){
				Actuator w= new Actuator(arrOfPathW[j],"window");
				w.getDataOM2M();
				windows.add(w);
			}
			//////// GET HEATING OF A ROOM //////////
			path = getPathBySensorType(port,"heating");
			String[] arrOfPathH = path.split(" ");
			for (int j=0;j< arrOfPathH.length;j++){
				Actuator h= new Actuator(arrOfPathH[j],"heating");
				h.getDataOM2M();
				heatings.add(h);
			}


			Classroom classroom = new Classroom(room,port,temperature, presence, heatings, lights, doors, windows);
			return classroom;
	}

/*	@GetMapping("/{ports}/{type}")
	public ArrayList<Integer> getData(@PathVariable("ports") String ports,@PathVariable("Type")  String Type){

		RestTemplate rest = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("x-m2m-origin", "admin:admin");
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		ArrayList<Integer> list=new ArrayList<Integer>();
		String url="http://localhost:"+ports+"?fu=1&lbl=Type/"+Type;
		ResponseEntity<String> response = rest.exchange(
						url, HttpMethod.GET, entity, String.class);
		String[] arrOfStr = response.getBody().split("\"",0);
		String[] arrOfPath = arrOfStr[3].split(" ",0); //list of the path to each *type* of a room
		System.out.println(arrOfPath);
		for (int l=0; l<arrOfPath.length;l++){
			System.out.println(arrOfPath[l]);
			ResponseEntity<String> Data = rest.exchange(
					"http://localhost:8080/~/"+arrOfPath[l]+"/DATA/la", HttpMethod.GET, entity, String.class);
			arrOfStr = Data.getBody().split("\"con\" : \"",0);
			arrOfStr = arrOfStr[1].split("\"",0);
			list.add(Integer.parseInt(String.valueOf(arrOfStr[0])));

		}
		System.out.println(Type+ " : "+list);
		return list;

	}*/


	@GetMapping("/classrooms/info")
	public ArrayList<Classroom> getAllClassroomInfo(){
		ArrayList<Classroom> classrooms= new ArrayList<Classroom>();
		for(int i=0; i< ports.length;i++){
			classrooms.add(getClassroomInfo(portRoomAssociation[i][1]));
		}
		return classrooms;
	}
}
