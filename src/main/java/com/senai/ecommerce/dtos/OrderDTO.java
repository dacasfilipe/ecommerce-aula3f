package com.senai.ecommerce.dtos;

import com.senai.ecommerce.entities.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    private Long id;
    private Instant moment;
    private OrderStatus status;
}
