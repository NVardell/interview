package com.interview.feature;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Feature Resource Test Controller
 *
 * @author NV
 * @since 1/9/2024
 */
@Log4j2
@RestController public class FeatureResource {
    @GetMapping("api/feature") public String getFeature() {
        log.info("[API] GET - Feature");
        return "[API] GET - Feature";
    }
}
