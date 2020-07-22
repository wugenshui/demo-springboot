package com.chenbo.demo.activity;

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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivitiApplicationTest {
    @Autowired
    private ProcessRuntime processRuntime;
    @Autowired
    private TaskRuntime taskRuntime;
    @Autowired
    private SecurityUtil securityUtil;


    /**
     * 查看流程定义
     */
    @Test
    public void contextLoads() {
        securityUtil.logInAs("salaboy");
        Page<ProcessDefinition> processDefinitionPage = processRuntime.processDefinitions(Pageable.of(0, 10));
        System.out.println("可用的流程定义数量：" + processDefinitionPage.getTotalItems());
        for (ProcessDefinition pd : processDefinitionPage.getContent()) {
            System.out.println("流程定义：" + pd);
        }
    }

    /**
     * 启动流程实例
     */
    @Test
    public void testStartProcess() {
        securityUtil.logInAs("ryandawsonuk");
        ProcessInstance processInstance = processRuntime.start(ProcessPayloadBuilder.start().withProcessDefinitionKey("approval").build());
        System.out.println(processInstance.getProcessDefinitionKey() + "创建实例ID：" + processInstance.getId());

        processInstance = processRuntime.start(ProcessPayloadBuilder.start().withProcessDefinitionKey("qingjia").build());
        System.out.println(processInstance.getProcessDefinitionKey() + "创建实例ID：" + processInstance.getId());
    }

    /**
     * 查询任务，并完成自己的任务
     */
    @Test
    public void testTask() {
        securityUtil.logInAs("ryandawsonuk");
        for (int i = 0; i < 10; i++) {
            Page<Task> taskPage = taskRuntime.tasks(Pageable.of(0, 10));
            if (taskPage.getTotalItems() > 0) {
                for (Task task : taskPage.getContent()) {
                    taskRuntime.claim(TaskPayloadBuilder.claim().withTaskId(task.getId()).build());
                    System.out.println("任务：" + task);
                    taskRuntime.complete(TaskPayloadBuilder.complete().withTaskId(task.getId()).build());
                    System.out.println("完成任务：" + task.getName());
                }
            } else {
                break;
            }
        }
    }
}
