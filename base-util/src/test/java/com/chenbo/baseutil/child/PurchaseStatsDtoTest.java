package com.chenbo.baseutil.child;

import com.chenbo.baseutil.util.FastJsonHelper;
import com.google.common.collect.Lists;
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
public class PurchaseStatsDtoTest {

    @Test
    public void trans() {
        String str = "";
        StringBuilder sb = new StringBuilder();
        sb.append("[{\"amount\":25.00,\"avgPrice\":0.880000,\"batchName\":\"scpc-01\",\"customerName\":\"aaa\",\"material\":\"A\",\"model\":\"大号黑皮笔记本\",\"modelCode\":\"\",\"modelId\":1525,\"organName\":\"无部门\",\"parentTypeId\":203,\"parentTypeName\":\"办公文具\",\"projectGroupName\":\"华为项目群\",\"projectName\":\"上海智能停车系统\",\"totalPrice\":22.2200,\"typeId\":203002,\"typeName\":\"本子\"}");
        sb.append(",{\"amount\":10.00,\"avgPrice\":1500.000000,\"batchName\":\"无批次\",\"customerName\":\"无供应商\",\"material\":\"B\",\"model\":\"转接板/V1.0\",\"modelId\":2,\"organName\":\"无部门\",\"parentTypeId\":201,\"parentTypeName\":\"PCB\",\"projectGroupName\":\"无项目群\",\"projectName\":\"非项目\",\"totalPrice\":12800.0000,\"typeId\":201001,\"typeName\":\"安全门PCB\"}");
        sb.append(",{\"amount\":500.00,\"avgPrice\":0.000000,\"batchName\":\"无批次\",\"customerName\":\"广州市惠美科技发展有限公司\",\"material\":\"C\",\"model\":\"转接板/V1.0\",\"modelId\":2,\"organName\":\"无部门\",\"parentTypeId\":201,\"parentTypeName\":\"PCB\",\"projectGroupName\":\"无项目群\",\"projectName\":\"非项目\",\"totalPrice\":0.0000,\"typeId\":201001,\"typeName\":\"安全门PCB\"}");
        sb.append(",{\"amount\":66.00,\"avgPrice\":111.000000,\"batchName\":\"无批次\",\"customerName\":\"无供应商\",\"material\":\"D\",\"model\":\"2层/100*100*1.6mm/2.54mm\",\"modelCode\":\"\",\"modelId\":3,\"organName\":\"无部门\",\"parentTypeId\":201,\"parentTypeName\":\"PCB\",\"projectGroupName\":\"无项目群\",\"projectName\":\"非项目\",\"totalPrice\":222.0000,\"typeId\":201002,\"typeName\":\"万能板\"},{\"amount\":2.00,\"avgPrice\":91.000000,\"batchName\":\"无批次\",\"customerName\":\"广州华精电电子有限公司\",\"material\":\"E\",\"model\":\"2层/100*100*1.6mm/2.54mm\",\"modelCode\":\"\",\"modelId\":3,\"organName\":\"无部门\",\"parentTypeId\":201,\"parentTypeName\":\"PCB\",\"projectGroupName\":\"无项目群\",\"projectName\":\"非项目\",\"totalPrice\":182.0000,\"typeId\":201002,\"typeName\":\"万能板\"}");
        sb.append(",{\"amount\":5.00,\"avgPrice\":0.000000,\"batchName\":\"无批次\",\"customerName\":\"京东/淘宝\",\"material\":\"F\",\"model\":\"2层/100*100*1.6mm/2.54mm\",\"modelCode\":\"\",\"modelId\":3,\"organName\":\"无部门\",\"parentTypeId\":201,\"parentTypeName\":\"PCB\",\"projectGroupName\":\"无项目群\",\"projectName\":\"非项目\",\"totalPrice\":0.0000,\"typeId\":201002,\"typeName\":\"万能板\"},{\"amount\":1.00,\"avgPrice\":1011.000000,\"batchName\":\"无批次\",\"customerName\":\"京东/淘宝\",\"material\":\"F\",\"model\":\"核心主板/V1.0\",\"modelId\":5,\"organName\":\"无部门\",\"parentTypeId\":201,\"parentTypeName\":\"PCB\",\"projectGroupName\":\"无项目群\",\"projectName\":\"非项目\",\"totalPrice\":1011.0000,\"typeId\":201003,\"typeName\":\"智能头盔PCB\"},{\"amount\":1.00,\"avgPrice\":661.000000,\"batchName\":\"无批次\",\"customerName\":\"京东/淘宝\",\"material\":\"PCB-智能头盔PCB-核心主板/V2.0\",\"model\":\"核心主板/V2.0\",\"modelId\":6,\"organName\":\"无部门\",\"parentTypeId\":201,\"parentTypeName\":\"PCB\",\"projectGroupName\":\"无项目群\",\"projectName\":\"非项目\",\"totalPrice\":661.0000,\"typeId\":201003,\"typeName\":\"智能头盔PCB\"}");
        sb.append(",{\"amount\":20.00,\"avgPrice\":5.750000,\"batchName\":\"无批次\",\"customerName\":\"京东/淘宝\",\"material\":\"PCB-智能头盔PCB-前置小板/V1.0\",\"model\":\"前置小板/V1.0\",\"modelId\":7,\"organName\":\"无部门\",\"parentTypeId\":201,\"parentTypeName\":\"PCB\",\"projectGroupName\":\"无项目群\",\"projectName\":\"非项目\",\"totalPrice\":115.0000,\"typeId\":201003,\"typeName\":\"智能头盔PCB\"},{\"amount\":63.00,\"avgPrice\":44.400000,\"batchName\":\"无批次\",\"customerName\":\"无供应商\",\"material\":\"办公生活-抽纸-90抽/盒\",\"model\":\"90抽/盒\",\"modelId\":8,\"organName\":\"无部门\",\"parentTypeId\":202,\"parentTypeName\":\"办公生活\",\"projectGroupName\":\"无项目群\",\"projectName\":\"非项目\",\"totalPrice\":888.0000,\"typeId\":202001,\"typeName\":\"抽纸\"},{\"amount\":5.00,\"avgPrice\":88.000000,\"batchName\":\"无批次\",\"customerName\":\"广州华精电电子有限公司\",\"material\":\"办公生活-抽纸-90抽/盒\",\"model\":\"90抽/盒\",\"modelId\":8,\"organName\":\"无部门\",\"parentTypeId\":202,\"parentTypeName\":\"办公生活\",\"projectGroupName\":\"无项目群\",\"projectName\":\"非项目\",\"totalPrice\":440.0000,\"typeId\":202001,\"typeName\":\"抽纸\"},{\"amount\":9.00,\"avgPrice\":13.890750,\"batchName\":\"无批次\",\"customerName\":\"京东/淘宝\",\"material\":\"办公生活-抽纸-90抽/盒\",\"model\":\"90抽/盒\",\"modelId\":8,\"organName\":\"无部门\",\"parentTypeId\":202,\"parentTypeName\":\"办公生活\",\"projectGroupName\":\"无项目群\",\"projectName\":\"非项目\",\"totalPrice\":131.7890,\"typeId\":202001,\"typeName\":\"抽纸\"}");
        sb.append(",{\"amount\":100.00,\"avgPrice\":2.000000,\"batchName\":\"无批次\",\"customerName\":\"无供应商\",\"material\":\"办公生活-卷纸-清风3层270段24卷\",\"model\":\"清风3层270段24卷\",\"modelId\":9,\"organName\":\"无部门\",\"parentTypeId\":202,\"parentTypeName\":\"办公生活\",\"projectGroupName\":\"无项目群\",\"projectName\":\"非项目\",\"totalPrice\":200.0000,\"typeId\":202003,\"typeName\":\"卷纸\"},{\"amount\":130.00,\"avgPrice\":3.753328,\"batchName\":\"无批次\",\"customerName\":\"京东/淘宝\",\"material\":\"办公生活-卷纸-清风3层270段24卷\",\"model\":\"清风3层270段24卷\",\"modelId\":9,\"organName\":\"无部门\",\"parentTypeId\":202,\"parentTypeName\":\"办公生活\",\"projectGroupName\":\"无项目群\",\"projectName\":\"非项目\",\"totalPrice\":489.8999,\"typeId\":202003,\"typeName\":\"卷纸\"},{\"amount\":12.00,\"avgPrice\":11.000000,\"batchName\":\"无批次\",\"customerName\":\"广州市纬度资讯科技有限公司\",\"material\":\"办公生活-洗手液-蓝月亮/500g套装（6瓶）\",\"model\":\"蓝月亮/500g套装（6瓶）\",\"modelId\":11,\"organName\":\"无部门\",\"parentTypeId\":202,\"parentTypeName\":\"办公生活\",\"projectGroupName\":\"无项目群\",\"projectName\":\"非项目\",\"totalPrice\":132.0000,\"typeId\":202008,\"typeName\":\"洗手液\"},{\"amount\":10.00,\"avgPrice\":50.010000,\"batchName\":\"无批次\",\"customerName\":\"京东/淘宝\",\"material\":\"办公生活-洗手液-蓝月亮/500g套装（6瓶）\",\"model\":\"蓝月亮/500g套装（6瓶）\",\"modelId\":11,\"organName\":\"无部门\",\"parentTypeId\":202,\"parentTypeName\":\"办公生活\",\"projectGroupName\":\"无项目群\",\"projectName\":\"非项目\",\"totalPrice\":499.7400,\"typeId\":202008,\"typeName\":\"洗手液\"}");
        sb.append("");
        sb.append("");
        sb.append("");
        sb.append("]");

        List<PurchaseStatsDto> sourceList = FastJsonHelper.deserializeList(sb.toString(), PurchaseStatsDto.class);
        //System.out.println(source);
        List<String> lstSubtotalKey = Arrays.asList("projectGroupName", "projectName");
        //List<String> lstSubtotalKey = Arrays.asList("projectGroupName", "projectName", "batchName");

        // 分组键值集合
        List<String> oldKeyValues = Arrays.asList("projectGroupName", "projectName");
        List<Integer> indexs = Arrays.asList(0, 0);
        //List<String> oldKeyValues = Arrays.asList("projectGroupName", "projectName", "batchName");
        List<PurchaseStatsDto> targetList = getChildList(sourceList, 0, null, 0, lstSubtotalKey, 0, oldKeyValues);
        System.out.println(FastJsonHelper.serialize(targetList));
    }

