package com.mayikt.recursion;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 数据关联详情
 * @author yangtingwei
 **/
public class DataCorrelationDetail  {
    private String uuid;
    //数据关联表uuid
    private String  dataCorrelationUuid;
    //字典项uuid
    private String  itemUuid;
    //字典项名称
    private String  itemName;
    //字典值uuid
    private String  codeUuid;
    //字典值名称
    private String  codeName;
    //子目录
    private List<DataCorrelationDetail> children;
    //父值uuid
    private String  parentUuid;
    //创建时间
    private LocalDateTime  createTime;
    //更新时间
    private LocalDateTime  updateTime;
    // 字典项顺序
    private Integer itemSort;
    // 字典值顺序
    private Integer sort;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDataCorrelationUuid() {
        return dataCorrelationUuid;
    }

    public void setDataCorrelationUuid(String dataCorrelationUuid) {
        this.dataCorrelationUuid = dataCorrelationUuid;
    }

    public String getItemUuid() {
        return itemUuid;
    }

    public void setItemUuid(String itemUuid) {
        this.itemUuid = itemUuid;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCodeUuid() {
        return codeUuid;
    }

    public void setCodeUuid(String codeUuid) {
        this.codeUuid = codeUuid;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getParentUuid() {
        return parentUuid;
    }

    public void setParentUuid(String parentUuid) {
        this.parentUuid = parentUuid;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public List<DataCorrelationDetail> getChildren() {
        return children;
    }

    public void setChildren(List<DataCorrelationDetail> children) {
        this.children = children;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getItemSort() {
        return itemSort;
    }

    public void setItemSort(Integer itemSort) {
        this.itemSort = itemSort;
    }

    @Override
    public String toString() {
        return "DataCorrelationDetail{" +
                "uuid='" + uuid + '\'' +
                ", dataCorrelationUuid='" + dataCorrelationUuid + '\'' +
                ", itemUuid='" + itemUuid + '\'' +
                ", itemName='" + itemName + '\'' +
                ", codeUuid='" + codeUuid + '\'' +
                ", codeName='" + codeName + '\'' +
                ", children=" + children +
                ", parentUuid='" + parentUuid + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", sort=" + sort +
                ", itemSort=" + itemSort +
                '}';
    }
}
