package intelligenceArtificielle;

public enum IAZoneCode {
	Rien(0),
	Parefeu(1),
	Decodeur(2),
	ZoneQuarantaine(3),
	CodeAccesBlue(4),
	CodeAccesRed(5),
	CodeAccesGreen(6),
	CodeAccesYellow(7),
	Trap(8),
	Virus(9);
	
	private int code;
	
	IAZoneCode(int code){
		this.code = code;
	}
	
	public int getCode(){
		return this.code;
	}

	public String getLetter() {
		
		switch(this.code){
			case 0: return "X";
			case 1: return "Pa";
			case 2: return "De";
			case 3: return "ZQ";
			case 4: return "CB";
			case 5: return "CR";
			case 6: return "CV";
			case 7: return "CJ";
			case 8: return "Pi";
			case 9: return "V";
			default: return null;
		}
	}

}
