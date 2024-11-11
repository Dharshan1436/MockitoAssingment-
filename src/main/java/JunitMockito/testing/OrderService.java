package JunitMockito.testing;

public class OrderService {
	private final MenuService menuService;
    private final PaymentService paymentService;

    public OrderService(MenuService menuService, PaymentService paymentService) {
        this.menuService = menuService;
        this.paymentService = paymentService;
    }

    public String placeOrder(Long menuItemId, int quantity, PaymentDetails paymentDetails) {
        
        MenuItem menuItem = menuService.getMenuItem(menuItemId);

        if (menuItem == null || menuItem.getStock() < quantity) {
            return "Item is out of stock.";
        }

        
        boolean paymentSuccessful = paymentService.processPayment(paymentDetails);
        if (!paymentSuccessful) {
            return "Payment failed.";
        }

        
        return "Order placed successfully.";
    }
}
