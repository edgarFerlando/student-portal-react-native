
package id.refactory.app.refactoryapps.api.models;

import java.util.List;

public class DataAssignment {

    private Integer id;
    private String assignmentType;
    private String title;
    private String url;
    private String description;
    private Integer score;
    private Object closedAt;
    private String createdAt;
    private String updatedAt;
    private String status;
    private Integer userId;
    private String resultValue;
    private String resultAttachments;
    private Object resultJson;
    private Object resultDescription;
    private Object resultAttachment;
    private String eta;
    private String assignees;
    private String relatedCurriculums;
    private String ticketUrl;
    private String author;
    private Boolean isOverdue;
    private List<Object> curriculumItems = null;
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAssignmentType() {
        return assignmentType;
    }

    public void setAssignmentType(String assignmentType) {
        this.assignmentType = assignmentType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Object getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Object closedAt) {
        this.closedAt = closedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getResultValue() {
        return resultValue;
    }

    public void setResultValue(String resultValue) {
        this.resultValue = resultValue;
    }

    public String getResultAttachments() {
        return resultAttachments;
    }

    public void setResultAttachments(String resultAttachments) {
        this.resultAttachments = resultAttachments;
    }

    public Object getResultJson() {
        return resultJson;
    }

    public void setResultJson(Object resultJson) {
        this.resultJson = resultJson;
    }

    public Object getResultDescription() {
        return resultDescription;
    }

    public void setResultDescription(Object resultDescription) {
        this.resultDescription = resultDescription;
    }

    public Object getResultAttachment() {
        return resultAttachment;
    }

    public void setResultAttachment(Object resultAttachment) {
        this.resultAttachment = resultAttachment;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getAssignees() {
        return assignees;
    }

    public void setAssignees(String assignees) {
        this.assignees = assignees;
    }

    public String getRelatedCurriculums() {
        return relatedCurriculums;
    }

    public void setRelatedCurriculums(String relatedCurriculums) {
        this.relatedCurriculums = relatedCurriculums;
    }

    public String getTicketUrl() {
        return ticketUrl;
    }

    public void setTicketUrl(String ticketUrl) {
        this.ticketUrl = ticketUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Boolean getIsOverdue() {
        return isOverdue;
    }

    public void setIsOverdue(Boolean isOverdue) {
        this.isOverdue = isOverdue;
    }

    public List<Object> getCurriculumItems() {
        return curriculumItems;
    }

    public void setCurriculumItems(List<Object> curriculumItems) {
        this.curriculumItems = curriculumItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
