package com.titanic.fork.web.controller.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.titanic.fork.service.account.RegisterService;
import com.titanic.fork.utils.TestEnum;
import com.titanic.fork.web.dto.request.account.RegisterRequestDto;
import com.titanic.fork.web.login.LoginEnum;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {RegisterController.class})
public class RegisterControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RegisterService registerService;

    private final String REQUEST_MAPPING = "/account";

    @ParameterizedTest
    @CsvSource({"guswns1653@gmail.com,password,hyunjun,010-7720-7957"})
    void 회원가입테스트한다(String email, String password, String name, String phoneNumber) throws Exception {
        /* given
         * serialize를 해서 content에 넣어야 함.
         */
        RegisterRequestDto registerRequestDto = RegisterRequestDto.of(email, password, name, phoneNumber);

        // when
        final ResultActions actions = mockMvc.perform(post(REQUEST_MAPPING)
                .header(TestEnum.ORIGIN.getValue(), TestEnum.ALL.getValue())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(registerRequestDto))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
        // then
        MvcResult mvcResult = actions
                .andExpect(status().isCreated())
                .andReturn();

        System.out.println(mvcResult.getResponse().getHeader(LoginEnum.AUTHORIZATION.getValue()));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
