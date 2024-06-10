package com.soCompany.sunflower;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.yaml")
class SunflowerApplicationTests {

    @Value("${app.images.communities-directory-path}")
    private String bucket;

    @Test
    void bucketPathTest() {
        String expect = "E:\\database\\Sunflower\\Images\\Communities";
        assertEquals(bucket, expect);
    }

}
