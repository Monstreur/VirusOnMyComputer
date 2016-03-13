package huds;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import autres.Couleur;
import intelligenceArtificielle.IAZone;
import intelligenceArtificielle.IAZoneCode;
import jeu.Plateau;

public class CarnetDeBord {
	private int x;
	private int y;
	private int width;
	private int height;
	private List<IAZone> zoneBleu;
	private List<IAZone> zoneRouge;
	private List<IAZone> zoneVerte;
	private List<IAZone> zoneJaune;
	
	public CarnetDeBord(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		List<Integer> list;
		
		list = Plateau.getZoneNums(Couleur.BLUE);
		this.zoneBleu = new ArrayList<IAZone>();
		this.zoneBleu.add(new IAZone(list.get(0).intValue(), IAZoneCode.Rien));
		this.zoneBleu.add(new IAZone(list.get(1).intValue(), IAZoneCode.Rien));
		this.zoneBleu.add(new IAZone(list.get(2).intValue(), IAZoneCode.Rien));
		this.zoneBleu.add(new IAZone(list.get(3).intValue(), IAZoneCode.Rien));
		this.zoneBleu.add(new IAZone(list.get(4).intValue(), IAZoneCode.Rien));
		this.zoneBleu.add(new IAZone(list.get(5).intValue(), IAZoneCode.Rien));
		
		list = Plateau.getZoneNums(Couleur.RED);
		this.zoneRouge = new ArrayList<IAZone>();
		this.zoneRouge.add(new IAZone(list.get(0).intValue(), IAZoneCode.Rien));
		this.zoneRouge.add(new IAZone(list.get(1).intValue(), IAZoneCode.Rien));
		this.zoneRouge.add(new IAZone(list.get(2).intValue(), IAZoneCode.Rien));
		this.zoneRouge.add(new IAZone(list.get(3).intValue(), IAZoneCode.Rien));
		this.zoneRouge.add(new IAZone(list.get(4).intValue(), IAZoneCode.Rien));
		this.zoneRouge.add(new IAZone(list.get(5).intValue(), IAZoneCode.Rien));
		
		list = Plateau.getZoneNums(Couleur.GREEN);
		this.zoneVerte = new ArrayList<IAZone>();
		this.zoneVerte.add(new IAZone(list.get(0).intValue(), IAZoneCode.Rien));
		this.zoneVerte.add(new IAZone(list.get(1).intValue(), IAZoneCode.Rien));
		this.zoneVerte.add(new IAZone(list.get(2).intValue(), IAZoneCode.Rien));
		this.zoneVerte.add(new IAZone(list.get(3).intValue(), IAZoneCode.Rien));
		this.zoneVerte.add(new IAZone(list.get(4).intValue(), IAZoneCode.Rien));
		this.zoneVerte.add(new IAZone(list.get(5).intValue(), IAZoneCode.Rien));
		
		list = Plateau.getZoneNums(Couleur.YELLOW);
		this.zoneJaune = new ArrayList<IAZone>();
		this.zoneJaune.add(new IAZone(list.get(0).intValue(), IAZoneCode.Rien));
		this.zoneJaune.add(new IAZone(list.get(1).intValue(), IAZoneCode.Rien));
		this.zoneJaune.add(new IAZone(list.get(2).intValue(), IAZoneCode.Rien));
		this.zoneJaune.add(new IAZone(list.get(3).intValue(), IAZoneCode.Rien));
		this.zoneJaune.add(new IAZone(list.get(4).intValue(), IAZoneCode.Rien));
		this.zoneJaune.add(new IAZone(list.get(5).intValue(), IAZoneCode.Rien));
		
	}
	
	public IAZone findZone(int numZone){
		Couleur c = Plateau.getColorOfZone(numZone);
		if(c.name().equals("BLUE")){
			for (IAZone iaZone : zoneBleu) {
				if(iaZone.getNumZone()==numZone)
					return iaZone;
			}
		}else if(c.name().equals("RED")){
			for (IAZone iaZone : zoneRouge) {
				if(iaZone.getNumZone()==numZone)
					return iaZone;
			}
		}else if(c.name().equals("GREEN")){
			for (IAZone iaZone : zoneVerte) {
				if(iaZone.getNumZone()==numZone)
					return iaZone;
			}
		}else if(c.name().equals("YELLOW")){
			for (IAZone iaZone : zoneJaune) {
				if(iaZone.getNumZone()==numZone)
					return iaZone;
			}
		}
		return null;
	}
	
