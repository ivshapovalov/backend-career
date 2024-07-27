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

package ru.intelinvest.career.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.intelinvest.career.models.StockFilter;
import ru.intelinvest.career.models.StocksPageable;
import ru.intelinvest.career.service.MoexService;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/moex")
public class MoexEndpoint {

    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int DEFAULT_PAGE_SIZE = 100;

    private final MoexService moexService;

    @PostMapping("stocks")
    public ResponseEntity<StocksPageable> processIntegration(@RequestBody StockFilter stockFilter,
                                                             @RequestParam(value = "page", required = false) Integer page,
                                                             @RequestParam(value = "size", required = false) Integer size
    ) {
        var listLevelsFilter = Optional.ofNullable(stockFilter.listLevels()).orElse(new HashSet<>());
        var secIdsFilter = Optional.ofNullable(stockFilter.secIds()).orElse(new HashSet<>());
        var pageCurrent = Objects.isNull(page) ? DEFAULT_PAGE_NUMBER : page;
        var pageSize = Objects.isNull(size) ? DEFAULT_PAGE_SIZE : size;

        var stocks = moexService.getStocks();

        var stocksFiltered = stocks.stream()
                .filter(stock -> listLevelsFilter.isEmpty() || listLevelsFilter.contains(stock.getListLevel()))
                .filter(stock -> secIdsFilter.isEmpty() || secIdsFilter.contains(stock.getSecId()))
                .filter(stock -> Objects.isNull(stockFilter.lotSize()) || stock.getLotSize().equals(stockFilter.lotSize()))
                .toList();

        var pagesTotal = (int) Math.ceil(1.0 * stocksFiltered.size() / pageSize);

        pageCurrent = pagesTotal < pageCurrent ? pagesTotal : pageCurrent;

        return ResponseEntity.ok(new StocksPageable(stocksFiltered.size(), pageSize, pagesTotal, pageCurrent,
                stocksFiltered.stream().skip((long) (pageCurrent - 1) * pageSize).limit(pageSize).toList()));
    }
}
