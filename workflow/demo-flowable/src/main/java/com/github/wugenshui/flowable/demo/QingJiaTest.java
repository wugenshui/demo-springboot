package com.github.wugenshui.flowable.demo;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.ExtensionAttribute;
import org.flowable.bpmn.model.ExtensionElement;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.engine.HistoryService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author : chenbo
 * @date : 2024-12-08
 */
@Component
public class QingJiaTest {
    @Resource
    private ProcessEngineConfiguration processEngineConfiguration;
    @Resource
    private ProcessEngine processEngine;
    @Resource
    private TaskService taskService;
    @Resource
    private HistoryService historyService;

    public void run() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 部署
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("qingjia.bpmn20.xml")
                .deploy();

        // 流程定义
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployment.getId())
                .singleResult();
        System.out.println("Found process definition : " + processDefinition.getName());

        // 加载 BPMN 模型
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinition.getId());
        FlowElement userTask = bpmnModel.getFlowElement("Activity_0idxf0p");
        Map<String, List<ExtensionElement>> extensionElements = userTask.getExtensionElements();
        List<ExtensionElement> properties = extensionElements.get("properties");
        ExtensionElement propertie = properties.get(0);

        Map<String, List<ExtensionAttribute>> attributes = propertie.getAttributes();
        String approveType = propertie.getAttributeValue(propertie.getNamespace(), "approveType");
        // 能获取到模型XML中的值，但过于复杂
        String type = propertie.getChildElements().get("property").get(0).getAttributes().get("value").get(0).getValue();
        System.out.println("type = " + type);
    }
}
