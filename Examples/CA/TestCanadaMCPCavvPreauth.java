package Canada;

import JavaAPI.*;

public class TestCanadaMCPCavvPreauth
{
	public static void main(String[] args)
	{
		String store_id = "store1";
		String api_token = "yesguy1";
//		String store_id = "monca02760";
//        String api_token = "ibnNzGEEphi0wdlivn9l";
		java.util.Date createDate = new java.util.Date();
		String order_id = "Test"+createDate.getTime();
		String amount = "5.00";
		String pan = "4242424242424242";
		String expdate = "1902";
		String crypt = "1";
		String processing_country_code = "CA";
		String ds_trans_id = "12345";
		String dynamic_descriptor = "ABC Comp";
		String cavv = "BwABApFSYyd4l2eQQFJjAAAAAAA=";
		boolean status_check = false;

		MCPCavvPreAuth mcpCavvPreauth = new MCPCavvPreAuth();
		mcpCavvPreauth.setOrderId(order_id);
		mcpCavvPreauth.setAmount(amount);
		mcpCavvPreauth.setPan(pan);
		mcpCavvPreauth.setExpdate(expdate);
		mcpCavvPreauth.setCryptType(crypt);
		mcpCavvPreauth.setCavv(cavv);
		mcpCavvPreauth.setDynamicDescriptor(dynamic_descriptor);
		//mcpCavvPreauth.setWalletIndicator(""); //Refer documentation for possible values

		mcpCavvPreauth.setThreeDSVersion("2"); //Mandatory for 3DS Version 2.0+
		mcpCavvPreauth.setThreeDSServerTransId("e11d4985-8d25-40ed-99d6-c3803fe5e68f"); //Mandatory for 3DS Version 2.0+ - obtained from MpiCavvLookup or MpiThreeDSAuthentication
		mcpCavvPreauth.setDsTransId(ds_trans_id); //Optional - to be used only if you are using 3rd party 3ds 2.0 service

		//optional - Credential on File details
		CofInfo cof = new CofInfo();
		cof.setPaymentIndicator("C");
		cof.setPaymentInformation("0");
		cof.setIssuerId("139X3130ASCXAS9");
		
		mcpCavvPreauth.setCofInfo(cof);
		AvsInfo avsCheck = new AvsInfo();
		avsCheck.setAvsStreetNumber("212");
		avsCheck.setAvsStreetName("Payton Street");
		avsCheck.setAvsZipCode("M1M1M1");
		mcpCavvPreauth.setAvsInfo(avsCheck);
		CvdInfo cvdCheck = new CvdInfo();
		cvdCheck.setCvdIndicator("1");
		cvdCheck.setCvdValue("099");
		mcpCavvPreauth.setCvdInfo(cvdCheck);

		//MCP Fields
		mcpCavvPreauth.setMCPVersion("1.0");
		mcpCavvPreauth.setCardholderAmount("35000");
		mcpCavvPreauth.setCardholderCurrencyCode("008");
		mcpCavvPreauth.setMCPRateToken("P1637698935227391");

		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(mcpCavvPreauth);
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
			//System.out.println("StatusCode = " + receipt.getStatusCode());
			//System.out.println("StatusMessage = " + receipt.getStatusMessage());
			System.out.println("IssuerId = " + receipt.getIssuerId());
			System.out.println("ThreeDSVersion = " + receipt.getThreeDSVersion());
			System.out.println("SourcePanLast4 = " + receipt.getSourcePanLast4());

			System.out.println("MerchantSettlementAmount = " + receipt.getMerchantSettlementAmount());
			System.out.println("CardholderAmount = " + receipt.getCardholderAmount());
			System.out.println("CardholderCurrencyCode = " + receipt.getCardholderCurrencyCode());
			System.out.println("MCPRate = " + receipt.getMCPRate());
			System.out.println("MCPErrorStatusCode = " + receipt.getMCPErrorStatusCode());
			System.out.println("MCPErrorMessage = " + receipt.getMCPErrorMessage());
			System.out.println("HostId = " + receipt.getHostId());		
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
