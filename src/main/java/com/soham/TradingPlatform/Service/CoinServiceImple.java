package com.soham.TradingPlatform.Service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soham.TradingPlatform.Entity.Coin;
import com.soham.TradingPlatform.Repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CoinServiceImple implements  CoinService{

    @Autowired
    private CoinRepository coinRepository;

    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public List<Coin> getCoinList(int page) {
        String url="https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&per_page=10&page="+page;
        RestTemplate restTemplate= new RestTemplate();
        try{
            HttpHeaders httpHeaders= new HttpHeaders();
            HttpEntity<String> entity= new HttpEntity<>("parameters",httpHeaders);
            ResponseEntity<String>response=restTemplate.exchange(url, HttpMethod.GET,entity,String.class);
            List<Coin> coinList=objectMapper.readValue(response.getBody(), new TypeReference<List<Coin>>() {
            });
            return coinList;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }


    }

    @Override
    public String getMarketChart(String coinId, int days) {
        String url = "https://api.coingecko.com/api/v3/coins/"+coinId+"/market_chart?vs_currency=usd&days="+days;
        RestTemplate restTemplate= new RestTemplate();
        try{
            HttpHeaders httpHeaders= new HttpHeaders();
            HttpEntity<String> entity= new HttpEntity<>("parameters",httpHeaders);
            ResponseEntity<String>response=restTemplate.exchange(url, HttpMethod.GET,entity,String.class);
            return response.getBody();


        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String getCoinSetails(String coinId)  {
        String url = "https://api.coingecko.com/api/v3/coins/"+coinId;
        RestTemplate restTemplate= new RestTemplate();
        try{
            HttpHeaders httpHeaders= new HttpHeaders();
            HttpEntity<String> entity= new HttpEntity<>("parameters",httpHeaders);
            ResponseEntity<String>response=restTemplate.exchange(url, HttpMethod.GET,entity,String.class);
            JsonNode jsonNode=objectMapper.readTree(response.getBody());
            Coin coin= new Coin();

            coin.setId(jsonNode.get("id").asText());
            coin.setSymbol(jsonNode.get("symbol").asText());
            coin.setName(jsonNode.get("name").asText());
            coin.setImage(jsonNode.get("image").get("large").asText());
            return response.getBody();


        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Coin findById(String coinId) {
        return null;
    }

    @Override
    public String searchCoin(String keyword) {
        return "";
    }

    @Override
    public String getTop50CoinByMarketCapRank() {
        return "";
    }

    @Override
    public String getTradingCoins() {
        return "";
    }
}
