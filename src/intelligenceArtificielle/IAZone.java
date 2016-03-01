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
	public int getCodeZone() {
		return this.iaCode.getCode();
	}
	public String toString(){
		return "("+this.numZone+" : "+this.iaCode.getCode()+")";
	}
}