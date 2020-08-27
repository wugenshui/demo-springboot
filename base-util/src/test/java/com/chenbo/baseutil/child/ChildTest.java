package com.chenbo.baseutil.child;

import com.chenbo.baseutil.util.FastJsonHelper;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 遍历生成子元素测试类
 *
 * @author : chenbo
 * @date : 2020-08-27
 */
@SpringBootTest
public class ChildTest {

    @Test
    public void trans() {
        String str = "";
        StringBuilder sb = new StringBuilder();
        sb.append("[{\"amount\":25.00,\"avgPrice\":0.880000,\"batchName\":\"scpc-01\",\"customerName\":\"aaa\",\"material\":\"办公文具-本子-大号黑皮笔记本\",\"model\":\"大号黑皮笔记本\",\"modelCode\":\"\",\"modelId\":1525,\"organName\":\"无部门\",\"parentTypeId\":203,\"parentTypeName\":\"办公文具\",\"projectGroupName\":\"华为项目群\",\"projectName\":\"上海智能停车系统\",\"totalPrice\":22.2200,\"typeId\":203002,\"typeName\":\"本子\"}");
        sb.append(",{\"amount\":10.00,\"avgPrice\":1500.000000,\"batchName\":\"无批次\",\"customerName\":\"无供应商\",\"material\":\"PCB-安全门PCB-转接板/V1.0\",\"model\":\"转接板/V1.0\",\"modelId\":2,\"organName\":\"无部门\",\"parentTypeId\":201,\"parentTypeName\":\"PCB\",\"projectGroupName\":\"无项目群\",\"projectName\":\"非项目\",\"totalPrice\":12800.0000,\"typeId\":201001,\"typeName\":\"安全门PCB\"}");
        sb.append(",{\"amount\":500.00,\"avgPrice\":0.000000,\"batchName\":\"无批次\",\"customerName\":\"广州市惠美科技发展有限公司\",\"material\":\"PCB-安全门PCB-转接板/V1.0\",\"model\":\"转接板/V1.0\",\"modelId\":2,\"organName\":\"无部门\",\"parentTypeId\":201,\"parentTypeName\":\"PCB\",\"projectGroupName\":\"无项目群\",\"projectName\":\"非项目\",\"totalPrice\":0.0000,\"typeId\":201001,\"typeName\":\"安全门PCB\"}");
        sb.append(",{\"amount\":66.00,\"avgPrice\":111.000000,\"batchName\":\"无批次\",\"customerName\":\"无供应商\",\"material\":\"PCB-万能板-2层/100*100*1.6mm/2.54mm\",\"model\":\"2层/100*100*1.6mm/2.54mm\",\"modelCode\":\"\",\"modelId\":3,\"organName\":\"无部门\",\"parentTypeId\":201,\"parentTypeName\":\"PCB\",\"projectGroupName\":\"无项目群\",\"projectName\":\"非项目\",\"totalPrice\":222.0000,\"typeId\":201002,\"typeName\":\"万能板\"},{\"amount\":2.00,\"avgPrice\":91.000000,\"batchName\":\"无批次\",\"customerName\":\"广州华精电电子有限公司\",\"material\":\"PCB-万能板-2层/100*100*1.6mm/2.54mm\",\"model\":\"2层/100*100*1.6mm/2.54mm\",\"modelCode\":\"\",\"modelId\":3,\"organName\":\"无部门\",\"parentTypeId\":201,\"parentTypeName\":\"PCB\",\"projectGroupName\":\"无项目群\",\"projectName\":\"非项目\",\"totalPrice\":182.0000,\"typeId\":201002,\"typeName\":\"万能板\"}");
        sb.append(",{\"amount\":5.00,\"avgPrice\":0.000000,\"batchName\":\"无批次\",\"customerName\":\"京东/淘宝\",\"material\":\"PCB-万能板-2层/100*100*1.6mm/2.54mm\",\"model\":\"2层/100*100*1.6mm/2.54mm\",\"modelCode\":\"\",\"modelId\":3,\"organName\":\"无部门\",\"parentTypeId\":201,\"parentTypeName\":\"PCB\",\"projectGroupName\":\"无项目群\",\"projectName\":\"非项目\",\"totalPrice\":0.0000,\"typeId\":201002,\"typeName\":\"万能板\"},{\"amount\":1.00,\"avgPrice\":1011.000000,\"batchName\":\"无批次\",\"customerName\":\"京东/淘宝\",\"material\":\"PCB-智能头盔PCB-核心主板/V1.0\",\"model\":\"核心主板/V1.0\",\"modelId\":5,\"organName\":\"无部门\",\"parentTypeId\":201,\"parentTypeName\":\"PCB\",\"projectGroupName\":\"无项目群\",\"projectName\":\"非项目\",\"totalPrice\":1011.0000,\"typeId\":201003,\"typeName\":\"智能头盔PCB\"},{\"amount\":1.00,\"avgPrice\":661.000000,\"batchName\":\"无批次\",\"customerName\":\"京东/淘宝\",\"material\":\"PCB-智能头盔PCB-核心主板/V2.0\",\"model\":\"核心主板/V2.0\",\"modelId\":6,\"organName\":\"无部门\",\"parentTypeId\":201,\"parentTypeName\":\"PCB\",\"projectGroupName\":\"无项目群\",\"projectName\":\"非项目\",\"totalPrice\":661.0000,\"typeId\":201003,\"typeName\":\"智能头盔PCB\"}");
        sb.append(",{\"amount\":20.00,\"avgPrice\":5.750000,\"batchName\":\"无批次\",\"customerName\":\"京东/淘宝\",\"material\":\"PCB-智能头盔PCB-前置小板/V1.0\",\"model\":\"前置小板/V1.0\",\"modelId\":7,\"organName\":\"无部门\",\"parentTypeId\":201,\"parentTypeName\":\"PCB\",\"projectGroupName\":\"无项目群\",\"projectName\":\"非项目\",\"totalPrice\":115.0000,\"typeId\":201003,\"typeName\":\"智能头盔PCB\"},{\"amount\":63.00,\"avgPrice\":44.400000,\"batchName\":\"无批次\",\"customerName\":\"无供应商\",\"material\":\"办公生活-抽纸-90抽/盒\",\"model\":\"90抽/盒\",\"modelId\":8,\"organName\":\"无部门\",\"parentTypeId\":202,\"parentTypeName\":\"办公生活\",\"projectGroupName\":\"无项目群\",\"projectName\":\"非项目\",\"totalPrice\":888.0000,\"typeId\":202001,\"typeName\":\"抽纸\"},{\"amount\":5.00,\"avgPrice\":88.000000,\"batchName\":\"无批次\",\"customerName\":\"广州华精电电子有限公司\",\"material\":\"办公生活-抽纸-90抽/盒\",\"model\":\"90抽/盒\",\"modelId\":8,\"organName\":\"无部门\",\"parentTypeId\":202,\"parentTypeName\":\"办公生活\",\"projectGroupName\":\"无项目群\",\"projectName\":\"非项目\",\"totalPrice\":440.0000,\"typeId\":202001,\"typeName\":\"抽纸\"},{\"amount\":9.00,\"avgPrice\":13.890750,\"batchName\":\"无批次\",\"customerName\":\"京东/淘宝\",\"material\":\"办公生活-抽纸-90抽/盒\",\"model\":\"90抽/盒\",\"modelId\":8,\"organName\":\"无部门\",\"parentTypeId\":202,\"parentTypeName\":\"办公生活\",\"projectGroupName\":\"无项目群\",\"projectName\":\"非项目\",\"totalPrice\":131.7890,\"typeId\":202001,\"typeName\":\"抽纸\"}");
        sb.append(",{\"amount\":100.00,\"avgPrice\":2.000000,\"batchName\":\"无批次\",\"customerName\":\"无供应商\",\"material\":\"办公生活-卷纸-清风3层270段24卷\",\"model\":\"清风3层270段24卷\",\"modelId\":9,\"organName\":\"无部门\",\"parentTypeId\":202,\"parentTypeName\":\"办公生活\",\"projectGroupName\":\"无项目群\",\"projectName\":\"非项目\",\"totalPrice\":200.0000,\"typeId\":202003,\"typeName\":\"卷纸\"},{\"amount\":130.00,\"avgPrice\":3.753328,\"batchName\":\"无批次\",\"customerName\":\"京东/淘宝\",\"material\":\"办公生活-卷纸-清风3层270段24卷\",\"model\":\"清风3层270段24卷\",\"modelId\":9,\"organName\":\"无部门\",\"parentTypeId\":202,\"parentTypeName\":\"办公生活\",\"projectGroupName\":\"无项目群\",\"projectName\":\"非项目\",\"totalPrice\":489.8999,\"typeId\":202003,\"typeName\":\"卷纸\"},{\"amount\":12.00,\"avgPrice\":11.000000,\"batchName\":\"无批次\",\"customerName\":\"广州市纬度资讯科技有限公司\",\"material\":\"办公生活-洗手液-蓝月亮/500g套装（6瓶）\",\"model\":\"蓝月亮/500g套装（6瓶）\",\"modelId\":11,\"organName\":\"无部门\",\"parentTypeId\":202,\"parentTypeName\":\"办公生活\",\"projectGroupName\":\"无项目群\",\"projectName\":\"非项目\",\"totalPrice\":132.0000,\"typeId\":202008,\"typeName\":\"洗手液\"},{\"amount\":10.00,\"avgPrice\":50.010000,\"batchName\":\"无批次\",\"customerName\":\"京东/淘宝\",\"material\":\"办公生活-洗手液-蓝月亮/500g套装（6瓶）\",\"model\":\"蓝月亮/500g套装（6瓶）\",\"modelId\":11,\"organName\":\"无部门\",\"parentTypeId\":202,\"parentTypeName\":\"办公生活\",\"projectGroupName\":\"无项目群\",\"projectName\":\"非项目\",\"totalPrice\":499.7400,\"typeId\":202008,\"typeName\":\"洗手液\"}");
        sb.append("");
        sb.append("");
        sb.append("");
        sb.append("]");

        List<PurchaseStatsDto> source = FastJsonHelper.deserializeList(sb.toString(), PurchaseStatsDto.class);
        //System.out.println(source);
        List<PurchaseStatsDto> target = new ArrayList<>();
        List<String> lstSubtotalKey = Arrays.asList("projectGroupName", "projectName", "batchName");
        //initChild(source, 0, target, lstSubtotalKey, 0);

        // 分组键值集合
        List<String> oldKeyValues = Arrays.asList("projectGroupName", "projectName", "batchName");
        int targetIndex = -1;
        //for (int i = 0; i < source.size(); i++) {
        //    PurchaseStatsDto purchaseStatsDto = source.get(i);
        //    for (int j = 0; j < lstSubtotalKey.size(); j++) {
        //        Object fieldValue = BeanUtil.getFieldValue(purchaseStatsDto, lstSubtotalKey.get(j));
        //        String keyValue = getAttribute(purchaseStatsDto, lstSubtotalKey.get(j));
        //        Assert.assertEquals(fieldValue.toString(), keyValue);
        //        if (keyValue != oldKeyValues.get(j)) {
        //            // 首层键值不对，则为新的一组
        //            if (j == 0) {
        //                target.add(source.get(i).clone());
        //                targetIndex++;
        //            } else if (j == 1) {
        //                List<PurchaseStatsDto> child = Arrays.asList(source.get(i).clone());
        //                target.get(targetIndex).setChild(child);
        //            }
        //        }
        //    }
        //}
        target = getChildList(source, 0, target, 0, lstSubtotalKey, 0, oldKeyValues);

        System.out.println(target);
    }

