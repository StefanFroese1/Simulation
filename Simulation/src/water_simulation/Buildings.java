package water_simulation;

public class Buildings {
	
	private int height;
	private int width;
	private int elevation;
	private int location;
	private int strength;
	private int damage [][];
	// give numbers in GUI pixels.
	public Buildings(int x, int y, int x2, int y2, int s) {
		location=x;
		elevation=y;
		width=x2;
		height=y2;
		strength=s;
		damage = new int [x2][y2];
	}
	
	public int getElevation() {
		return elevation;
	}
	
	public int getLocation() {
		return location;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getDamage(int x, int y) {
		return damage [x][y];
	}
	
	public Water doDamage(Water w) {
		for (int i=location;i<location+width;i++) {
			for (int i2=elevation;i2<elevation+height;i2++) {
				if(w.GetPixel(i, i2)==1) {
					damage [i-location][i2-elevation] ++;
					if(damage [i-location][i2-elevation] > strength) {
						damage [i-location][i2-elevation] = 1001;
						w.SetPixel(i, i2, 0);
					}
				}
			}
		}
		return w;
	}

}
