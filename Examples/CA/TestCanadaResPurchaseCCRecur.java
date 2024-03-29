package Canada;

import JavaAPI.*;

public class TestCanadaResPurchaseCCRecur
{
	public static void main(String[] args)
	{
		java.util.Date createDate = new java.util.Date(); 
		String order_id = "Test"+createDate.getTime();
		String store_id = "store5";
		String api_token = "yesguy";
		String data_key = "muW95BX60n4NHOnb7J6b3G8n2";
		String amount = "1.00";
		String cust_id = "customer1"; //if sent will be submitted, otherwise cust_id from profile will be used
		String crypt_type = "2";
		String processing_country_code = "CA";
		boolean status_check = false;

		ResPurchaseCC resPurchaseCC = new ResPurchaseCC();
		resPurchaseCC.setDataKey(data_key);
		resPurchaseCC.setOrderId(order_id);
		resPurchaseCC.setCustId(cust_id);
		resPurchaseCC.setAmount(amount);
		resPurchaseCC.setCryptType(crypt_type);

		//NT Response Option
		boolean get_nt_response = true;
		resPurchaseCC.setGetNtResponse(get_nt_response);

		/************************* Recur Variables **********************************/

		String recur_unit = "month";
		String start_now = "false";
		String start_date = "2019/12/01";
		String num_recurs = "12";
		String period = "1";
		String recur_amount = "30.00";

		/************************* Recur Object Option1 ******************************/

		Recur recurring_cycle = new Recur(recur_unit, start_now, start_date,
				num_recurs, period, recur_amount);

		resPurchaseCC.setRecur(recurring_cycle);
		

		//Mandatory - Credential on File details
		CofInfo cof = new CofInfo();
		cof.setPaymentIndicator("R");
		cof.setPaymentInformation("2");
		cof.setIssuerId("139X3130ASCXAS9");
		
		resPurchaseCC.setCofInfo(cof);
		
		HttpsPostRequest mpgReq = new HttpsPostRequest();
		mpgReq.setProcCountryCode(processing_country_code);
		mpgReq.setTestMode(true); //false or comment out this line for production transactions
		mpgReq.setStoreId(store_id);
		mpgReq.setApiToken(api_token);
		mpgReq.setTransaction(resPurchaseCC);
		mpgReq.setStatusCheck(status_check);
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
			System.out.println("AVSResponse = " + receipt.getAvsResultCode());
			System.out.println("CVDResponse = " + receipt.getCvdResultCode());
			System.out.println("RecurSuccess = " + receipt.getRecurSuccess());
			System.out.println("ResSuccess = " + receipt.getResSuccess());
			System.out.println("PaymentType = " + receipt.getPaymentType());
			System.out.println("Cust ID = " + receipt.getResCustId());
			System.out.println("Phone = " + receipt.getResPhone());
			System.out.println("Email = " + receipt.getResEmail());
			System.out.println("Note = " + receipt.getResNote());
			System.out.println("Masked Pan = " + receipt.getResMaskedPan());
			System.out.println("Exp Date = " + receipt.getResExpdate());
			System.out.println("Crypt Type = " + receipt.getResCryptType());
			System.out.println("Avs Street Number = " + receipt.getResAvsStreetNumber());
			System.out.println("Avs Street Name = " + receipt.getResAvsStreetName());
			System.out.println("Avs Zipcode = " + receipt.getResAvsZipcode());
			System.out.println("IssuerId = " + receipt.getIssuerId());

			if(get_nt_response) {
				System.out.println("NTResponseCode = " + receipt.getNTResponseCode());
				System.out.println("NTMessage = " + receipt.getNTMessage());
				System.out.println("NTUsed = " + receipt.getNTUsed());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
