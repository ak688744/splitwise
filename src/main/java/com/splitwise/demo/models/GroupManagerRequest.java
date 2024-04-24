package com.splitwise.demo.models;

import java.util.List;

public class GroupManagerRequest {
    String groupName;
    List<String> userNames;

    public GroupManagerRequest() {
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<String> getUserNames() {
        return userNames;
    }

    public void setUserNames(List<String> userNames) {
        this.userNames = userNames;
    }

    public GroupManagerRequest(String groupName, List<String> userNames) {
        this.groupName = groupName;
        this.userNames = userNames;
    }
}
