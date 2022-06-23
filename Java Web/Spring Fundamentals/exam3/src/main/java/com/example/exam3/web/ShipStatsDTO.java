package com.example.exam3.web;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShipStatsDTO {
    String name;
    Integer health;
    Integer power;
}
