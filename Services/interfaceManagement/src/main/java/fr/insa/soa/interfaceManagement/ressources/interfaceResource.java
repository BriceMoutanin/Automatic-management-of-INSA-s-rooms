package fr.insa.soa.interfaceManagement.ressources;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/interface")
public class interfaceResource {
	
	@CrossOrigin(origins = "*")
	@GetMapping("/post/{Room}/{Actuator}/{data}")
	public void postData(@PathVariable("Room") String r,@PathVariable("Actuator") String a, @PathVariable("data") int data){
		RestTemplate rest = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("x-m2m-origin", "admin:admin");
		headers.set("Content-Type", "application/xml;ty=4");
		String body = "<m2m:cin xmlns:m2m=\"http://www.onem2m.org/xml/protocols\">\n<cnf>application/xml</cnf>\n<con>";
		body+=Integer.toString(data);
		body+="</con>\n</m2m:cin>";
		HttpEntity<String> entity = new HttpEntity<String>(body,headers);
		String url="http://localhost:8080/~/"+r+"/"+r+"/"+a+"/DATA";
		ResponseEntity<String> Data = rest.exchange(
				url, HttpMethod.POST, entity, String.class);
		System.out.println(Data.getBody());
	}
}
