package kr.co.polycube.backendtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.polycube.backendtest.user.dto.PostUserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExceptionTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testBadRequest() throws Exception {
        PostUserRequest userRequest = new PostUserRequest();

        mockMvc.perform(MockMvcRequestBuilders.post("/users") // 잘못된 ID로 요청
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.reason").value("공백이 아닌 이름을 입력해 주세요."));
    }

    @Test
    public void testResourceNotFound() throws Exception {
        mockMvc.perform(get("/users/99999")) // 존재하지 않는 사용자 ID 요청
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.reason").value("존재하지 않는 id입니다."));
    }
}
