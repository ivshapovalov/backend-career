package ru.intelinvest.career.models;

import java.util.List;

public record StocksPageable(int stocksTotal, int pageSize, int pagesTotal, int pageCurrent, List<Stock> stocks) {
}
