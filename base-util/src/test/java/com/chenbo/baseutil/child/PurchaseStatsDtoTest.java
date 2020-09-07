package com.chenbo.baseutil.child;

import cn.hutool.core.io.file.FileReader;
import com.chenbo.baseutil.util.FastJsonHelper;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<String, String> map = new HashMap<>();
        map.put("source1.json", "target1.json");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String sourceFileName = entry.getKey();
            String targetFileName = entry.getValue();

            FileReader fileReader = new FileReader(sourceFileName);
            String source = fileReader.readString();

            List<PurchaseStatsDto> sourceList = FastJsonHelper.deserializeList(source, PurchaseStatsDto.class);
            List<String> lstSubtotalKey = Arrays.asList("projectGroupName", "projectName", "batchName");

            // 分组键值集合
            List<String> oldKeyValues = new ArrayList<>();
            oldKeyValues.addAll(lstSubtotalKey);
            List<PurchaseStatsDto> targetList = getChildList(sourceList, 0, null, lstSubtotalKey, 0, oldKeyValues);
            String actual = FastJsonHelper.serialize(targetList);
            System.out.println(actual);
            FileReader targetFileReader = new FileReader(targetFileName);
            String target = targetFileReader.readString();
            Assert.assertEquals(target, actual);
        }
    }

    private void addData(PurchaseStatsDto source, List<PurchaseStatsDto> targetList, int deep) {
        List<PurchaseStatsDto> temp = targetList;
        for (int i = 0; i < deep; i++) {
            temp = temp.get(temp.size() - 1).getChild();
        }
        temp.add(source.clone());
    }

    private void addGroup(PurchaseStatsDto source, List<PurchaseStatsDto> targetList, int deep) {
        List<PurchaseStatsDto> temp = targetList;
        for (int i = 0; i < deep; i++) {
            temp = temp.get(temp.size() - 1).getChild();
        }
        temp.add(source.clone());
    }

    private List<PurchaseStatsDto> getChildList(List<PurchaseStatsDto> sourceList, int deep, List<PurchaseStatsDto> targetList,
                                                List<String> groups, int groupIndex, List<String> oldKeyValues) {
        System.out.println("-----------\t第" + (deep + 1) + "层,还剩 " + sourceList.size() + "条数据 groupIndex = " + groupIndex + " oldKeyValues = " + oldKeyValues + "\t---------- - ");
        if (CollectionUtils.isEmpty(targetList)) {
            targetList = new ArrayList<>();
        }
        PurchaseStatsDto source = sourceList.get(0).clone();
        source.setCurLevel(groupIndex);
        source.setMaxLevel(groups.size());

        String key = groups.get(groupIndex);
        String keyValue = getAttribute(source, key);
        String groupAttrs = source.getProjectGroupName() + " " + source.getProjectName() + " " + source.getBatchName() + " " + source.getMaterial();
        // **************** 分组相同 ****************
        if (keyValue.equals(oldKeyValues.get(groupIndex))) {
            System.out.println(groupAttrs + "\t[ " + groups.get(groupIndex) + " 分组相同" + " ]");
            if (groupIndex >= groups.size() - 1) {
                // 最后一组相同，则直接添加
                addData(source, targetList, groups.size());
                // 添加数据后移除该条记录
                sourceList.remove(0);
                System.out.println("+ 数据2 " + source.getMaterial());
            } else {
                getChildList(sourceList, deep, targetList, groups, groupIndex + 1, oldKeyValues);
            }
        } else {
            // **************** 分组不同 ****************
            System.out.println(groupAttrs + "\t[ " + groups.get(groupIndex) + " 分组不同" + " ]");
            // 如果分组不同，代表为不同分组
            source.setSubtotalKey(keyValue);
            PurchaseStatsDto cur = removeOtherAttr(source.clone()).clone();
            addGroup(cur, targetList, groupIndex);

            System.out.println("+ 分组 " + source.getSubtotalKey() + " " + source.getCurLevel() + "/" + source.getMaxLevel());
            oldKeyValues.set(groupIndex, keyValue);

            cur.setChild(getChildList(sourceList, deep + 1, targetList, groups, (groupIndex + 1) % groups.size(), oldKeyValues));
        }
        if (!CollectionUtils.isEmpty(sourceList) && deep == 0) {
            getChildList(sourceList, 0, targetList, groups, 0, oldKeyValues);
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

}
