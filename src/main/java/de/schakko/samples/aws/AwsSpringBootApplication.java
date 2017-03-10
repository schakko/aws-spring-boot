package de.schakko.samples.aws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Configuration
@ComponentScan
public class AwsSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsSpringBootApplication.class, args);
	}

	@Configuration
	public static class MyConfiguration {
		/**
		 * Load Git properties. The information is also available through an
		 * Actuator endpoint.
		 * 
		 * @see <a href=
		 *      "http://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html">http://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html</a>
		 * @see <a href=
		 *      "http://www.baeldung.com/spring-git-information">http://www.baeldung.com/spring-git-information</a>
		 * @return
		 */
		@Bean
		public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
			PropertySourcesPlaceholderConfigurer propsConfig = new PropertySourcesPlaceholderConfigurer();
			propsConfig.setLocation(new ClassPathResource("git.properties"));
			propsConfig.setIgnoreResourceNotFound(true);
			propsConfig.setIgnoreUnresolvablePlaceholders(true);
			return propsConfig;
		}
	}

}
