package intelligenceArtificielle;

import java.util.ArrayList;
import java.util.List;

import Joueurs.Joueur;
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
		this.VirusIsIn = ManipulationNombre.randomRange(1,24);
		this.ListZoneForBlue = this.createListIAZone(Couleur.BLUE);
		this.ListZoneForRed = this.createListIAZone(Couleur.RED);
		this.ListZoneForGreen = this.createListIAZone(Couleur.GREEN);
		this.ListZoneForYellow = this.createListIAZone(Couleur.YELLOW);

		System.out.println(VirusIsIn);
		
		System.out.println(ListZoneForBlue);

		System.out.println(ListZoneForRed);

		System.out.println(ListZoneForGreen);

		System.out.println(ListZoneForYellow);
	}
	
	public IAZone getCase(Couleur colorJoueur, int numCase){
		List<IAZone> list = null;
		if(colorJoueur.name().equals("BLUE"))
			list=this.ListZoneForBlue;
		else if(colorJoueur.name().equals("RED"))
			list=this.ListZoneForRed;
		else if(colorJoueur.name().equals("GREEN"))
			list=this.ListZoneForGreen;
		else if(colorJoueur.name().equals("YELLOW"))
			list=this.ListZoneForYellow;
		else
			list = new ArrayList<IAZone>();
		
		for (IAZone iaZone : list) {
			if(iaZone.getNumZone()==numCase){
				return iaZone;
			}
		}
		IAZone iazone = new IAZone(numCase, IAZoneCode.Rien);
		list.add(iazone);
		
		return iazone;
	}
	
	public String getInfosOnCase(IAZone iazone){
		return getMessageForIAZoneCode(iazone.getCodeNum());
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

public void doActionForCase(Joueur j, IAZone iazone) {
		switch (iazone.getCodeNum()) {
		case 1:
			j.setParefeu(true);
			break;
		case 2:
			j.setDecodeur(true);
			break;
		case 3:
			j.setZoneQuarantaine(true);
			break;
		case 4:
			j.setCodeAccesBlue(true);
			break;
		case 5:
			j.setCodeAccesRed(true);
			break;
		case 6:
			j.setCodeAccesGreen(true);;
			break;
		case 7:
			j.setCodeAccesYellow(true);
			break;
		case 8:
			System.out.println("PIEGE A FAIRE");
			break;
		default:
			break;
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
	*/
	
	public int getVirusIsIn() {
		return VirusIsIn;
	}


	private List<IAZone> createListIAZone(Couleur color) {
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

		list.add(new IAZone(numOccuper.get(1),IAZoneCode.Parefeu));
		list.add(new IAZone(numOccuper.get(2),IAZoneCode.Decodeur));
		list.add(new IAZone(numOccuper.get(3),IAZoneCode.ZoneQuarantaine));
		
		couleurOccuper = Couleur.CheminCouleurForCodeAccess();
		numAutoriser = Plateau.getZoneNums(couleurOccuper.get(0));
		numOccuper.add(ManipulationNombre.randomIn(numAutoriser, numOccuper));
		numAutoriser = Plateau.getZoneNums(couleurOccuper.get(1));
		numOccuper.add(ManipulationNombre.randomIn(numAutoriser, numOccuper));
		numAutoriser = Plateau.getZoneNums(couleurOccuper.get(2));
		numOccuper.add(ManipulationNombre.randomIn(numAutoriser, numOccuper));
		numAutoriser = Plateau.getZoneNums(couleurOccuper.get(3));
		numOccuper.add(ManipulationNombre.randomIn(numAutoriser, numOccuper));
		
		list.add(new IAZone(numOccuper.get(4),IAZoneCode.CodeAccesBlue));
		list.add(new IAZone(numOccuper.get(5),IAZoneCode.CodeAccesRed));
		list.add(new IAZone(numOccuper.get(6),IAZoneCode.CodeAccesGreen));
		list.add(new IAZone(numOccuper.get(7),IAZoneCode.CodeAccesYellow));
		
		
		couleurOccuper = new ArrayList<Couleur>();
		couleurOccuper.add(Plateau.getColorOfZone(this.VirusIsIn));
		couleurOccuper.add(Couleur.RandomColor(couleurOccuper));
		couleurOccuper.add(Couleur.RandomColor(couleurOccuper));
		couleurOccuper.add(Couleur.RandomColor(couleurOccuper));
		
		numAutoriser = Plateau.getZoneNums(couleurOccuper.get(1));
		numOccuper.add(ManipulationNombre.randomIn(numAutoriser, numOccuper));
		numAutoriser = Plateau.getZoneNums(couleurOccuper.get(2));
		numOccuper.add(ManipulationNombre.randomIn(numAutoriser, numOccuper));
		numAutoriser = Plateau.getZoneNums(couleurOccuper.get(3));
		numOccuper.add(ManipulationNombre.randomIn(numAutoriser, numOccuper));

		list.add(new IAZone(numOccuper.get(8),IAZoneCode.Trap));
		list.add(new IAZone(numOccuper.get(9),IAZoneCode.Trap));
		list.add(new IAZone(numOccuper.get(10),IAZoneCode.Trap));
		list.add(new IAZone(numOccuper.get(0),IAZoneCode.Trap));
		
		return list;
	}
}
