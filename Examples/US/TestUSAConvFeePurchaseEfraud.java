package USA;

import JavaAPI.*;

public class TestUSAConvFeePurchaseEfraud
{
	public static void main(String args[])
	{
		String store_id = "monusqa138";
		String api_token = "qatoken";
		java.util.Date createDate = new java.util.Date();
		String order_id = "Test"+createDate.getTime();
		String amount = "10.23";
		String pan = "4242424242424242";
		String expiry_date = "1911";
		String crypt = "7";
		String commcard_invoice = "";
		String commcard_tax_amount = "";
		String processing_country_code = "US";
		
		AvsInfo avs = new AvsInfo ("123", "Edgar Street", "M1M1M1");
		CvdInfo cvd = new CvdInfo ("1", "099");

		Purchase purchase = new Purchase();
		purchase.setOrderId(order_id);
		purchase.setAmount(amount);
		purchase.setPan(pan);
		purchase.setExpdate(expiry_date);
		purchase.setCryptType(crypt);
		purchase.setCommcardInvoice(commcard_invoice);
		purchase.setCommcardTaxAmount(commcard_tax_amount);
		
		purchase.setAvsInfo (avs);
		purchase.setCvdInfo (cvd);

		//Convenience Fee
		ConvFeeInfo convFeeInfo = new ConvFeeInfo();
		convFeeInfo.setConvenienceFee("5.00");
		purchase.setConvFeeInfo(convFeeInfo);

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(purchase);
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
			System.out.println("CvdResultCode = " + receipt.getCvdResultCode());
			System.out.println("AvsResultCode = " + receipt.getAvsResultCode());
			System.out.println("CfSuccess = " + receipt.getCfSuccess());
			System.out.println("CfStatus = " + receipt.getCfStatus());
			System.out.println("FeeAmount = " + receipt.getFeeAmount());
			System.out.println("FeeRate = " + receipt.getFeeRate());
			System.out.println("FeeType = " + receipt.getFeeType());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
