package com.mayikt.recursion;

import cn.hutool.core.collection.CollectionUtil;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author ：liuwq
 * @Date ：Created in 2023/10/25 17:33
 * @Description：
 * @Version : v1.0
 * @Copyright : Powerd By Winhong Tec Inc On 2023. All rights reserved.
 */
public class TreeUtils {

    public static List<DataCorrelationDetail> buildTree(List<DataCorrelationDetail> dataCorrelationDetails) {
        // 返回的树形数据
        List<DataCorrelationDetail> tree = new ArrayList();
        // 第一次遍历
        for (DataCorrelationDetail address : dataCorrelationDetails) {
            if (StringUtils.isEmpty(address.getParentUuid())) {
                tree.add(findChild(address, dataCorrelationDetails));
            }
        }
        return tree;
    }

    private static DataCorrelationDetail findChild(DataCorrelationDetail address, List<DataCorrelationDetail> list) {
        // 定义list用于存储子节点
        List<DataCorrelationDetail> children = new ArrayList();
        for (DataCorrelationDetail node : list) {
            if (!StringUtils.isEmpty(node.getParentUuid()) && node.getParentUuid().equals(address.getUuid())) {
                // 调用递归
                children.add(findChild(node, list));
            }
        }
        address.setChildren(children);
        return address;
    }


    public static void recursion(List<DataCorrelationDetail> dataCorrelationDetails, List<DataCorrelationDetail> allList) {
        Map<String, Integer> itemSortMap = new HashMap(16);
        Map<String, Integer> sortMap = new HashMap(16);

        for (DataCorrelationDetail dataCorrelationDetail : dataCorrelationDetails) {
            String itemUuid = dataCorrelationDetail.getItemUuid();
            if (!itemSortMap.containsKey(itemUuid)) {
                itemSortMap.put(itemUuid, itemSortMap.size());
            }
            dataCorrelationDetail.setItemSort(itemSortMap.get(itemUuid));

            if (!sortMap.containsKey(itemUuid)) {
                sortMap.put(itemUuid, 0);
            } else {
                sortMap.put(itemUuid, sortMap.get(itemUuid) + 1);
            }
            dataCorrelationDetail.setSort(sortMap.get(itemUuid));
            allList.add(dataCorrelationDetail);

            List<DataCorrelationDetail> children = dataCorrelationDetail.getChildren();
            if(!CollectionUtil.isEmpty(children)){
                recursion(children, allList);
            }

        }
    }

    public static void main(String[] args) {
        List<DataCorrelationDetail> list = new ArrayList<>();
        DataCorrelationDetail dataCorrelationDetail = new DataCorrelationDetail();
        dataCorrelationDetail.setUuid("1");
        dataCorrelationDetail.setItemUuid("1");
        dataCorrelationDetail.setItemName("单一网络");
        dataCorrelationDetail.setCodeName("自然资源区");
        list.add(dataCorrelationDetail);

        DataCorrelationDetail dataCorrelationDetail2 = new DataCorrelationDetail();
        dataCorrelationDetail2.setUuid("2");
        dataCorrelationDetail2.setItemUuid("2");
        dataCorrelationDetail2.setItemName("刘文强测试字典项1");
        dataCorrelationDetail2.setCodeName("fff");
        list.add(dataCorrelationDetail2);

        DataCorrelationDetail dataCorrelationDetail3 = new DataCorrelationDetail();
        dataCorrelationDetail3.setUuid("3");
        dataCorrelationDetail3.setItemUuid("2");
        dataCorrelationDetail3.setItemName("刘文强测试字典项1");
        dataCorrelationDetail3.setCodeName("asfa");
        list.add(dataCorrelationDetail3);

        DataCorrelationDetail dataCorrelationDetail4 = new DataCorrelationDetail();
        dataCorrelationDetail4.setUuid("4");
        dataCorrelationDetail4.setItemUuid("2");
        dataCorrelationDetail4.setItemName("刘文强测试字典项1");
        dataCorrelationDetail4.setCodeName("gdsfgy");
        list.add(dataCorrelationDetail4);

        DataCorrelationDetail dataCorrelationDetail5 = new DataCorrelationDetail();
        dataCorrelationDetail5.setUuid("5");
        dataCorrelationDetail5.setItemUuid("2");
        dataCorrelationDetail5.setItemName("刘文强测试字典项1");
        dataCorrelationDetail5.setCodeName("gdsf");
        list.add(dataCorrelationDetail5);

        DataCorrelationDetail dataCorrelationDetail6 = new DataCorrelationDetail();
        dataCorrelationDetail6.setUuid("6");
        dataCorrelationDetail6.setItemUuid("3");
        dataCorrelationDetail6.setItemName("可选配件");
        dataCorrelationDetail6.setCodeName("1TB SAS硬盘");
        dataCorrelationDetail6.setParentUuid("2");
        list.add(dataCorrelationDetail6);

        DataCorrelationDetail dataCorrelationDetail7 = new DataCorrelationDetail();
        dataCorrelationDetail7.setUuid("7");
        dataCorrelationDetail7.setItemUuid("3");
        dataCorrelationDetail7.setItemName("可选配件");
        dataCorrelationDetail7.setCodeName("1TB SSD硬盘");
        dataCorrelationDetail7.setParentUuid("2");
        list.add(dataCorrelationDetail7);

        DataCorrelationDetail dataCorrelationDetail8 = new DataCorrelationDetail();
        dataCorrelationDetail8.setUuid("8");
        dataCorrelationDetail8.setItemUuid("4");
        dataCorrelationDetail8.setItemName("存储桶名");
        dataCorrelationDetail8.setCodeName("NFS");
        dataCorrelationDetail8.setParentUuid("2");
        list.add(dataCorrelationDetail8);

        DataCorrelationDetail dataCorrelationDetail9 = new DataCorrelationDetail();
        dataCorrelationDetail9.setUuid("9");
        dataCorrelationDetail9.setItemUuid("4");
        dataCorrelationDetail9.setItemName("存储桶名");
        dataCorrelationDetail9.setCodeName("SASA");
        dataCorrelationDetail9.setParentUuid("2");
        list.add(dataCorrelationDetail9);

        List<DataCorrelationDetail> allList = new ArrayList<>();
        List<DataCorrelationDetail> tree = buildTree(list);

        recursion(tree, allList);
        System.out.println(allList);
    }

}
