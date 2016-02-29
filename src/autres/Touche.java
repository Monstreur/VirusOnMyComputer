package autres;

public class Touche {
	public static int Passer = 0;
	public static int Explorer = 1;
	public static int Attaquer = 2;
	
	private float nbappuie;
	private int key;
	private int lastdelta;
	
	public Touche(int key){
		this.key=key;
		this.nbappuie=0;
		this.lastdelta=0;
	}
	
	public float getNbappui() {
		return nbappuie;
	}
	
	public int getKey() {
		return key;
	}
	
	public int getLastdelta() {
		return lastdelta;
	}
	
	public void appuie(int delta){
		if(!this.is_appuie()){
			this.nbappuie+=0.5;
			this.lastdelta=delta;
		}
	}
	
	public void relache(){
		if(this.is_appuie()){
			this.nbappuie+=0.5;
			this.lastdelta=0;
		}
	}
	
	public boolean is_appuie(){
		return (this.nbappuie%1==0.5);
	}
	
}
