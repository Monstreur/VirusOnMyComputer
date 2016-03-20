package huds;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class ScrollPanel {
	private int x;
	private int y;
	private int width;
	private int height;
	private int yScroll;
	private int hScroll;
	
	public ScrollPanel(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.yScroll = 0;
		this.hScroll = height;
	}
	
	public void draw(Graphics g){
		g.setColor(Color.lightGray);
		g.fillRect(x, y+yScroll, width, hScroll);
	}
	
	public void setHScroll(int hScroll){
		this.hScroll=hScroll;
	}
	
	public void setHScroll(float percentScroll){
		this.hScroll=(int)(percentScroll*height);
	}

	public void moveDown(int nb){
		this.yScroll+=nb;
	}
	public void moveUp(int nb){
		this.yScroll-=nb;
	}

	public void moveToStart() {
		this.yScroll=0;
	}
	public void moveToEnd() {
		this.yScroll=height-hScroll;
	}
}
