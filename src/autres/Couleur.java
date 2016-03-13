package autres;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;

public enum Couleur {
	BLUE ("BLUE",50,50,255),
	RED ("RED",255,50,50),
	GREEN ("GREEN",50,255,50),
	YELLOW ("YELLOW",255,255,50);
	
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
			Couleur c3 = null;
			for (Couleur c2 : tmp) {
				if(c.name.equals(c2.name))
					c3=c2;
			}
			tmp.remove(c3);
		}
		return tmp.get(ManipulationNombre.randomRange(0, tmp.size()-1));
	}
	
	public static List<Couleur> CheminCouleurForCodeAccess(){
		List<Couleur> ret = new ArrayList<Couleur>();
		List<Couleur> couleurs = new ArrayList<Couleur>();
		couleurs.add(Couleur.BLUE);
		couleurs.add(Couleur.RED);
		couleurs.add(Couleur.GREEN);
		couleurs.add(Couleur.YELLOW);

		int r  = ManipulationNombre.randomRange(0, 5);
		if(r==0){
			ret.add(couleurs.get(1));
			ret.add(couleurs.get(2));
			ret.add(couleurs.get(3));
			ret.add(couleurs.get(0));
		}else if(r==1){
			ret.add(couleurs.get(1));
			ret.add(couleurs.get(3));
			ret.add(couleurs.get(0));
			ret.add(couleurs.get(2));
		}else if(r==2){
			ret.add(couleurs.get(2));
			ret.add(couleurs.get(0));
			ret.add(couleurs.get(3));
			ret.add(couleurs.get(1));
		}else if(r==3){
			ret.add(couleurs.get(2));
			ret.add(couleurs.get(3));
			ret.add(couleurs.get(1));
			ret.add(couleurs.get(0));
		}else if(r==4){
			ret.add(couleurs.get(3));
			ret.add(couleurs.get(0));
			ret.add(couleurs.get(1));
			ret.add(couleurs.get(2));
		}else if(r==5){
			ret.add(couleurs.get(3));
			ret.add(couleurs.get(2));
			ret.add(couleurs.get(0));
			ret.add(couleurs.get(1));
		}
		
		return ret;
	}
	  
	public Color getColor(){
		return this.color;
	}
	  
	public String toString(){
		return this.name;
	}
}