	public void setDecouverte(int numZone, IAZoneCode iazonecode){
		this.findZone(numZone).setCodeZone(iazonecode);
	}
	
	public void render(Graphics g){
		int i = height/4;
		int cpt;
		int cptY;
		int margeExt = width/10;
		int margeInt = width/10;
		int taille = width/5;
		int margeYExt = i/20;
		int margeYInt = i/10;
		int tailleY = i*4/10;
		g.setColor(new Color(100, 100, 255));
		g.drawRect(x, 0, width, i);
		
		cpt = 0;
		cptY = 0;
		for (IAZone iaZone : zoneBleu) {
			g.drawString(""+iaZone.getNumZone(), x+margeExt+cpt*(taille+margeInt), y+margeYExt+cptY*(tailleY+margeYInt));
			g.drawRect(x+margeExt+cpt*(taille+margeInt), y+margeYExt+cptY*(tailleY+margeYInt), taille, tailleY);
			g.setColor(Color.white);
			g.drawString(""+iaZone.getCodeLetter(), x+margeExt+cpt*(taille+margeInt)+taille/2-g.getFont().getWidth(""+iaZone.getCodeLetter())/2, y+margeYExt+cptY*(tailleY+margeYInt)+tailleY/2-7);
			g.setColor(new Color(100, 100, 255));
			
			cpt++;
			if(cpt==3){
				cpt=0;
				cptY++;
			}
		}
		
		g.setColor(new Color(255, 100, 100));
		g.drawRect(x, i, width, i);
		cpt = 0;
		cptY = 0;
		for (IAZone iaZone : zoneRouge) {
			g.drawString(""+iaZone.getNumZone(), x+margeExt+cpt*(taille+margeInt), y+i+margeYExt+cptY*(tailleY+margeYInt));
			g.drawRect(x+margeExt+cpt*(taille+margeInt), y+i+margeYExt+cptY*(tailleY+margeYInt), taille, tailleY);
			g.setColor(Color.white);
			g.drawString(""+iaZone.getCodeLetter(), x+margeExt+cpt*(taille+margeInt)+taille/2-g.getFont().getWidth(""+iaZone.getCodeLetter())/2, y+i+margeYExt+cptY*(tailleY+margeYInt)+tailleY/2-7);
			g.setColor(new Color(255, 100, 100));
			
			cpt++;
			if(cpt==3){
				cpt=0;
				cptY++;
			}
		}
		
		g.setColor(new Color(100, 255, 100));
		g.drawRect(x, 2*i, width, i);
		cpt = 0;
		cptY = 0;
		for (IAZone iaZone : zoneVerte) {
			g.drawString(""+iaZone.getNumZone(), x+margeExt+cpt*(taille+margeInt), y+2*i+margeYExt+cptY*(tailleY+margeYInt));
			g.drawRect(x+margeExt+cpt*(taille+margeInt), y+2*i+margeYExt+cptY*(tailleY+margeYInt), taille, tailleY);
			g.setColor(Color.white);
			g.drawString(""+iaZone.getCodeLetter(), x+margeExt+cpt*(taille+margeInt)+taille/2-g.getFont().getWidth(""+iaZone.getCodeLetter())/2, y+2*i+margeYExt+cptY*(tailleY+margeYInt)+tailleY/2-7);
			g.setColor(new Color(100, 255, 100));
			
			cpt++;
			if(cpt==3){
				cpt=0;
				cptY++;
			}
		}
		
		g.setColor(new Color(255, 255, 100));
		g.drawRect(x, 3*i, width, i);
		cpt = 0;
		cptY = 0;
		for (IAZone iaZone : zoneJaune) {
			g.drawString(""+iaZone.getNumZone(), x+margeExt+cpt*(taille+margeInt), y+3*i+margeYExt+cptY*(tailleY+margeYInt));
			g.drawRect(x+margeExt+cpt*(taille+margeInt), y+3*i+margeYExt+cptY*(tailleY+margeYInt), taille, tailleY);
			g.setColor(Color.white);
			g.drawString(""+iaZone.getCodeLetter(), x+margeExt+cpt*(taille+margeInt)+taille/2-g.getFont().getWidth(""+iaZone.getCodeLetter())/2, y+3*i+margeYExt+cptY*(tailleY+margeYInt)+tailleY/2-7);
			g.setColor(new Color(255, 255, 100));
			
			cpt++;
			if(cpt==3){
				cpt=0;
				cptY++;
			}
		}
	}
	
}
