package Zones;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import autres.CarreArea;
import autres.Couleur;

public class Zone implements ZoneMarchable{
	private String name;
	private Couleur color;
	private List<ZoneMarchable> zoneMarchables;
	private Image highlight;
	private CarreArea button;
	private float scale;
	
	public Zone(String name, Couleur color, int x, int y, int hPlateau){
		this.name = name;
		this.color = color;
		this.zoneMarchables = new ArrayList<ZoneMarchable>();
		this.scale = ((float)hPlateau/(float)24187);
		this.button = new CarreArea((int)(x*scale),(int)(y*scale),(int)(2083*scale),(int)(1208*scale));
		
		try {
			this.highlight = new Image("res/img/zoneHL.png");
			this.highlight.setAlpha(0.2f);
		} catch (SlickException e) {
			System.out.println("SlickException: Erreur de chargement de l'image d'un node ("+e.getMessage()+")");
		}
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
	
	public void drawHL(){
		int w = (int) (this.button.getWidth()*1.5);
		int h = (int) (this.button.getHeight()*1.5);
		int x = this.button.getX()+(this.button.getWidth()/2)-(w/2);
		int y = this.button.getY()+(this.button.getHeight()/2)-(h/2);
		this.highlight.draw(x,y,w,h);
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
