package Models;

import Supermarket.Options;
import Supermarket.Supermarket;

public class DiscountAisle extends Aisle {

	public DiscountAisle() {
		for (int n = 0; n < Options.DISCOUNTAISLE_SHELF_COUNT && ProductTypes.HasDiscountProducts(); n++) {
			shelves.add(new Shelf(ProductTypes.GetRandomDiscountProductType()));
		}
	}
	
	@Override
	public void update(Supermarket supermarket) {
		for(int n = 0; n < shelves.size(); n++) {
			if (!shelves.get(n).getProductType().hasDiscount()) {
				shelves.remove(shelves.get(n));
			}
		}
		super.update(supermarket);
	}
}
