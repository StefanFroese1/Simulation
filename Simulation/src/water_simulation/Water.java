package water_simulation;

public class Water {

	int tide, wave;
	int [][] display;
	
	
	public Water() {
		tide=30;
		wave=3;
		display=new int [160][90];
		for(int i1=0;i1<159;i1++) {
			for(int i2=0;i2>89;i2++) {
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
	public int ARandomMethod() {
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
		int y;
		double sin=tide+(wave*(Math.sin(tick/5)));
		y=(int)Math.round(sin);
		int yChange=y;
		/*
		//the do-while loop makes all the the pixels below the wave water
		do {
			
			display[159][yChange]=1;
			yChange--;
		}while(yChange>0);
		yChange=y;
		//this do-while makes everything above the wave sky. 
		do {
			display[159][yChange+1]=0;
			yChange++;
		}while(yChange<88);
		*/
		for(int i1=0;i1<158;i1++) {
			for(int i2=0;i2<89;i2++) {
				display[i1][i2]=display[i1+1][i2];
				System.out.print(display[i1][i2]+" ");
			}
		}
		System.out.println();
	}
	
}
