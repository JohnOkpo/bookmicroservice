package com.stark3ase.ratems.repository;

import com.stark3ase.ratems.model.RateModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface RateRepository extends JpaRepository<RateModel, UUID>
{
//    List<RateModel> getRateByBookId(Long bookId);
//    @Query("SELECT SUM(r.rating) FROM RateModel r WHERE r.bookId = :bookId")
//    Integer getRateByBookId(Long bookId);
}
