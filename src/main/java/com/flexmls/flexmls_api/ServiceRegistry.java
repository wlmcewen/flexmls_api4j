package com.flexmls.flexmls_api;

import com.flexmls.flexmls_api.services.ContactService;
import com.flexmls.flexmls_api.services.ListingService;
import com.flexmls.flexmls_api.services.MarketStatisticsService;
import com.flexmls.flexmls_api.services.PropertyTypeService;
import com.flexmls.flexmls_api.services.StandardFieldService;
import com.flexmls.flexmls_api.services.SystemInfoService;

/**
 * Single point of access for working with all API services.
 */
public class ServiceRegistry {
	
	private Client client;
	// Services
	private ContactService contactService; 
	private ListingService listingService;
	private PropertyTypeService propertyTypeService;
	private MarketStatisticsService marketStatisticsService;
	private SystemInfoService systemInfoService;
	private StandardFieldService standardFieldService;
	
	public ServiceRegistry(Client client) {
		super();
		this.client = client;
	}

	public ContactService getContactService() {
		if(contactService == null){
			contactService = new ContactService(client);
		}
		return contactService;
	}

	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
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

	public SystemInfoService getSystemInfoService() {
		if(systemInfoService == null){
			systemInfoService = new SystemInfoService(client);
		}
		return systemInfoService;
	}

	public void setSystemInfoService(SystemInfoService systemInfoService) {
		this.systemInfoService = systemInfoService;
	}

	public StandardFieldService getStandardFieldService() {
		if(standardFieldService == null){
			standardFieldService = new StandardFieldService(client);
		}
		return standardFieldService;
	}

	public void setStandardFieldService(StandardFieldService standardFieldService) {
		this.standardFieldService = standardFieldService;
	}

}
