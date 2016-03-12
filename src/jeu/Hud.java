package jeu;

import java.util.List;

import org.newdawn.slick.Graphics;

import huds.Chat;
import slickAdding.TextMultiLines;

public class Hud {
	private TextMultiLines notification;
	private int timeLastNotif;
	private Chat chat;
	
	public Hud(int x, int y, int width, int height){
		this.notification=new TextMultiLines("", 20);
		this.timeLastNotif=0;
		this.chat = new Chat(x,y+height-200,width,200,">");
	}
	
	public void setNotification(String notification,int time){
		this.timeLastNotif=time;
		this.notification.setString(notification);
		if(!notification.isEmpty())
			this.chat.add(notification);
	}
	
	public int getTime() {
		return timeLastNotif;
	}
	
	public void render(Graphics g, int x, int y){
		this.chat.renderMessage(g);
		this.notification.render(g, x+15, y+130);
		this.chat.render(g);
	}
	

    public void keyPressed(int key, char c){
    	this.chat.keyPressed(key, c);
    }

    public void mousePressed(int button, int x, int y) {
    	this.chat.mousePressed(button, x, y);
    }
    
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
    	this.chat.mouseMoved(oldx, oldy, newx, newy);
    }
    
    public void mouseWheelMoved(int change) {
    	this.chat.mouseWheelMoved(change);
    }
	
}
