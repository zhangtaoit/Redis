package com.kaisheng.activity;


import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

public class HelloTest {
    ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

    /**
     * 第一步部署流程  在act_re_deployment、act_re_procdef、act_ge_bytearray表中插入数据
     */
    @Test
    public void deployment() {
        Deployment deployment = engine.getRepositoryService()//获取RepositoryService
                .createDeployment()//创建一个部署对象
                .name("普通部署")
                .addClasspathResource("diagrams/Hello.bpmn") //从classpath中获取bpmn资源
                .deploy();
        System.out.println("deployment: " + deployment);
    }

    /**
     * 第二步 启动流程表 在act_ru_task表中插入数据
     */
    @Test
    public void startActivity() {
        ProcessInstance processInstance = engine.getRuntimeService().startProcessInstanceByKey("myProcess_1");
        System.out.println("processInstance: " + processInstance);
    }

    /**
     * 第三步 查看任务列表 查看act_ru_task表
     */
    @Test
    public void getTaskList() {
        List<Task> taskList = engine.getTaskService().createTaskQuery()//创建查询对象
         //where条件查询
        .taskAssignee("zhangsan")//根据办理人查询
        //.taskCandidateUser("")//根据组任务办理人查询
        //.processDefinitionId("myProcess_1:1:15004")//根据流程定义的Id来查询
        //.processInstanceId("")//根据流程实例Id来查询
        //.executionId("");//根据执行对象的ID来查询

        //排序
        //.orderByTaskCreateTime().desc()//根据创建时间升序排序

        //返回结果集
        //.singleResult()//返回唯一的结果集
        //.count()//返回结果集的数量
        //.listPage(0,5)//分页查询
        .list(); //返回list列表

        for(Task task : taskList) {
            System.out.println("task: " + task);
        }
    }

    /**
     * 第四步 完成任务 删除act_ru_task表的数据,在act_hi_actinst表中插入数据
     */
    @Test
    public void endActivity() {
        engine.getTaskService().complete("42502");
        System.out.println("任务完成...");
    }

    /**
     * 以zip格式部署流程 避免中文乱码
     */
    @Test
    public void zipStartActivity() {
        //获取zip文件的输入流
       /* InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("diagrams/ZipHello.zip");
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);

        Deployment deployment = engine.getRepositoryService()//获得RepositoryService
                .createDeployment() //创建一个部署对象
                .name("以zip格式部署")
                .addZipInputStream(zipInputStream) //指定zip文件完成部署
                .deploy();

        System.out.println("name: " + deployment.getName() + "/Id: " + deployment.getId());*/

        //获取zip文件的输入流
        InputStream input = this.getClass().getClassLoader()
                .getResourceAsStream("diagrams/ZipHello.zip");
        ZipInputStream zipInput = new ZipInputStream(input);

        Deployment deploy = engine.getRepositoryService().createDeployment().name("zipDeploy")
                .addZipInputStream(zipInput).deploy();

        System.out.println("流程id：" + deploy.getId());
        System.out.println("流程name" + deploy.getName());
    }

    /**
     * 获取流程定义
     */
    @Test
    public void getProcessDefinition() {
        List<ProcessDefinition> processDefinitionList =  engine.getRepositoryService().createProcessDefinitionQuery()//创建一个流程查询对象
                .processDefinitionKey("myProcess_1")
                .list();

        System.out.println("processDefinitionList: " + processDefinitionList);

    }

    /**
     * 删除流程
     * 1.不带级联删除(只删除没有启动的流程,否则会报错)
     * 2.级联删除(不管流程是否启动,都会被删除)
     */
    @Test
    public void DelProcessDefinition() {
        //不带级联删除
        //engine.getRepositoryService().deleteDeployment("15001");

        //级联删除
        engine.getRepositoryService().deleteDeployment("37501",true);
        System.out.println("删除成功...");
    }

    /**
     * 获取PNG流程图 查询act_re_procdef表
     */
    @Test
    public void getActivityPngImage() throws IOException {
        ProcessDefinition processDefinition = engine.getRepositoryService().createProcessDefinitionQuery()
                .processDefinitionId("myProcess_1:2:55004").singleResult();

        System.out.println("id:" + processDefinition.getId() + "/name: " + processDefinition.getName());

        String proId = processDefinition.getDeploymentId();
        String name = processDefinition.getDiagramResourceName();

        InputStream inputStream = engine.getRepositoryService().getResourceAsStream(proId,name);

        FileUtils.copyInputStreamToFile(inputStream,new File("D:/Hello.png"));

    }

}
