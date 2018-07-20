package com.dashidao.foundation.service.impl;

import com.dashidao.core.dao.IGenericDAO;
import com.dashidao.core.query.GenericPageList;
import com.dashidao.core.query.PageObject;
import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.query.support.IQueryObject;
import com.dashidao.foundation.domain.AgentGoods;
import com.dashidao.foundation.domain.AgentGoods;
import com.dashidao.foundation.service.IAgentGoodsService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AgentGoodsServiceImpl
    implements IAgentGoodsService {
    @Resource(name = "agentGoodsDAO")
    private IGenericDAO<AgentGoods> agentgoodsdao;

    public boolean save(AgentGoods AgentGoods){
        try {
            this.agentgoodsdao.save(AgentGoods);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public AgentGoods getObjById(Long id){
        AgentGoods AgentGoods = (AgentGoods)this.agentgoodsdao.get(id);
        if (AgentGoods != null){
            return AgentGoods;
        }

        return null;
    }

    public boolean delete(Long id){
        try {
            this.agentgoodsdao.remove(id);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean batchDelete(List<Serializable> AgentGoodsIds){
        for (Serializable id : AgentGoodsIds){
            delete((Long)id);
        }

        return true;
    }

    public IPageList list(IQueryObject properties){
        if (properties == null){
            return null;
        }
        String query = properties.getQuery();
        Map params = properties.getParameters();
        GenericPageList pList = new GenericPageList(AgentGoods.class, query,
                params, this.agentgoodsdao);
        if (properties != null){
            PageObject pageObj = properties.getPageObj();
            if (pageObj != null)
                pList.doList(pageObj.getCurrentPage() == null ? 0 : pageObj
                             .getCurrentPage().intValue(), pageObj.getPageSize() == null ? 0 :
                             pageObj.getPageSize().intValue());
        }else{
            pList.doList(0, -1);
        }

        return pList;
    }

    public boolean update(AgentGoods AgentGoods){
        try {
            this.agentgoodsdao.update(AgentGoods);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public List<AgentGoods> query(String query, Map params, int begin, int max){
        return this.agentgoodsdao.query(query, params, begin, max);
    }
}




