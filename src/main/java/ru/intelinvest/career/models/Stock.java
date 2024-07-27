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

package ru.intelinvest.career.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Stock {

    @JsonProperty("SECID")
    private String secId;

    @JsonProperty("BOARDID")
    private String boardId;

    @JsonProperty("SHORTNAME")
    private String shortName;

    @JsonProperty("PREVPRICE")
    private BigDecimal prevPrice;

    @JsonProperty("LOTSIZE")
    private Integer lotSize;

    @JsonProperty("FACEVALUE")
    private BigDecimal faceValue;

    @JsonProperty("STATUS")
    private String status;

    @JsonProperty("BOARDNAME")
    private String boardName;

    @JsonProperty("DECIMALS")
    private Integer decimals;

    @JsonProperty("SECNAME")
    private String secName;

    @JsonProperty("REMARKS")
    private Object remarks;

    @JsonProperty("MARKETCODE")
    private String marketCode;

    @JsonProperty("INSTRID")
    private String instrId;

    @JsonProperty("SECTORID")
    private Object sectorId;

    @JsonProperty("MINSTEP")
    private BigDecimal minStep;

    @JsonProperty("PREVWAPRICE")
    private BigDecimal preWaPrice;

    @JsonProperty("FACEUNIT")
    private String faceUnit;

    @JsonProperty("PREVDATE")
    private LocalDate prevDate;

    @JsonProperty("ISSUESIZE")
    private Long issueSize;

    @JsonProperty("ISIN")
    private String isin;

    @JsonProperty("LATNAME")
    private String latName;

    @JsonProperty("REGNUMBER")
    private String regNumber;

    @JsonProperty("PREVLEGALCLOSEPRICE")
    private BigDecimal prevLegalClosePrice;

    @JsonProperty("CURRENCYID")
    private String currencyId;

    @JsonProperty("SECTYPE")
    private String secType;

    @JsonProperty("LISTLEVEL")
    private Integer listLevel;

    @JsonProperty("SETTLEDATE")
    private LocalDate settleDate;

}
