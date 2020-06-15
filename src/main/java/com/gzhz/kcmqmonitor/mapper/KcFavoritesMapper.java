package com.gzhz.kcmqmonitor.mapper;

import com.gzhz.kcmqmonitor.entity.KcAttList;
import com.gzhz.kcmqmonitor.entity.KcFavorites;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface KcFavoritesMapper {

    int deleteByPrimaryKey(Integer id);


    int insert(KcFavorites record);


    int insertSelective(KcFavorites record);


    KcFavorites selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(KcFavorites record);


    int updateByPrimaryKey(KcFavorites record);

    /**
     * 通过 userId  查询用户关注的快传列表
     */
    List<KcFavorites> selectByUserId(Long userId);


    /**
     * 通过 kid 查询关注该条快传的用户列表
     */
    List<KcFavorites> selectByKid(Integer kid);

}