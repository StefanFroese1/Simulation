package water_simulation;

public class Buildings {

	private int height;
	private int width;
	private int elevation;
	private int location;
	private int strength;
	private int damage[][];

	// give numbers in GUI pixels..
	public Buildings(int x, int y, int x2, int y2, int s) {
		location = x;
		elevation = y;
		width = x2;
		height = y2;
		strength = s;
		damage = new int[x2][y2];
		for (int i = 0; i < x2; i++) {
			for (int i2 = 0; i2 < y2; i2++) {
				damage[i][i2] = s;
			}
		}
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
		return damage[x][y];
	}

	public void doGravity() {
		boolean layerGone;
		for (int i = 0; i < height; i++) {
			layerGone = true;
			for (int i2 = 0; i2 < width; i2++) {
				if (damage[i2][i] > 0) {
					layerGone = false;
				}
			}
			if (layerGone) {
				for (int i3 = 0; i3 < width; i3++) {
					for (int i4 = i; i4 < height - 1; i4++) {
						damage[i3][i4] = damage[i3][i4 + 1];
					}
					damage[i3][height - 1] = 0;
				}
			}
		}
	}

	public Water doDamage(Water w) {
		for (int i = location; i < location + width; i++) {
			for (int i2 = elevation; i2 < elevation + height; i2++) {
				if (w.GetPixel(i, 90 - i2) == 1) {
					damage[i - location][i2 - elevation]--;
					if (damage[i - location][i2 - elevation] > 0) {
						w.SetPixel(i, 90 - i2, 0);
					}
				}
			}
		}
		return w;
	}

}
