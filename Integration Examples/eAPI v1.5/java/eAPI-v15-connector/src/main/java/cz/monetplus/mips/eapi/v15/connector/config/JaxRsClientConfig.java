package cz.monetplus.mips.eapi.v15.connector.config;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cz.monetplus.mips.eapi.v15.connector.INativeAPIv15Resource;

/**
 * JAX RS client configuration
 *
 */
@Configuration
public class JaxRsClientConfig {
	
	@Value("${native.api.url}")
	private String url;
	
	@Value("${native.api.trust.all.certs:true}")
	private boolean trustAllCerts;
	
	@Value("${native.api.truststore:}")
	private String trustStore;
	
	@Value("${native.api.truststore.password:}")
	private String trustStorePassword;
	
	@Value("${native.api.connect.timeout.ms:10000}")
	private int connectTimeout;

	@Value("${native.api.receive.timeout.ms:10000}")
	private int receiveTimeout;

	@Bean
	public JacksonJaxbJsonProvider jacksonJaxbJsonProvider() {
		return new JacksonJaxbJsonProvider();
	}

	@Bean
	public INativeAPIv15Resource getNativeAPIv1ResourceRestClient() {
		return new JaxRsClientStarter<INativeAPIv15Resource>().start(INativeAPIv15Resource.class, url, 
				trustAllCerts, trustStore, trustStorePassword, 
				getProviders(), connectTimeout, receiveTimeout);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List<?> getProviders() {
		List providers = new ArrayList();
		providers.add(jacksonJaxbJsonProvider());
		return providers;
	}

}