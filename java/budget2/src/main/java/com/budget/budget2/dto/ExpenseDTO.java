package com.budget.budget2.dto;

import java.time.LocalDateTime;

public class ExpenseDTO {
    private Long id;
    private String title;
    private Double amount;
    private String comment;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String createdAt;
    private ExpenseTypeDTO expenseType;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public ExpenseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public ExpenseTypeDTO getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseTypeDTO expenseTypeDTO) {
        this.expenseType = expenseTypeDTO;
    }
}
