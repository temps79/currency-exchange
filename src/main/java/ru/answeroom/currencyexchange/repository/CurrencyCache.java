package ru.answeroom.currencyexchange.repository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import ru.answeroom.currencyexchange.model.ws.ValuteCursOnDate;
import ru.answeroom.currencyexchange.soap.CurrencyClient;

import javax.xml.datatype.DatatypeConfigurationException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class CurrencyCache {
    private final CurrencyClient currencyClient;
    private Map<String, ValuteCursOnDate> valuteCursOnDateMap;
    private Instant lastUpdate;


    @PostConstruct
    private void init() throws DatatypeConfigurationException {
        this.getValuteCursOnTodayByRequest();
    }

    /**
     * Возвращает список валют на сегодня
     */
    public List<ValuteCursOnDate> getValuteCursOnToday() {
        long mills = Instant.now().truncatedTo(ChronoUnit.DAYS).toEpochMilli() - lastUpdate.toEpochMilli();
        long days = TimeUnit.MICROSECONDS.convert(mills, TimeUnit.DAYS);
        if (days >= 1) {
            try {
                return getValuteCursOnTodayByRequest();
            } catch (DatatypeConfigurationException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
        }
        return new ArrayList<>(valuteCursOnDateMap.values());
    }

    /**
     * Запрос актуального курса
     */
    private List<ValuteCursOnDate> getValuteCursOnTodayByRequest() throws DatatypeConfigurationException {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        lastUpdate = Instant.now().truncatedTo(ChronoUnit.DAYS);
        List<ValuteCursOnDate> valuteCursOnDates = currencyClient.getValuteCursOnDate(cal);
        valuteCursOnDateMap = valuteCursOnDates.stream().collect(Collectors.toMap(ValuteCursOnDate::getCode, valuteCursOnDate -> valuteCursOnDate));
        return valuteCursOnDates;
    }
}
