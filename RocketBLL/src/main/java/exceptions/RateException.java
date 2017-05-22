package exceptions;

import rocketDomain.RateDomainModel;

public class RateException extends Exception 
{
	//	TODO - RocketBLL RateException - RateDomainModel should be an attribute of RateException
	//	* Add RateRomainModel as an attribute
	//	* Create a constructor, passing in RateDomainModel
	//	* Create a getter (no setter, set value only in Constructor)
	
	private RateDomainModel rate_domain_model;
	private double Income;
	private int Credit;
	
	public RateException()
	{
		super();
	}
	
	public RateException(RateDomainModel rate_domain_model){
		this.rate_domain_model = rate_domain_model;
	}
	
	public RateException( int Credit, double Income)
	{
		this();
		this.Income = Income;
		this.Credit = Credit;
	}
	
	public RateException(RateDomainModel rate_domain_model, int Credit, double Income){
		this.rate_domain_model = rate_domain_model;
		this.Credit = Credit;
		this.Income = Income;
	}
	
	public RateDomainModel getRatedomainmodel() {
		return rate_domain_model;
	}
	
	public int getCredit() {
		return Credit;
	}


	public double getIncome() {
		return Income;
	}
}
