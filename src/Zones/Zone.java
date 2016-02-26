package Zones;

import java.util.ArrayList;
import java.util.List;

import autres.Couleur;

public class Zone implements ZoneMarchable{
	private String name;
	private Couleur color;
	private List<ZoneMarchable> zoneMarchables;
	
	public Zone(String name, Couleur color){
		this.name = name;
		this.color = color;
		this.zoneMarchables = new ArrayList<ZoneMarchable>();
	}
	/*
	public Zone(String name, Couleur color, List<ZoneMarchable> zoneMarchables){
		this.name = name;
		this.color = color;
		this.zoneMarchables = zoneMarchables;
	}
	*/
	
	public List<ZoneMarchable> getZoneMarchables() {
		return zoneMarchables;
	}
	@Override
	public boolean addZoneMarchable(ZoneMarchable zm) {
		return this.zoneMarchables.add(zm);
	}
	
	public int getNum() {
		return Integer.parseInt(name);
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
