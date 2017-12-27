package com.iss.model;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session")
public class currencyModel {

	private Date date;
	private String sourceCurrency;
	private String targetCurrency;
	private String conversionRate;

	/**
	 * Model Function getDate() retrieves Date
	 *
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Model Function setDate() sets Date
	 *
	 */

	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Model Function getSourceCurrency() retrieves SCurr
	 *
	 */
	public String getSourceCurrency() {
		return sourceCurrency;
	}

	/**
	 * Model Function setSourceCurrency() sets sCurr
	 *
	 */
	public void setSourceCurrency(String sourceCurrency) {
		this.sourceCurrency = sourceCurrency;
	}

	/**
	 * Model Function getTargetCurrency() retrieves tCurr
	 *
	 */
	public String getTargetCurrency() {
		return targetCurrency;
	}

	/**
	 * Model Function setTargetCurrency() sets tCurr
	 *
	 */
	public void setTargetCurrency(String targetCurrency) {
		this.targetCurrency = targetCurrency;
	}

	/**
	 * Model Function getConversionRate() retrieves conversionrate
	 *
	 */
	public String getConversionRate() {
		return conversionRate;
	}

	/**
	 * Model Function setConversionRatecy() sets conversionRate
	 *
	 */
	public void setConversionRate(String conversionRate) {
		this.conversionRate = conversionRate;
	}

	public currencyModel(Date date, String source, String rate, String target) {
		super();
		this.date = date;
		this.sourceCurrency = source;
		this.targetCurrency = target;
		this.conversionRate = rate;
	}

	@Override
	public String toString() {
		return "currencyModel [Date=" + this.date + ", sCurrency=" + this.sourceCurrency + ", tCurrency="
				+ this.targetCurrency + ", Rate=" + this.conversionRate + "]";
	}

}
