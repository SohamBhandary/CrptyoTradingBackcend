package com.soham.TradingPlatform.Controllers;

import com.soham.TradingPlatform.Entity.Assest;
import com.soham.TradingPlatform.Entity.User;
import com.soham.TradingPlatform.Service.AssestService;
import com.soham.TradingPlatform.Service.UserService;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assest")
public class AssestController {
    private  AssestService assetService;
    @Autowired
    private UserService userService;

    @GetMapping("/{assetId}")
    public ResponseEntity<Assest> getAssetById(@PathVariable Long assetId) throws Exception {
        Assest asset = assetService.getAssestById(assetId);
        return ResponseEntity.ok().body(asset);
    }

    @GetMapping("/coin/{coinId}/user")
    public ResponseEntity<Assest> getAssetByUserIdAndCoinId(
            @PathVariable String coinId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        User user=userService.finduserProfileByJwt(jwt);
        Assest asset = assetService.findAssetByUserIdAndCoinid(user.getId(), coinId);
        return ResponseEntity.ok().body(asset);
    }

    @GetMapping()
    public ResponseEntity<List<Assest>> getAssetsForUser(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user=userService.finduserProfileByJwt(jwt);
        List<Assest> assets = assetService.getUserAssets(user.getId());
        return ResponseEntity.ok().body(assets);
    }
}
