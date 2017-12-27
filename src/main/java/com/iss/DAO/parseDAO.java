package com.iss.DAO;

import com.iss.model.currencyModel;

public interface parseDAO {
	/**
	 * Pluggable interafaces  For easy reusability 
	 *
	 *truncateTable deletes the entire table   
	 *insert taken currencyModel as arguement and inserts the object into MySQL Table
	 */
	public void truncateTable();
	public void insert(currencyModel currency);

	
    
}
