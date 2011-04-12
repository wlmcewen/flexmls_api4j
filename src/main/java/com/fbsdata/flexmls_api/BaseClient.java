package com.fbsdata.flexmls_api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

public abstract class BaseClient<U> implements HttpActions<Response, U>{

	private static Logger logger = Logger.getLogger(Client.class);
	protected Configuration config = null;
	protected Connection<Response> connection = null;
	protected Connection<Response> secure = null;
	private Session session = null;

	public BaseClient() {
		super();
	}

	@Override
	public Response get(String path, Map<U, String> options)
			throws FlexmlsApiClientException {
				return new ReAuthable("GET", path, stringifyParameterKeys(options)) {
					@Override
					public Response run(String path, String body) throws FlexmlsApiClientException {
						return connection.get(path);
					}
				}.execute();
			}

	@Override
	public Response post(String path, String body, Map<U, String> options)
			throws FlexmlsApiClientException {
				return new ReAuthable("POST", path, body, stringifyParameterKeys(options)) {
					@Override
					public Response run(String path, String body) throws FlexmlsApiClientException {
						return connection.post(path,body);
					}
				}.execute();
			}

	@Override
	public Response put(String path, String body, Map<U, String> options)
			throws FlexmlsApiClientException {
				return new ReAuthable("PUT", path, body, stringifyParameterKeys(options)) {
					@Override
					public Response run(String path, String body) throws FlexmlsApiClientException {
						return connection.put(path,body);
					}
				}.execute();
			}

	@Override
	public Response delete(String path, Map<U, String> options)
			throws FlexmlsApiClientException {
				return new ReAuthable("DELETE", path, stringifyParameterKeys(options)) {
					@Override
					public Response run(String path, String body) throws FlexmlsApiClientException {
						return connection.delete(path);
					}
				}.execute();
			}

	abstract Map<String, String> stringifyParameterKeys(Map<U, String> parms);

	protected void log(String action, String path) {
		if(logger.isDebugEnabled()){
			logger.debug("Request: [" + action + "] - " + path);
		}
	}

	protected void reauth() throws FlexmlsApiClientException {
		if(session == null || session.isExpired()){
			authenticate();
		}
	}

	Session authenticate() throws FlexmlsApiClientException {
		StringBuffer b = new StringBuffer(config.getApiSecret());
		b.append("ApiKey").append(config.getApiKey());
		String signature = sign(b.toString());
		String path = authPath(signature);
		log("AUTH-POST", path);
		Response response = secure.post(path,"");
		List<Session> sessions = response.getResults(Session.class);
		if(sessions.isEmpty()){
			throw new FlexmlsApiClientException("Service error.  No session returned for service authentication.");
		}
		Session s = sessions.get(0);
		setSession(s);
		return s;
	}

	private String authPath(String sig) {
		StringBuffer b = new StringBuffer();
		b.append("/").append(config.getVersion()).append("/session?");
		b.append("ApiKey=").append(config.getApiKey());
		b.append("&ApiSig=").append(sig);
		return b.toString();
	}

	protected Map<String,String> sessionParams() {
		Map<String, String> params = new HashMap<String, String>();
		if(config.getApiUser() != null){
			params.put("ApiUser", config.getApiUser());
		}
		params.put("AuthToken", session.getToken());
		return params;
	}

	protected String requestPath(String path, String signature, Map<String, String> params) {
		StringBuffer b = new StringBuffer();
		b.append("/").append(config.getVersion()).append(path).append("?");
		b.append("ApiSig").append("=").append(signature);
		for (String key : params.keySet()) {
			b.append("&").append(key).append("=").append(params.get(key));
		}
		return b.toString();
	}

	protected String sign(String s) {
		return DigestUtils.md5Hex(s);
	}

	protected String signToken(String path, Map<String, String> options, String body) {
		StringBuffer b = new StringBuffer(config.getApiSecret());
		b.append("ApiKey").append(config.getApiKey());
		b.append("ServicePath/").append(config.getVersion()).append(path);
		b.append(buildParamString(options));
		b.append(body);
		return sign(b.toString());
	}

	protected String buildParamString(Map<String, String> params) {
		List<String> list = new ArrayList<String>(params.keySet());
		Collections.sort(list);
		StringBuffer buffer = new StringBuffer();
		for (String key : list) {
			buffer.append(key).append(params.get(key));
		}
		return buffer.toString();
	}

	protected String setupRequest(String path, String body, Map<String, String> options) {
		String sig = signToken(path, options, body);
		Map<String, String> params = sessionParams();
		params.putAll(options);
		return requestPath(path, sig, params);
	}

	public void getSession(Session session) {
		this.session = session;
	}

	protected void setSession(Session session) {
		this.session = session;
	}

	public Configuration getConfig() {
		return config;
	}

	protected void setConfig(Configuration config) {
		this.config = config;
	}

	abstract class ReAuthable {
		private String command;
		private String path;
		private String body = "";
		private Map<String, String> options;
		
		public ReAuthable(String command, String path, String body,
				Map<String, String> options) {
			super();
			this.command = command;
			this.path = path;
			this.body = body;
			this.options = options;
		}
		public ReAuthable(String command, String path, Map<String, String> options) {
			this(command, path, "", options);
		}
		public Response execute() throws FlexmlsApiClientException {
			reauth();
			String apiPath = setupRequest(path, body, options);
			log(command, apiPath);
			return run(apiPath, body);
		}
		protected abstract Response run(String path, String body) throws FlexmlsApiClientException;
	}
	

}