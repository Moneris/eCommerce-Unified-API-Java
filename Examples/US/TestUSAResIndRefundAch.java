package USA;

import JavaAPI.*;

public class TestUSAResIndRefundAch
{
	public static void main(String[] args)
	{
		java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime(); 
		String store_id = "monusqa002";
		String api_token = "qatoken";
		String data_key = "hYa5CcGERZkfzzWReCAlXzB0e";
		String amount = "1.00";
		String cust_id = "customer1";
		String processing_country_code = "US";

		ResIndRefundAch resIndRefundAch = new ResIndRefundAch();
		resIndRefundAch.setOrderId(order_id);
		resIndRefundAch.setCustId(cust_id);
		resIndRefundAch.setAmount(amount);
		resIndRefundAch.setDataKey(data_key);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(resIndRefundAch);
		mpgReq.send();

		try
		{
			Receipt receipt = mpgReq.getReceipt();

			System.out.println("DataKey = " + receipt.getDataKey());
			System.out.println("ReceiptId = " + receipt.getReceiptId());
			System.out.println("ReferenceNum = " + receipt.getReferenceNum());
			System.out.println("ResponseCode = " + receipt.getResponseCode());
			System.out.println("AuthCode = " + receipt.getAuthCode());
			System.out.println("Message = " + receipt.getMessage());
			System.out.println("TransDate = " + receipt.getTransDate());
			System.out.println("TransTime = " + receipt.getTransTime());
			System.out.println("TransType = " + receipt.getTransType());
			System.out.println("Complete = " + receipt.getComplete());
			System.out.println("TransAmount = " + receipt.getTransAmount());
			System.out.println("CardType = " + receipt.getCardType());
			System.out.println("TxnNumber = " + receipt.getTxnNumber());
			System.out.println("TimedOut = " + receipt.getTimedOut());
			System.out.println("ResSuccess = " + receipt.getResSuccess());
			System.out.println("PaymentType = " + receipt.getPaymentType()); 
			System.out.println("Cust ID = " + receipt.getResCustId());
			System.out.println("Phone = " + receipt.getResPhone());
			System.out.println("Email = " + receipt.getResEmail());
			System.out.println("Note = " + receipt.getResNote());
			System.out.println("Sec = " + receipt.getResSec());
			System.out.println("Cust First Name = " + receipt.getResCustFirstName());
			System.out.println("Cust Last Name = " + receipt.getResCustLastName());
			System.out.println("Cust Address 1 = " + receipt.getResCustAddress1());
			System.out.println("Cust Address 2 = " + receipt.getResCustAddress2());
			System.out.println("Cust City = " + receipt.getResCustCity());
			System.out.println("Cust State = " + receipt.getResCustState());
			System.out.println("Cust Zip = " + receipt.getResCustZip());
			System.out.println("Routing Num = " + receipt.getResRoutingNum());
			System.out.println("Masked Account Num = " + receipt.getResMaskedAccountNum());
			System.out.println("Check Num = " + receipt.getResCheckNum());
			System.out.println("Account Type = " + receipt.getResAccountType());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
