package ru.answeroom.currencyexchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.answeroom.currencyexchange.soap.CurrencyClient;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.Date;
import java.util.GregorianCalendar;

@SpringBootApplication
public class CurrencyExchangeApplication {



	public static void main(String[] args) throws DatatypeConfigurationException {
		SpringApplication.run(CurrencyExchangeApplication.class, args);
	}


}
