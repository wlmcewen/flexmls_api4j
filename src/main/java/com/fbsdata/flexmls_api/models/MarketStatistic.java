package com.fbsdata.flexmls_api.models;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
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
				throws IOException, JsonProcessingException {
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
	
	enum MarketStatFields {
		// Volume
		ActiveListVolume("ActiveListVolume"),
		NewListVolume("NewListVolume"),
		PendedListVolume("PendedListVolume"),
		SoldListVolume("SoldListVolume"),
		SoldSaleVolume("SoldSaleVolume"),
		//DOM
		AverageDom("AverageDom"),
		AverageCdom("AverageCdom"),
		// Ratio
		SaleToOriginalListPriceRatio("SaleToOriginalListPriceRatio"),
		SaleToListPriceRatio("SaleToListPriceRatio"),
		// Price
		ActiveAverageListPrice("ActiveAverageListPrice"),
		NewAverageListPrice("NewAverageListPrice"),
		PendedAverageListPrice("PendedAverageListPrice"),
		SoldAverageListPrice("SoldAverageListPrice"),
		SoldAverageSoldPrice("SoldAverageSoldPrice"),
		ActiveMedianListPrice("ActiveMedianListPrice"),
		NewMedianListPrice("NewMedianListPrice"),
		PendedMedianListPrice("PendedMedianListPrice"),
		SoldMedianListPrice("SoldMedianListPrice"),
		SoldMedianSoldPrice("SoldMedianSoldPrice"),
		// Inventory
		ActiveListings("ActiveListings"),
		NewListings("NewListings"),
		PendedListings("PendedListings"),
		SoldListings("SoldListings"),
		// Absortion
		AbsorptionRate("AbsorptionRate");

		private static final Map<String, MarketStatFields> lookup = new HashMap<String, MarketStatFields>();

		static {
			for (MarketStatFields s : EnumSet.allOf(MarketStatFields.class)) {
				lookup.put(s.getKey(), s);
			}
		}

		private String key;
		private MarketStatFields(String key){
			this.key = key;
		}

		public String getKey() {
			return key;
		}

		public static MarketStatFields get(String code){
			return lookup.get(code);
		}		
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
		if (MarketStatFields.get(key) != null) {
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
	
	public void setAttribute2(String key, Object obj){
		if (MarketStatFields.get(key) != null) {
			List<?> list = (List<?>)obj;
			if(list != null){
				List<Float> floats = new ArrayList<Float>();
				for (Object i : list) {
					Float f = Float.parseFloat(i.toString());
					floats.add(f);
				}
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
