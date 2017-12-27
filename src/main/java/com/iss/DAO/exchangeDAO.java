package com.iss.DAO;

import com.iss.model.currencyModel;

public interface exchangeDAO {
	
	/**
	 * Pluggable interaface For easy reusability 
	 *
	 */
	public currencyModel findExchangeRates(String paramDate, String sCurr);

}
