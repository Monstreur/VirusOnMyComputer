package Joueurs;

import Zones.ZoneMarchable;
import autres.Couleur;

public enum Joueur {

	BLUE(Couleur.BLUE),
	RED(Couleur.RED),
	GREEN(Couleur.GREEN),
	YELLOW(Couleur.YELLOW);
	
	private String name;
	private Couleur color;
	private ZoneMarchable caseActuelle;
	private boolean parefeu;
	private boolean decodeur;
	private boolean zoneQuarantaine;
	private boolean codeAccesRed;
	private boolean codeAccesBlue;
	private boolean codeAccesGreen;
	private boolean codeAccesYellow;
	private int stockDeplacement;
	
	Joueur(Couleur color) {
		this.color = color;
		this.parefeu = false;
		this.decodeur = false;
		this.zoneQuarantaine = false;
		this.codeAccesBlue = (this.color.toString().equals("BLUE"));
		this.codeAccesRed = (this.color.toString().equals("RED"));
		this.codeAccesGreen = (this.color.toString().equals("GREEN"));
		this.codeAccesYellow = (this.color.toString().equals("YELLOW"));
		this.stockDeplacement = 0;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public ZoneMarchable getCaseActuelle(){
		return this.caseActuelle;
	}
	public void setCaseActuelle(ZoneMarchable zm){
		this.caseActuelle = zm;
	}
	
	public Couleur getColor(){
		return this.color;
	}

	public int getStockDeplacement() {
		return stockDeplacement;
	}
	public void setStockDeplacement(int nbDeplacement) {
		this.stockDeplacement = nbDeplacement;
	}
	public boolean removeDeplacement(int nbDeplacement) {
		if(this.stockDeplacement<nbDeplacement)
			return false;
		this.stockDeplacement -= nbDeplacement;
		return true;
	}
	
	public String toString(){
		return "[Joueur] "+name+" ("+color+") "
				+ "["+parefeu+";"+decodeur+";"+zoneQuarantaine+"] "
				+ "["+codeAccesBlue+";"+codeAccesRed+";"+codeAccesGreen+";"+codeAccesYellow+"]"
				+ "Case Actuelle: ["+caseActuelle+"]";
	}
	
}
