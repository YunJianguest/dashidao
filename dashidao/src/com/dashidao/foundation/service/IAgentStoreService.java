package com.dashidao.foundation.service;

import java.util.List;
import java.util.Map;

import com.dashidao.foundation.domain.AgentStore;

public abstract interface IAgentStoreService {
    public abstract boolean save(AgentStore paramActivityStore);
    
    public List<AgentStore> query(String query, Map params, int begin, int max);
}




