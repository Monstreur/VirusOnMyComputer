package main;


import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import Joueurs.Joueur;
import Zones.Zone;
import Zones.ZoneMarchable;
import jeu.Hud;
import jeu.Jeu;
import autres.AlertWindow;
import autres.Couleur;
import autres.Touche;
import intelligenceArtificielle.IAZone;
import intelligenceArtificielle.IAZoneCode;

public class FenetreJeu extends BasicGame {
    private GameContainer container;
    private int timepast;
    private Jeu jeu;
    private Hud hud;
    private Touche[] touches;
	private List<ZoneMarchable> deplacementPossible;
	private AlertWindow alertWindow;
	private Font font;

	public FenetreJeu() {
        super("Virus on my Computer");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
    	this.container = container;
		container.setShowFPS(false);
		this.timepast=0;
		this.jeu = new Jeu(container.getHeight());
		
		this.touches=new Touche[3];
		this.touches[Touche.Attaquer]= new Touche(Input.KEY_TAB);
		this.touches[Touche.Explorer]= new Touche(Input.KEY_ENTER);
		this.touches[Touche.Passer]= new Touche(Input.KEY_SPACE);
		
		this.deplacementPossible = new ArrayList<ZoneMarchable>();
		
		this.hud = new Hud(container.getHeight(), 0,container.getWidth()-container.getHeight(),container.getHeight());
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
    	this.font = g.getFont();
    	this.jeu.render(container,g);
    	
    	for (ZoneMarchable zm : deplacementPossible) {
			zm.drawHL();
		}
    	this.hud.render(g, container.getHeight(), 0);
    	
    	if(this.alertWindow!=null)
    		this.alertWindow.render(g);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
    	this.timepast+=delta;
    	
    	Joueur joueur = this.jeu.getListeJoueurs().getJoueur(this.jeu.getJoueurActuel());
    	
    	this.deplacementPossible = this.jeu.deplacementPossibleJoueur(joueur);
    	
    	if(this.touches[Touche.Explorer].is_appuie() && joueur.getCaseActuelle() instanceof Zone){
			int numZone = ((Zone)joueur.getCaseActuelle()).getNum();
    		IAZone iazone = this.jeu.getIA().getCase(this.jeu.getJoueurActuel(), numZone);
    		if(iazone.getCodeNum()!=0){
    			System.out.println(iazone.getCodeNum());
    			if(iazone.getCodeNum()==8 && numZone==this.jeu.getIA().getVirusIsIn() && joueur.isParefeu() && joueur.isDecodeur() && joueur.isZoneQuarantaine()){
    				this.hud.setDecouverte(numZone, IAZoneCode.Virus);
    				this.joueurWin(this.jeu.getJoueurActuel());
    			}else
    				this.hud.setDecouverte(numZone, iazone.getCodeZone());
    		}
    		this.hud.setNotification(this.jeu.getJoueurActuel().getColor(),this.jeu.explore(), this.timepast);
    		this.touches[Touche.Explorer].relache();
    		this.touches[Touche.Passer].appuie(this.timepast);
    	}
    	
    	Joueur joueurInSameCase = this.jeu.getListeJoueurs().sameCase(this.jeu.getJoueurActuel());
    	if(this.touches[Touche.Attaquer].is_appuie() && joueurInSameCase!=null){
    		this.hud.setNotification(this.jeu.getJoueurActuel().getColor(),this.jeu.attaque(this.jeu.getListeJoueurs().getJoueur(this.jeu.getJoueurActuel()),joueurInSameCase), this.timepast);
    		this.touches[Touche.Attaquer].relache();
			this.touches[Touche.Passer].appuie(this.timepast);
    	}
    	if(this.touches[Touche.Passer].is_appuie()){
    		this.jeu.joueurSuivant();
    		this.touches[Touche.Passer].relache();
    	}
    	
    	if(this.timepast-this.hud.getTime()>2000){
    		this.hud.setNotification(this.jeu.getJoueurActuel().getColor(),"", this.timepast);
    	}
    	
    	this.hud.update(this.jeu.getJoueurActuel());
    }
    
    private void joueurWin(Couleur joueurActuel) throws SlickException {
    	this.alertWindow = new AlertWindow(this.font,"Victoire !","Vous avez gagné la partie. Bravo !",container.getWidth(),container.getHeight());
	}

	public void keyPressed(int key, char c){

    	boolean focused = this.hud.keyPressed(key, c);
    	if(!focused){
        	if (Input.KEY_F == key) {
                container.exit();
            }
        	if(Input.KEY_G == key){
        		this.alertWindow = null;
        	}
	    	if (this.touches[Touche.Attaquer].getKey() == key)
	    		this.touches[Touche.Attaquer].appuie(this.timepast);
	    	if (this.touches[Touche.Explorer].getKey() == key)
	    		this.touches[Touche.Explorer].appuie(this.timepast);
	    	if (this.touches[Touche.Passer].getKey() == key)
	    		this.touches[Touche.Passer].appuie(this.timepast);
    	}
    }

    @Override
    public void keyReleased(int key, char c) {
    	
    	if (this.touches[Touche.Attaquer].getKey() == key)
    		this.touches[Touche.Attaquer].relache();
    	if (this.touches[Touche.Explorer].getKey() == key)
    		this.touches[Touche.Explorer].relache();
    	if (this.touches[Touche.Passer].getKey() == key)
    		this.touches[Touche.Passer].relache();
    }
    

    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
    	this.jeu.getPlateau().mouseMoved(oldx, oldy, newx, newy);
    	this.hud.mouseMoved(oldx, oldy, newx, newy);
    }
    
    @Override
    public void mousePressed(int button, int x, int y) {
    	ZoneMarchable zm = this.jeu.getPlateau().mousePressed(button, x, y);
    	if(zm!=null && this.deplacementPossible.contains(zm)){
    		this.jeu.deplacementJoueur(this.jeu.getListeJoueurs().getJoueur(this.jeu.getJoueurActuel()), zm);
    	}
    	this.hud.mousePressed(button, x, y);
    }
    
    @Override
    public void mouseWheelMoved(int change) {
    	this.hud.mouseWheelMoved(change);
    }
    
    public static void main(String[] args) throws SlickException {
    	new AppGameContainer(new FenetreJeu(), 800, 600, false).start();
    }
}