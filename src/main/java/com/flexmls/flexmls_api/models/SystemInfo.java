package com.flexmls.flexmls_api.models;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;


public class SystemInfo extends Base {
	
	@JsonProperty("Id")
	private String id;
	@JsonProperty("OfficeId")
	private String officeId;
	@JsonProperty("MlsId")
	private String mlsId;
	
	@JsonProperty("Name")
	private String name;
	@JsonProperty("Office")
	private String office;
	@JsonProperty("Mls")
	private String mls;

	@JsonProperty("Configuration")
	private List<SystemInfo.Configuration> configurationList = new ArrayList<SystemInfo.Configuration>();

	public static class Configuration extends Base {
		
		@JsonProperty("MlsLogos")
		private List<String> mlsLogos;
		
		@JsonProperty("IdxDisclaimer")
		private String idxDisclaimer;
		@JsonProperty("IdxLogoSmall")
		private String idxLogoSmall;
		@JsonProperty("IdxLogo")
		private String idxLogo;
		
		@JsonProperty("OAuth2ServiceEndpointPrivate")
		private String oauth2ServiceEndpointPrivate;
		
		public List<String> getMlsLogos() {
			return mlsLogos;
		}

		public void setMlsLogos(List<String> mlsLogos) {
			this.mlsLogos = mlsLogos;
		}

		public String getIdxDisclaimer() {
			return idxDisclaimer;
		}
		public void setIdxDisclaimer(String idxDisclaimer) {
			this.idxDisclaimer = idxDisclaimer;
		}

		public String getIdxLogoSmall() {
			return idxLogoSmall;
		}

		public void setIdxLogoSmall(String idxLogoSmall) {
			this.idxLogoSmall = idxLogoSmall;
		}

		public String getIdxLogo() {
			return idxLogo;
		}

		public void setIdxLogo(String idxLogo) {
			this.idxLogo = idxLogo;
		}

		public String getOauth2ServiceEndpointPrivate() {
			return oauth2ServiceEndpointPrivate;
		}

		public void setOauth2ServiceEndpointPrivate(String oauth2ServiceEndpointPrivate) {
			this.oauth2ServiceEndpointPrivate = oauth2ServiceEndpointPrivate;
		}
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getMlsId() {
		return mlsId;
	}

	public void setMlsId(String mlsId) {
		this.mlsId = mlsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getMls() {
		return mls;
	}

	public void setMls(String mls) {
		this.mls = mls;
	}

	public Configuration getConfiguration() {
		return configurationList.get(0);
	}

	public void setConfiguration(Configuration configuration) {
		this.configurationList.clear();
		this.configurationList.add(configuration);
	}
	
}
