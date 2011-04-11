package com.fbsdata.flexmls_api.services;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fbsdata.flexmls_api.Client;
import com.fbsdata.flexmls_api.FlexmlsApiClientException;

import com.fbsdata.flexmls_api.models.ResourceEntity;

/**
 * Base service class that abstracts the HTTP rest requests using some handy methods.
 * @param <T> Resource entity that this service end point provides.
 */
public abstract class BaseService<T extends ResourceEntity> {
	protected static final Map<String, String> EMPTY = new HashMap<String, String>();
	private Class<T> klass;
	private Client c = null;

	public BaseService(Client c) {
		super();
		this.c = c;
	}

	public Client getClient() {
		return c;
	}

	public T get(String id) throws FlexmlsApiClientException {
		return get(id, EMPTY);
	}

	public T get(String id, Map<String, String> options)
			throws FlexmlsApiClientException {
		return c.get(getPath(id), options).getResults(model()).get(0);
	}

	public List<T> find() throws FlexmlsApiClientException {
		return find(EMPTY);
	}

	public List<T> find(Map<String, String> options)
			throws FlexmlsApiClientException {
		return c.get(getPath(), options).getResults(model());
	}

	protected abstract String getPath();

	protected String getPath(String resourceId) {
		return new StringBuffer(getPath()).append("/").append(resourceId)
				.toString();
	}

	/**
	 * This is a kludgy workaround to get the generic class sent in for the resource. 
	 * It may not work in all instances, in which case the actual \<Model\>.class value 
	 * should be explicitly returned.
	 * 
	 * It is not perfect, but it is WAY better than having to pass it in to all service 
	 * implementations.
	 */
	@SuppressWarnings("unchecked")
	protected Class<T> model() {
		if(klass != null){
			return klass;
		}
		ParameterizedType superclass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		klass = (Class<T>) superclass.getActualTypeArguments()[0];
		if(klass == null) {
			throw new IllegalArgumentException("Unable to determine the service model class, it will need to be explicitly passed.");
		}
		return klass;
	}

}
