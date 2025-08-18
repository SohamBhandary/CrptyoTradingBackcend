package com.soham.TradingPlatform.Service;

import com.soham.TradingPlatform.Entity.Assest;
import com.soham.TradingPlatform.Entity.Coin;
import com.soham.TradingPlatform.Entity.User;

import java.util.List;

public interface AssestService {

    Assest createAssest(User user, Coin coin, double quanity);
    Assest getAssestById(Long assestId) throws Exception;
    Assest getAssestByUserIdandId(Long userId,Long assetId);
    List<Assest> getUserAssets(Long userId);
    Assest updateAssest(Long assetid,double quanity) throws Exception;
    Assest findAssetByUserIdAndCoinid(Long userId,String coinId);

    void deleteAssest(Long assestId);

}
