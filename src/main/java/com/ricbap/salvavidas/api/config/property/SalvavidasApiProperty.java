package com.ricbap.salvavidas.api.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("salvavidas")
public class SalvavidasApiProperty {
	
	private String originPermitida = "https://salvavidas.tk"; //"https://salvavidas-ui.herokuapp.com"; //"http://localhost:4200"; // "https://salvavidas-ui.herokuapp.com"; 
	
	public String getOriginPermitida() {
		return originPermitida;
	}
	public void setOriginPermitida(String originPermitida) {
		this.originPermitida = originPermitida;
	}
	
	private final Seguranca seguranca = new Seguranca();	
	public Seguranca getSeguranca() {
		return seguranca;
	}	

	public static class Seguranca {
		private boolean enableHttps;

		public boolean isEnableHttps() {
			return enableHttps;
		}
		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}		
	}

}
