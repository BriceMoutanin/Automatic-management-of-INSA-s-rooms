package fr.insa.soa.scenarioManagement.model;

import java.util.ArrayList;

public class ListClassroom {
	private ArrayList<Classroom> listCl;

	public ListClassroom() {
		super();
	}

	public ListClassroom(ArrayList<Classroom> listCl) {
		super();
		this.listCl = listCl;
	}

	public ArrayList<Classroom> getListCl() {
		return listCl;
	}

	public void setListCl(ArrayList<Classroom> listCl) {
		this.listCl = listCl;
	}
	
}

