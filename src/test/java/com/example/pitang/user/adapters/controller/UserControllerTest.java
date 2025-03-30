package com.example.pitang.user.adapters.controller;

import com.example.pitang.user.application.dto.user.UserRequestPostDTO;
import com.example.pitang.user.application.usecase.CreateUserInteractor;
import com.example.pitang.user.domain.model.Car;
import com.example.pitang.user.domain.model.User;
import com.example.pitang.user.utils.CarMockConstants;
import com.example.pitang.user.utils.UserMockConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private CreateUserInteractor createUserInteractor;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private UserController userController;

    public static final String FIELD_BLANK = "";

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    void testCreateUser_Success() throws Exception {
        UserRequestPostDTO userRequestPostDTO = new UserRequestPostDTO(
                UserMockConstants.FIRST_NAME,
                UserMockConstants.LAST_NAME,
                UserMockConstants.EMAIL,
                UserMockConstants.BIRTHDAY,
                UserMockConstants.LOGIN,
                UserMockConstants.PASSWORD,
                UserMockConstants.PHONE,
                List.of(new Car(
                        CarMockConstants.CAR_YEAR,
                        CarMockConstants.CAR_LICENSE_PLATE,
                        CarMockConstants.CAR_MODEL,
                        CarMockConstants.CAR_COLOR
                ))
        );

        String userJson = objectMapper.writeValueAsString(userRequestPostDTO);

        mockMvc.perform(post("/user")
                        .contentType("application/json")
                        .content(userJson))
                .andExpect(status().isOk());

        verify(createUserInteractor).createUser(any(User.class));
    }

    @Test
    void testCreateUser_BadRequest() throws Exception {

        UserRequestPostDTO userRequestPostDTO = new UserRequestPostDTO(
                FIELD_BLANK, FIELD_BLANK, FIELD_BLANK, null, FIELD_BLANK, FIELD_BLANK, FIELD_BLANK, null
        );

        String userJson = objectMapper.writeValueAsString(userRequestPostDTO);

        mockMvc.perform(post("/user")
                        .contentType("application/json")
                        .content(userJson)
                )
                .andExpect(status().isBadRequest());
    }
}
