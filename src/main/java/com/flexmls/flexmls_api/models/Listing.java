package com.flexmls.flexmls_api.models;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Listing extends ResourceEntity {
	
	@JsonProperty("StandardFields")
	private StandardFields standardFields;
	
	public StandardFields getStandardFields() {
		return standardFields;
	}
	
	public void setStandardFields(StandardFields standardFields) {
		this.standardFields = standardFields;
	}

	public static class StandardFields extends Base {
	    @JsonProperty("StreetNumber")
	    private String streetNumber;
	    @JsonProperty("Longitude")
	    private String longitude;
	    @JsonProperty("City")
	    private String city;
	    @JsonProperty("ListingId")
	    private String listingId;
	    @JsonProperty("PublicRemarks")
	    private String publicRemarks;
	    @JsonProperty("BuildingAreaTotal")
	    private String buildingAreaTotal;
	    @JsonProperty("YearBuilt")
	    private int yearBuilt;
	    @JsonProperty("StreetName")
	    private String streetName;
	    @JsonProperty("ListPrice")
	    private String listPrice;
	    @JsonProperty("PostalCode")
	    private String postalCode;
	    @JsonProperty("Latitude")
	    private String latitude;
	    @JsonProperty("BathsThreeQuarter")
	    private String bathsThreeQuarter;
	    @JsonProperty("BathsFull")
	    private String bathsFull;
	    @JsonProperty("BathsTotal")
	    private String bathsTotal;
	    @JsonProperty("StateOrProvince")
	    private String stateOrProvince;
	    @JsonProperty("PropertyType")
	    private String propertyType;
	    @JsonProperty("StreetAdditionalInfo")
	    private String streetAdditionalInfo;
	    @JsonProperty("StreetDirPrefix")
	    private String streetDirPrefix;
	    @JsonProperty("BedsTotal")
	    private String bedsTotal;
	    @JsonProperty("StreetDirSuffix")
	    private String streetDirSuffix;
	    @JsonProperty("ListingKey")
	    private String listingKey;
	    @JsonProperty("ListOfficeName")
	    private String listOfficeName;
	    @JsonProperty("BathsHalf")
	    private String bathsHalf;
	    @JsonProperty("ModificationTimestamp")
	    private Date modificationTimestamp;
	    @JsonProperty("CountyOrParish")
	    private String countyOrParish;
		public String getStreetNumber() {
			return streetNumber;
		}
		public void setStreetNumber(String streetNumber) {
			this.streetNumber = streetNumber;
		}
		public String getLongitude() {
			return longitude;
		}
		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getListingId() {
			return listingId;
		}
		public void setListingId(String listingId) {
			this.listingId = listingId;
		}
		public String getPublicRemarks() {
			return publicRemarks;
		}
		public void setPublicRemarks(String publicRemarks) {
			this.publicRemarks = publicRemarks;
		}
		public String getBuildingAreaTotal() {
			return buildingAreaTotal;
		}
		public void setBuildingAreaTotal(String buildingAreaTotal) {
			this.buildingAreaTotal = buildingAreaTotal;
		}
		public int getYearBuilt() {
			return yearBuilt;
		}
		public void setYearBuilt(int yearBuilt) {
			this.yearBuilt = yearBuilt;
		}
		public String getStreetName() {
			return streetName;
		}
		public void setStreetName(String streetName) {
			this.streetName = streetName;
		}
		public String getListPrice() {
			return listPrice;
		}
		public void setListPrice(String listPrice) {
			this.listPrice = listPrice;
		}
		public String getPostalCode() {
			return postalCode;
		}
		public void setPostalCode(String postalCode) {
			this.postalCode = postalCode;
		}
		public String getLatitude() {
			return latitude;
		}
		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}
		public String getBathsThreeQuarter() {
			return bathsThreeQuarter;
		}
		public void setBathsThreeQuarter(String bathsThreeQuarter) {
			this.bathsThreeQuarter = bathsThreeQuarter;
		}
		public String getBathsFull() {
			return bathsFull;
		}
		public void setBathsFull(String bathsFull) {
			this.bathsFull = bathsFull;
		}
		public String getBathsTotal() {
			return bathsTotal;
		}
		public void setBathsTotal(String bathsTotal) {
			this.bathsTotal = bathsTotal;
		}
		public String getStateOrProvince() {
			return stateOrProvince;
		}
		public void setStateOrProvince(String stateOrProvince) {
			this.stateOrProvince = stateOrProvince;
		}
		public String getPropertyType() {
			return propertyType;
		}
		public void setPropertyType(String propertyType) {
			this.propertyType = propertyType;
		}
		public String getStreetAdditionalInfo() {
			return streetAdditionalInfo;
		}
		public void setStreetAdditionalInfo(String streetAdditionalInfo) {
			this.streetAdditionalInfo = streetAdditionalInfo;
		}
		public String getStreetDirPrefix() {
			return streetDirPrefix;
		}
		public void setStreetDirPrefix(String streetDirPrefix) {
			this.streetDirPrefix = streetDirPrefix;
		}
		public String getBedsTotal() {
			return bedsTotal;
		}
		public void setBedsTotal(String bedsTotal) {
			this.bedsTotal = bedsTotal;
		}
		public String getStreetDirSuffix() {
			return streetDirSuffix;
		}
		public void setStreetDirSuffix(String streetDirSuffix) {
			this.streetDirSuffix = streetDirSuffix;
		}
		public String getListingKey() {
			return listingKey;
		}
		public void setListingKey(String listingKey) {
			this.listingKey = listingKey;
		}
		public String getListOfficeName() {
			return listOfficeName;
		}
		public void setListOfficeName(String listOfficeName) {
			this.listOfficeName = listOfficeName;
		}
		public String getBathsHalf() {
			return bathsHalf;
		}
		public void setBathsHalf(String bathsHalf) {
			this.bathsHalf = bathsHalf;
		}
		public Date getModificationTimestamp() {
			return modificationTimestamp;
		}
		public void setModificationTimestamp(Date modificationTimestamp) {
			this.modificationTimestamp = modificationTimestamp;
		}
		public String getCountyOrParish() {
			return countyOrParish;
		}
		public void setCountyOrParish(String countyOrParish) {
			this.countyOrParish = countyOrParish;
		}
	}
	
	public String getStreetNumber() {
		return standardFields.streetNumber;
	}
	public String getLongitude() {
		return standardFields.longitude;
	}
	public String getCity() {
		return standardFields.city;
	}
	public String getListingId() {
		return standardFields.listingId;
	}
	public String getPublicRemarks() {
		return standardFields.publicRemarks;
	}
	public String getBuildingAreaTotal() {
		return standardFields.buildingAreaTotal;
	}
	public int getYearBuilt() {
		return standardFields.yearBuilt;
	}
	public String getStreetName() {
		return standardFields.streetName;
	}
	public String getListPrice() {
		return standardFields.listPrice;
	}
	public String getPostalCode() {
		return standardFields.postalCode;
	}
	public String getLatitude() {
		return standardFields.latitude;
	}
	public String getBathsThreeQuarter() {
		return standardFields.bathsThreeQuarter;
	}
	public String getBathsFull() {
		return standardFields.bathsFull;
	}
	public String getBathsTotal() {
		return standardFields.bathsTotal;
	}
	public String getStateOrProvince() {
		return standardFields.stateOrProvince;
	}
	public String getPropertyType() {
		return standardFields.propertyType;
	}
	public String getStreetAdditionalInfo() {
		return standardFields.streetAdditionalInfo;
	}
	public String getStreetDirPrefix() {
		return standardFields.streetDirPrefix;
	}
	public String getBedsTotal() {
		return standardFields.bedsTotal;
	}
	public String getStreetDirSuffix() {
		return standardFields.streetDirSuffix;
	}
	public String getListingKey() {
		return standardFields.listingKey;
	}
	public String getListOfficeName() {
		return standardFields.listOfficeName;
	}
	public String getBathsHalf() {
		return standardFields.bathsHalf;
	}
	public Date getModificationTimestamp() {
		return standardFields.modificationTimestamp;
	}
	public String getCountyOrParish() {
		return standardFields.countyOrParish;
	}
	
	
}
