package com.LMSystem.Sara.Mohamed_Library.Management.System.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.LMSystem.Sara.Mohamed_Library.Management.System.Model.BorrowingRecord;

@Repository
public interface BorrowingRepository extends JpaRepository<BorrowingRecord, Long> {
}