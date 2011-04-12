package com.fbsdata.flexmls_api.models;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.fbsdata.flexmls_api.ApiParameter;
import com.fbsdata.flexmls_api.Configuration;
import com.fbsdata.flexmls_api.FlexmlsApiClientException;
import com.fbsdata.flexmls_api.MockClient;
import com.fbsdata.flexmls_api.PropertyAsserter;
import com.fbsdata.flexmls_api.Response;
import com.fbsdata.flexmls_api.services.MarketStatisticsService;


public class MarketStatisticTest {
	private static final String JSON_ABSORPTION = "marketstatistics_absorption.json";
	private static final String JSON_DOM = "marketstatistics_dom.json";
	private static final String JSON_INV = "marketstatistics_inventory.json";
	private static final String JSON_PRICE = "marketstatistics_price.json";
	private static final String JSON_RATIO = "marketstatistics_ratio.json";
	private static final String JSON_VOL = "marketstatistics_volume.json";
	
	MockClient c;
	MarketStatisticsService s;
	
	@Before
	public void setup() throws FlexmlsApiClientException{
		Configuration cf = new Configuration();
		cf.setApiUser("SOME_GUY");
		c = MockClient.mock(cf);
		s = new MarketStatisticsService(c);
	}
	
	@Test
	public void testProperties(){
		PropertyAsserter.assertBasicGetterSetterBehavior(new MarketStatistic());
	}
	
	@Test
	public void testGet() throws FlexmlsApiClientException {
		c.stubGet(s.getPath() + "/ratio", JSON_RATIO, 200);
		Response r = c.get(s.getPath() + "/ratio", new HashMap<ApiParameter, String>());
		assertNotNull(r);
		MarketStatistic m = r.getResults(MarketStatistic.class).get(0);
		assertEquals(12, m.getDates().size());
		assertEquals(0, m.getAttributes().size());
	}

	@Test
	public void testRatio() throws FlexmlsApiClientException {
		c.stubGet(s.getPath() + "/ratio", JSON_RATIO, 200);
		MarketStatistic m = s.ratio();
		assertEquals(12, m.getDates().size());
		assertEquals(0, m.getAttributes().size());
		assertEquals(2, m.getMarketAttributes().size());
		assertEquals(12, m.getMarketAttributes().get("SaleToOriginalListPriceRatio").size());
		assertEquals(Double.valueOf(0.9834), m.getMarketAttributes().get("SaleToOriginalListPriceRatio").get(0));
	}

	@Test
	public void testAbsorbtion() throws FlexmlsApiClientException {
		c.stubGet(s.getPath() + "/absorption", JSON_ABSORPTION, 200);
		MarketStatistic m = s.absorption();
		assertEquals(12, m.getDates().size());
		assertEquals(0, m.getAttributes().size());
		assertEquals(1, m.getMarketAttributes().size());
		assertEquals(12, m.getMarketAttributes().get("AbsorptionRate").size());
		assertEquals(Double.valueOf(6.11), m.getMarketAttributes().get("AbsorptionRate").get(0));
	}
	
	@Test
	public void testPrice() throws FlexmlsApiClientException {
		c.stubGet(s.getPath() + "/price", JSON_PRICE, 200);
		MarketStatistic m = s.price();
		assertEquals(12, m.getDates().size());
		assertEquals(0, m.getAttributes().size());
		assertEquals(10, m.getMarketAttributes().size());
		assertEquals(12, m.getMarketAttributes().get("ActiveAverageListPrice").size());
		assertEquals(Integer.valueOf(100000), m.getMarketAttributes().get("ActiveAverageListPrice").get(0));
	}

	@Test
	public void testDOM() throws FlexmlsApiClientException {
		c.stubGet(s.getPath() + "/dom", JSON_DOM, 200);
		MarketStatistic m = s.dom();
		assertEquals(12, m.getDates().size());
		assertEquals(0, m.getAttributes().size());
		assertEquals(2, m.getMarketAttributes().size());
		assertEquals(12, m.getMarketAttributes().get("AverageDom").size());
		assertEquals(Integer.valueOf(100), m.getMarketAttributes().get("AverageDom").get(0));
	}

	@Test
	public void testInventory() throws FlexmlsApiClientException {
		c.stubGet(s.getPath() + "/inventory", JSON_INV, 200);
		MarketStatistic m = s.inventory();
		assertEquals(12, m.getDates().size());
		assertEquals(0, m.getAttributes().size());
		assertEquals(4, m.getMarketAttributes().size());
		assertEquals(12, m.getMarketAttributes().get("ActiveListings").size());
		assertEquals(Integer.valueOf(10000), m.getMarketAttributes().get("ActiveListings").get(0));
	}

	@Test
	public void testVolume() throws FlexmlsApiClientException {
		c.stubGet(s.getPath() + "/volume", JSON_VOL, 200);
		MarketStatistic m = s.volume();
		assertEquals(12, m.getDates().size());
		assertEquals(0, m.getAttributes().size());
		assertEquals(5, m.getMarketAttributes().size());
		assertEquals(12, m.getMarketAttributes().get("ActiveListVolume").size());
		assertEquals(Integer.valueOf(135650975), m.getMarketAttributes().get("ActiveListVolume").get(0));
	}

}
