package com.kaisheng.activity;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

//用户组任务
public class CandidateUserTest {

    private ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
    @Test
    public void deployment() {
        engine.getRepositoryService().createDeployment()
                .name("userTask5").addClasspathResource("diagrams/candidateUser.bpmn").deploy();
    }

    @Test
    public void startActivity() {

        engine.getRuntimeService().startProcessInstanceByKey("candidate");
    }
    //领取任务
    @Test
    public void getTask() {
        engine.getTaskService().claim("190004","A");
    }
}
