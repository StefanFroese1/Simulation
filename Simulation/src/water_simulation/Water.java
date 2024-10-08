package water_simulation;

public class Water {

	int tide, wave;
	int[][] display;

	public Water() {
		tide = 20;
		wave = 3;
		display = new int[160][90];
		// this loop makes all spaces blank
		for (int i1 = 0; i1 < 159; i1++) {
			for (int i2 = 0; i2 > 89; i2++) {
				display[i1][i2] = 0;
			}

		}
		// this loop makes the land using math
		for (int i1 = 0; i1 < 159; i1++) {
			for (int i2 = 0; i2 < 90; i2++) {
				if (3 * (Math.pow(i2, 2)) - 5000 < -(Math.pow(i1, 2))) {
					display[i1][(89 - i2)] = 2;
				}
			}
		}
	}

	// returns the wave size
	public int getWave() {
		return wave;
	}

	// returns the tide height
	public int getTide() {
		return tide;
	}

	// manually sets pixels to a specified color
	public void setPixel(int x, int y, int t) {
		display[x][y] = t;
	}

	// sets the size of the waves
	public void setWave(int in) {
		wave = in;
	}

	// sets the tide height
	public void setTide(int in) {
		tide = in;
	}

	// returns the specified pixel
	public int getPixel(int x, int y) {

		return display[x][y];
	}

	// this makes the wave
	public void waveTick(int tick) {
		int y;
		// this calculates the wave using math
		double sin = tide + (wave * (Math.sin((double) tick / 5)));
		y = (int) Math.round(sin);
		int yChange = y;

		// this sets the first line of pixels to the calculated wave.
		if (yChange > 0 && yChange < 89) {
			display[159][y] = 1;
		}
		// the do-while loop makes all the the pixels below the wave water
		do {

			if (yChange > 0 && yChange < 89) {
				display[159][yChange] = 0;
			}
			yChange--;
		} while (yChange > 0);
		yChange = y;
		// this do-while makes everything above the wave sky.
		do {
			if (yChange > 0 && yChange < 89) {
				display[159][yChange + 1] = 1;
			}
			yChange++;
		} while (yChange < 89);

		// these loops make a pixel copy the color of the pixel before it last tick
		for (int i1 = 0; i1 < 159; i1++) {
			for (int i2 = 88; i2 > 0; i2--) {
				if (display[i1][i2 + 1] == 0 && display[i1][i2] == 1) {
					display[i1][i2] = 0;
					display[i1][i2 + 1] = 1;
				}
			}
		}

		for (int i1 = 0; i1 < 159; i1++) {
			for (int i2 = 0; i2 < 90; i2++) {
				if (display[i1][i2] == 0 || display[i1][i2] == 1) {
					display[i1][i2] = display[i1 + 1][i2];
				}
			}
		}
	}
}