package com.dashidao.foundation.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dashidao.core.dao.IGenericDAO;
import com.dashidao.foundation.domain.AgentGoods;
import com.dashidao.foundation.domain.AgentStore;
import com.dashidao.foundation.service.IAgentStoreService;

@Service
@Transactional
public class AgentStoreServiceImpl
    implements IAgentStoreService {
    @Resource(name = "agentStoreDAO")
    private IGenericDAO<AgentStore> agentstoredao;

    public boolean save(AgentStore AgentStore){
        try {
            this.agentstoredao.save(AgentStore);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public List<AgentStore> query(String query, Map params, int begin, int max){
        return this.agentstoredao.query(query, params, begin, max);
    }
}




