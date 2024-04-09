package meejitack__burger;

import java.util.ArrayList;

public class MySide extends Side {
    
	private static ArrayList<MySide> mySideBasket = new ArrayList<>();
	public MySide(String name, double price, String info) {
        super(name, price, info);
    }

    // Other methods specific to MySide can go here
}