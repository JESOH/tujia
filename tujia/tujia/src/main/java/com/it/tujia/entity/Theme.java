package com.it.tujia.entity;

import java.util.List;

/**
 * Created by kkguo on 2015/11/14.
 */
public  class Theme {
    public int groupId;
    public String groupName;
    public List<Hotel> list;
    public  int displayType;

    public int getDisplayType() {
        return displayType;
    }

    public void setDisplayType(int displayType) {
        this.displayType = displayType;
    }

    public Theme() {
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Hotel> getList() {
        return list;
    }

    public void setList(List<Hotel> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Theme{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", list=" + list +
                '}';
    }
}

