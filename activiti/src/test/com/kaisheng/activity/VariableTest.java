package com.kaisheng.activity;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.ibatis.annotations.One;
import org.junit.Test;

import java.util.*;
//设置流程变量
public class VariableTest {

    ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

    /**
     * 设置流程变量
     */
    @Test
    public void variableStart() {
        Map<String,Object> variables = new HashMap<>();
        variables.put("day",5);
        variables.put("startTime",new Date());
        //启动流程
        ProcessInstance processInstance = engine.getRuntimeService().startProcessInstanceByKey("myProcess_1",variables);
        System.out.println(processInstance);
    }

    /**
     * 获取设置的流程变量
     */
    @Test
    public void getVariable() {
        Integer day = (Integer) engine.getRuntimeService().getVariable("62501","day");
        System.out.println("day: " + day);

        Map<String,Object> maps = engine.getRuntimeService().getVariables("62501");

        List<String> keyList = new ArrayList<>();
        keyList.add("day");
        Map<String,Object> variables = engine.getRuntimeService().getVariables("62501",keyList);

        System.out.println("maps: " + maps.size());
        System.out.println("variable: " + variables.size());

    }

    /**
     *设置act_ru_variable表中变量
     */
    @Test
    public void setRuntimeVariable() {
        Map<String,Object> maps = new HashMap<>();
        maps.put("day1",7);
        maps.put("createTime",new Date());
        engine.getRuntimeService().setVariables("62501",maps);
    }

    /**
     *设置act_ru_task表中变量
     */
    @Test
    public void setTaskVariable() {
        Map<String,Object> maps = new HashMap<>();
        maps.put("day2",6);
        maps.put("startTime",new Date());
        engine.getTaskService().setVariables("62506",maps);
    }

    /**
     *获取atc_ru_task表中的值
     */
    @Test
    public void getTaskVariable() {
        Integer days = (Integer) engine.getTaskService().getVariable("62506","day2");
        System.out.println("days:" + days);
    }
}
