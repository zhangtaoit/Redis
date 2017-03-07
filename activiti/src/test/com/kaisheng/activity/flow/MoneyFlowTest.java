package com.kaisheng.activity.flow;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MoneyFlowTest {

    private ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

    /**
     * 部署流程
     */
    @Test
    public void deployMoneyFlow() {
        Deployment deployment = engine.getRepositoryService()
                .createDeployment()
                .name("moneyFlow")
                .addClasspathResource("diagrams/MoneyFlow.bpmn")
                .deploy();

        System.out.println("name: " + deployment.getName() + "/id: " + deployment.getId());

    }
    /**
     * 排他网关 启动流程
     */
    @Test
    public void startMoneyFlow() {
        Map<String,Object> maps = new HashMap<>();
        maps.put("money",700);
        ProcessInstance processInstance = engine.getRuntimeService().startProcessInstanceByKey("money",maps);

        System.out.println("name: " + processInstance.getName());
        System.out.println("key:" + processInstance.getProcessDefinitionKey());

    }

}
