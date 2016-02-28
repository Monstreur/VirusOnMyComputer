package jeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Zones.Passerelle;
import Zones.Zone;
import Zones.ZoneApparition;
import Zones.ZoneMarchable;
import autres.CarreArea;
import autres.Couleur;

public class Plateau {
	private List<ZoneApparition> zoneApparitions;
	private List<ZoneMarchable> zoneMarchables;
	private Image img;
	
	public Plateau(int hPlateau){
		this.zoneApparitions = new ArrayList<ZoneApparition>();
		this.zoneApparitions.add(new ZoneApparition(Couleur.BLUE,2362,23008,hPlateau));
		this.zoneApparitions.add(new ZoneApparition(Couleur.RED,23008,2362,hPlateau));
		this.zoneApparitions.add(new ZoneApparition(Couleur.GREEN,2362,0,hPlateau));
		this.zoneApparitions.add(new ZoneApparition(Couleur.YELLOW,0,2362,hPlateau));
		
		this.zoneMarchables = new ArrayList<ZoneMarchable>();
		List<Zone> zones = this.ajouteZones(hPlateau);
		List<Passerelle> passerelles = this.ajoutePasserelles(hPlateau);
		
		this.createLinksForZones(zones,passerelles);
		this.createLinksForPasserelles(zones,passerelles);

		this.zoneApparitions.get(0).addZoneMarchable(passerelles.get(0));
		this.zoneApparitions.get(1).addZoneMarchable(passerelles.get(4));
		this.zoneApparitions.get(2).addZoneMarchable(passerelles.get(8));
		this.zoneApparitions.get(3).addZoneMarchable(passerelles.get(12));

		this.zoneMarchables.addAll(zones);
		this.zoneMarchables.addAll(passerelles);
		
		try {
			this.img = new Image("res/img/plateau.png");
		} catch (SlickException e) {
			System.out.println("SlickException: Erreur de chargement de l'image du plateau ("+e.getMessage()+")");
		}
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException {
		this.img.draw(0, 0, container.getHeight(), container.getHeight());
	}

	public ZoneMarchable getZoneApparition(Couleur color) {
		for (ZoneApparition zm : this.zoneApparitions) {
			if(zm.getColor().equals(color)){
				return zm;
			}
		}
		return null;
	}
	
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
    	for (ZoneMarchable zm : zoneMarchables) {
			if(zm.getCarreArea().contain(newx, newy))
				System.out.println("In : "+zm);
		}
    }
	
	public void draw(Graphics g) {
		for (ZoneMarchable zm : zoneMarchables) {
			CarreArea tmp = zm.getCarreArea();
			if(tmp.isHover())
				tmp.draw(g);
		}
    }
	
