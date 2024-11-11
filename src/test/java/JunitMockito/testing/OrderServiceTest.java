package JunitMockito.testing;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class OrderServiceTest {
	@Mock
	private MenuService menuService;

	@Mock
	private PaymentService paymentService;

	@InjectMocks
	private OrderService orderService;
	@Mock
	private PaymentDetails paymentDetails;

	@Before
	public void setup() {
		MockitoAnnotations.openMocks(this);
		paymentDetails = new PaymentDetails("1234-5678-9101-1121", "12/25", 50.00);
	}

	@Test
	public void testPlaceOrder_ItemAvailable_PaymentSuccess() {

		MenuItem menuItem = new MenuItem(1L, "Pizza", 50, 10.00);
		when(menuService.getMenuItem(1L)).thenReturn(menuItem);
		when(paymentService.processPayment(paymentDetails)).thenReturn(true);

		String result = orderService.placeOrder(1L, 2, paymentDetails);

		assertEquals("Order placed successfully.", result);

		verify(menuService, times(1)).getMenuItem(1L);
		verify(paymentService, times(1)).processPayment(paymentDetails);
	}

	@Test
	public void testPlaceOrder_ItemOutOfStock() {

		MenuItem menuItem = new MenuItem(1L, "Pizza", 0, 10.00);
		when(menuService.getMenuItem(1L)).thenReturn(menuItem);

		String result = orderService.placeOrder(1L, 2, paymentDetails);

		assertEquals("Item is out of stock.", result);

		verify(menuService, times(1)).getMenuItem(1L);
		verify(paymentService, never()).processPayment(paymentDetails);
	}

	@Test
	public void testPlaceOrder_PaymentFailed() {

		MenuItem menuItem = new MenuItem(1L, "Pizza", 50, 10.00);
		when(menuService.getMenuItem(1L)).thenReturn(menuItem);
		when(paymentService.processPayment(paymentDetails)).thenReturn(false);

		String result = orderService.placeOrder(1L, 2, paymentDetails);

		assertEquals("Payment failed.", result);

		verify(menuService, times(1)).getMenuItem(1L);
		verify(paymentService, times(1)).processPayment(paymentDetails);
	}
}
