package com.chenbo.demo.spring.cloud.alibaba.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : chenbo
 * @date : 2021-01-14
 */
//@SpringBootApplication
public class SentinelApplication {
    public static void main(String[] args) {
        initFlowRules();
        while (true) {
            Entry entry = null;
            try {
                Thread.sleep(100);
                entry = SphU.entry("HelloWorld");
                /*您的业务逻辑 - 开始*/
                System.out.println("hello world");
                /*您的业务逻辑 - 结束*/
            } catch (BlockException | InterruptedException e1) {
                /*流控逻辑处理 - 开始*/
                System.out.println("block!");
                /*流控逻辑处理 - 结束*/
            } finally {
                if (entry != null) {
                    entry.exit();
                }
            }
        }
//        SpringApplication.run(SentinelApplication.class, args);
    }

    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        rule.setCount(5);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
}
