package hello;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import countries.core.common.Country;
import countries.core.common.Currency;

@Component
public class CountryRepository {
	private static Map<String, Country> countries = new HashMap<>();

	@PostConstruct
	public void initData() {
		Country spain = new Country();
		spain.setName("Spain");
		spain.setCapital("Madrid");
		spain.setCurrency(Currency.EUR);
		spain.setPopulation(46704314);

		countries.put(spain.getName(), spain);

		Country poland = new Country();
		poland.setName("Poland");
		poland.setCapital("Warsaw");
		poland.setCurrency(Currency.PLN);
		poland.setPopulation(38186860);

		countries.put(poland.getName(), poland);

		Country uk = new Country();
		uk.setName("United Kingdom");
		uk.setCapital("London");
		uk.setCurrency(Currency.GBP);
		uk.setPopulation(63705000);

		countries.put(uk.getName(), uk);
	}

	public Country findCountry(String name) {
		Assert.notNull(name, "The uuid must not be null");
		Assert.notNull(name, "The country's name must not be null");
		return countries.get(name);
	}

	public Country addCountry(String uuid, String name, String capital, Currency currency, int population) {
		if (!verifyUuid(uuid)) {
			return null;
		}
		Country newCountry = new Country();
		newCountry.setName(name);
		newCountry.setCapital(capital);
		newCountry.setCurrency(currency);
		newCountry.setPopulation(population);
		countries.put(name, newCountry);
		return newCountry;
	}

	public String deleteCountry(String uuid, String name) {
		if (!verifyUuid(uuid)) {
			return null;
		}
		countries.remove(name);
		return name;
	}

	private boolean verifyUuid(String uuid) {
		Collection<String> uuids = ActiveUsersRepository.getLoginUuid().values();
		if (uuids.contains(uuid)) {
			return true;
		}
		;
		return false;
	}
}
