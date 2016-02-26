package intelligenceArtificielle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import autres.Couleur;
import autres.ManipulationNombre;
import jeu.Plateau;

public class IntelligenceArtificielle {
	private List<IAZone> ListZoneForBlue;
	private List<IAZone> ListZoneForRed;
	private List<IAZone> ListZoneForGreen;
	private List<IAZone> ListZoneForYellow;
	private int VirusIsIn; // num de la Zone où est le virus
	
	public IntelligenceArtificielle(){
		this.VirusIsIn = ManipulationNombre.randomRange(0, 23);
		this.ListZoneForBlue = this.createListIAZoneFor(Couleur.BLUE);
		this.ListZoneForRed = this.createListIAZoneFor(Couleur.RED);
		this.ListZoneForGreen = this.createListIAZoneFor(Couleur.GREEN);
		this.ListZoneForYellow = this.createListIAZoneFor(Couleur.YELLOW);
	}
	
	public String getInfosOnCase(Couleur colorJoueur, int numCase){
		List<IAZone> list = null;
		if(colorJoueur.toString().equals("BLEU"))
			list=this.ListZoneForBlue;
		else if(colorJoueur.toString().equals("RED"))
			list=this.ListZoneForRed;
		else if(colorJoueur.toString().equals("GREEN"))
			list=this.ListZoneForGreen;
		else if(colorJoueur.toString().equals("YELLOW"))
			list=this.ListZoneForYellow;
		else
			list = new ArrayList<IAZone>();
		for (IAZone iaZone : list) {
			if(iaZone.getNumZone()==numCase){
				return getMessageForIAZoneCode(iaZone.getCodeZone());
			}
		}
		return "Il n'y a rien ici";
	}

	private String getMessageForIAZoneCode(int codeZone) {
		switch (codeZone) {
		case 1:
			return "Vous venez de trouvez un Parefeu.";
		case 2:
			return "Vous venez de trouvez un Décodeur.";
		case 3:
			return "Vous venez de trouvez une Zone de Quarantaine.";
		case 4:
			return "Vous venez de trouvez un code permettant d'accéder aux zones Bleues.";
		case 5:
			return "Vous venez de trouvez un code permettant d'accéder aux zones Rouges.";
		case 6:
			return "Vous venez de trouvez un code permettant d'accéder aux zones Vertes.";
		case 7:
			return "Vous venez de trouvez un code permettant d'accéder aux zones Jaunes.";
		case 8:
			return "Vous êtes tombé dans un piege.";
		default:
			return "Il n'y a rien ici";
		}
	}
	
	/*
	public List<IAZone> getListZoneForBlue() {
		return ListZoneForBlue;
	}
	public List<IAZone> getListZoneForRed() {
		return ListZoneForRed;
	}
	public List<IAZone> getListZoneForGreen() {
		return ListZoneForGreen;
	}
	public List<IAZone> getListZoneForYellow() {
		return ListZoneForYellow;
	}
	public int getVirusIsIn() {
		return VirusIsIn;
	}
	*/

