package ru.answeroom.currencyexchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.datatype.DatatypeConfigurationException;

@SpringBootApplication
public class CurrencyExchangeApplication {
    public static void main(String[] args) throws DatatypeConfigurationException {
        SpringApplication.run(CurrencyExchangeApplication.class, args);
    }

}
