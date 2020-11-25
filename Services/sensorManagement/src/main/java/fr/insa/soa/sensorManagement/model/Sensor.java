package fr.insa.soa.sensorManagement.model;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Sensor {
	String type;
	int value;
	
	public Sensor(String type, int value) {
		super();
		this.type = type;
		this.value = value;
	}

	public Sensor(String type) {
		super();
		this.type = type;
	}

	public Sensor() {
		super();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void getDataOM2M(String path){
		RestTemplate rest = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("x-m2m-origin", "admin:admin");
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		ResponseEntity<String> Data = rest.exchange(
					"http://localhost:8080/~/"+path+"/DATA/la", HttpMethod.GET, entity, String.class);
		String[] arrOfStr = Data.getBody().split("\"con\" : \"",0);
		arrOfStr = arrOfStr[1].split("\"",0);
		this.value = Integer.parseInt(String.valueOf(arrOfStr[0]));
		System.out.println(this.type+ " : "+this.value);
		
	}
	
}
