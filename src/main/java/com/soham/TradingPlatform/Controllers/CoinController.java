package com.soham.TradingPlatform.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soham.TradingPlatform.Entity.Coin;
import com.soham.TradingPlatform.Service.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coins")
public class CoinController {
    @Autowired
    private CoinService coinService;
    @Autowired
    private ObjectMapper objectMapper;
    @GetMapping
    ResponseEntity<List<Coin>>getCoinList(@RequestParam("page") int page)
    {
        List<Coin> coinList=coinService.getCoinList(page);


        return new ResponseEntity<>(coinList, HttpStatus.ACCEPTED);
    }
    @GetMapping("/{coinId}/chart")
    ResponseEntity<JsonNode> getMarketChart(@PathVariable String coinId,
                                            @RequestParam("days")int days) throws Exception {
        String coins=coinService.getMarketChart(coinId,days);
        JsonNode jsonNode = objectMapper.readTree(coins);


        return ResponseEntity.ok(jsonNode);

    }

    @GetMapping("/search")
    ResponseEntity<JsonNode> searchCoin(@RequestParam("q") String keyword) throws JsonProcessingException {
        String coin=coinService.searchCoin(keyword);
        JsonNode jsonNode = objectMapper.readTree(coin);

        return ResponseEntity.ok(jsonNode);

    }
    @GetMapping("/top50")
    ResponseEntity<JsonNode> getTop50CoinByMarketCapRank() throws JsonProcessingException {
        String coin=coinService.getTop50CoinByMarketCapRank();
        JsonNode jsonNode = objectMapper.readTree(coin);

        return ResponseEntity.ok(jsonNode);

    }
    @GetMapping("/trading")
    ResponseEntity<JsonNode> getTreadingCoin() throws JsonProcessingException {
        String coin=coinService.getTrendingCoins();
        JsonNode jsonNode = objectMapper.readTree(coin);
        return ResponseEntity.ok(jsonNode);

    }

    @GetMapping("/details/{coinId}")
    ResponseEntity<JsonNode> getCoinDetails(@PathVariable String coinId) throws JsonProcessingException {
        String coin=coinService.getCoinSetails(coinId);
        JsonNode jsonNode = objectMapper.readTree(coin);

        return ResponseEntity.ok(jsonNode);

    }


}
