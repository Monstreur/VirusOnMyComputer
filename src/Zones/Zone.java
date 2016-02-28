package Zones;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Image;

import autres.CarreArea;
import autres.Couleur;

public class Zone implements ZoneMarchable{
	private String name;
	private Couleur color;
	private List<ZoneMarchable> zoneMarchables;
	private Image highlight;
	private CarreArea button;
	
	public Zone(String name, Couleur color, int x, int y, int hPlateau){
		this.name = name;
		this.color = color;
		this.zoneMarchables = new ArrayList<ZoneMarchable>();
		float scale = ((float)hPlateau/(float)24187);
		this.button = new CarreArea((int)(x*scale),(int)(y*scale),(int)(2083*scale),(int)(1208*scale));
	}
	
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
	
	@Override
	public String toString(){
		return "[Zone] "+name+" "+color+" "+button;
	}
	
	@Override
	public void setButtonHover(boolean hover){
		this.button.setHover(hover);
	}
	@Override
	public CarreArea getCarreArea() {
		return this.button;
	}
}
