package JunitMockito.testing;

public class PaymentDetails {
	private String cardNumber;
    private String expirationDate;
    private double amount;

  
    public PaymentDetails() {
		super();
	}

	public PaymentDetails(String cardNumber, String expirationDate, double amount) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.amount = amount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public double getAmount() {
        return amount;
    }
}
