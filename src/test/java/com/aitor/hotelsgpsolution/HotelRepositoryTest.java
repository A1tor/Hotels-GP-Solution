package com.aitor.hotelsgpsolution;

import com.aitor.hotelsgpsolution.model.Hotel;
import com.aitor.hotelsgpsolution.repository.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class HotelRepositoryTest {

    @Autowired
    private HotelRepository hotelRepository;

    @BeforeEach
    void clearAndFillDb() {
        hotelRepository.deleteAll();

        var h1 = new Hotel();
        h1.setName("Marriott Minsk");
        h1.setBrand("Marriott");
        h1.setCity("Minsk");
        h1.setCountry("Belarus");

        var h2 = new Hotel();
        h2.setName("Hilton Minsk");
        h2.setBrand("Hilton");
        h2.setCity("Minsk");
        h2.setCountry("Belarus");

        var h3 = new Hotel();
        h3.setName("Marriott Moscow");
        h3.setBrand("Marriott");
        h3.setCity("Moscow");
        h3.setCountry("Russia");

        var h4 = new Hotel();
        h4.setName("Hotel Travola");
        h4.setBrand("LocalBrand");
        h4.setCity("Travola");
        h4.setCountry("Travola");

        hotelRepository.saveAll(List.of(h1, h2, h3, h4));
    }

    @Test
    
    void testGetHistogramByBrand() {
        var result = hotelRepository.getHistgramByBrand();
        var histogram = parseMap(result);
        assertThat(histogram).hasSize(3); 
        assertThat(histogram.get("Marriott")).isEqualTo(2L);
        assertThat(histogram.get("Hilton")).isEqualTo(1L);
        assertThat(histogram.get("LocalBrand")).isEqualTo(1L);
    }

    @Test
    void testGetHistogramByCity() {
        var result = hotelRepository.getHistgramByCity();
        var histogram = parseMap(result);
        assertThat(histogram).containsKey("Minsk");
        assertThat(histogram).containsKey("Moscow");
        assertThat(histogram.get("Minsk")).isEqualTo(2L);
        assertThat(histogram.get("Moscow")).isEqualTo(1L);
        if (histogram.containsKey(null))
            assertThat(histogram.get(null)).isEqualTo(1L);
    }

    @Test
    void testGetHistogramByCountry() {
        var result = hotelRepository.getHistgramByCountry();
        var histogram = parseMap(result);
        assertThat(histogram).hasSize(3);
        assertThat(histogram.get("Belarus")).isEqualTo(2L);
        assertThat(histogram.get("Russia")).isEqualTo(1L);  
        assertThat(histogram.get("Travola")).isEqualTo(1L);
    }

    @Test
    void testFindAll() {
        assertThat(hotelRepository.findAll()).hasSize(4);
    }

    Map<String, Long> parseMap(List<Object[]> list){
        var returnMap = new HashMap<String, Long>();
        list.forEach(item -> returnMap.put((String) item[0], (Long) item[1]));
        return returnMap;
    }
}
