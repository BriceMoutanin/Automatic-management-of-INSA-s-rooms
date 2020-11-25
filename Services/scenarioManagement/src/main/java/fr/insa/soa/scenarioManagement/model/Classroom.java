package fr.insa.soa.scenarioManagement.model;

import java.util.ArrayList;

public class Classroom {
	private String name;
	private String port;
	private Sensor temp;
	private Sensor presence;
	private ArrayList<Actuator> heating;
	private ArrayList<Actuator> lights;
	private ArrayList<Actuator> doors;
	private ArrayList<Actuator> windows;

	public Classroom(String name, String port, Sensor temp, Sensor presence, ArrayList<Actuator> heating,
			ArrayList<Actuator> lights, ArrayList<Actuator> doors, ArrayList<Actuator> windows) {
		super();
		this.name = name;
		this.port = port;
		this.temp = temp;
		this.presence = presence;
		this.heating = heating;
		this.lights = lights;
		this.doors = doors;
		this.windows = windows;
	}

	public Classroom() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Sensor getTemp() {
		return temp;
	}

	public Sensor getPresence() {
		return presence;
	}

	public ArrayList<Actuator> getHeating() {
		return heating;
	}

	public void setHeating(ArrayList<Actuator> heating) {
		this.heating = heating;
	}

	public ArrayList<Actuator> getLights() {
		return lights;
	}

	public void setLights(ArrayList<Actuator> lights) {
		this.lights = lights;
	}

	public ArrayList<Actuator> getDoors() {
		return doors;
	}

	public void setDoors(ArrayList<Actuator> doors) {
		this.doors = doors;
	}

	public ArrayList<Actuator> getWindows() {
		return windows;
	}

	public void setWindows(ArrayList<Actuator> windows) {
		this.windows = windows;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
	


}
