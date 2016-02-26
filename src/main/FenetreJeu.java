package main;


import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import jeu.Jeu;

public class FenetreJeu extends BasicGame {
    private GameContainer container;
    private int timepast;
    private Jeu jeu;
    private int mouseX;
    private int mouseY;

	public FenetreJeu() {
        super("Virus on my Computer");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
    	this.container = container;
		container.setShowFPS(false);
		this.timepast=0;
		this.jeu = new Jeu();
	}

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
    	
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
    	this.timepast+=delta;
    	
    }
    
    public void keyPressed(int key, char c){

	}

    @Override
    public void keyReleased(int key, char c) {
    	if (Input.KEY_F == key) {
            container.exit();
        }
    }
    

    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
    	this.mouseX=newx;
    	this.mouseY=newy;
    	
    	
    }
    
    @Override
    public void mousePressed(int button, int x, int y) {
    	//System.out.println(button+" ("+x+";"+y+") ("+(this.jeu.getMap().getXCamera()-x)+";"+(this.jeu.getMap().getYCamera()-y)+")");
    }
    
    public static void main(String[] args) throws SlickException {
    	new AppGameContainer(new FenetreJeu(), 1024, 768, false).start();
    }
}