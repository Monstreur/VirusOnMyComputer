package jeu;

import java.util.ArrayList;
import java.util.List;

public class Passerelle implements ZoneMarchable{
	private List<ZoneMarchable> zoneMarchables;

	public Passerelle(){
		this(new ArrayList<ZoneMarchable>());
	}
	public Passerelle(List<ZoneMarchable> zoneMarchables){
		this.zoneMarchables = zoneMarchables;
	}
	
	public List<ZoneMarchable> getZoneMarchables() {
		return zoneMarchables;
	}
	public void setZoneMarchables(List<ZoneMarchable> zoneMarchables) {
		this.zoneMarchables = zoneMarchables;
	}

	public String toString(){
		return "[Passerelle]";
	}
}
