package water_simulation;

public class Buildings {

	private int height;
	private int width;
	private int elevation;
	private int location;
	private int strength;
	private int damage[][];

	//
	public Buildings(int x, int y, int x2, int y2, int s) {
		location = x;
		elevation = y;
		width = x2;
		height = y2;
		strength = s;
		damage = new int[x2][y2];
		// these loops set the strength of the building
		for (int i1 = 0; i1 < x2; i1++) {
			for (int i2 = 0; i2 < y2; i2++) {
				damage[i1][i2] = s;
			}
		}
	}

	// this returns how high the building is
	public int getElevation() {
		return elevation;
	}

	// this returns the distance from the left
	public int getLocation() {
		return location;
	}

	// this returns the width of the building
	public int getWidth() {
		return width;
	}

	// this returns the height of the building
	public int getHeight() {
		return height;
	}

	// this returns the specified pixels damage
	public int getDamage(int x, int y) {
		return damage[x][y];
	}

	// this moves floating layers of building down until it touches something
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

	// this calculates the damage to the building, ten water pixels to destroy a
	// building pixel
	public Water doDamage(Water w) {
		for (int i = location; i < location + width; i++) {
			for (int i2 = elevation; i2 < elevation + height; i2++) {
				if (w.getPixel(i, 90 - i2) == 1) {
					damage[i - location][i2 - elevation]--;
					if (damage[i - location][i2 - elevation] > 0) {
						w.setPixel(i, 90 - i2, 0);
					}
				}
			}
		}
		return w;
	}

	// this returns the number of broken buildings
	public int getBroken() {
		int broken = 0;
		for (int i1 = 0; i1 < width; i1++) {
			for (int i2 = 0; i2 < height - 1; i2++) {
				if (damage[i1][i2] < 1) {
					broken++;
				}
			}
		}
		return (broken);
	}

	// this resets the buildings
	public void reset() {

		damage = new int[width][height];
		for (int i = 0; i < width; i++) {
			for (int i2 = 0; i2 < height; i2++) {
				damage[i][i2] = strength;
			}
		}
	}

}
