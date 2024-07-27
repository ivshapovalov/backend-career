package ru.intelinvest.career.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.intelinvest.career.exception.RemoteApiException;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class EthereumService {

    private final RestTemplate restTemplate;
    @Value("${app.ethereum.api.key.token}")
    private String etherScanApiToken;
    @Value("${app.ethereum.usdt.contract.address}")
    private String usdtContractAddress;

    public BigDecimal getBalance(String address) {
        String etherScanUrl =
                "https://api.etherscan.io/api?module=account&action=tokenbalance&tag=latest&contractaddress=%s&address=%s&apikey=%s"
                        .formatted(usdtContractAddress, address, etherScanApiToken);

        try {
            ResponseEntity<EtherScanApiResponse> response = restTemplate.getForEntity(etherScanUrl, EtherScanApiResponse.class);
            BigDecimal normalizedResult = convertWeiToBigDecimal(response.getBody().result());
            return normalizedResult;
        } catch (Exception e) {
            throw new RemoteApiException(e.getMessage());
        }
    }

    private BigDecimal convertWeiToBigDecimal(BigDecimal origin) {
        return origin.divide(BigDecimal.valueOf(10e18));
    }

    private record EtherScanApiResponse(String status, String message, BigDecimal result) {
    }
}
