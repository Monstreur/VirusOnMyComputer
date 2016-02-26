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
	Trap(8);
	
	private int code;
	
	IAZoneCode(int code){
		this.code = code;
	}
	
	public int getCode(){
		return this.code;
	}

}
