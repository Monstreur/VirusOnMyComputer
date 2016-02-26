package Zones;

import java.util.ArrayList;
import java.util.List;

public class Passerelle implements ZoneMarchable{
	private List<ZoneMarchable> zoneMarchables;
	private int num;

	public Passerelle(int num){
		this.zoneMarchables = new ArrayList<ZoneMarchable>();
		this.num=num;
	}
	
	public List<ZoneMarchable> getZoneMarchables() {
		return zoneMarchables;
	}

	@Override
	public boolean addZoneMarchable(ZoneMarchable zm) {
		return this.zoneMarchables.add(zm);
	}

	public String toString(){
		return "[Passerelle "+num+"]";
	}
}