    private List<PurchaseStatsDto> getChildList(List<PurchaseStatsDto> sourceList, int sourceIndex, List<PurchaseStatsDto> targetList,
                                                int targetIndex, List<String> groups, int groupIndex, List<String> oldKeyValues) {
        System.out.println("-----------\t第" + sourceIndex + "条数据 targetIndex=" + targetIndex + " groupIndex=" + groupIndex + " oldKeyValues=" + oldKeyValues + "\t-----------");
        if (CollectionUtils.isEmpty(targetList)) {
            targetList = new ArrayList<>();
        }

        // 分组已处理完毕，添加元素即可
        if (groupIndex == groups.size()) {
            PurchaseStatsDto purchaseStatsDto = sourceList.get(sourceIndex).clone();
            purchaseStatsDto.setCurLevel(groups.size());
            purchaseStatsDto.setMaxLevel(groups.size());
            targetList.add(purchaseStatsDto);
            System.out.println("+ 数据 " + purchaseStatsDto.getMaterial());
            return targetList;
        }

        for (int i = 0; i < sourceList.size(); i++) {
            PurchaseStatsDto source = sourceList.get(i).clone();
            source.setCurLevel(groupIndex);
            source.setMaxLevel(groups.size());


            String key = groups.get(groupIndex);
            String keyValue = getAttribute(source, key);
            //System.out.println("ProjectGroupName=" + source.getProjectGroupName() + " ProjectName=" + source.getProjectName() + " batchName=" + source.getBatchName());
            String groupAttrs = source.getProjectGroupName() + " " + source.getProjectName() + " " + source.getBatchName() + " " + source.getMaterial();
            // **************** 分组相同 ****************
            if (keyValue.equals(oldKeyValues.get(groupIndex))) {
                System.out.println(groupAttrs + "\t[ " + groups.get(groupIndex) + " 分组相同" + " ]");
                //PurchaseStatsDto target = targetList.get(targetIndex);
                // 最后一组相同，则直接添加
                if (groupIndex == groups.size() - 1) {
                    targetList.add(source.clone());
                    return targetList;
                    //System.out.println("当前集合子元素增加source " + source.getCurLevel() + "/" + source.getMaxLevel());
                } else {
                    //targetList.add(source.clone());
                    //System.out.println("当前集合增加source " + source.getCurLevel() + "/" + source.getMaxLevel());
                    //getChildList(sourceList, sourceIndex, targetList, targetIndex, groups, groupIndex + 1, oldKeyValues);
                }
            } else {
                // **************** 分组不同 ****************
                System.out.println(groupAttrs + "\t[ " + groups.get(groupIndex) + " 分组不同" + " ]");
                // 如果分组不同，代表为不同分组
                source.setSubtotalKey(keyValue);
                PurchaseStatsDto target = removeOtherAttr(source.clone());
                targetList.add(target);
                System.out.println("+ 分组 " + source.getSubtotalKey() + " " + source.getCurLevel() + "/" + source.getMaxLevel());
                oldKeyValues.set(groupIndex, keyValue);
                target.setChild(getChildList(sourceList, i, target.getChild(), 0, groups, groupIndex + 1, oldKeyValues));
            }
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

    private PurchaseStatsDto removeOtherAttr(PurchaseStatsDto dto) {
        dto.setAmount(null);
        dto.setAvgPrice(null);
        dto.setTotalPrice(null);
        //dto.setParentTypeId(null);
        //dto.setParentTypeName(null);
        //dto.setTypeId(null);
        //dto.setTypeName(null);
        //dto.setModelId(null);
        dto.setMaterial(null);
        //dto.setModel(null);
        //dto.setModelCode(null);
        //dto.setProjectId(null);
        dto.setProjectName(null);
        dto.setCustomerName(null);
        dto.setProjectGroupName(null);
        dto.setBatchName(null);
        dto.setOrganName(null);
        dto.setChild(Lists.newArrayList());
        return dto;
    }

    private List<PurchaseStatsDto> getChildList2(List<PurchaseStatsDto> sourceList, int sourceIndex, List<PurchaseStatsDto> targetList,
                                                 int targetIndex, List<String> groups, int groupIndex, List<String> oldKeyValues) {
        if (CollectionUtils.isEmpty(targetList)) {
            targetList = new ArrayList<>();
        }
        PurchaseStatsDto target = new PurchaseStatsDto();
        List<Integer> indexs = Arrays.asList(0, 0);
        for (PurchaseStatsDto source : sourceList) {

            for (int i = 0; i < groups.size(); i++) {
                String key = groups.get(i);
                String newkeyValue = getAttribute(source, key);
                if (oldKeyValues.get(i).equals(newkeyValue)) {
                    // 分组相同
                    if (i == groups.size() - 1) {
                        //if (CollectionUtils.isEmpty(target.getChild())) {
                        //    target.setChild(new ArrayList<>());
                        //}
                        //System.out.println("明细 " + source.clone());
                        //target.getChild().add(source.clone());
                    }
                } else {
                    // 分组不同
                    oldKeyValues.set(i, newkeyValue);
                    target = source.clone();
                    System.out.println("分组 " + removeOtherAttr(target));
                    targetList.add(removeOtherAttr(target));
                }
            }
        }

        return targetList;
    }

}
