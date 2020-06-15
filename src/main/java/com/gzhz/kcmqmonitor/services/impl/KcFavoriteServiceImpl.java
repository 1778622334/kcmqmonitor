package com.gzhz.kcmqmonitor.services.impl;

import com.gzhz.kcmqmonitor.entity.KcFavorites;
import com.gzhz.kcmqmonitor.mapper.KcFavoritesMapper;
import com.gzhz.kcmqmonitor.services.KcFavoriteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("kcFavoriteService")
public class KcFavoriteServiceImpl implements KcFavoriteService {

    @Resource
    KcFavoritesMapper kcFavoritesMapper ;

    /**
     * 通过 userId  查询关注列表
     */
    @Override
    public List<KcFavorites> selectByUserId(Long userId){

        List<KcFavorites> kcFavorites = new ArrayList<>();
        kcFavorites = kcFavoritesMapper.selectByUserId(userId);
        return kcFavorites;

    }
    @Override
    public int deleteByPrimaryKey(Integer id){return 0;}

    @Override
    public int insert(KcFavorites record){
        return 0;
    }

    @Override
    public int insertSelective(KcFavorites record){
        return 0;
    }

    @Override
    public KcFavorites selectByPrimaryKey(Integer id){
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(KcFavorites record){return 0;}

    @Override
    public int updateByPrimaryKey(KcFavorites record){return 0;}
}
