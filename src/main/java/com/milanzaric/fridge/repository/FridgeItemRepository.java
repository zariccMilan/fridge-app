package com.milanzaric.fridge.repository;


import com.milanzaric.fridge.entity.FridgeItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FridgeItemRepository extends JpaRepository<FridgeItem, UUID> {
    List<FridgeItem> findAllByIsDeletedFalse();

    Optional<FridgeItem> findByIdAndIsDeletedFalse(UUID id);

    List<FridgeItem> findAllByBestBeforeBetweenAndIsDeletedFalse(
            LocalDate from,
            LocalDate to
    );
}
