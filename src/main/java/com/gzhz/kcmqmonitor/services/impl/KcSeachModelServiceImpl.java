package com.gzhz.kcmqmonitor.services.impl;

import com.gzhz.kcmqmonitor.entity.KcSearchModel;
import com.gzhz.kcmqmonitor.mapper.KcSearchModelMapper;
import com.gzhz.kcmqmonitor.services.KcSeachModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("kcSeachModelService")
public class KcSeachModelServiceImpl implements KcSeachModelService {

    @Resource
    KcSearchModelMapper kcSearchModelMapper;

    @Override
    public int deleteByPrimaryKey(Integer id){
        return 0;
    }

    @Override
    public int insert(KcSearchModel record){
        int result = kcSearchModelMapper.insert(record);
        return result;
    }

    @Override
    public int insertSelective(KcSearchModel record){
        int result = kcSearchModelMapper.insertSelective(record);
        return result;
    }

    @Override
    public KcSearchModel selectByPrimaryKey(Integer id){
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(KcSearchModel record){
        return 0;
    }

    @Override
    public int updateByPrimaryKey(KcSearchModel record){
        return 0;
    }

}
