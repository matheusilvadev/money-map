package tech.astrocode.web_backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tech.astrocode.web_backend.configuration.WebServerConfiguration;

@SpringBootApplication
public class WebBackendApplication {
	private static final Logger LOG = LoggerFactory.getLogger(WebBackendApplication.class);
	public static void main(String[] args) {
		LOG.info("Starting Money Map...");
		SpringApplication.run(WebServerConfiguration.class, args);
		LOG.info("Ending of starting Money Map...");
	}

}
