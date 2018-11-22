package com.hz.learnboot.webflux.test.handler;

import com.hz.learnboot.webflux.test.domain.City;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hezhao on 2018-07-12 20:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityHandlerTest {

    @Autowired
    private WebTestClient webClient;

    private static Map<String, City> cityMap = new HashMap<>();

    @BeforeClass
    public static void setup() throws Exception {
        City wl = new City();
        wl.setId(1L);
        wl.setProvinceId(2L);
        wl.setCityName("WL");
        wl.setDescription("WL IS GOOD");
        cityMap.put("WL", wl);
    }

    @Test
    public void testSave() throws Exception {

        City expectCity = webClient.post().uri("/city")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(cityMap.get("WL")))
                .exchange()
                .expectStatus().isOk()
                .expectBody(City.class).returnResult().getResponseBody();

        Assert.assertNotNull(expectCity);
        Assert.assertEquals(expectCity.getId(), cityMap.get("WL").getId());
        Assert.assertEquals(expectCity.getCityName(), cityMap.get("WL").getCityName());
    }

    @Test
    public void testFindAll() throws Exception {

        Flux<City> expectCity = webClient.get().uri("/city")
                .exchange()
                .expectStatus().isOk().returnResult(City.class).getResponseBody();

        City city = expectCity.blockFirst();
        System.out.println(city);
    }

}