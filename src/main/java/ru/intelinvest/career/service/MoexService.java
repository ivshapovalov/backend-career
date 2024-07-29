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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.intelinvest.career.exception.RemoteApiException;
import ru.intelinvest.career.models.Stock;

import java.util.List;
import java.util.Objects;
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
            List<MoexApiResponse> apiResponse = objectMapper.convertValue(response.getBody(), new TypeReference<>() {});
            return apiResponse.stream()
                    .filter(apiResponseElement -> Objects.nonNull(apiResponseElement.securities()))
                    .flatMap(apiResponseElement -> apiResponseElement.securities().stream())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RemoteApiException(e.getMessage());
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private record MoexApiResponse(
            @JsonProperty("charsetinfo") CharsetInfo charsetInfo,
            @JsonProperty("securities") List<Stock> securities) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private record CharsetInfo(String name) {

    }
}
