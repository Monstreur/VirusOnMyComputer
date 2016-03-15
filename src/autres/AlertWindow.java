package autres;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

import slickAdding.TextMultiLines;

public class AlertWindow {
	private int containerW;
	private int containerH;
	private int width;
	private int height;
	private TextMultiLines text;
	private String title;

	public AlertWindow(Font f, String title, String text,int containerW, int containerH){
		this.containerW = containerW;
		this.containerH = containerH;
		int maxwidth = containerW/2;
		int limit = (maxwidth-20)/f.getWidth("W");
		this.text = new TextMultiLines(text,limit);
		this.width = this.text.getMaxLength()*f.getWidth("W")+20;
		this.height = this.text.getNbLines()*15+70;
		this.title=title;
	}
	public void render(Graphics g){
		g.setColor(new Color(100,100,100));
		g.fillRoundRect((this.containerW-(width+8))/2, (this.containerH-(height+8))/2, width+8, height+8,10);
		g.setColor(new Color(200,200,200));
		g.fillRoundRect((this.containerW-(width+4))/2, (this.containerH-(height+4))/2, width+4, height-4,10);
		g.setColor(Color.black);
		g.fillRoundRect((this.containerW-width)/2, (this.containerH-height)/2, width, height,10);
		g.setColor(Color.white);
		g.drawString(this.title, (this.containerW-width+20)/2, (this.containerH-height)/2);
		this.text.render(g, (this.containerW-g.getFont().getWidth(this.text.getMostLength()))/2, (this.containerH-((this.text.getNbLines()-2)*15))/2);
	}
}
