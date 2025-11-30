package com.milanzaric.fridge.repository;


import com.milanzaric.fridge.entity.ItemLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItemLocationRepository extends JpaRepository<ItemLocation, UUID> {
}
