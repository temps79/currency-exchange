package ru.answeroom.currencyexchange.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.answeroom.currencyexchange.model.ws.ValuteCursOnDate;
import ru.answeroom.currencyexchange.repository.CurrencyCache;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CurrencyAPI {
    private final CurrencyCache currencyCache;


    /**
     * Возвращает список валют на сегодня
     */
    public List<ValuteCursOnDate> getValuteCursOnToday() {
        return currencyCache.getValuteCursOnToday();
    }
}
