package jeu;

public enum ZoneApparition {
	BLUE(Couleur.BLUE),
	RED(Couleur.RED),
	GREEN(Couleur.GREEN),
	YELLOW(Couleur.YELLOW);
	
	private Couleur color;
	
	ZoneApparition(Couleur color){
		this.color = color;
	}

	public String toString(){
		return "[ZoneApparition] "+color.toString();
	}
}
