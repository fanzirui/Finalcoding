package rocketServer;

import java.io.IOException;

import exceptions.RateException;
import netgame.common.Hub;
import rocketBase.RateBLL;
import rocketData.LoanRequest;


public class RocketHub extends Hub {

	private RateBLL _RateBLL = new RateBLL();
	
	public RocketHub(int port) throws IOException {
		super(port);
	}

	@Override
	protected void messageReceived(int ClientID, Object message){
		System.out.println("Message Received by Hub");
		
		if (message instanceof LoanRequest) {
			resetOutput();
			
			LoanRequest lq = (LoanRequest) message;
			

			double rate = 0;
			try
			{
				rate = RateBLL.getRate(lq.getiCreditScore());
			}
			catch(RateException RE){
				System.out.println("Your credit score is too low");
				sendToAll(RE);
			}
			catch(Exception x){
				throw x;
			}
			
			lq.setdPayment(RateBLL.getPayment(rate, lq.getiTerm(), (lq.getdAmount()-lq.getiDownPayment()), 0, false));
			try
			{
				if (lq.getdPayment() > 0.28*(lq.getIncome()/12))
				{
					RateException RX = new RateException(lq.getiCreditScore(), lq.getIncome());
					sendToAll(RX);
				}
			}
			catch(Exception e)
			{
				throw e;
			}
			
			try
			{
				if (lq.getdPayment() > (0.36*(lq.getIncome()/12 + lq.getExpenses())))
				{
					RateException re = new RateException(lq.getiCreditScore(), lq.getIncome());
					sendToAll(re);
				}
			}
			catch(Exception e)
			{
				throw e;
			}
			
			
			sendToAll(lq);
		}
	}
}
