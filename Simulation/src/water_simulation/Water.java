package water_simulation;

public class Water {

	int tide, wave;
	public Water(int tideIn,int waveIn) {
		tide=tideIn;
		wave=waveIn;
	}
	public int GetWave() {
		return wave;
	}
	public int GetTide() {
		return tide;
	}
	
}
