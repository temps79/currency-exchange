package ru.answeroom.currencyexchange.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.answeroom.currencyexchange.model.ws.ValuteCursOnDate;
import ru.answeroom.currencyexchange.repository.CurrencyCache;
import ru.answeroom.currencyexchange.soap.CurrencyClient;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CurrencyAPI {
    private final CurrencyCache currencyCache;


    /**
     * Возвращает список валют на сегодня
     */
    public List<ValuteCursOnDate> getValuteCursOnToday()  {
        return currencyCache.getValuteCursOnToday();
    }
}
