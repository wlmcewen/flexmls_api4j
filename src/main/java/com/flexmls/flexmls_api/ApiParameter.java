package com.flexmls.flexmls_api;

/**
 * All the documented API response codes.
 */
public enum ApiParameter {
	// Search
	_filter,
	_expand,
	// Pagination
	_pagination,
	_limit,
	_page,
	_orderby,
	// Market Stats
	LocationField,
	LocationValue,
	Options,
	PropertyTypeCode,
	// Hotsheet
	HotSheet,
	OpenHouses,
	// Api client fundamentals
	ApiKey,
	ApiSig,
	AuthToken,
	ApiUser;

}
