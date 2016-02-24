package jeu;

import java.util.ArrayList;
import java.util.List;

public class Jeu {
	private List<Joueur> joueurs;
	//private IntelligenceArtificielle ia;
	private Plateau plateau;
	
	public Jeu(){
		this.joueurs = new ArrayList<Joueur>();
		this.joueurs.add(Joueur.BLUE);
		this.joueurs.add(Joueur.RED);
		this.joueurs.add(Joueur.GREEN);
		this.joueurs.add(Joueur.YELLOW);
		this.plateau = new Plateau();
	}
	
	public String toString(){
		String ret ="[Jeu]\n";
		for (Joueur j : this.joueurs) {
			ret += "\t"+j.toString()+"\n";
		}
		ret += ""+this.plateau;
		return ret;
	}
	
	public static void main(String[] args){
		Jeu j = new Jeu();
		System.out.print(j);
	}
	
	
}
