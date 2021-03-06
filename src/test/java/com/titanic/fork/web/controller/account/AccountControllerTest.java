package com.titanic.fork.web.controller.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.titanic.fork.service.account.AccountService;
import com.titanic.fork.service.account.RegisterService;
import com.titanic.fork.utils.LocalEnum;
import com.titanic.fork.utils.LocalTestEnum;
import com.titanic.fork.web.dto.request.account.NewPhoneNumberRequest;
import com.titanic.fork.web.dto.request.account.RegisterRequest;
import com.titanic.fork.web.login.LoginEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AccountController.class)
public class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AccountService accountService;

    @MockBean
    RegisterService registerService;

    private final String REQUEST_MAPPING = "/account";
    private static final String token = LocalTestEnum.JWT_TOKEN_LOCAL_TEST_1.token;

    @ParameterizedTest
    @CsvSource({"localTest1@gmail.com,password,hyunjun,010-7720-7957"})
    void register(String email, String password, String name, String phoneNumber) throws Exception {
        /* given
         * serialize를 해서 content에 넣어야 함.
         */
        RegisterRequest registerRequest = RegisterRequest.of(email, password, name, phoneNumber);

        // when
        mockMvc.perform(post(REQUEST_MAPPING)
                .header(LocalEnum.ORIGIN.getValue(), LocalEnum.ALL.getValue())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(registerRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    @DisplayName("로그인된 상태에서 핸드폰 번호를 수정하는 API 테스트")
    @ParameterizedTest
    @CsvSource({"010-1234-5678"})
    void changePhoneNumber(String phoneNumber) throws Exception {
        // given
        NewPhoneNumberRequest newPhoneNumberRequest = NewPhoneNumberRequest.from(phoneNumber);
        String requestUrl = REQUEST_MAPPING + "/phone-number";

        // when, then
        mockMvc.perform(put(requestUrl)
                .header(LocalEnum.ORIGIN.getValue(), LocalEnum.ALL.getValue())
                .header(LoginEnum.AUTHORIZATION.getValue(), token)
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
