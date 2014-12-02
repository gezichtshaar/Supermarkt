package com.supermarket.interfaces;

import com.supermarket.main.Supermarket;

public interface Actor {
	//true if should be destroyed
	public boolean act(Supermarket supermarket);
}
