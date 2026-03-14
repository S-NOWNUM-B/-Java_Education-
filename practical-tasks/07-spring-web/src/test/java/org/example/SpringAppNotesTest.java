package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import static org.junit.jupiter.api.Assertions.*;

/**
 * <h1>Интеграционное тестирование Spring Web</h1>
 * 
 * <p>Проверка того, что REST-контроллер корректно поднимается
 * и отвечает на HTTP-запросы.</p>
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringAppNotesTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testHelloEndpoint() {
        String url = "http://localhost:" + port + "/api/hello?name=Tester";
        String response = restTemplate.getForObject(url, String.class);
        
        assertTrue(response.contains("Привет, Tester"));
    }

    @Test
    public void testStatusEndpoint() {
        String url = "http://localhost:" + port + "/api/status";
        AppStatus status = restTemplate.getForObject(url, AppStatus.class);
        
        assertNotNull(status);
        assertEquals(200, status.getCode());
        assertEquals("Running", status.getStatus());
    }
}
