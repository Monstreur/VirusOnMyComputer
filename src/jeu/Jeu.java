package jeu;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import Joueurs.Joueur;
import Joueurs.ListeJoueurs;
import Zones.Passerelle;
import Zones.Zone;
import Zones.ZoneMarchable;
import autres.Couleur;
import intelligenceArtificielle.IAZone;
import intelligenceArtificielle.IntelligenceArtificielle;

public class Jeu {
	private ListeJoueurs joueurs;
	private Couleur joueurActuel;
	private IntelligenceArtificielle ia;
	private Plateau plateau;
	private int nbDeplacementMaxJoueur;
	
	public Jeu(int hPlateau){
		this.nbDeplacementMaxJoueur = 3;
		this.plateau = new Plateau(hPlateau);
		this.joueurs = new ListeJoueurs();
		this.joueurs.add(Joueur.BLUE);
		this.joueurs.add(Joueur.RED);
		this.joueurs.add(Joueur.GREEN);
		this.joueurs.add(Joueur.YELLOW);

		this.joueurs.getJoueur(Couleur.BLUE).setCaseActuelle(this.plateau.getZoneApparition(Couleur.BLUE));
		this.joueurs.getJoueur(Couleur.RED).setCaseActuelle(this.plateau.getZoneApparition(Couleur.RED));
		this.joueurs.getJoueur(Couleur.GREEN).setCaseActuelle(this.plateau.getZoneApparition(Couleur.GREEN));
		this.joueurs.getJoueur(Couleur.YELLOW).setCaseActuelle(this.plateau.getZoneApparition(Couleur.YELLOW));
		
		this.ia = new IntelligenceArtificielle();
		this.nbDeplacementMaxJoueur = 3;

		this.joueurActuel = Couleur.BLUE;
		this.joueurs.getJoueur(this.joueurActuel).setStockDeplacement(this.nbDeplacementMaxJoueur);
	}
	
	public List<ZoneMarchable> deplacementPossibleJoueur(Joueur joueur){
		if(joueur.getStockDeplacement()>1){
			ZoneMarchable caseStart = joueur.getCaseActuelle();
			List<ZoneMarchable> zms = new ArrayList<ZoneMarchable>();
			List<ZoneMarchable> zonetoSearch = new ArrayList<ZoneMarchable>();
			List<ZoneMarchable> zoneaddSearch = new ArrayList<ZoneMarchable>();
			zonetoSearch.add(caseStart);
			for(int i = 0; i < joueur.getStockDeplacement(); i++){
				for (ZoneMarchable zmsearch : zonetoSearch) {
					for (ZoneMarchable zm : zmsearch.getZoneMarchables()) {
						if(!zms.contains(zm) && !zm.equals(caseStart)){
							if(!zonetoSearch.contains(zm) && !zoneaddSearch.contains(zm)){
								if((zm instanceof Zone && joueur.canAccesTo(((Zone)zm).getColor())) || !(zm instanceof Zone))
									zoneaddSearch.add(zm);
							}
							if((zm instanceof Zone && joueur.canAccesTo(((Zone)zm).getColor())) || !(zm instanceof Zone))
								zms.add(zm);
						}
					}
				}
				if(zoneaddSearch.isEmpty())
					break;
				else{
					zonetoSearch.addAll(zoneaddSearch);
					zoneaddSearch = new ArrayList<ZoneMarchable>();
				}
			}
			return zms;
		}
		return new ArrayList<ZoneMarchable>();
	}
	public List<ZoneMarchable> deplacementPossibleMP(Couleur colorJoueur){
		ZoneMarchable caseStart = this.joueurs.getJoueur(colorJoueur).getCaseActuelle();
		List<ZoneMarchable> zms = new ArrayList<ZoneMarchable>();
		List<ZoneMarchable> zonetoSearch = new ArrayList<ZoneMarchable>();
		List<ZoneMarchable> zoneaddSearch = new ArrayList<ZoneMarchable>();
		zonetoSearch.add(caseStart);
		while(true){
			for (ZoneMarchable zmsearch : zonetoSearch) {
				for (ZoneMarchable zm : zmsearch.getZoneMarchables()) {
					if(!zms.contains(zm) && !zm.equals(caseStart)){
						if(zm instanceof Passerelle && !zonetoSearch.contains(zm) && !zoneaddSearch.contains(zm)){
							zoneaddSearch.add(zm);
						}
						zms.add(zm);
					}
				}
			}
			if(zoneaddSearch.isEmpty())
				break;
			else{
				zonetoSearch.addAll(zoneaddSearch);
				zoneaddSearch = new ArrayList<ZoneMarchable>();
			}
		}
		return zms;
	}
	
	
	public void deplacementJoueur(Joueur joueur,ZoneMarchable zm) {
		joueur.removeDeplacement(this.nbDeplacementMaxJoueur);
		joueur.setCaseActuelle(zm);
		System.out.println("Vous êtes maintenant sur la case "+joueur.getCaseActuelle());
	}

	public void explore() {
		Joueur joueur = this.joueurs.getJoueur(joueurActuel);
		Zone caseActuelle = (Zone)joueur.getCaseActuelle();
		IAZone iazone = ia.getCase(joueurActuel, caseActuelle.getNum());
		System.out.println(caseActuelle+" : "+ia.getInfosOnCase(iazone));
		ia.doActionForCase(joueur, iazone);
	}

	public void joueurSuivant(){
		if(this.joueurActuel.name().equals("BLUE"))
			this.joueurActuel=Couleur.RED;
		else if(this.joueurActuel.name().equals("RED"))
			this.joueurActuel=Couleur.GREEN;
		else if(this.joueurActuel.name().equals("GREEN"))
			this.joueurActuel=Couleur.YELLOW;
		else if(this.joueurActuel.name().equals("YELLOW"))
			this.joueurActuel=Couleur.BLUE;

		this.joueurs.getJoueur(this.joueurActuel).setStockDeplacement(this.nbDeplacementMaxJoueur);
	}
	
    public void render(GameContainer container, Graphics g) throws SlickException {
    	this.plateau.render(container, g);
    }
	
	public String toString(){
		String ret ="[Jeu]\n";
		for (Joueur j : this.joueurs) {
			ret += "\t"+j.toString()+"\n";
		}
		ret += ""+this.plateau;
		return ret;
	}
	
	public Plateau getPlateau(){
		return this.plateau;
	}

	public ListeJoueurs getListeJoueurs() {
		return this.joueurs;
	}

	public Couleur getJoueurActuel() {
		return this.joueurActuel;
	}
	
	
}
