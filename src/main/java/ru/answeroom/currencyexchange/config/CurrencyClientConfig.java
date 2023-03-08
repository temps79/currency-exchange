package ru.answeroom.currencyexchange.config;

import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.SOAPConstants;
import jakarta.xml.soap.SOAPException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import ru.answeroom.currencyexchange.model.ws.GetCursOnDateXML;
import ru.answeroom.currencyexchange.model.ws.GetCursOnDateXMLResponse;
import ru.answeroom.currencyexchange.model.ws.GetCursOnDateXMLResult;
import ru.answeroom.currencyexchange.model.ws.ValuteCursOnDate;
import ru.answeroom.currencyexchange.soap.CurrencyClient;

@Configuration
public class CurrencyClientConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(GetCursOnDateXML.class,
                GetCursOnDateXMLResponse.class,
                GetCursOnDateXMLResult.class,
                ValuteCursOnDate.class);
        return marshaller;
    }

    @Bean
    public CurrencyClient countryClient(Jaxb2Marshaller marshaller) throws SOAPException {
        MessageFactory msgFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
        SaajSoapMessageFactory newSoapMessageFactory = new SaajSoapMessageFactory(msgFactory);
        CurrencyClient client = new CurrencyClient();
        client.setMessageFactory(newSoapMessageFactory);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);

        return client;
    }
}
