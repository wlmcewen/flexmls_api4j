package com.flexmls.flexmls_api.models;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.deser.DateDeserializer;


public class MarketStatistic extends Base {
	private static Logger logger = Logger.getLogger(MarketStatistic.class);
	
	public static class MDY_DateDeserializer extends DateDeserializer {
        private static final SimpleDateFormat FORMAT = new SimpleDateFormat("MM/dd/yyyy");
		@Override
		public Date deserialize(JsonParser jsonParser, DeserializationContext ctxt)
				throws IOException {
	        String date = jsonParser.getText();
	        try {
	            return FORMAT.parse(date);
	        } catch (ParseException e) {
	        	logger.debug("Failed parsing expected date format.  Trying again with standard formats.", e);
	        }
			return super.deserialize(jsonParser, ctxt);
		}
		
	}

	private Map<String, List<Float>> attributes = new HashMap<String, List<Float>>();
	
	public static enum MarketStatFields {
		// Volume
		ActiveListVolume,
		NewListVolume,
		PendedListVolume,
		SoldListVolume,
		SoldSaleVolume,
		//DOM
		AverageDom,
		AverageCdom,
		// Ratio
		SaleToOriginalListPriceRatio,
		SaleToListPriceRatio,
		// Price
		ActiveAverageListPrice,
		NewAverageListPrice,
		PendedAverageListPrice,
		SoldAverageListPrice,
		SoldAverageSoldPrice,
		ActiveMedianListPrice,
		NewMedianListPrice,
		PendedMedianListPrice,
		SoldMedianListPrice,
		SoldMedianSoldPrice,
		// Inventory
		ActiveListings,
		NewListings,
		PendedListings,
		SoldListings,
		// Absortion
		AbsorptionRate;

	}

	@JsonProperty("Dates")
	@JsonDeserialize(contentUsing=MDY_DateDeserializer.class)
	private List<Date> dates;

	public List<Date> getDates() {
		return dates;
	}

	public void setDates(List<Date> dates) {
		this.dates = dates;
	}
	
	@JsonAnySetter
	public void setAttribute(String key, Object obj){
		if (MarketStatFields.valueOf(key) != null) {
			@SuppressWarnings("unchecked")
			List<Float> floats = (List<Float>)obj;
			if(floats != null){
				setAttribute(key, floats);
				return;
			}
			logger.warn("Key value is not the expected json type: "  + key);
		}
		super.setAttribute(key, obj);
	}
	
	public void setAttribute(String key, List<Float> value){
		if(logger.isDebugEnabled()){
			logger.debug("Added attribute: "  + key);
		}
		attributes.put(key, value);
	}

	public Map<String, List<Float>> getMarketAttributes(){
		return attributes;
	}	

}
