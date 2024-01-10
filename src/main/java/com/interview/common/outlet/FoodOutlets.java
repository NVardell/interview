package com.interview.common.outlet;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Food Outlets Model
 *
 * @author NV
 * @since 1/9/2024
 */
@Data @Builder public class FoodOutlets {
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<FoodOutlet> data;
}
