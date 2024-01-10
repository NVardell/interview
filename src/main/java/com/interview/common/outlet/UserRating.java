package com.interview.common.outlet;

import lombok.Builder;
import lombok.Data;

/**
 * User Rating Model
 *
 * @author NV
 * @since 1/9/2024
 */
@Data @Builder public class UserRating {
    private double average_rating;
    private int votes;
}
