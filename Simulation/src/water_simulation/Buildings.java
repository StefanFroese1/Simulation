package water_simulation;

public class Buildings {
	
	// give numbers in GUI pixels.
	int height;
	int elevation;
	int damage;
	
	public int GetDamage(int tide,int wave){
		
		damage=(height+elevation)-tide;
		
		return damage;
	}

}
