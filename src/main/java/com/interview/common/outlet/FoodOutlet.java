package com.interview.common.outlet;

import lombok.Builder;
import lombok.Data;

/**
 * Individual Food Outlet Model
 *
 * @author NV
 * @since 1/9/2024
 */
@Data @Builder public class FoodOutlet {
    private int id;
    private String name;
    private String city;
    private int estimated_cost;
    private UserRating user_rating;
}
