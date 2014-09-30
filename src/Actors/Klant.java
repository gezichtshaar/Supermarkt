package Actors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import Interfaces.Koopzone;
import Interfaces.Persoon;
import Models.LoopRoute;
import Models.Product;
import Supermarkt.Supermarkt;

public abstract class Klant implements Persoon{
	protected LoopRoute<Koopzone> locatie;
	private List<Product> winkelwagen;
	private BigDecimal saldo;
	
	public Klant(LoopRoute<Koopzone> locatie) {
		this.locatie = locatie;
		winkelwagen = new ArrayList<Product>();
		saldo = new BigDecimal(10);//Needs fix
	}
	
	public final void act(Supermarkt supermarkt) {
		preAct(supermarkt);
		act(supermarkt);
		postAct(supermarkt);
	}
	
	private final void preAct(Supermarkt supermarkt) {
	}
	
	protected abstract void actKlant(Supermarkt supermarkt);
	
	private final void postAct(Supermarkt supermarkt) {
		if (locatie == null) {
			supermarkt.afrekenen(this);
		}
	}

	public BigDecimal getSaldo() {
		return saldo;
	}
	
	public void takeFromSaldo(BigDecimal amount) {
		this.saldo = this.saldo.subtract(amount);
	}

	public List<Product> getWinkelwagen() {
		return winkelwagen;
	}
}