	private List<IAZone> createListIAZoneFor(Couleur color) {
		List<Integer> numOccuper = new ArrayList<Integer>();
		List<Integer> numAutoriser = new ArrayList<Integer>();
		List<Couleur> couleurOccuper = new ArrayList<Couleur>();
		List<IAZone> list = new ArrayList<IAZone>();
		
		numOccuper.add(this.VirusIsIn);
		
		couleurOccuper.add(color);
		couleurOccuper.add(Couleur.RandomColor(couleurOccuper));
		couleurOccuper.add(Couleur.RandomColor(couleurOccuper));
		couleurOccuper.add(Couleur.RandomColor(couleurOccuper));
		
		numAutoriser = Plateau.getZoneNums(couleurOccuper.get(1));
		numOccuper.add(ManipulationNombre.randomIn(numAutoriser, numOccuper));
		numAutoriser = Plateau.getZoneNums(couleurOccuper.get(2));
		numOccuper.add(ManipulationNombre.randomIn(numAutoriser, numOccuper));
		numAutoriser = Plateau.getZoneNums(couleurOccuper.get(3));
		numOccuper.add(ManipulationNombre.randomIn(numAutoriser, numOccuper));

		list.add(new IAZone(numOccuper.get(0),IAZoneCode.Decodeur));
		list.add(new IAZone(numOccuper.get(1),IAZoneCode.Parefeu));
		list.add(new IAZone(numOccuper.get(2),IAZoneCode.ZoneQuarantaine));
		
		couleurOccuper = new ArrayList<Couleur>();
		couleurOccuper.add(Couleur.BLUE);
		couleurOccuper.add(Couleur.RandomColor(couleurOccuper));
		
		couleurOccuper.remove(0);
		couleurOccuper.add(Couleur.RED);
		couleurOccuper.add(Couleur.RandomColor(couleurOccuper));
		
		couleurOccuper.remove(1);
		couleurOccuper.add(Couleur.GREEN);
		
		boolean find=false;
		for (Couleur c : couleurOccuper) {
			if(Couleur.YELLOW.toString().equals(c.toString()))
				find=true;
		}
		if(!find)
			couleurOccuper.add(Couleur.YELLOW);
		else
			couleurOccuper.add(Couleur.RandomColor(couleurOccuper));

		couleurOccuper.remove(2);
		couleurOccuper.add(Couleur.YELLOW);
		couleurOccuper.add(Couleur.RandomColor(couleurOccuper));
		

		numAutoriser = Plateau.getZoneNums(couleurOccuper.get(0));
		numOccuper.add(ManipulationNombre.randomIn(numAutoriser, numOccuper));
		numAutoriser = Plateau.getZoneNums(couleurOccuper.get(1));
		numOccuper.add(ManipulationNombre.randomIn(numAutoriser, numOccuper));
		numAutoriser = Plateau.getZoneNums(couleurOccuper.get(2));
		numOccuper.add(ManipulationNombre.randomIn(numAutoriser, numOccuper));
		numAutoriser = Plateau.getZoneNums(couleurOccuper.get(3));
		numOccuper.add(ManipulationNombre.randomIn(numAutoriser, numOccuper));

		list.add(new IAZone(numOccuper.get(3),IAZoneCode.CodeAccesBlue));
		list.add(new IAZone(numOccuper.get(4),IAZoneCode.CodeAccesRed));
		list.add(new IAZone(numOccuper.get(5),IAZoneCode.CodeAccesGreen));
		list.add(new IAZone(numOccuper.get(6),IAZoneCode.CodeAccesYellow));
		
		couleurOccuper = new ArrayList<Couleur>();
		couleurOccuper.add(Couleur.RandomColor(couleurOccuper));
		couleurOccuper.add(Couleur.RandomColor(couleurOccuper));
		couleurOccuper.add(Couleur.RandomColor(couleurOccuper));
		couleurOccuper.add(Couleur.RandomColor(couleurOccuper));
		

		numAutoriser = Plateau.getZoneNums(couleurOccuper.get(0));
		numOccuper.add(ManipulationNombre.randomIn(numAutoriser, numOccuper));
		numAutoriser = Plateau.getZoneNums(couleurOccuper.get(1));
		numOccuper.add(ManipulationNombre.randomIn(numAutoriser, numOccuper));
		numAutoriser = Plateau.getZoneNums(couleurOccuper.get(2));
		numOccuper.add(ManipulationNombre.randomIn(numAutoriser, numOccuper));
		numAutoriser = Plateau.getZoneNums(couleurOccuper.get(3));
		numOccuper.add(ManipulationNombre.randomIn(numAutoriser, numOccuper));

		list.add(new IAZone(numOccuper.get(7),IAZoneCode.Trap));
		list.add(new IAZone(numOccuper.get(8),IAZoneCode.Trap));
		list.add(new IAZone(numOccuper.get(9),IAZoneCode.Trap));
		list.add(new IAZone(numOccuper.get(10),IAZoneCode.Trap));
		
		return list;
	}
}
