package JunitMockito.testing;

public class MenuItem {

	private Long id;
    private String name;
    private int stock;
    private double price;

    
    public MenuItem() {
		super();
	}

	public MenuItem(Long id, String name, int stock, double price) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }
}
