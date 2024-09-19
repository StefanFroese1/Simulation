package water_simulation;
//import java.math.;

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
	public void SetWave(int in) {
		wave=in;
	}
	public void SetTide(int in) {
		tide=in;
	}
	public int GetPixel(int x,int y) {
		
		
		
		if(display[x][y]>0) {
			return 1;
		}else {
			return 0;
		}
		
	}
	
}