    private List<PurchaseStatsDto> getChildList(List<PurchaseStatsDto> sourceList, int sourceIndex, List<PurchaseStatsDto> targetList,
                                                int targetIndex, List<String> groups, int groupIndex, List<String> oldKeyValues) {
        PurchaseStatsDto source = sourceList.get(sourceIndex).clone();
        source.setCurLevel(groupIndex);
        source.setMaxLevel(groups.size());
        if (CollectionUtils.isEmpty(targetList)) {
            targetList = new ArrayList<>();
        }

        String key = groups.get(groupIndex);
        String keyValue = getAttribute(source, key);
        // 如果分组相同，代表为相同分组
        if (keyValue.equals(oldKeyValues.get(groupIndex))) {
            PurchaseStatsDto target = targetList.get(targetIndex);
            // 最后一组相同，则直接添加
            if (groupIndex == groups.size() - 1) {
                target.getChild().add(source);
            } else {
                targetList.add(source);
                getChildList(sourceList, sourceIndex, target.getChild(), 0, groups, groupIndex + 1, oldKeyValues);
            }
        } else {
            // 如果分组不同，代表为不同分组

            targetList.add(source.clone());
            // 首层键值不对，则为新的一组
            //if (groupIndex == 0) {
            //    targetList.add(source);
            //    targetIndex++;
            //} else
            //if (groupIndex == target.getCurLevel()) {
            //    List<PurchaseStatsDto> child = Arrays.asList(source);
            //    target.setChild(child);
            //}
            PurchaseStatsDto target = targetList.get(targetIndex);
            oldKeyValues.set(groupIndex, keyValue);
            // 最后一组相同，则直接添加
            if (groupIndex == groups.size() - 1) {
                if (CollectionUtils.isEmpty(target.getChild())) {
                    target.setChild(new ArrayList<>());
                }
                PurchaseStatsDto child = source.clone();
                child.setCurLevel(groups.size());
                target.getChild().add(child);
                //getChildList(sourceList, sourceIndex + 1, targetList, 0, groups, 0, oldKeyValues);
            } else {
                target.setChild(getChildList(sourceList, sourceIndex, target.getChild(), 0, groups, groupIndex + 1, oldKeyValues));
            }
        }
        if (sourceIndex < sourceList.size() - 1) {
            getChildList(sourceList, sourceIndex + 1, targetList, 0, groups, groupIndex, oldKeyValues);
        }
        return targetList;
    }

    /**
     * 获取bean属性
     */
    private String getAttribute(PurchaseStatsDto dto, String key) {
        String result = "";
        try {
            Field declaredField = dto.getClass().getDeclaredField(key);
            declaredField.setAccessible(true);
            result = declaredField.get(dto).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
