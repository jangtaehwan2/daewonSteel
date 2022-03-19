package com.daewon.daewon.repository;

import com.daewon.daewon.domain.steeldata.SteelData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SteelDataRepository extends JpaRepository<SteelData, Long> {
    List<SteelData> findByDateBetweenAndDeletedOrderByDateAsc(LocalDate startDate, LocalDate endDate, boolean deleted);
    List<SteelData> findByDeletedOrderByDateAsc(boolean deleted);
}
