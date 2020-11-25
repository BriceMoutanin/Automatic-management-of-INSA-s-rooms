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
	public static String[] ports = {"8181","8282"}; //recense les ports(mn-cse) de toutes les salles du GEI
	public static String[][] portRoomAssociation= getAllRooms();//associe chaque port au nom de son mn-cse (Room1, Room2...)
	
	
	ArrayList<Classroom> classrooms = new ArrayList<Classroom>();
	String url="";
	
	// Recuperer les noms des salles à partir des ports
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
			//ex : Requete GET sur http://localhost:8181
			//On retrouve le nom de la salle dans la variable <ri>
			ResponseEntity<String> response = rest.exchange(
					url, HttpMethod.GET, entity, String.class);
			String responseBody= response.getBody().replaceAll("/", "");
			String[] arrOfStr = responseBody.split("<ri>",0);
			result[i][1] = arrOfStr[1];
		}
		System.out.println(result);
		return result;
	}

	//Depuis la salle donnée (port) renvoie tous les paths des sensors d'un type donné séparé par des espaces
	//Par exemple /8181/light renvoie "Room1/Room1/light1 Room1/Room1/light2"
	@GetMapping("/{port}/{sensorType}")
	public String getPathBySensorType(@PathVariable("port") String port, @PathVariable("sensorType") String type){
		//URL de recherche de path par label 
		String url="http://localhost:"+port+"?fu=1&lbl=Type/"+type;
		
		//Requete GET 
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

	//Permet d'obtenir toutes les infos d'une classe donnée (sous forme de Classroom) à partir de son nom
	//Exemple : /Room1/info 
	@GetMapping("/{room}/info")
	public Classroom getClassroomInfo(@PathVariable String room){
		//Recherche du port associé au nom de la salle donnée en argument
		boolean find= false;
		String port="";
		int i=0;

		while(find==false && i<ports.length){
			if ( portRoomAssociation[i][1].equals(room)){
				find = true;
				port = portRoomAssociation[i][0];
			}
			i++;
		}

		//Creation des futurs éléments de la Classroom
		ArrayList<Actuator> heatings=new ArrayList<Actuator>();
		ArrayList<Actuator> lights=new ArrayList<Actuator>();
		ArrayList<Actuator> doors=new ArrayList<Actuator>();
		ArrayList<Actuator> windows=new ArrayList<Actuator>();

		System.out.println("INFORMATION "+ room);

		//////// GET TEMPERATURE OF A ROOM //////////
		//Recupere le path (unique pour la température) de la temperature pour la salle donnée
		String path = getPathBySensorType(port,"temperature");
		//Crée le sensor temperature avec son path et son type associé
		Sensor temperature=new Sensor(path,"temperature");

		System.out.println(path);
		System.out.println("Temperature : ");
		//Recupére la valeur de temperature sur OM2M et la met dans la variable value du sensor
		System.out.println(temperature.getDataOM2M());

		//////// GET PRESENCE OF A ROOM //////////
		//Tout pareil qu'avant
		path = getPathBySensorType(port,"presence");
		Sensor presence =new Sensor(path, "presence");
		System.out.println("Présence : ");
		System.out.println(presence.getDataOM2M());

		//////// GET LIGHTS OF A ROOM //////////
		//La seule difference ici c'est qu'il y a plusieurs path 
		//donc on le fait dans une boucle for pour recup toutes 
		//les données et les mettre dans le tableau d'Actuator
		path = getPathBySensorType(port,"light");
		String[] arrOfPathL = path.split(" ");
		for (int j=0;j< arrOfPathL.length;j++){
			Actuator l= new Actuator(arrOfPathL[j],"light");
			l.getDataOM2M();
			lights.add(l);
		}
		//////// GET DOORS OF A ROOM //////////
		//Tout pareil 
		path = getPathBySensorType(port,"door");
		String[] arrOfPathD = path.split(" ");
		for (int j=0;j< arrOfPathD.length;j++){
			Actuator d= new Actuator(arrOfPathD[j], "door");
			d.getDataOM2M();
			doors.add(d);
		}
		//////// GET WINDOWS OF A ROOM //////////
		//Tout pareil
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
		//Tu mets toutes ces belles infos dans la Classroom et pouf ça marche
		Classroom classroom = new Classroom(room,port,temperature, presence, heatings, lights, doors, windows);
		return classroom;
	}
	
//Je sais plus trop ce que c'est mais je l'ai commenté donc ça doit pas être important
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

	//Recupere les infos de TOUTES les salles 
	@GetMapping("/classrooms/info")
	public ArrayList<Classroom> getAllClassroomInfo(){
		ArrayList<Classroom> classrooms= new ArrayList<Classroom>();
		for(int i=0; i< ports.length;i++){
			classrooms.add(getClassroomInfo(portRoomAssociation[i][1]));
		}
		return classrooms;
	}
}
