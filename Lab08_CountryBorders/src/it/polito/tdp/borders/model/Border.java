package it.polito.tdp.borders.model;

public class Border {
	
	private Country primoStato;
	private Country secondoStato;
	
	public Border(Country primoStato, Country secondoStato) {
		super();
		this.primoStato = primoStato;
		this.secondoStato = secondoStato;
	}

	public Country getPrimoStato() {
		return primoStato;
	}

	public void setPrimoStato(Country primoStato) {
		this.primoStato = primoStato;
	}

	public Country getSecondoStato() {
		return secondoStato;
	}

	public void setSecondoStato(Country secondoStato) {
		this.secondoStato = secondoStato;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((primoStato == null) ? 0 : primoStato.hashCode());
		result = prime * result + ((secondoStato == null) ? 0 : secondoStato.hashCode());
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
		Border other = (Border) obj;
		if (primoStato == null) {
			if (other.primoStato != null)
				return false;
		} else if (!primoStato.equals(other.primoStato))
			return false;
		if (secondoStato == null) {
			if (other.secondoStato != null)
				return false;
		} else if (!secondoStato.equals(other.secondoStato))
			return false;
		return true;
	}
}