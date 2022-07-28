package com.github.wugenshui.activity.controller;

import com.github.wugenshui.activity.util.SecurityUtil;
import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.model.payloads.ClaimTaskPayload;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : chenbo
 * @date : 2020-07-22
 */
@RestController
@RequestMapping("/")
public class ActivitiController {

    /**
     * 流程引擎对象
     */
    @Resource
    ProcessEngine processEngine;
    @Resource
    TaskService taskService;
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
    @RequestMapping("/start/{user}/{day}")
    public HashMap startInstance(@PathVariable("user") String user, @PathVariable("day") Integer day) {
        HashMap hashMap = new HashMap(10);
        Map<String, Object> variables = new HashMap<>();
        variables.put("user", user);
        variables.put("day", day);
        ProcessInstance instance = processRuntime.start(ProcessPayloadBuilder.start().withProcessDefinitionKey("bingjia").withVariables(variables).build());
        hashMap.put("流程实例", instance);

        org.activiti.engine.task.Task task = taskService.createTaskQuery().processInstanceId(instance.getId()).singleResult();
        hashMap.put("当前步骤", task.toString());

        Map<String, Object> variables1 = taskService.getVariables(task.getId());
        hashMap.put("变量", variables1);
        return hashMap;
    }

    /**
     * 待办任务
     */
    @RequestMapping("/todo")
    public String todo() {
        List<org.activiti.engine.task.Task> list = taskService.createTaskQuery().list();
        return list.toString();
    }

    /**
     * 获取任务，拾取任务，并且执行
     */
    @RequestMapping("/do/{user}")
    public HashMap getTaskA(@PathVariable("user") String user, String next) {
        HashMap hashMap = new HashMap(10);
        // 指定组内任务人
        securityUtil.logInAs(user);
        Page<Task> tasks = taskRuntime.tasks(Pageable.of(0, 10));
        if (tasks.getTotalItems() > 0) {
            for (Task task : tasks.getContent()) {
                hashMap.put(task.getName(), task);
                // 拾取任务
                if (StringUtils.isEmpty(task.getAssignee())) {
                    ClaimTaskPayload claim;
                    if (StringUtils.isNoneEmpty(next)) {
                        claim = TaskPayloadBuilder.claim().withAssignee(next).withTaskId(task.getId()).build();
                    } else {
                        claim = TaskPayloadBuilder.claim().withTaskId(task.getId()).build();
                    }
                    taskRuntime.claim(claim);
                }
                // 执行任务
                taskRuntime.complete(TaskPayloadBuilder.complete().withTaskId(task.getId()).build());
                hashMap.put(task.getName(), task);
            }
        }
        return hashMap;
    }
}
