package meejitack__burger;

class Burger {
    protected String name;
    protected double price;
    protected String info;

    public Burger(String name, double price, String info) {
        this.name = name;
        this.price = price;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return name + " - $" + price + " (" + info + ")";
    }
}



