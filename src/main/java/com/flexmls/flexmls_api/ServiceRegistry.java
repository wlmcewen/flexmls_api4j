package com.flexmls.flexmls_api;

import com.flexmls.flexmls_api.services.ListingService;
import com.flexmls.flexmls_api.services.MarketStatisticsService;
import com.flexmls.flexmls_api.services.PropertyTypeService;

/**
 * Single point of access for working with all API services.
 */
public class ServiceRegistry {
	
	private Client client;
	// Services
	private ListingService listingService;
	private PropertyTypeService propertyTypeService;
	private MarketStatisticsService marketStatisticsService;
	
	public ServiceRegistry(Client client) {
		super();
		this.client = client;
	}

	public ListingService getListingService() {
		if(listingService == null){
			listingService = new ListingService(client);
		}
		return listingService;
	}

	public void setListingService(ListingService listingService) {
		this.listingService = listingService;
	}

	public PropertyTypeService getPropertyTypeService() {
		if(propertyTypeService == null){
			propertyTypeService = new PropertyTypeService(client);
		}
		return propertyTypeService;
	}

	public void setPropertyTypeService(PropertyTypeService propertyTypeService) {
		this.propertyTypeService = propertyTypeService;
	}
	
	public MarketStatisticsService getMarketStatisticsService() {
		if(marketStatisticsService == null){
			marketStatisticsService = new MarketStatisticsService(client);
		}
		return marketStatisticsService;
	}

	public void setMarketStatisticsService(
			MarketStatisticsService marketStatisticsService) {
		this.marketStatisticsService = marketStatisticsService;
	}


}
