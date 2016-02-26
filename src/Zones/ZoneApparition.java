package Zones;

import java.util.ArrayList;
import java.util.List;

import autres.Couleur;

public enum ZoneApparition implements ZoneMarchable {
	BLUE(Couleur.BLUE),
	RED(Couleur.RED),
	GREEN(Couleur.GREEN),
	YELLOW(Couleur.YELLOW);
	
	private Couleur color;
	private List<ZoneMarchable> zoneMarchables;
	
	ZoneApparition(Couleur color){
		this.color = color;
		this.zoneMarchables = new ArrayList<ZoneMarchable>();
	}

	public String toString(){
		return "[ZoneApparition] "+color.toString();
	}

	@Override
	public List<ZoneMarchable> getZoneMarchables() {
		return this.zoneMarchables;
	}

	@Override
	public boolean addZoneMarchable(ZoneMarchable zm) {
		return this.zoneMarchables.add(zm);
	}
	
	public Couleur getColor(){
		return this.color;
	}
}
