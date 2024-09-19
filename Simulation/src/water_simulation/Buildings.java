package water_simulation;

public class Buildings {
	
	int height;
	int elevation;
	int damage;
	// give numbers in GUI pixels.
	public Buildings(int A, int B) {
		height=A;
		elevation=B;
		
	}
	
	public int GetDamage(int tide,int wave){
		
		damage=tide/(height+elevation);
		
		return damage;
	}

}
