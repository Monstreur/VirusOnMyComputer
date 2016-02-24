package jeu;

import java.util.ArrayList;
import java.util.List;

public class Zone implements ZoneMarchable{
	private String name;
	private Couleur color;
	private List<ZoneMarchable> zoneMarchables;
	
	public Zone(String name, Couleur color){
		this(name, color, new ArrayList<ZoneMarchable>());
	}
	
	public Zone(String name, Couleur color, List<ZoneMarchable> zoneMarchables){
		this.name = name;
		this.color = color;
		this.zoneMarchables = zoneMarchables;
	}
	
	public List<ZoneMarchable> getZoneMarchables() {
		return zoneMarchables;
	}
	public void setZoneMarchables(List<ZoneMarchable> zoneMarchables) {
		this.zoneMarchables = zoneMarchables;
	}
	public String getName() {
		return name;
	}
	public Couleur getColor() {
		return color;
	}

	public String toString(){
		return "[Zone] "+name+" "+color.toString();
	}
}
