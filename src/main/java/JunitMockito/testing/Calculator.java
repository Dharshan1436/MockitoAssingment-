package JunitMockito.testing;

public class Calculator {
	 
	CalculatorServices service;
	public Calculator(CalculatorServices service) {
		this.service=service;
	}
public  int  perform(int a,int b) {//2 3 (a+b)*a
	return service.add(a, b)*a; 
//	return (a+b)*a;
}
}
