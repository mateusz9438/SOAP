
package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import countries.core.GetCountryResponse;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(QuoteClient.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class);
		
	}

	@Bean
	CommandLineRunner lookup(CountriesClient countriesClient) {
		return args -> {
			String name = "Polan";

			if (args.length > 0) {
				name = args[0];
			}
			GetCountryResponse response = countriesClient.getCountry(name);
			log.info(response.getCountry().getName());
			log.info(response.getCountry().getCapital());
			log.info(response.getCountry().getCurrency().toString());
			log.info(response.getCountry().getPopulation()+"");
			
		};
	}

}
