package br.com.it.helpdesk.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_TICKETS")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Ticket implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String title;
    private String description;
    private StatusEnum status;
    private PriorityEnum priority;
    private DepartmentEnum department;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "assignedTo_id")
    private User assignedTo;

    @OneToMany(mappedBy = "ticket")
    private Set<Comment> comments;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public PriorityEnum getPriority() {
        return priority;
    }

    public void setPriority(PriorityEnum priority) {
        this.priority = priority;
    }

    public DepartmentEnum getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEnum department) {
        this.department = department;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
