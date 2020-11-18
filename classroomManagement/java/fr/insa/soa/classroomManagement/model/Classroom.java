package fr.insa.soa.classroomManagement.model;

import java.util.ArrayList;

public class Classroom {
	private String name;
	private int temp;
	private int presence;
	private int heating;
	private ArrayList<Integer> lights;
	private ArrayList<Integer> doors;
	private ArrayList<Integer> windows;
	

	public Classroom(String name, int temp, int presence, int heating, ArrayList<Integer> lights,
			ArrayList<Integer> doors, ArrayList<Integer> windows) {
		super();
		this.name = name;
		this.temp = temp;
		this.presence = presence;
		this.heating = heating;
		this.lights = lights;
		this.doors = doors;
		this.windows = windows;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTemp() {
		return temp;
	}
	public void setTemp(int temp) {
		this.temp = temp;
	}
	public int getPresence() {
		return presence;
	}
	public void setPresence(int presence) {
		this.presence = presence;
	}
	public int getHeating() {
		return heating;
	}
	public void setHeating(int heating) {
		this.heating = heating;
	}
	public ArrayList<Integer> getLights() {
		return lights;
	}
	public void setLights(ArrayList<Integer> lights) {
		this.lights = lights;
	}
	public ArrayList<Integer> getDoors() {
		return doors;
	}
	public void setDoors(ArrayList<Integer> doors) {
		this.doors = doors;
	}
	public ArrayList<Integer> getWindows() {
		return windows;
	}
	public void setWindows(ArrayList<Integer> windows) {
		this.windows = windows;
	}
	
}
