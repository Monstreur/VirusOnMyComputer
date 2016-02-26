package Joueurs;

import java.util.ArrayList;

import autres.Couleur;

@SuppressWarnings("serial")
public class ListeJoueurs extends ArrayList<Joueur>{
	
	public Joueur getJoueur(Couleur color){
		for (Joueur j : this) {
			if(j.getColor().equals(color)){
				return j;
			}
		}
		return null;
	}
}
