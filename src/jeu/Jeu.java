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
import autres.ManipulationNombre;
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

	public String explore() {
		Joueur joueur = this.joueurs.getJoueur(joueurActuel);
		Zone caseActuelle = (Zone)joueur.getCaseActuelle();
		IAZone iazone = ia.getCase(joueurActuel, caseActuelle.getNum());
		System.out.println(caseActuelle+" : "+ia.getInfosOnCase(iazone));
		String message;
		if(iazone.getCodeNum()==8 && caseActuelle.getNum()==ia.getVirusIsIn() && joueur.isParefeu() && joueur.isDecodeur() && joueur.isZoneQuarantaine())
			message = "Vous avez trouvez le Virus !";
		else{
			message = ia.getInfosOnCase(iazone);
			message += ia.doActionForCase(joueur, iazone, plateau);
		}
		
		return message;
	}

	public String attaque(Joueur j, Joueur j2) {
		int r = ManipulationNombre.randomRange(0, 4);
		if(r<2){
			List<Integer> nums = new ArrayList<Integer>();
			nums.add(0);
			if(this.joueurs.howManyColorCanPick(j,j2)>0)
				nums.add(1);
			if(j2.isParefeu() && !j.isParefeu())
				nums.add(2);
			if(j2.isDecodeur() && !j.isDecodeur())
				nums.add(3);
			if(j2.isZoneQuarantaine() && !j.isZoneQuarantaine())
				nums.add(4);
			int rand = ManipulationNombre.randomIn(nums, new ArrayList<Integer>());
			int rand2;
			int num = 0;
			switch(rand){
				case 0:
					rand2 = ManipulationNombre.randomRange(0, j2.howManyColorCanAcces()-1);
					num = 0;
					if(j2.canAccesTo(Couleur.BLUE)){
						if(rand2==num){
							j2.setCaseActuelle(plateau.getZoneApparition(Couleur.BLUE));
							/*
							 * Pour les autres joueurs.
							 * return "Le joueur "+j2.getColor()+" a été téléporté à la Zone d'Appariton Bleue";
							 */
							return "Vous avez été téléporté à la Zone d'Appariton Bleue";
						}
						num++;
					}
					if(j2.canAccesTo(Couleur.RED)){
						if(rand2==num){
							j2.setCaseActuelle(plateau.getZoneApparition(Couleur.RED));
							return "Vous avez été téléporté à la Zone d'Appariton Rouge";
						}
						num++;
					}
					if(j2.canAccesTo(Couleur.GREEN)){
						if(rand2==num){
							j2.setCaseActuelle(plateau.getZoneApparition(Couleur.GREEN));
							return "Vous avez été téléporté à la Zone d'Appariton Verte";
						}
						num++;
					}
					if(j2.canAccesTo(Couleur.YELLOW)){
						if(rand2==num){
							j2.setCaseActuelle(plateau.getZoneApparition(Couleur.YELLOW));
							return "Vous avez été téléporté à la Zone d'Appariton Jaune";
						}
						num++;
					}
					break;
				case 1:
					rand2 = ManipulationNombre.randomRange(0, this.joueurs.howManyColorCanPick(j, j2)-1);
					num = 0;
					if(j2.canAccesTo(Couleur.BLUE) && !j.canAccesTo(Couleur.BLUE)){
						if(rand2==num){
							j.setCodeAccesBlue(true);
							j2.setCodeAccesBlue(false);
							/*
							 * Pour le joueur 1.
							 * return "Vous avez volé le Code d'acces Bleue du joueur "+j2.getColor();
							 * Pour les autres joueurs.
							 * return "Le joueur "+j.getColor()+" a volé le Code d'acces Bleue du joueur "+j2.getColor();
							 */
							return "Le joueur "+j.getColor()+" vous a volé votre Code d'acces Bleue";
						}
						num++;
					}
					if(j2.canAccesTo(Couleur.RED) && !j.canAccesTo(Couleur.RED)){
						if(rand2==num){
							j.setCodeAccesRed(true);
							j2.setCodeAccesRed(false);
							return "Le joueur "+j.getColor()+" vous a volé votre Code d'acces Rouge";
						}
						num++;
					}
					if(j2.canAccesTo(Couleur.GREEN) && !j.canAccesTo(Couleur.GREEN)){
						if(rand2==num){
							j.setCodeAccesGreen(true);
							j2.setCodeAccesGreen(false);
							return "Le joueur "+j.getColor()+" vous a volé votre Code d'acces Vert";
						}
						num++;
					}
					if(j2.canAccesTo(Couleur.YELLOW) && !j.canAccesTo(Couleur.YELLOW)){
						if(rand2==num){
							j.setCodeAccesYellow(true);
							j2.setCodeAccesYellow(false);
							return "Le joueur "+j.getColor()+" vous a volé votre Code d'acces Jaune";
						}
						num++;
					}
					case 2:
						j.setParefeu(true);
						j2.setParefeu(false);
						return "Le joueur "+j.getColor()+" vous a volé votre Parefeu";
					case 3:
						j.setDecodeur(true);
						j2.setDecodeur(false);
						return "Le joueur "+j.getColor()+" vous a volé votre Decodeur";
					case 4:
						j.setZoneQuarantaine(true);
						j2.setZoneQuarantaine(false);
						return "Le joueur "+j.getColor()+" vous a volé votre Zone de Quarantaine";
			}
		}
		return "Le joueur "+j.getColor()+" a manqué le joueur "+j2.getColor();
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

	public IntelligenceArtificielle getIA() {
		return this.ia;
	}
	
	
}
