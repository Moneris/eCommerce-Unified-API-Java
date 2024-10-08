package Canada;

import JavaAPI.GooglePayPurchase;
import JavaAPI.GooglePayTokenPurchase;
import JavaAPI.HttpsPostRequest;
import JavaAPI.Receipt;

public class TestCanadaGooglePayTokenPurchase
{
	public static void main(String[] args)
	{
		java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
		String store_id = "store5";
		String api_token = "yesguy";
		String amount = "1.00";
		String crypt_type = "2";
		String cust_id = "nqa-cust_id";
		String network = "MASTERCARD";
		String data_key = "ot-5N7NHu23o7w82xL8KLOXV2vC1";
		String threeds_server_trans_id = "de1b97ee-c610-4877-b53f-c1c5ecd99bf0";
		String ds_trans_id = "de1b97ee-c610-4877-b53f-c1c5ecd99bf0";
		String threeds_version = "2.2";
		String cavv = "kAABApFSYyd4l2eQQFJjAAAAAAA=";		String dynamic_descriptor = "nqa-dd";
		String processing_country_code = "CA";
		boolean status_check = false;

		GooglePayTokenPurchase googlePayTokenPurchase = new GooglePayTokenPurchase();
		googlePayTokenPurchase.setOrderId(order_id);
		googlePayTokenPurchase.setCustId(cust_id);
		googlePayTokenPurchase.setAmount(amount);
		googlePayTokenPurchase.setCryptType(crypt_type);
		googlePayTokenPurchase.setNetwork(network);
		googlePayTokenPurchase.setDataKey(data_key);
		googlePayTokenPurchase.setThreeDSServerTransId(threeds_server_trans_id);
		googlePayTokenPurchase.setDSTransId(ds_trans_id);
		googlePayTokenPurchase.setThreeDSVersion(threeds_version);
		googlePayTokenPurchase.setCavv(cavv);
		googlePayTokenPurchase.setDynamicDescriptor(dynamic_descriptor);
		
		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(googlePayTokenPurchase);
		mpgReq.setStatusCheck(status_check);

		//Optional - Proxy
		mpgReq.setProxy(false); //true to use proxy
		mpgReq.setProxyHost("proxyURL");
		mpgReq.setProxyPort("proxyPort"); 
		mpgReq.setProxyUser("proxyUser"); //optional - domainName\User
		mpgReq.setProxyPassword("proxyPassword"); //optional
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
			System.out.println("HostId = " + receipt.getHostId());
			System.out.println("IssuerId = " + receipt.getIssuerId());
			System.out.println("SourcePanLast4 = " + receipt.getSourcePanLast4());
			System.out.println("ThreeDSVersion = " + receipt.getThreeDSVersion());
			System.out.println("CavvResultCode = " + receipt.getCavvResultCode());
			System.out.println("DataKey = " + receipt.getDataKey());
			System.out.println("Par = " + receipt.getPar());
			System.out.println("GooglepayPaymentMethod = " + receipt.getGooglepayPaymentMethod());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
