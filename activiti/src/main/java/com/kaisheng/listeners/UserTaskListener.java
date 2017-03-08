package com.kaisheng.listeners;


import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class UserTaskListener implements TaskListener{
    @Override
    public void notify(DelegateTask delegateTask) {
        delegateTask.setAssignee("wangWu");
    }
}
