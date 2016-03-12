package huds;

import org.newdawn.slick.Color;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class InputText {
	private int x;
	private int y;
	private int width;
	private int height;
	private String placeholder;
	private String text;
	private boolean focused;
	
	public InputText(int x, int y, int width, int height, String placeholder, String text){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.placeholder = placeholder;
		this.text = text;
	}
	public InputText(int x, int y, int width, int height, String placeholder){
		this(x,y,width,height,placeholder,"");
	}
	public InputText(int x, int y, int width, int height){
		this(x,y,width,height,"","");
	}
	
	public void draw(Graphics g){
		g.setColor(new Color(50, 50, 50));
		g.fillRect(x, y, width, height);
		g.setColor(Color.white);
		g.fillRect(x+2, y+2, width-4, height-4);
		g.setColor(Color.black);
		g.drawString(this.text, x+5, y+7);
	}
	public int getHeight() {
		return this.height;
	}

    public void keyPressed(int key, char c){
    	if(this.focused){
	    	if(Input.KEY_BACK==key && this.text.length()>0)
	    		this.text = this.text.substring(0,this.text.length()-1);
	    	else
	    		this.text+=c;
    	}
    }

    public void mousePressed(int button, int x2, int y2) {
    	if(button==0 && x2>=x && x2<=(x+width) && y2>=y && y2<=(y+height))
			this.focused=true;
		else
			this.focused=false;
    }
}
