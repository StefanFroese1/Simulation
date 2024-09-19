package water_simulation;

public class Water {

	int tide, wave;
	int [][] display;
	
	public Water(int tideIn,int waveIn) {
		tide=tideIn;
		wave=waveIn;
		display=new int [160][90];
	}
	public int GetWave() {
		return wave;
	}
	public int GetTide() {
		return tide;
	}
	
}
