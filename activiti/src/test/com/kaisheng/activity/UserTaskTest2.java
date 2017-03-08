package com.kaisheng.activity;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

//用户任务
public class UserTaskTest2 {

    private ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
    @Test
    public void deployment() {
        engine.getRepositoryService().createDeployment()
                .name("userTask2").addClasspathResource("diagrams/UserTask2.bpmn").deploy();
    }

    @Test
    public void startActivity() {

        engine.getRuntimeService().startProcessInstanceByKey("task");
    }

    @Test
    public void delActivity() {
        engine.getRepositoryService().deleteDeployment("182501",true);
    }
}
