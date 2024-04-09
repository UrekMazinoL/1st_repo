package meejitack__burger;

public class Side {
    private String name;
    private double price;
    private String info;

    // Constructor, getters, setters, etc. omitted for brevity

    public Side(String name, double price, String info) {
    	super();
    	this.name = name;
    	this.price = price;
    	this.info = info;
    }
    

	public void sizeUp() {
        this.price += 2.1;
    }


	public void set() {
        this.price *= 1.3;
    }

    public void sizeUpNSet() {
        this.price = (int) (this.price * 1.3) + 2.1;
    }

    @Override
    public String toString() {
        return name + " - $" + price + " (" + info + ")";
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}