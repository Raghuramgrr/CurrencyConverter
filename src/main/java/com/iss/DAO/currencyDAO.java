package com.iss.DAO;

import java.util.List;

import com.iss.model.currencyModel;
public interface currencyDAO
{ 
	/**
	 * Pluggable interafaces  For easy reusability 
	 *
	 *FindByDate takes one Date param 
	 *FindByDateRange takes 2 Date Params 
	 */
	public List<currencyModel> findByDate(String Date);

	public List<currencyModel> findByDateRange(String sDate,String eDate);
 
}