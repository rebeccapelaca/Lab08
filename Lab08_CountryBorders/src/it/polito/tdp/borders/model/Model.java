package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private BordersDAO bordersDAO; 
	private UndirectedGraph<Country, DefaultEdge> graph;

	public Model() {
		
		this.graph = new SimpleGraph<>(DefaultEdge.class);
		this.bordersDAO = new BordersDAO();
	}
	
	public Set<Country> creaGrafo(int anno) {
		
		Graphs.addAllVertices(graph, bordersDAO.loadAllCountries(anno));
		
		for(Border b : bordersDAO.getCountryPairs(anno)) 
			graph.addEdge(b.getPrimoStato(), b.getSecondoStato());
		
		return graph.vertexSet();
	}
	
	public Set<Country> calcolaNumeroConfinanti() {
		
		List<Country> neighboursList = new ArrayList<Country>();
		
		for(Country vertice : graph.vertexSet()) {
			neighboursList.addAll(Graphs.neighborListOf(graph, vertice));
			vertice.aggiungiConfinanti(neighboursList);
			vertice.setNumeroConfinanti(neighboursList.size());
			neighboursList.clear();
		}
		
		return graph.vertexSet();
	}
	
	public int calcolaComponentiConnesse() {
		
		ConnectivityInspector<Country, DefaultEdge> inspector = new ConnectivityInspector<Country, DefaultEdge>(graph);
		return inspector.connectedSets().size();
	}
}
