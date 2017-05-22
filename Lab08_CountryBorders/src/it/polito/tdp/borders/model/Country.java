package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.List;

public class Country {
	
	private int code;
	private String abb;
	private String name;
	private List<Country> confinanti;
	private int numeroConfinanti;
	
	public Country(int code, String abb, String name) {
		super();
		this.code = code;
		this.abb = abb;
		this.name = name;
		this.confinanti = new ArrayList<Country>();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getAbb() {
		return abb;
	}

	public void setAbb(String abb) {
		this.abb = abb;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Country> getCountries() {
		return confinanti;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (code != other.code)
			return false;
		return true;
	}

	public void aggiungiConfinanti(List<Country> neighboursList) {
		
		confinanti.addAll(neighboursList);	
	}

	public void setNumeroConfinanti(int size) {
		
		this.numeroConfinanti = size;	
	}

	@Override
	public String toString() {
		return "codice=" + code + "   abb=" + abb + "   nome=" + name + "   numeroConfinanti=" + numeroConfinanti;
	}
}