	public static List<Integer> getZoneNums(Couleur color){
		List<Integer> ret = new ArrayList<Integer>();
		if(color.toString().equals("BLUE")){
			ret.add(0);
			ret.add(4);
			ret.add(8);
			ret.add(12);
			ret.add(16);
			ret.add(20);
		}
		else if(color.toString().equals("RED")){
			ret.add(1);
			ret.add(5);
			ret.add(9);
			ret.add(13);
			ret.add(17);
			ret.add(21);
		}
		else if(color.toString().equals("GREEN")){
			ret.add(2);
			ret.add(6);
			ret.add(10);
			ret.add(14);
			ret.add(18);
			ret.add(22);
		}
		else if(color.toString().equals("YELLOW")){
			ret.add(3);
			ret.add(7);
			ret.add(11);
			ret.add(15);
			ret.add(19);
			ret.add(23);
		}
		return ret;
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
	
	
	
	
	
	private List<Zone> ajouteZones(int hPlateau) {
		List<Zone> zones = new ArrayList<Zone>();
		zones.add(new Zone("01",Couleur.BLUE,16151,20265,hPlateau));
		zones.add(new Zone("02",Couleur.RED,20521,16089,hPlateau));
		zones.add(new Zone("03",Couleur.GREEN,11253,2646,hPlateau));
		zones.add(new Zone("04",Couleur.YELLOW,13594,17787,hPlateau));
		zones.add(new Zone("05",Couleur.BLUE,16151,15488,hPlateau));
		zones.add(new Zone("06",Couleur.RED,19479,11691,hPlateau));
		zones.add(new Zone("07",Couleur.GREEN,15651,1688,hPlateau));
		zones.add(new Zone("08",Couleur.YELLOW,2694,16579,hPlateau));
		zones.add(new Zone("09",Couleur.BLUE,10853,20265,hPlateau));
		zones.add(new Zone("10",Couleur.RED,19479,6393,hPlateau));
		zones.add(new Zone("11",Couleur.GREEN,5955,2646,hPlateau));
		zones.add(new Zone("12",Couleur.YELLOW,2693,11291,hPlateau));
		zones.add(new Zone("13",Couleur.BLUE,6455,21223,hPlateau));
		zones.add(new Zone("14",Couleur.RED,14702,6393,hPlateau));
		zones.add(new Zone("15",Couleur.GREEN,5172,14033,hPlateau));
		zones.add(new Zone("16",Couleur.YELLOW,1651,6893,hPlateau));
		zones.add(new Zone("17",Couleur.BLUE,17001,8949,hPlateau));
		zones.add(new Zone("18",Couleur.RED,8512,5124,hPlateau));
		zones.add(new Zone("19",Couleur.GREEN,5955,7423,hPlateau));
		zones.add(new Zone("20",Couleur.YELLOW,7471,16589,hPlateau));
		zones.add(new Zone("21",Couleur.BLUE,11053,13165,hPlateau));
		zones.add(new Zone("22",Couleur.RED,13136,11466,hPlateau));
		zones.add(new Zone("23",Couleur.GREEN,11053,9816,hPlateau));
		zones.add(new Zone("24",Couleur.YELLOW,8970,11466,hPlateau));
		return zones;
	}
	
	private List<Passerelle> ajoutePasserelles(int hPlateau) {
		List<Passerelle> passerelles = new ArrayList<Passerelle>();
		passerelles.add(new Passerelle(1,14289,20477,hPlateau));
		passerelles.add(new Passerelle(2,19479,20688,hPlateau));
		passerelles.add(new Passerelle(3,21216,19480,hPlateau));
		passerelles.add(new Passerelle(4,20431,14033,hPlateau));
		passerelles.add(new Passerelle(5,20172,9207,hPlateau));
		passerelles.add(new Passerelle(6,20431,4245,hPlateau));
		passerelles.add(new Passerelle(7,19080,2549,hPlateau));
		passerelles.add(new Passerelle(8,14178,2409,hPlateau));
		passerelles.add(new Passerelle(9,9207,2763,hPlateau));
		passerelles.add(new Passerelle(10,4017,2552,hPlateau));
		passerelles.add(new Passerelle(11,2280,3760,hPlateau));
		passerelles.add(new Passerelle(12,3039,9296,hPlateau));
		passerelles.add(new Passerelle(13,3298,14122,hPlateau));
		passerelles.add(new Passerelle(14,3039,19084,hPlateau));
		passerelles.add(new Passerelle(15,4390,20780,hPlateau));
		passerelles.add(new Passerelle(16,9318,20831,hPlateau));
		passerelles.add(new Passerelle(17,4730,18692,hPlateau));
		passerelles.add(new Passerelle(18,6469,17886,hPlateau));
		passerelles.add(new Passerelle(19,8970,18302,hPlateau));
		passerelles.add(new Passerelle(20,11548,17999,hPlateau));
		passerelles.add(new Passerelle(21,16500,17797,hPlateau));
		passerelles.add(new Passerelle(22,18786,18695,hPlateau));
		passerelles.add(new Passerelle(23,17887,17394,hPlateau));
		passerelles.add(new Passerelle(24,18188,13944,hPlateau));
		passerelles.add(new Passerelle(25,17877,11549,hPlateau));
		passerelles.add(new Passerelle(26,17955,7254,hPlateau));
		passerelles.add(new Passerelle(27,18740,4637,hPlateau));
		passerelles.add(new Passerelle(28,17001,5443,hPlateau));
		passerelles.add(new Passerelle(29,14526,4938,hPlateau));
		passerelles.add(new Passerelle(30,11948,5241,hPlateau));
		passerelles.add(new Passerelle(31,6996,5443,hPlateau));
		passerelles.add(new Passerelle(32,4710,4545,hPlateau));
		passerelles.add(new Passerelle(33,5609,5846,hPlateau));
		passerelles.add(new Passerelle(34,5282,9385,hPlateau));
		passerelles.add(new Passerelle(35,5583,11780,hPlateau));
		passerelles.add(new Passerelle(36,5515,16075,hPlateau));
		passerelles.add(new Passerelle(37,10560,15911,hPlateau));
		passerelles.add(new Passerelle(38,12443,16791,hPlateau));
		passerelles.add(new Passerelle(39,13336,15304,hPlateau));
		passerelles.add(new Passerelle(40,16393,12819,hPlateau));
		passerelles.add(new Passerelle(41,15907,11466,hPlateau));
		passerelles.add(new Passerelle(42,15907,9863,hPlateau));
		passerelles.add(new Passerelle(43,12936,7329,hPlateau));
		passerelles.add(new Passerelle(44,11053,6449,hPlateau));
		passerelles.add(new Passerelle(45,10160,7936,hPlateau));
		passerelles.add(new Passerelle(46,7077,10510,hPlateau));
		passerelles.add(new Passerelle(47,7563,11863,hPlateau));
		passerelles.add(new Passerelle(48,7563,13466,hPlateau));
		passerelles.add(new Passerelle(49,8971,13769,hPlateau));
		passerelles.add(new Passerelle(50,14590,13512,hPlateau));
		passerelles.add(new Passerelle(51,14525,9471,hPlateau));
		passerelles.add(new Passerelle(52,8880,9817,hPlateau));
		passerelles.add(new Passerelle(53,11253,11203,hPlateau));
		passerelles.add(new Passerelle(54,11255,12210,hPlateau));
		passerelles.add(new Passerelle(55,12241,12210,hPlateau));
		passerelles.add(new Passerelle(56,12241,11203,hPlateau));
		return passerelles;
	}
	
	private void createLinksForZones(List<Zone> zones, List<Passerelle> passerelles) {
		zones.get(0).addZoneMarchable(passerelles.get(0));
		zones.get(0).addZoneMarchable(passerelles.get(1));

		zones.get(1).addZoneMarchable(passerelles.get(2));
		zones.get(1).addZoneMarchable(passerelles.get(3));
		zones.get(1).addZoneMarchable(passerelles.get(23));

		zones.get(2).addZoneMarchable(passerelles.get(7));
		zones.get(2).addZoneMarchable(passerelles.get(8));
		
		zones.get(3).addZoneMarchable(passerelles.get(0));
		zones.get(3).addZoneMarchable(passerelles.get(19));
		zones.get(3).addZoneMarchable(passerelles.get(20));
		zones.get(3).addZoneMarchable(passerelles.get(37));
		zones.get(3).addZoneMarchable(passerelles.get(38));

		zones.get(4).addZoneMarchable(passerelles.get(20));
		zones.get(4).addZoneMarchable(passerelles.get(22));
		zones.get(4).addZoneMarchable(passerelles.get(38));
		zones.get(4).addZoneMarchable(passerelles.get(39));

		zones.get(5).addZoneMarchable(passerelles.get(3));
		zones.get(5).addZoneMarchable(passerelles.get(4));

		zones.get(6).addZoneMarchable(passerelles.get(6));
		zones.get(6).addZoneMarchable(passerelles.get(7));
		zones.get(6).addZoneMarchable(passerelles.get(28));

		zones.get(7).addZoneMarchable(passerelles.get(12));
		zones.get(7).addZoneMarchable(passerelles.get(13));

		zones.get(8).addZoneMarchable(passerelles.get(0));
		zones.get(8).addZoneMarchable(passerelles.get(15));

		zones.get(9).addZoneMarchable(passerelles.get(4));
		zones.get(9).addZoneMarchable(passerelles.get(5));

		zones.get(10).addZoneMarchable(passerelles.get(8));
		zones.get(10).addZoneMarchable(passerelles.get(9));

		zones.get(11).addZoneMarchable(passerelles.get(11));
		zones.get(11).addZoneMarchable(passerelles.get(12));

		zones.get(12).addZoneMarchable(passerelles.get(14));
		zones.get(12).addZoneMarchable(passerelles.get(15));
		zones.get(12).addZoneMarchable(passerelles.get(18));

		zones.get(13).addZoneMarchable(passerelles.get(25));
		zones.get(13).addZoneMarchable(passerelles.get(27));
		zones.get(13).addZoneMarchable(passerelles.get(41));
		zones.get(13).addZoneMarchable(passerelles.get(42));

		zones.get(14).addZoneMarchable(passerelles.get(12));
		zones.get(14).addZoneMarchable(passerelles.get(34));
		zones.get(14).addZoneMarchable(passerelles.get(35));
		zones.get(14).addZoneMarchable(passerelles.get(46));
		zones.get(14).addZoneMarchable(passerelles.get(47));

		zones.get(15).addZoneMarchable(passerelles.get(10));
		zones.get(15).addZoneMarchable(passerelles.get(11));
		zones.get(15).addZoneMarchable(passerelles.get(33));

		zones.get(16).addZoneMarchable(passerelles.get(4));
		zones.get(16).addZoneMarchable(passerelles.get(24));
		zones.get(16).addZoneMarchable(passerelles.get(25));
		zones.get(16).addZoneMarchable(passerelles.get(40));
		zones.get(16).addZoneMarchable(passerelles.get(41));

		zones.get(17).addZoneMarchable(passerelles.get(8));
		zones.get(17).addZoneMarchable(passerelles.get(29));
		zones.get(17).addZoneMarchable(passerelles.get(30));
		zones.get(17).addZoneMarchable(passerelles.get(43));
		zones.get(17).addZoneMarchable(passerelles.get(44));

		zones.get(18).addZoneMarchable(passerelles.get(30));
		zones.get(18).addZoneMarchable(passerelles.get(32));
		zones.get(18).addZoneMarchable(passerelles.get(44));
		zones.get(18).addZoneMarchable(passerelles.get(45));

		zones.get(19).addZoneMarchable(passerelles.get(17));
		zones.get(19).addZoneMarchable(passerelles.get(35));
		zones.get(19).addZoneMarchable(passerelles.get(36));
		zones.get(19).addZoneMarchable(passerelles.get(47));

		zones.get(20).addZoneMarchable(passerelles.get(38));
		zones.get(20).addZoneMarchable(passerelles.get(48));
		zones.get(20).addZoneMarchable(passerelles.get(49));
		zones.get(20).addZoneMarchable(passerelles.get(53));
		zones.get(20).addZoneMarchable(passerelles.get(54));

		zones.get(21).addZoneMarchable(passerelles.get(41));
		zones.get(21).addZoneMarchable(passerelles.get(49));
		zones.get(21).addZoneMarchable(passerelles.get(50));
		zones.get(21).addZoneMarchable(passerelles.get(54));
		zones.get(21).addZoneMarchable(passerelles.get(55));

		zones.get(22).addZoneMarchable(passerelles.get(44));
		zones.get(22).addZoneMarchable(passerelles.get(50));
		zones.get(22).addZoneMarchable(passerelles.get(51));
		zones.get(22).addZoneMarchable(passerelles.get(52));
		zones.get(22).addZoneMarchable(passerelles.get(55));

		zones.get(23).addZoneMarchable(passerelles.get(47));
		zones.get(23).addZoneMarchable(passerelles.get(48));
		zones.get(23).addZoneMarchable(passerelles.get(51));
		zones.get(23).addZoneMarchable(passerelles.get(52));
		zones.get(23).addZoneMarchable(passerelles.get(53));
	}
	
	private void createLinksForPasserelles(List<Zone> zones, List<Passerelle> passerelles) {
		passerelles.get(0).addZoneMarchable(zones.get(0));
		passerelles.get(0).addZoneMarchable(zones.get(3));
		passerelles.get(0).addZoneMarchable(zones.get(8));
		passerelles.get(0).addZoneMarchable(this.zoneApparitions.get(0));

		passerelles.get(1).addZoneMarchable(zones.get(0));
		passerelles.get(1).addZoneMarchable(passerelles.get(2));
		passerelles.get(1).addZoneMarchable(passerelles.get(21));

		passerelles.get(2).addZoneMarchable(zones.get(1));
		passerelles.get(2).addZoneMarchable(passerelles.get(1));

		passerelles.get(3).addZoneMarchable(zones.get(1));
		passerelles.get(3).addZoneMarchable(zones.get(5));

		passerelles.get(4).addZoneMarchable(zones.get(5));
		passerelles.get(4).addZoneMarchable(zones.get(9));
		passerelles.get(4).addZoneMarchable(zones.get(16));
		passerelles.get(4).addZoneMarchable(this.zoneApparitions.get(1));

		passerelles.get(5).addZoneMarchable(zones.get(9));
		passerelles.get(5).addZoneMarchable(passerelles.get(6));
		passerelles.get(5).addZoneMarchable(passerelles.get(26));
		
		passerelles.get(6).addZoneMarchable(zones.get(6));
		passerelles.get(6).addZoneMarchable(passerelles.get(5));

		passerelles.get(7).addZoneMarchable(zones.get(2));
		
		passerelles.get(8).addZoneMarchable(zones.get(2));
		passerelles.get(8).addZoneMarchable(zones.get(10));
		passerelles.get(8).addZoneMarchable(zones.get(17));
		passerelles.get(8).addZoneMarchable(this.zoneApparitions.get(2));

		passerelles.get(9).addZoneMarchable(zones.get(10));
		passerelles.get(9).addZoneMarchable(passerelles.get(10));
		passerelles.get(9).addZoneMarchable(passerelles.get(31));
		
		passerelles.get(10).addZoneMarchable(zones.get(15));
		passerelles.get(10).addZoneMarchable(passerelles.get(9));

		passerelles.get(11).addZoneMarchable(zones.get(15));
		passerelles.get(11).addZoneMarchable(zones.get(11));

		passerelles.get(12).addZoneMarchable(zones.get(7));
		passerelles.get(12).addZoneMarchable(zones.get(11));
		passerelles.get(12).addZoneMarchable(zones.get(14));
		passerelles.get(12).addZoneMarchable(this.zoneApparitions.get(3));
		
		passerelles.get(13).addZoneMarchable(zones.get(7));
		passerelles.get(13).addZoneMarchable(passerelles.get(14));
		passerelles.get(13).addZoneMarchable(passerelles.get(16));
		
		passerelles.get(14).addZoneMarchable(zones.get(12));
		passerelles.get(14).addZoneMarchable(passerelles.get(13));
		
		passerelles.get(15).addZoneMarchable(zones.get(8));		
		passerelles.get(15).addZoneMarchable(zones.get(12));
		
		passerelles.get(16).addZoneMarchable(passerelles.get(13));
		passerelles.get(16).addZoneMarchable(passerelles.get(17));
		
		passerelles.get(17).addZoneMarchable(zones.get(19));
		passerelles.get(17).addZoneMarchable(passerelles.get(16));

		passerelles.get(18).addZoneMarchable(zones.get(12));
		passerelles.get(18).addZoneMarchable(passerelles.get(19));
		passerelles.get(18).addZoneMarchable(passerelles.get(36));
		
		passerelles.get(19).addZoneMarchable(zones.get(3));
		passerelles.get(19).addZoneMarchable(passerelles.get(18));
		
		passerelles.get(20).addZoneMarchable(zones.get(3));
		passerelles.get(20).addZoneMarchable(zones.get(4));

		passerelles.get(21).addZoneMarchable(passerelles.get(1));
		passerelles.get(21).addZoneMarchable(passerelles.get(22));

		passerelles.get(22).addZoneMarchable(zones.get(4));
		passerelles.get(22).addZoneMarchable(passerelles.get(21));

		passerelles.get(23).addZoneMarchable(zones.get(1));
		passerelles.get(23).addZoneMarchable(passerelles.get(24));
		passerelles.get(23).addZoneMarchable(passerelles.get(39));

		passerelles.get(24).addZoneMarchable(zones.get(16));
		passerelles.get(24).addZoneMarchable(passerelles.get(23));

		passerelles.get(25).addZoneMarchable(zones.get(13));
		passerelles.get(25).addZoneMarchable(zones.get(16));

		passerelles.get(26).addZoneMarchable(passerelles.get(5));
		passerelles.get(26).addZoneMarchable(passerelles.get(27));

		passerelles.get(27).addZoneMarchable(zones.get(13));
		passerelles.get(27).addZoneMarchable(passerelles.get(26));

		passerelles.get(28).addZoneMarchable(zones.get(6));
		passerelles.get(28).addZoneMarchable(passerelles.get(29));
		passerelles.get(28).addZoneMarchable(passerelles.get(42));
		
		passerelles.get(29).addZoneMarchable(zones.get(17));
		passerelles.get(29).addZoneMarchable(passerelles.get(28));
		
		passerelles.get(30).addZoneMarchable(zones.get(17));
		passerelles.get(30).addZoneMarchable(zones.get(18));

		passerelles.get(31).addZoneMarchable(passerelles.get(9));
		passerelles.get(31).addZoneMarchable(passerelles.get(32));
		
		passerelles.get(32).addZoneMarchable(zones.get(18));
		passerelles.get(32).addZoneMarchable(passerelles.get(31));
		
		passerelles.get(33).addZoneMarchable(zones.get(15));
		passerelles.get(33).addZoneMarchable(passerelles.get(34));
		passerelles.get(33).addZoneMarchable(passerelles.get(45));
		
		passerelles.get(34).addZoneMarchable(zones.get(14));
		passerelles.get(34).addZoneMarchable(passerelles.get(33));
		
		passerelles.get(35).addZoneMarchable(zones.get(14));
		passerelles.get(35).addZoneMarchable(zones.get(19));
		
		passerelles.get(36).addZoneMarchable(zones.get(19));
		passerelles.get(36).addZoneMarchable(passerelles.get(37));
		passerelles.get(36).addZoneMarchable(passerelles.get(48));
		
		passerelles.get(37).addZoneMarchable(zones.get(3));
		passerelles.get(37).addZoneMarchable(passerelles.get(36));
		passerelles.get(37).addZoneMarchable(passerelles.get(38));
		
		passerelles.get(38).addZoneMarchable(zones.get(3));
		passerelles.get(38).addZoneMarchable(zones.get(4));
		passerelles.get(38).addZoneMarchable(zones.get(20));
		passerelles.get(38).addZoneMarchable(passerelles.get(37));
		
		passerelles.get(39).addZoneMarchable(zones.get(4));
		passerelles.get(39).addZoneMarchable(passerelles.get(23));
		passerelles.get(39).addZoneMarchable(passerelles.get(40));
		passerelles.get(39).addZoneMarchable(passerelles.get(49));
		
		passerelles.get(40).addZoneMarchable(zones.get(16));
		passerelles.get(40).addZoneMarchable(passerelles.get(39));
		passerelles.get(40).addZoneMarchable(passerelles.get(41));
		
		passerelles.get(41).addZoneMarchable(zones.get(13));
		passerelles.get(41).addZoneMarchable(zones.get(16));
		passerelles.get(41).addZoneMarchable(zones.get(21));
		passerelles.get(41).addZoneMarchable(passerelles.get(40));
		
		passerelles.get(42).addZoneMarchable(zones.get(13));
		passerelles.get(42).addZoneMarchable(passerelles.get(28));
		passerelles.get(42).addZoneMarchable(passerelles.get(43));
		passerelles.get(42).addZoneMarchable(passerelles.get(50));
		
		passerelles.get(43).addZoneMarchable(zones.get(17));
		passerelles.get(43).addZoneMarchable(passerelles.get(42));
		passerelles.get(43).addZoneMarchable(passerelles.get(44));
		
		passerelles.get(44).addZoneMarchable(zones.get(17));
		passerelles.get(44).addZoneMarchable(zones.get(18));
		passerelles.get(44).addZoneMarchable(zones.get(22));
		passerelles.get(44).addZoneMarchable(passerelles.get(43));
		
		passerelles.get(45).addZoneMarchable(zones.get(18));
		passerelles.get(45).addZoneMarchable(passerelles.get(33));
		passerelles.get(45).addZoneMarchable(passerelles.get(46));
		passerelles.get(45).addZoneMarchable(passerelles.get(51));
		
		passerelles.get(46).addZoneMarchable(zones.get(14));
		passerelles.get(46).addZoneMarchable(passerelles.get(45));
		passerelles.get(46).addZoneMarchable(passerelles.get(47));
		
		passerelles.get(47).addZoneMarchable(zones.get(14));
		passerelles.get(47).addZoneMarchable(zones.get(19));
		passerelles.get(47).addZoneMarchable(zones.get(23));
		passerelles.get(47).addZoneMarchable(passerelles.get(46));

		passerelles.get(48).addZoneMarchable(zones.get(20));
		passerelles.get(48).addZoneMarchable(zones.get(23));
		passerelles.get(48).addZoneMarchable(passerelles.get(36));
		
		passerelles.get(49).addZoneMarchable(zones.get(20));
		passerelles.get(49).addZoneMarchable(zones.get(21));
		passerelles.get(49).addZoneMarchable(passerelles.get(39));
		
		passerelles.get(50).addZoneMarchable(zones.get(21));
		passerelles.get(50).addZoneMarchable(zones.get(22));
		passerelles.get(50).addZoneMarchable(passerelles.get(42));
		
		passerelles.get(51).addZoneMarchable(zones.get(22));
		passerelles.get(51).addZoneMarchable(zones.get(23));
		passerelles.get(51).addZoneMarchable(passerelles.get(45));
		
		passerelles.get(52).addZoneMarchable(zones.get(22));
		passerelles.get(52).addZoneMarchable(zones.get(23));
		passerelles.get(52).addZoneMarchable(passerelles.get(53));
		passerelles.get(52).addZoneMarchable(passerelles.get(55));
		
		passerelles.get(53).addZoneMarchable(zones.get(20));
		passerelles.get(53).addZoneMarchable(zones.get(23));
		passerelles.get(53).addZoneMarchable(passerelles.get(52));
		passerelles.get(53).addZoneMarchable(passerelles.get(54));
		
		passerelles.get(54).addZoneMarchable(zones.get(20));
		passerelles.get(54).addZoneMarchable(zones.get(21));
		passerelles.get(54).addZoneMarchable(passerelles.get(53));
		passerelles.get(54).addZoneMarchable(passerelles.get(55));
		
		passerelles.get(55).addZoneMarchable(zones.get(21));
		passerelles.get(55).addZoneMarchable(zones.get(22));
		passerelles.get(55).addZoneMarchable(passerelles.get(52));
		passerelles.get(55).addZoneMarchable(passerelles.get(54));
	}
}
