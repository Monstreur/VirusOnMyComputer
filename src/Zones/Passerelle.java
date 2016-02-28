package Zones;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import autres.CarreArea;

public class Passerelle implements ZoneMarchable{
	private List<ZoneMarchable> zoneMarchables;
	private int num;
	private Image highlight;
	private CarreArea button;

	public Passerelle(int num, int x, int y, int hPlateau){
		this.zoneMarchables = new ArrayList<ZoneMarchable>();
		this.num=num;
		try {
			this.highlight = new Image("res/img/nodeHL.png");
		} catch (SlickException e) {
			System.out.println("SlickException: Erreur de chargement de l'image d'un node ("+e.getMessage()+")");
		}
		float scale = ((float)hPlateau/(float)24187);
		this.button = new CarreArea((int)(x*scale),(int)(y*scale),(int)(693*scale),(int)(785*scale));
	}
	@Override
	public List<ZoneMarchable> getZoneMarchables() {
		return zoneMarchables;
	}

	@Override
	public boolean addZoneMarchable(ZoneMarchable zm) {
		return this.zoneMarchables.add(zm);
	}

	public String toString(){
		return "[Passerelle "+num+"] "+button;
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
