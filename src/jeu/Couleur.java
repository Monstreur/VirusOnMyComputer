package jeu;

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
	  
	public Color getColor(){
		return this.color;
	}
	  
	public String toString(){
		return this.name;
	}
}
