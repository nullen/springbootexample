package com.myservice.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myservice.model.User;
import com.myservice.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    private static final long ID = 10L;
    private static final String NAME = "TEST NAME";
    private static final String PHONE = "123456789";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private UserService userService;

    @Test
    public void shouldIsAlvieReturnOK() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/user/ping")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(equalTo("User REST Service is alive.")));
    }

    @Test
    public void shouldCreateUserReturn201() throws Exception {
        when(this.userService.create(any(User.class))).thenReturn(10L);

        String json = mapper.writeValueAsString(mockUser());
        this.mvc.perform(MockMvcRequestBuilders.post("/user/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
    }

    @Test
    public void shouldFindUserByIdReturnUser() throws Exception {
        when(this.userService.findUserById(any(Long.class))).thenReturn(mockUser());

        this.mvc.perform(MockMvcRequestBuilders.get("/user/10")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.user.id").value(ID))
            .andExpect(jsonPath("$.user.name").value(NAME))
            .andExpect(jsonPath("$.user.phone").value(PHONE));
    }

    private User mockUser() {
        User user = new User();
        user.setId(ID);
        user.setName(NAME);
        user.setPhone(PHONE);

        return user;
    }
}
