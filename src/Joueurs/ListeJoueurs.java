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

	public Joueur sameCase(Couleur c) {
		Joueur j = this.getJoueur(c);
		for (Joueur j2 : this) {
			if(!j2.getColor().equals(c) && j.getCaseActuelle()==j2.getCaseActuelle())
				return j2;
		}
		return null;
	}

	public int howManyColorCanPick(Joueur j, Joueur j2) {
		int cpt = 0;
		if(j2.canAccesTo(Couleur.BLUE) && !j.canAccesTo(Couleur.BLUE))
			cpt++;
		if(j2.canAccesTo(Couleur.RED) && !j.canAccesTo(Couleur.RED))
			cpt++;
		if(j2.canAccesTo(Couleur.GREEN) && !j.canAccesTo(Couleur.GREEN))
			cpt++;
		if(j2.canAccesTo(Couleur.YELLOW) && !j.canAccesTo(Couleur.YELLOW))
			cpt++;
		return cpt;
	}
}
