package com.chenbo.demo.activity.controller;

import com.chenbo.demo.activity.util.SecurityUtil;
import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskRuntime;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author : chenbo
 * @date : 2020-07-22
 */
@RestController
@RequestMapping("/")
public class ActivitiController {
    @Resource
    private ProcessRuntime processRuntime;
    @Resource
    private TaskRuntime taskRuntime;
    @Resource
    private SecurityUtil securityUtil;

    /**
     * 查询流程定义
     */
    @RequestMapping("/")
    public HashMap getProcess() {
        HashMap hashMap = new HashMap(10);
        //查询所有流程定义信息
        Page<ProcessDefinition> processDefinitionPage = processRuntime.processDefinitions(Pageable.of(0, 10));
        hashMap.put("流程数量", processDefinitionPage.getTotalItems());
        //获取流程信息
        for (ProcessDefinition processDefinition : processDefinitionPage.getContent()) {
            hashMap.put(processDefinition.getKey(), processDefinition);
        }
        return hashMap;
    }

    /**
     * 启动流程示例
     */
    @RequestMapping("/start")
    public HashMap startInstance() {
        HashMap hashMap = new HashMap(10);
        ProcessInstance instance = processRuntime.start(ProcessPayloadBuilder.start().withProcessDefinitionKey("approval").build());
        hashMap.put("启动流程", instance);
        return hashMap;
    }

    /**
     * 获取任务，拾取任务，并且执行
     */
    @RequestMapping("/do")
    public HashMap getTask() {
        HashMap hashMap = new HashMap(10);
        // 指定组内任务人
        securityUtil.logInAs("a");
        Page<Task> tasks = taskRuntime.tasks(Pageable.of(0, 10));
        if (tasks.getTotalItems() > 0) {
            for (Task task : tasks.getContent()) {
                hashMap.put(task.getName(), task);
                //拾取任务
                taskRuntime.claim(TaskPayloadBuilder.claim().withTaskId(task.getId()).build());
                //执行任务
                taskRuntime.complete(TaskPayloadBuilder.complete().withTaskId(task.getId()).build());
            }
        }
        return hashMap;
    }
}
