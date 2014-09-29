package Actors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import Interfaces.Persoon;
import Models.LoopRoute;
import Models.Product;
import Supermarkt.Supermarkt;

public abstract class Klant implements Persoon{
	protected LoopRoute locatie;
	private List<Product> winkelwagen;
	private BigDecimal saldo;
	
	public Klant(LoopRoute locatie) {
		this.locatie = locatie;
		winkelwagen = new ArrayList<Product>();
		saldo = new BigDecimal(10);//Needs fix
	}
	
	public void update(Supermarkt supermarkt) {
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
