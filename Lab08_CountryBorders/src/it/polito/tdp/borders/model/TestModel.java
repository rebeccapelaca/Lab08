package it.polito.tdp.borders.model;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();
		
		System.out.println("Creo il grafo relativo al 2000");
		model.creaGrafo(2000);
		
		System.out.println("Numero di nazioni trovate : " + model.creaGrafo(2000).size());
		System.out.println("Vertici trovati : \n" + model.creaGrafo(2000));
		
		System.out.println("Vertici trovato con relativo numero di stati confinanti : \n" + model.calcolaNumeroConfinanti());

		System.out.format("Numero componenti connesse: %d\n", model.calcolaComponentiConnesse());
		
	}
}
