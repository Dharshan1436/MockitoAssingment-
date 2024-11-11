package JunitMockito.testing;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;


public class TestCalculator  {
	Calculator c=null;
//	CalculatorServices service= Mockito.mock(CalculatorServices.class);
	@Mock
	CalculatorServices service;
	@Rule public MockitoRule rule=MockitoJUnit.rule();
	@Before
	public void setUp() {
		c=new Calculator(service);
	}

	
	@Test   
public void testPerform() {
		when(service.add(2, 3)).thenReturn(5);
		assertEquals(10, c.perform(2, 3));
		verify(service).add(2, 3);
}
}
 