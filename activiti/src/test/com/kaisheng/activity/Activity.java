package com.kaisheng.activity;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

public class Activity {

    /**
     * 初始化数据库 第一种方法
     */
    @Test
    public void initActivity() {
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        //数据库连接的配置
        configuration.setJdbcDriver("com.mysql.jdbc.Driver");
        configuration.setJdbcUrl("jdbc:mysql:///t_activiti");
        configuration.setJdbcUsername("root");
        configuration.setJdbcPassword("root");

        /**
         * public static final String DB_SCHEMA_UPDATE_FALSE = "false"; 不能自动创建表,需表存在
         public static final String DB_SCHEMA_UPDATE_CREATE_DROP = "create-drop"; 先删除表在创建
         public static final String DB_SCHEMA_UPDATE_TRUE = "true"; 若表不存在,则创建表
         */

        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        ProcessEngine processEngine = configuration.buildProcessEngine();

        System.out.println("processEngine: " + processEngine);
    }

    /**
     * 初始化数据库 第二种方法创建activiti.cfg.xml文件
     */
    @Test
    public void initXmlActivity() {
        //第二种 通过ProcessEngineConfiguration对象来调用
        //ProcessEngine engine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();

        //第三种 通过ProcessEngines对象的getDefaultProcessEngine方法来初始化数据库
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

        System.out.println("engine:" + engine);
    }

}
