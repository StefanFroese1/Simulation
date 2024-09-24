package water_simulation;
import java.math.*;

public class Water {

	int tide, wave;
	int [][] display;
	
	
	public Water() {
		tide=40;
		wave=3;
		display=new int [160][90];
		for(int i1=0;i1<160;i1++) {
			for(int i2=0;i2>90;i2++) {
				display[i1][i2]=0;
			}
		}
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
	public int GetPixel(int x, int y) {
			
		return display[x][y];
	}
	public void WaveTick(int tick) {
		
		double sin=tide+(wave*(Math.sin(tick/5)));
		int y=(int)Math.round(sin);
		//the do-while loop makes all the the pixels below the wave water
		do {
			display[160][y]=1;
			y--;
		}while(y>0);
		//this do-while makes everything above the wave sky. 
		do {
			display[160][y+1]=0;
			y++;
		}while(y>0);
		
		for(int i1=0;i1<159;i1++) {
			for(int i2=0;i2>90;i2++) {
				display[i1][i2]=display[i1+1][i2];
			}
		}
	}
	
}
