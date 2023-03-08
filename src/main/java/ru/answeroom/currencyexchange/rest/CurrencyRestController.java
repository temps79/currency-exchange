package ru.answeroom.currencyexchange.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.answeroom.currencyexchange.api.CurrencyAPI;
import ru.answeroom.currencyexchange.model.ws.ValuteCursOnDate;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/currency")
public class CurrencyRestController {
    private final CurrencyAPI currencyApi;

    @GetMapping("/today")
    public ResponseEntity<List<ValuteCursOnDate>> getValuteCursOnToday() {
        return ResponseEntity.ok(currencyApi.getValuteCursOnToday());
    }
}
