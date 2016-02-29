package Zones;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
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
		float scale = ((float)hPlateau/(float)24187);
		this.button = new CarreArea((int)(x*scale),(int)(y*scale),(int)(693*scale),(int)(785*scale));
		
		try {
			this.highlight = new Image("res/img/nodeHL.png");
			this.highlight.setAlpha(0.2f);
		} catch (SlickException e) {
			System.out.println("SlickException: Erreur de chargement de l'image d'un node ("+e.getMessage()+")");
		}
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
	@Override
	public void drawHL() {
		int w = (int) (this.button.getWidth()*1.5);
		int h = (int) (this.button.getHeight()*1.5);
		int x = this.button.getX()+(this.button.getWidth()/2)-(w/2);
		int y = this.button.getY()+(this.button.getHeight()/2)-(h/2);
		this.highlight.draw(x,y,w,h);
	}
}
