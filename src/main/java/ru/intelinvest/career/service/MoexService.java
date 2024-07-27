/*
 * STRICTLY CONFIDENTIAL
 * TRADE SECRET
 * PROPRIETARY:
 *       "Intelinvest" Ltd, TIN 1655386205
 *       420107, REPUBLIC OF TATARSTAN, KAZAN CITY, SPARTAKOVSKAYA STREET, HOUSE 2, ROOM 119
 * (c) "Intelinvest" Ltd, 2019
 *
 * СТРОГО КОНФИДЕНЦИАЛЬНО
 * КОММЕРЧЕСКАЯ ТАЙНА
 * СОБСТВЕННИК:
 *       ООО "Интеллектуальные инвестиции", ИНН 1655386205
 *       420107, РЕСПУБЛИКА ТАТАРСТАН, ГОРОД КАЗАНЬ, УЛИЦА СПАРТАКОВСКАЯ, ДОМ 2, ПОМЕЩЕНИЕ 119
 * (c) ООО "Интеллектуальные инвестиции", 2019
 */

package ru.intelinvest.career.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.intelinvest.career.exception.RemoteApiException;
import ru.intelinvest.career.models.Stock;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MoexService {

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    @Value("${app.moex.endpoint}")
    private String moexUrl;

    public List<Stock> getStocks() {

        try {
            ResponseEntity<Object> response = restTemplate.getForEntity(moexUrl, Object.class);
            return extractStocksFromResponse(response);
        } catch (Exception e) {
            throw new RemoteApiException(e.getMessage());
        }
    }

    private List<Stock> extractStocksFromResponse(ResponseEntity<Object> response) {
        Object body = response.getBody();
        List<LinkedHashMap> stocksProperties = (List) ((LinkedHashMap) ((ArrayList<?>) body).get(1)).get("securities");
        return stocksProperties.stream()
                .map(stockProperties
                        -> objectMapper.convertValue(stockProperties, Stock.class))
                .collect(Collectors.toList());
    }
}
