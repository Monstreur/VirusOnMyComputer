package autres;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.Color;

public enum Couleur {
	BLUE ("BLUE",0,0,175),
	RED ("RED",175,0,0),
	GREEN ("GREEN",0,175,0),
	YELLOW ("YELLOW",175,175,0);
	
	private String name;
	private Color color;
	
	Couleur(String name, int r, int g, int b){
		this.name = name;
		this.color = new Color(r,g,b);
	}
	
	public static Couleur RandomColor(List<Couleur> excepts){
		List<Couleur> tmp = new ArrayList<Couleur>();
		tmp.add(Couleur.BLUE);
		tmp.add(Couleur.RED);
		tmp.add(Couleur.GREEN);
		tmp.add(Couleur.YELLOW);
		for (Couleur c : excepts) {
			if(tmp.contains(c))
				tmp.remove(c);
		}
		return tmp.get(ManipulationNombre.randomRange(0, tmp.size()-1));
	}
	  
	public Color getColor(){
		return this.color;
	}
	  
	public String toString(){
		return this.name;
	}
}
