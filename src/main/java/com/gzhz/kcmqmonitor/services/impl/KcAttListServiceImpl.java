package com.gzhz.kcmqmonitor.services.impl;

import com.gzhz.kcmqmonitor.entity.KcAttList;
import com.gzhz.kcmqmonitor.mapper.KcAttListMapper;
import com.gzhz.kcmqmonitor.services.KcAttListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("kcAttListService")
public class KcAttListServiceImpl implements KcAttListService {

    @Resource
    private KcAttListMapper kcAttListMapper;


    /**
     * 通过sessionID 查询附件列表
     * @param seeionId
     * @return
     */
    @Override
    public List<KcAttList> setlectBysessionIdAndGuid(String seeionId ){
        List<KcAttList> kcAttLists = new ArrayList<>();
        kcAttLists = kcAttListMapper.setlectBysessionIdAndGuid(seeionId);

        return kcAttLists;
    }

    @Override
    public int deleteByPrimaryKey(Integer id){
        return 0;
    }

    @Override
    public int insert(KcAttList record){
        return 0;
    }

    @Override
    public int insertSelective(KcAttList record){
        return 0;
    }

    @Override
    public KcAttList selectByPrimaryKey(Integer id){
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(KcAttList record){
        return 0;
    }

    @Override
    public int updateByPrimaryKey(KcAttList record){
        return 0;
    }

}
