package com.ricbap.salvavidas.api.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("salvavidas")
public class SalvavidasApiProperty {
	
	private String originPermitida = "http://localhost:4200"; //"https://salvavidas.tk"; //"https://salvavidas-ui.herokuapp.com"; //"http://localhost:4200"; // "https://salvavidas-ui.herokuapp.com"; 
		
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
	
	
	
	private final Mail mail = new Mail();
	
	public Mail getMail() {
		return mail;
	}	
	
	public static class Mail {
		
		private String host;
		private Integer port;
		private String username;
		private String password;		
		
		public String getHost() {
			return host;
		}
		public void setHost(String host) {
			this.host = host;
		}
		public Integer getPort() {
			return port;
		}
		public void setPort(Integer port) {
			this.port = port;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}		
	}	
	
	
	
	private final S3 s3 = new S3();
	
	public S3 getS3() {
		return s3;
	}
	
	public static class S3 {
		private String accessKeyId;
		private String secretAccessKey;
		private String bucket = "sv-salvavidas-arquivos";
		
		public String getAccessKeyId() {
			return accessKeyId;
		}
		public void setAccessKeyId(String accessKeyId) {
			this.accessKeyId = accessKeyId;
		}
		public String getSecretAccessKey() {
			return secretAccessKey;
		}
		public void setSecretAccessKey(String secretAccessKey) {
			this.secretAccessKey = secretAccessKey;
		}
		public String getBucket() {
			return bucket;
		}
		public void setBucket(String bucket) {
			this.bucket = bucket;
		}
	}

	
}
