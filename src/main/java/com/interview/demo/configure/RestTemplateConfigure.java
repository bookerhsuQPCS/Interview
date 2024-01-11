package com.interview.demo.configure;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class RestTemplateConfigure {
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		try {
			// 信任所有憑證的 Trust Manager
			TrustManager[] trustAllCerts = new TrustManager[] {

					new X509TrustManager() {
						public X509Certificate[] getAcceptedIssuers() {
							return null;
						}

						public void checkClientTrusted(X509Certificate[] certs, String authType) {
						}

						public void checkServerTrusted(X509Certificate[] certs, String authType) {
						}
					} };

			// 忽略憑證的 Hostname Verifier
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};

			// 初始化 SSL Context
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, trustAllCerts, new SecureRandom());

			// 創建 RestTemplate
			SimpleClientHttpRequestFactory requestFactory = (SimpleClientHttpRequestFactory) restTemplate
					.getRequestFactory();
			requestFactory.setBufferRequestBody(false);
			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

		} catch (Exception e) {
			log.error(null, e);
		}

		return restTemplate;
	}
}
