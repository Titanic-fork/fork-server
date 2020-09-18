package com.titanic.fork.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.titanic.fork.repository.AccountRepository;
import com.titanic.fork.service.account.AccountService;
import com.titanic.fork.web.dto.request.account.RegisterWantDto;
import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class WebMvcControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AccountService accountService;

//    @MockBean
//    AccountRepository accountRepository;

    @ParameterizedTest
    @CsvSource({"guswns1651@gmail.com,password,hyunjun,010-7720-7957"})
    void 회원가입테스트한다(String email, String password, String name, String phoneNumber) throws Exception {
        /* given
         * serialize를 해서 content에 넣어야 하는 듯?
         */
        RegisterWantDto registerWantDto = RegisterWantDto.of(email, password, name, phoneNumber);

        // when
        final ResultActions actions = mockMvc.perform(post("/account")
                .header("Origin", "*")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(registerWantDto))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
        // then
        actions
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
