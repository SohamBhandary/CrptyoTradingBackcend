package com.soham.TradingPlatform.Service;

import com.soham.TradingPlatform.Entity.Assest;
import com.soham.TradingPlatform.Entity.Coin;
import com.soham.TradingPlatform.Entity.User;
import com.soham.TradingPlatform.Repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AssestServiceImple implements  AssestService{
    @Autowired
    private AssetRepository assetRepository;

    @Override
    public Assest createAssest(User user, Coin coin, double quanity) {
        Assest assest= new Assest();
        assest.setUser(user);
        assest.setCoin(coin);
        assest.setQuantity(quanity);
        assest.setBuyprice(coin.getCurrentPrice());
        return assetRepository.save(assest);



    }

    @Override
    public Assest getAssestById(Long assestId) throws Exception {
        return assetRepository.findById(assestId).orElseThrow(()-> new Exception("Assest not found") );
    }

    @Override
    public Assest getAssestByUserIdandId(Long userId, Long assetId) {
        return null;
    }

    @Override
    public List<Assest> getUserAssets(Long userId) {
        return assetRepository.findByUserId(userId);
    }

    @Override
    public Assest updateAssest(Long assetid, double quanity) throws Exception {
        Assest oldassest=getAssestById(assetid);
        oldassest.setQuantity(quanity +oldassest.getQuantity());
      return   assetRepository.save(oldassest);


    }

    @Override
    public Assest findAssetByUserIdAndCoinid(Long userId, String coinId) {
        return assetRepository.findByUserIdandCoinId(userId,coinId);
    }

    @Override
    public void deleteAssest(Long assestId) {
        assetRepository.deleteById(assestId);

    }
}
