package ru.intelinvest.career.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public record StockFilter(@JsonProperty("LISTLEVEL") Set<Integer> listLevels,
                          @JsonProperty("SECID") Set<String> secIds,
                          @JsonProperty("LOTSIZE") Integer lotSize) {
}
