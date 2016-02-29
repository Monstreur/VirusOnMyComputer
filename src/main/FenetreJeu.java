package main;


import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import Joueurs.Joueur;
import Zones.Zone;
import Zones.ZoneMarchable;
import jeu.Jeu;
import autres.Touche;

public class FenetreJeu extends BasicGame {
    private GameContainer container;
    private int timepast;
    private Jeu jeu;
    private int mouseX;
    private int mouseY;
    private Touche[] touches;
	private List<ZoneMarchable> deplacementPossible;

	public FenetreJeu() {
        super("Virus on my Computer");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
    	this.container = container;
		container.setShowFPS(false);
		this.timepast=0;
		this.jeu = new Jeu(container.getHeight());
		System.out.println(this.jeu.getPlateau());
		
		this.touches=new Touche[3];
		this.touches[Touche.Attaquer]= new Touche(Input.KEY_TAB);
		this.touches[Touche.Explorer]= new Touche(Input.KEY_ENTER);
		this.touches[Touche.Passer]= new Touche(Input.KEY_SPACE);
		
		this.deplacementPossible = new ArrayList<ZoneMarchable>();
	}

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
    	this.jeu.render(container,g);
    	
    	for (ZoneMarchable zm : deplacementPossible) {
			zm.drawHL();
		}
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
    	this.timepast+=delta;
    	
    	Joueur joueur = this.jeu.getListeJoueurs().getJoueur(this.jeu.getJoueurActuel());
    	
    	this.deplacementPossible = this.jeu.deplacementPossibleJoueur(joueur);
    	
    	if(this.touches[Touche.Explorer].is_appuie() && joueur.getCaseActuelle() instanceof Zone){
    		this.jeu.explore();
    		this.touches[Touche.Explorer].relache();
    	}
    	if(this.touches[Touche.Passer].is_appuie()){
    		System.out.println("Joueur SUIVANT !!!");
    		this.jeu.joueurSuivant();
    		this.touches[Touche.Passer].relache();
    	}
    	
    }
    
    public void keyPressed(int key, char c){

    	if (this.touches[Touche.Attaquer].getKey() == key)
    		this.touches[Touche.Attaquer].appuie(this.timepast);
    	if (this.touches[Touche.Explorer].getKey() == key)
    		this.touches[Touche.Explorer].appuie(this.timepast);
    	if (this.touches[Touche.Passer].getKey() == key)
    		this.touches[Touche.Passer].appuie(this.timepast);
	}

    @Override
    public void keyReleased(int key, char c) {
    	if (Input.KEY_F == key) {
            container.exit();
        }

    	if (this.touches[Touche.Attaquer].getKey() == key)
    		this.touches[Touche.Attaquer].relache();
    	if (this.touches[Touche.Explorer].getKey() == key)
    		this.touches[Touche.Explorer].relache();
    	if (this.touches[Touche.Passer].getKey() == key)
    		this.touches[Touche.Passer].relache();
    }
    

    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
    	this.mouseX=newx;
    	this.mouseY=newy;
    	
    	this.jeu.getPlateau().mouseMoved(oldx, oldy, newx, newy);
    }
    
    @Override
    public void mousePressed(int button, int x, int y) {
    	ZoneMarchable zm = this.jeu.getPlateau().mousePressed(button, x, y);
    	if(zm!=null && this.deplacementPossible.contains(zm)){
    		this.jeu.deplacementJoueur(this.jeu.getListeJoueurs().getJoueur(this.jeu.getJoueurActuel()), zm);
    	}
    }
    
    public static void main(String[] args) throws SlickException {
    	new AppGameContainer(new FenetreJeu(), 800, 600, false).start();
    }
}