package model;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session")
public class currencyModel {

	private Date date;
	private String sourceCurrency;
	private String targetCurrency;
	private String conversionRate;
	
	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getSourceCurrency() {
		return sourceCurrency;
	}
	public void setSourceCurrency(String sourceCurrency) {
		this.sourceCurrency = sourceCurrency;
	}
	public String getTargetCurrency() {
		return targetCurrency;
	}
	public void setTargetCurrency(String targetCurrency) {
		this.targetCurrency = targetCurrency;
	}
	public String getConversionRate() {
		return conversionRate;
	}
	public void setConversionRate(String conversionRate) {
		this.conversionRate = conversionRate;
	}
	
	
	public currencyModel(Date date,String source,String rate,String target) {
	   super();
		this.date =date;
		this.sourceCurrency=source;
		this.targetCurrency=target;
		this.conversionRate=rate;
	}
	
	@Override
    public String toString() {
        return "currencyModel [Date=" + this.date + ", sCurrency=" + this.sourceCurrency + ", tCurrency="
                + this.targetCurrency + ", Rate=" + this.conversionRate + "]";
    }

	
	
	
	
	
}
