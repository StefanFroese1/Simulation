package water_simulation;

public class Main {

	public static void main(String[] args) {
		new MyFrame();
		Buildings A=new Buildings(30,40);
		System.out.print(A.GetDamage(50,0));
		
	}

}
