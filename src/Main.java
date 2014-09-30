import Models.Afdeling;
import Models.Kassa;
import Models.LoopRoute;
import Models.Pad;
import Supermarkt.Supermarkt;

public class Main {
	public static void main(String[] args) {
		Supermarkt supermarkt = new Supermarkt(
				new LoopRoute[] {
					new LoopRoute<Pad>(new Pad()),
					new LoopRoute<Pad>(new Pad()),
					new LoopRoute<Pad>(new Pad()),
					new LoopRoute<Afdeling>(new Afdeling()) }, 
				new int[][] { 
					{ 0, 1, 3 },
					{ 0, 2, 3 } });
		
		supermarkt.run();
	}
}
