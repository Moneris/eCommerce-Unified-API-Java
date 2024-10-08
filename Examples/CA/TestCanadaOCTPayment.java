package Canada;

import JavaAPI.*;

public class TestCanadaOCTPayment
{
	public static void main(String[] args)
	{
		java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
		String store_id = "store5";
		String api_token = "yesguy";
		String cust_id = "my customer id";
		String amount = "20.00";
		String pan = "4761260000000134";
		String expdate = "2012"; //YYMM
		String crypt = "7";
		String processing_country_code = "CA";
		boolean status_check = false;

		OCTPayment octPayment = new OCTPayment();
		octPayment.setOrderId(order_id);
		octPayment.setCustId(cust_id);
		octPayment.setAmount(amount);
		octPayment.setPan(pan);
		octPayment.setExpdate(expdate);
		octPayment.setCryptType(crypt);
		octPayment.setDynamicDescriptor("123456");

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(octPayment);
		mpgReq.setStatusCheck(status_check);
		mpgReq.send();

		try
		{
			Receipt receipt = mpgReq.getReceipt();

			System.out.println("CardType = " + receipt.getCardType());
			System.out.println("TransAmount = " + receipt.getTransAmount());
			System.out.println("TxnNumber = " + receipt.getTxnNumber());
			System.out.println("ReceiptId = " + receipt.getReceiptId());
			System.out.println("TransType = " + receipt.getTransType());
			System.out.println("ReferenceNum = " + receipt.getReferenceNum());
			System.out.println("ResponseCode = " + receipt.getResponseCode());
			System.out.println("ISO = " + receipt.getISO());
			System.out.println("BankTotals = " + receipt.getBankTotals());
			System.out.println("Message = " + receipt.getMessage());
			System.out.println("AuthCode = " + receipt.getAuthCode());
			System.out.println("Complete = " + receipt.getComplete());
			System.out.println("TransDate = " + receipt.getTransDate());
			System.out.println("TransTime = " + receipt.getTransTime());
			System.out.println("Ticket = " + receipt.getTicket());
			System.out.println("TimedOut = " + receipt.getTimedOut());
			System.out.println("IsVisaDebit = " + receipt.getIsVisaDebit());
			System.out.println("FastFundsIndicator = " + receipt.getFastFundsIndicator());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
