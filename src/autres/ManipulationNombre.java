package autres;

import java.util.List;
import java.util.Random;

public class ManipulationNombre {
	
	public static int randomRange(int min, int max){
		Random rand =new Random();
		return rand.nextInt((max - min) + 1) + min;
	}
	
	public static int randomRange(int min, int max, List<Integer> excepts){
		Random rand = new Random();
		int numRandom = 0;
		do
			numRandom = rand.nextInt((max - min) + 1) + min;
		while(excepts.contains(numRandom));
		return numRandom;
	}

	public static int randomIn(List<Integer> numAutoriser, List<Integer> excepts) {
		int numRandom = 0;
		do
			numRandom = randomRange(0,numAutoriser.size()-1);
		while(excepts.contains(numAutoriser.get(numRandom)));
		return numAutoriser.get(numRandom);
	}
}
