import Models.Kassa;
import Models.LoopRoute;
import Models.Pad;
import Supermarkt.Supermarkt;

public class Main {
	public static void main() {
		Supermarkt supermarkt = new Supermarkt(
				new LoopRoute[] {
					new LoopRoute<Pad>(new Pad()),
					new LoopRoute<Pad>(new Pad()),
					new LoopRoute<Pad>(new Pad()),
					new LoopRoute<Kassa>(new Kassa()) }, 
				new int[][] { 
					{ 1, 2, 4 },
					{ 1, 3, 4 } });
		
		supermarkt.run();
	}
}
