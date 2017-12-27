package com.iss.DAO;

import java.util.List;

import com.iss.model.currencyModel;
public interface currencyDAO
{
	public List<currencyModel> findByDate(String Date);

	public List<currencyModel> findByDateRange(String sDate,String eDate);
 
}