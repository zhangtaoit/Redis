package com.kaisheng.activity;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

//用户任务
public class UserTaskTest {

    private ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
    @Test
    public void deployment() {
        engine.getRepositoryService().createDeployment()
                .name("userTask").addClasspathResource("diagrams/UserTask.bpmn").deploy();
    }

    @Test
    public void startActivity() {
        Map<String,Object> maps = new HashMap<>();
        maps.put("userId","jack");
        engine.getRuntimeService().startProcessInstanceByKey("userTask1",maps);
    }
}
