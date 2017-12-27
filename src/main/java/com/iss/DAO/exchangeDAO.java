package com.iss.DAO;

import com.iss.model.currencyModel;

public interface exchangeDAO {
	public currencyModel findExchangeRates(String paramDate, String sCurr);

}
