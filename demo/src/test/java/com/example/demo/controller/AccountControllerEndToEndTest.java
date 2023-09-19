package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerEndToEndTest {

    @Test
    void contextLoads() {
    }

    private Account account;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        account = Account.builder()
                .username("kuro")
                .role("User")
                .email("kuro@gmail.com")
                .bankAccountNumber("12345678")
                .isPaymentConfirmed(true)
                .paymentHistory(0)
                .activeOrders(0)
                .build();
    }

    @Test
    void test() throws  Exception{

        ObjectMapper objectMapper = new ObjectMapper();
        String expectedJson = objectMapper.writeValueAsString(account);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/account"))
                .andExpect((ResultMatcher) content().json(expectedJson));
    }

    @Test
    void createOrder() throws Exception{

    }
}