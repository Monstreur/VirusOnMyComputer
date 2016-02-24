package jeu;

import java.util.ArrayList;
import java.util.List;

public class Plateau {
	private List<ZoneApparition> zoneApparitions;
	private List<ZoneMarchable> zoneMarchables;
	
	public Plateau(){
		this.zoneApparitions = new ArrayList<ZoneApparition>();
		this.zoneApparitions.add(ZoneApparition.BLUE);
		this.zoneApparitions.add(ZoneApparition.RED);
		this.zoneApparitions.add(ZoneApparition.GREEN);
		this.zoneApparitions.add(ZoneApparition.YELLOW);
		
		this.zoneMarchables = new ArrayList<ZoneMarchable>();
		this.zoneMarchables.add(new Zone("01",Couleur.BLUE));
		this.zoneMarchables.add(new Zone("02",Couleur.RED));
		this.zoneMarchables.add(new Zone("03",Couleur.GREEN));
		this.zoneMarchables.add(new Zone("04",Couleur.YELLOW));
		this.zoneMarchables.add(new Zone("05",Couleur.BLUE));
		this.zoneMarchables.add(new Zone("06",Couleur.RED));
		this.zoneMarchables.add(new Zone("07",Couleur.GREEN));
		this.zoneMarchables.add(new Zone("08",Couleur.YELLOW));
		this.zoneMarchables.add(new Zone("09",Couleur.BLUE));
		this.zoneMarchables.add(new Zone("10",Couleur.RED));
		this.zoneMarchables.add(new Zone("11",Couleur.GREEN));
		this.zoneMarchables.add(new Zone("12",Couleur.YELLOW));
		this.zoneMarchables.add(new Zone("13",Couleur.BLUE));
		this.zoneMarchables.add(new Zone("14",Couleur.RED));
		this.zoneMarchables.add(new Zone("15",Couleur.GREEN));
		this.zoneMarchables.add(new Zone("16",Couleur.YELLOW));
		this.zoneMarchables.add(new Zone("17",Couleur.BLUE));
		this.zoneMarchables.add(new Zone("18",Couleur.RED));
		this.zoneMarchables.add(new Zone("19",Couleur.GREEN));
		this.zoneMarchables.add(new Zone("20",Couleur.YELLOW));
		this.zoneMarchables.add(new Zone("21",Couleur.BLUE));
		this.zoneMarchables.add(new Zone("22",Couleur.RED));
		this.zoneMarchables.add(new Zone("23",Couleur.GREEN));
		this.zoneMarchables.add(new Zone("24",Couleur.YELLOW));

		this.zoneMarchables.add(new Passerelle());
		this.zoneMarchables.add(new Passerelle());
		this.zoneMarchables.add(new Passerelle());
		this.zoneMarchables.add(new Passerelle());
		this.zoneMarchables.add(new Passerelle());
		this.zoneMarchables.add(new Passerelle());
		this.zoneMarchables.add(new Passerelle());
		this.zoneMarchables.add(new Passerelle());
		this.zoneMarchables.add(new Passerelle());
	}

	@Override
	public String toString() {
		String ret = "[Plateau]\n";
		for (ZoneApparition za : zoneApparitions) {
			ret += "\t"+za.toString()+"\n";
		}
		for (ZoneMarchable z : zoneMarchables) {
			ret += "\t"+z.toString()+"\n";
		}
		return ret;
	}
	
	
}
