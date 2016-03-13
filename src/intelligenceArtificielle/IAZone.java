package intelligenceArtificielle;

public class IAZone{
	private IAZoneCode iaCode;
	private int numZone;
	
	public IAZone(int numZone, IAZoneCode iaCode){
		this.numZone = numZone;
		this.iaCode = iaCode;
	}

	public int getNumZone() {
		return this.numZone;
	}
	public int getCodeNum() {
		return this.iaCode.getCode();
	}
	public IAZoneCode getCodeZone() {
		return this.iaCode;
	}
	public String toString(){
		return "("+this.numZone+" : "+this.iaCode.getCode()+")";
	}

	public void setCodeZone(IAZoneCode iazonecode) {
		this.iaCode=iazonecode;
	}

	public String getCodeLetter() {
		return this.iaCode.getLetter();
	}
}