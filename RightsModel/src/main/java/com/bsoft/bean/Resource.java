package com.bsoft.bean;

public class Resource {
    private Long resourceId;

    private Short resourceType;
    
    private String resourcePath;

    private Short status;

    private String resourceName;

    private String description;

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Short getResourceType() {
        return resourceType;
    }

    public void setResourceType(Short resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath == null ? null : resourcePath.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}