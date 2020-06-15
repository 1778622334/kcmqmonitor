package com.gzhz.kcmqmonitor.services;

import com.gzhz.kcmqmonitor.entity.KcFavorites;
import org.springframework.stereotype.Service;

import java.util.List;


public interface KcFavoriteService {
    int deleteByPrimaryKey(Integer id);


    int insert(KcFavorites record);


    int insertSelective(KcFavorites record);


    KcFavorites selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(KcFavorites record);


    int updateByPrimaryKey(KcFavorites record);

    /**
     * 通过 userId  查询关注列表
     */
    List<KcFavorites> selectByUserId(Long userId);
}
