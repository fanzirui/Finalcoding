package rocketBase;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.BeforeClass;
import org.junit.Test;

import rocketDomain.RateDomainModel;

public class Rate_Test {
	
	@Test
	public void test1() throws Exception {
		ArrayList<RateDomainModel> rate = RateDAL.getAllRates();
		

		assertEquals(rate.size(), 5);
		assertEquals(rate.get(4).getiMinCreditScore(), 800);
		assertEquals(rate.get(3).getiMinCreditScore(), 750);
		assertEquals(rate.get(2).getiMinCreditScore(), 700);
		assertEquals(rate.get(1).getiMinCreditScore(), 650);
		assertEquals(rate.get(0).getiMinCreditScore(), 600);

		assertEquals(rate.get(4).getdInterestRate(), 5.50, 0.01);
		assertEquals(rate.get(3).getdInterestRate(), 5.75, 0.01);
		assertEquals(rate.get(2).getdInterestRate(), 6.00, 0.01);
		assertEquals(rate.get(1).getdInterestRate(), 6.50, 0.01);
		assertEquals(rate.get(0).getdInterestRate(), 7.00, 0.01);

	}

	public void RateExceptionTest() throws Exception
	{
		int size = RateDAL.getAllRates().size();
		
		if(size == 0)
		{
			throw new Exception();
		}
	}
}

