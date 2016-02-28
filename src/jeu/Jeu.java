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
		
		this.joueurActuel = Couleur.BLUE;
		
		this.ia = new IntelligenceArtificielle();
		this.nbDeplacementMaxJoueur = 3;
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
		return null;
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
	
	
	private ZoneMarchable deplacementJoueur(Joueur joueur,int nbDeplacement) {
		System.out.println("Vous êtes actuellement sur la case "+joueur.getCaseActuelle());
		List<ZoneMarchable> deplacements = this.deplacementPossibleJoueur(joueur);
		ZoneMarchable zoneNew = null;
		if(deplacements==null)
			System.out.println("Impossible, cete case est trop loin");
		else{
			/*
			System.out.println("\nLes zones où vous pouvez aller sont:");
			for (ZoneMarchable zm : deplacements) {
				System.out.println("\t"+zm);
			}
			System.out.println("\nLes zones où votre MicroProgramme peut aller sont:");
			for (ZoneMarchable zm : this.deplacementPossibleMP(this.joueurActuel)) {
				System.out.println("\t"+zm);
			}
			*/
			zoneNew = deplacements.get(0);
			return zoneNew;
		}
		return null;
	}

	private void explore(Joueur joueur) {
		ZoneMarchable caseActuelle = joueur.getCaseActuelle();
		System.out.println(ia.getInfosOnCase(joueur.getColor(), ((Zone)caseActuelle).getNum()));
	}
	
	public void jouerTour(){
		Joueur joueur = this.joueurs.getJoueur(joueurActuel);
		joueur.setStockDeplacement(this.nbDeplacementMaxJoueur);
		
		ZoneMarchable zm = this.deplacementJoueur(joueur, this.nbDeplacementMaxJoueur);
		joueur.removeDeplacement(this.nbDeplacementMaxJoueur);
		joueur.setCaseActuelle(zm);
		System.out.println("Vous êtes maintenant sur la case "+joueur.getCaseActuelle());
		if(joueur.getCaseActuelle() instanceof Zone)
			this.explore(joueur);
	}

	public void jouerRound(){
		this.jouerTour();
		this.joueurActuel=Couleur.RED;
		this.jouerTour();
		this.joueurActuel=Couleur.GREEN;
		this.jouerTour();
		this.joueurActuel=Couleur.YELLOW;
		this.jouerTour();
		this.joueurActuel=Couleur.BLUE;
		
	}
	
    public void render(GameContainer container, Graphics g) throws SlickException {
    	this.plateau.render(container, g);
    	this.plateau.draw(g);
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

	/*
	public static void main(String[] args){
		Jeu j = new Jeu(1024);
		j.jouerRound();
		j.jouerRound();
		System.out.print(j);
		
		System.out.println(j.joueurs.getJoueur(Couleur.BLUE).getCaseActuelle());
		System.out.println(j.joueurs.getJoueur(Couleur.BLUE).getCaseActuelle().getZoneMarchables());
		
		j.joueurs.getJoueur(Couleur.BLUE).setCaseActuelle(j.joueurs.getJoueur(Couleur.BLUE).getCaseActuelle().getZoneMarchables().get(0));

		System.out.println(j.joueurs.getJoueur(Couleur.BLUE).getCaseActuelle());
		System.out.println(j.joueurs.getJoueur(Couleur.BLUE).getCaseActuelle().getZoneMarchables());

		j.joueurs.getJoueur(Couleur.BLUE).setCaseActuelle(j.joueurs.getJoueur(Couleur.BLUE).getCaseActuelle().getZoneMarchables().get(0));
		
		System.out.println(j.joueurs.getJoueur(Couleur.BLUE).getCaseActuelle());
		System.out.println(j.joueurs.getJoueur(Couleur.BLUE).getCaseActuelle().getZoneMarchables());

		j.joueurs.getJoueur(Couleur.BLUE).setCaseActuelle(j.joueurs.getJoueur(Couleur.BLUE).getCaseActuelle().getZoneMarchables().get(1));
		
		System.out.println(j.joueurs.getJoueur(Couleur.BLUE).getCaseActuelle());
		System.out.println(j.joueurs.getJoueur(Couleur.BLUE).getCaseActuelle().getZoneMarchables());

		j.joueurs.getJoueur(Couleur.BLUE).setCaseActuelle(j.joueurs.getJoueur(Couleur.BLUE).getCaseActuelle().getZoneMarchables().get(1));
		
		System.out.println(j.joueurs.getJoueur(Couleur.BLUE).getCaseActuelle());
		System.out.println(j.joueurs.getJoueur(Couleur.BLUE).getCaseActuelle().getZoneMarchables());

		j.joueurs.getJoueur(Couleur.BLUE).setCaseActuelle(j.joueurs.getJoueur(Couleur.BLUE).getCaseActuelle().getZoneMarchables().get(0));
		
		System.out.println(j.joueurs.getJoueur(Couleur.BLUE).getCaseActuelle());
		System.out.println(j.joueurs.getJoueur(Couleur.BLUE).getCaseActuelle().getZoneMarchables());
		
	}
	*/
	
	
}
