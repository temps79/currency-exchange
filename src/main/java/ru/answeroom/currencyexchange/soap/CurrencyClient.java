package ru.answeroom.currencyexchange.soap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import ru.answeroom.currencyexchange.model.ws.GetCursOnDateXML;
import ru.answeroom.currencyexchange.model.ws.GetCursOnDateXMLResponse;
import ru.answeroom.currencyexchange.model.ws.ValuteCursOnDate;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class CurrencyClient extends WebServiceGatewaySupport {

    public List<ValuteCursOnDate> getValuteCursOnDate(GregorianCalendar cal) throws DatatypeConfigurationException {
        List<String> allowedCodeValute = List.of("RUB", "USD", "EUR");
        GetCursOnDateXMLResponse response = getCursOnDateXMLResponse(cal);
        final List<ValuteCursOnDate> courses = response.getGetCursOnDateXMLResult().getValuteData();
        return courses.stream()
                .filter(valuteCursOnDate -> allowedCodeValute.contains(valuteCursOnDate.getChCode()))
                .peek(course -> course.setName(course.getName().trim()))
                .collect(Collectors.toList());
    }

    public GetCursOnDateXMLResponse getCursOnDateXMLResponse(GregorianCalendar cal) throws DatatypeConfigurationException {
        GetCursOnDateXML request = new GetCursOnDateXML();
        XMLGregorianCalendar calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        request.setOnDate(calendar);

        final String soapAction = "http://www.cbr.ru/dailyinfowebserv/dailyinfo.asmx?wsdl";
        GetCursOnDateXMLResponse response = (GetCursOnDateXMLResponse) getResponse(soapAction, request);
        if (response == null) {
            throw new IllegalStateException("Не удалось получить данные от ЦБ РФ");
        }
        return response;
    }

    public Object getResponse(String soapAction, Object request) {
        return getWebServiceTemplate().marshalSendAndReceive(soapAction, request);
    }
}
