package com.budget.budget2.repository;

import com.budget.budget2.entity.ExpenseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseTypeRepository extends JpaRepository<ExpenseType, Long> {

    @Query("select e from ExpenseType e where e.id = ?1")
    ExpenseType getOne(int id);
}