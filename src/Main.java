import java.util.Scanner;

import Interfaces.Koopzone;
import Models.Afdeling;
import Models.LoopRoute;
import Models.Pad;
import Supermarkt.Supermarkt;

public class Main {
	public static void main(String[] args) {

		@SuppressWarnings("unchecked")
		Supermarkt supermarkt = new Supermarkt(
				new LoopRoute[] {
					new LoopRoute<Koopzone>(new Pad()),
					new LoopRoute<Koopzone>(new Pad()),
					new LoopRoute<Koopzone>(new Pad()),
					new LoopRoute<Koopzone>(new Afdeling()) }, 
				new int[][] {
					{ 0, 1, 3 },
					{ 0, 2, 3 } });

		new Thread(supermarkt).start();

		Scanner console = new Scanner(System.in);

		while (supermarkt.isRunning() && console.hasNextLine()) {
			switch (console.nextLine()) {
			case "q":
				supermarkt.stop();
				break;
			case "stats":
				System.out.println(supermarkt.toString());
				break;
			}
		}
		console.close();
	}
}
