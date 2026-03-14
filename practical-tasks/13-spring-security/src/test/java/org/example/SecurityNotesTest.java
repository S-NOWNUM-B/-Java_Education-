package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * <h1>Тестирование безопасности</h1>
 * 
 * <p>Мы проверяем, что фильтры безопасности корректно ограничивают доступ
 * в зависимости от роли пользователя.</p>
 */
@SpringBootTest
@AutoConfigureMockMvc
public class SecurityNotesTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testPublicAccess() throws Exception {
        mockMvc.perform(get("/public"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testAdminAccessForbiddenForUser() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testAdminAccessAllowedForAdmin() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk());
    }
}
