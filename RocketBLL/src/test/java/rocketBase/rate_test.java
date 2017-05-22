package rocketBase;

import static org.junit.Assert.*;

import org.apache.poi.ss.formula.functions.FinanceLib;
import org.junit.Test;

import exceptions.RateException;
import rocketDomain.RateDomainModel;

public class rate_test 
{
	
	RateDomainModel r1 =  new RateDomainModel(); 
	//TODO - RocketBLL rate_test
	//		Check to see if a known credit score returns a known interest rate
	
	//TODO - RocketBLL rate_test
	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score
	
	@Test
	public void rate_1_Test() throws RateException {
		
		assertTrue(5.5==RateBLL.getRate(820));
		assertTrue(6.5==RateBLL.getRate(690));
		
		assertNotEquals(6.0, RateBLL.getRate(640));
		assertNotEquals(5.5, RateBLL.getRate(620));
	}
	
	@Test(expected = RateException.class) 
	public void rate_2_Test() throws RateException {
		RateBLL.getRate(400);
	}
	
	@Test
	public void GetPaymentTest() throws RateException{
		double pmt = RateBLL.getPayment(4, 30, 300000, 0, false);
		System.out.println(pmt);
		java.text.DecimalFormat format =new java.text.DecimalFormat("#.00");  
		format.format(pmt);
		assertEquals(pmt,1432.25,0.01);
}
	
	
}
