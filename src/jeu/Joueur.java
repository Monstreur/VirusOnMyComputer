package jeu;

public enum Joueur {

	BLUE(Couleur.BLUE),
	RED(Couleur.RED),
	GREEN(Couleur.GREEN),
	YELLOW(Couleur.YELLOW);
	
	private String name;
	private Couleur color;
	private boolean parefeu;
	private boolean decodeur;
	private boolean zoneQuarantaine;
	private boolean codeAccesRed;
	private boolean codeAccesBlue;
	private boolean codeAccesGreen;
	private boolean codeAccesYellow;
	
	Joueur(Couleur color) {
		this.color = color;
		this.parefeu = false;
		this.decodeur = false;
		this.zoneQuarantaine = false;
		this.codeAccesBlue = (this.color.toString()=="BLUE");
		this.codeAccesRed = (this.color.toString()=="RED");
		this.codeAccesGreen = (this.color.toString()=="GREEN");
		this.codeAccesYellow = (this.color.toString()=="YELLOW");
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String toString(){
		return "[Joueur] "+name+" ("+color+") "
				+ "["+parefeu+";"+decodeur+";"+zoneQuarantaine+"] "
				+ "["+codeAccesBlue+";"+codeAccesRed+";"+codeAccesGreen+";"+codeAccesYellow+"]";
	}
	
}
