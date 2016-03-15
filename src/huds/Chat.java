package huds;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import autres.Couleur;
import slickAdding.TextMultiLines;

public class Chat {
	private int x;
	private int y;
	private int width;
	private int height;
	private InputText text;
	private String prefixMessage;
	private List<String> messages;
	private int yMessage;
	private int tailleTotal;
	private ScrollPanel scroll;
	private boolean hovered;
	private List<Couleur> couleurs;
	
	public Chat(int x, int y, int width, int height, String prefixMessage){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.prefixMessage = prefixMessage;
		this.messages = new ArrayList<String>();
		this.couleurs = new ArrayList<Couleur>();
		this.text = new InputText(x, y+height-35, width, 35, "Ecrire un message...");
		this.scroll = new ScrollPanel(x+width-15, y, 15, height-this.text.getHeight());
		this.yMessage=0;
		this.tailleTotal=0;
		this.hovered=false;
	}
	
	public void add(Couleur c, String message){
		TextMultiLines tm = new TextMultiLines(prefixMessage+message, 20);
		this.tailleTotal+=tm.getNbLines()*15;
		System.out.println(yMessage+tailleTotal + " " + (height-this.text.getHeight()));
		if((yMessage+tailleTotal)>(height-this.text.getHeight()-10))
			yMessage-=yMessage+tailleTotal-(height-this.text.getHeight()-10);
		if((height-this.text.getHeight())>tailleTotal)
			this.scroll.setHScroll((height-this.text.getHeight()));
		else
			this.scroll.setHScroll(((float)(height-this.text.getHeight())/tailleTotal));

		System.out.println("tailletotal "+tailleTotal);
		System.out.println("height "+(height-this.text.getHeight()));
		this.scroll.moveToEnd();
		this.messages.add(message);
		this.couleurs.add(c);
	}
	
	public void renderMessage(Graphics g){
		g.setColor(Color.white);
		int cpt=0;
		int cptc=0;
		for (String m : messages) {
			g.setColor(this.couleurs.get(cptc).getColor());
			TextMultiLines tm = new TextMultiLines(prefixMessage+m, 20);
			tm.render(g, x, y+(cpt*15)+yMessage);
			cpt+=tm.getNbLines();
			cptc++;
		}
		g.setColor(Color.black);
		g.fillRect(x, 0, width, y);
	}
	
	public void render(Graphics g){
		g.setColor(new Color(50, 50, 50));
		g.drawRect(x, y, width, height);
		this.scroll.draw(g);
		this.text.draw(g);
	}

    public boolean keyPressed(int key, char c){
    	return this.text.keyPressed(key, c);
    }

    public void mousePressed(int button, int x, int y) {
    	this.text.mousePressed(button, x, y);
    }
    
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
    	if(newx>=x && newx<=(x+width) && newy>=y && newy<=(y+height-this.text.getHeight())){
    		this.hovered = true;
    	}else{
    		this.hovered = false;
    	}
    }
    
    
    public void mouseWheelMoved(int change) {
    	if(this.hovered){
    		if(change>0){
        		if((yMessage+4)<0){
        			this.scroll.moveUp(1);
					yMessage+=4;
        		}else if(yMessage<0){
    				yMessage=0;
        			this.scroll.moveToStart();
    			}
    		}else if(change<0){
    			if((yMessage-4+tailleTotal)>(height-this.text.getHeight()-10)){
					this.scroll.moveDown(1);
        			yMessage-=4;
    			}else if((yMessage+tailleTotal)>(height-this.text.getHeight()-10)){
					yMessage=(height-this.text.getHeight()-10)-tailleTotal;
        			this.scroll.moveToEnd();
    			}
    		}
    	}
    }

	public InputText getInput() {
		return this.text;
	}
}
