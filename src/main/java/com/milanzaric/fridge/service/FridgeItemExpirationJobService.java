package com.milanzaric.fridge.service;


import com.milanzaric.fridge.entity.FridgeItem;
import com.milanzaric.fridge.repository.FridgeItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FridgeItemExpirationJobService {

    private final FridgeItemRepository fridgeItemRepository;

    @Scheduled(fixedRate = 30_000)
    public void checkExpiringItems() {
        LocalDate today = LocalDate.now();
        LocalDate inTwoDays = today.plusDays(2); // everything that expires in next 2 days

        List<FridgeItem> expiringItems = fridgeItemRepository
                .findAllByBestBeforeBetweenAndIsDeletedFalse(today, inTwoDays);

        if (!expiringItems.isEmpty()) {
            System.out.println("Items that expire soon:");
            expiringItems.forEach(item ->
                    System.out.println(" - " + item.getName() + " (bestBefore=" + item.getBestBefore() + ")"));
        }
    }
}
