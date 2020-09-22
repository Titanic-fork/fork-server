package com.titanic.fork.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.titanic.fork.service.account.AccountService;
import com.titanic.fork.utils.TestEnum;
import com.titanic.fork.web.dto.request.account.NewPhoneNumberRequest;
import com.titanic.fork.web.login.LoginEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AccountController.class)
public class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AccountService accountService;

    private final String REQUEST_MAPPING = "/account";

    @DisplayName("로그인된 상태에서 핸드폰 번호를 수정하는 API 테스트")
    @ParameterizedTest
    @CsvSource({"guswns1651@gmail.com,010-1234-5678"})
    void 핸드폰번호_수정API를_테스트한다(String email, String phoneNumber) throws Exception {
        // given
        NewPhoneNumberRequest newPhoneNumberRequest = NewPhoneNumberRequest.of(email, phoneNumber);
        String requestUrl = REQUEST_MAPPING + "/phone-number";

        // when, then
        mockMvc.perform(put(requestUrl)
                .header(TestEnum.ORIGIN.getValue(), TestEnum.ALL.getValue())
                .header(LoginEnum.AUTHORIZATION.getValue(), TestEnum.JWT_TOKEN_EXAMPLE.getValue())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(newPhoneNumberRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
