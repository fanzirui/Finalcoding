package rocketBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.formula.functions.*;
import org.hibernate.Session;

import exceptions.RateException;
import rocketDomain.RateDomainModel;
import util.HibernateUtil;

public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();
	
	public static double getRate(int GivenCreditScore) throws RateException 
	{
		double dInterestRate = 0;

		
		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
		for(RateDomainModel r: rates){
			if (GivenCreditScore >= r.getiMinCreditScore()){
				
				dInterestRate = r.getdInterestRate();
			}
		}	
		
		if (dInterestRate==0){
			throw new RateException(new RateDomainModel());
		}
		
		return dInterestRate;
		
	}

	
	public static double getPayment(double r, int n, double p, double f, boolean t)
	{		
		return Math.abs(FinanceLib.pmt(r/1200, n*12, p, f, t));
	}
}
