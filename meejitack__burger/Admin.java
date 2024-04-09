package meejitack__burger;

class Admin {
    private final String username = "admin";
    private final String password = "admin";

    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public void addProduct(String productName, double price, String info) {
        // 제품을 메뉴나 데이터베이스에 추가하는 로직을 여기에 추가
        System.out.println("Product added: " + productName + " - $" + price + " (" + info + ")");
    }
}