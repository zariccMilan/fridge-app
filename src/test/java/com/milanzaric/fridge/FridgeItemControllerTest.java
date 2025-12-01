package com.milanzaric.fridge;


import com.milanzaric.fridge.controller.FridgeItemController;
import com.milanzaric.fridge.dto.fridgeitem.FridgeItemDTO;
import com.milanzaric.fridge.service.FridgeItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FridgeItemController.class)
public class FridgeItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FridgeItemService fridgeItemService;

    @Test
    void getAllFridgeItems_returnsOkAndList() throws Exception {
        FridgeItemDTO dto = new FridgeItemDTO(
                UUID.randomUUID(),
                "Milk",
                1,
                LocalDateTime.now(),
                LocalDate.now().plusDays(3),
                null,
                false,
                UUID.randomUUID(),
                "Dairy",
                UUID.randomUUID(),
                "Fridge"
        );

        given(fridgeItemService.getAllFridgeItems()).willReturn(List.of(dto));

        mockMvc.perform(get("/api/fridge/fridge-items")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Milk"));

    }

    @Test
    void getFridgeById_returnsOkAndItem() throws Exception {
        UUID id = UUID.randomUUID();
        FridgeItemDTO dto = new FridgeItemDTO(
                id,
                "Beef",
                2,
                LocalDateTime.now(),
                LocalDate.now().plusDays(5),
                null,
                false,
                UUID.randomUUID(),
                "Meats",
                UUID.randomUUID(),
                "Freezer"
        );

        given(fridgeItemService.getFridgeItemById(id)).willReturn(dto);

        mockMvc.perform(get("/api/fridge/fridge-items/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.name").value("Beef"));
    }
}
