package Zones;

import java.util.ArrayList;
import java.util.List;

import autres.CarreArea;
import autres.Couleur;

public class ZoneApparition implements ZoneMarchable {
	
	private Couleur color;
	private List<ZoneMarchable> zoneMarchables;
	private CarreArea button;
	
	public ZoneApparition(Couleur color, int x, int y, int hPlateau){
		this.color = color;
		this.zoneMarchables = new ArrayList<ZoneMarchable>();
		float scale = ((float)hPlateau/(float)24187);
		this.button = new CarreArea((int)(x*scale),(int)(y*scale),(int)(19465*scale),(int)(1181*scale));
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

	@Override
	public void setButtonHover(boolean hover){
		this.button.setHover(hover);
	}
	@Override
	public CarreArea getCarreArea() {
		return this.button;
	}
}
