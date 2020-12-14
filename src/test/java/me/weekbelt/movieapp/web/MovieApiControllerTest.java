package me.weekbelt.movieapp.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.weekbelt.movieapp.domain.movie.form.MovieParam;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class MovieApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("Movie 정보 생성 API")
    void createMovie() throws Exception {
        //given
        String requestUri = "/movies";
        ClassPathResource classPathResource = new ClassPathResource("file/test_image/testImage.png");
        MockMultipartFile movieImage = new MockMultipartFile("movieImageMultipartFile", classPathResource.getFilename(), MediaType.IMAGE_PNG_VALUE, classPathResource.getInputStream());

        MovieParam movieParam = MovieParam.builder()
                .title("대부")
                .titleEnglish("The Godfather")
                .year(1972)
                .genres(List.of("범죄"))
                .rating(9.18)
                .runtime(175)
                .summary("마리오 푸조의 소설 《대부》를 원작으로 파라마운트 픽쳐스 사가 제작하고 프랜시스 포드 코폴라가 감독한 3부작 영화. ")
                .description("1947년 돈 코를레오네(Vito Corleone: 말론 브란도 분)의 호화 저택에서는 막내딸 코니(Connie Corleone Rizzi: 탈리아 샤이어 분)와 카를로(Carlo Rizzi: 지아니 루소 분)와의 초호화판 결혼식이 거행되고 있다. 시실리아에서의 이민과 모진 고생 끝에 미국 암흑가의 보스로 군림하는 마피아의 두목 돈 코를레오네. 재력과 조직력을 동원, 갖가지 고민을 호소하는 사람들의 문제를 해결해, 사람들은 그를 ‘대부(代父)’라 부른다. 돈 코를레오네는 9세때 그의 고향인 시실리아에서 가족 모두가 살해 당하고 오직 그만 살아남아 미국으로 도피하여 밑바닥 범죄 세계를 경험하면서 확고한 기반을 다지게 된다. 부모의 복수를 위해 시실리로 돌아와 조직적 범죄를 통해 비약적인 성공을 거두게 된다. 그러던 어느날 돈 코를레오네의 라이벌인 탓타리아 패밀리의 마약 밀매인 소롯소(Sollozzo: 알 레티어리 분)가 돈 코를레오네를 저격, 중상을 입히는데...")
                .build();

        // when
        ResultActions resultActions = mockMvc.perform(multipart(requestUri)
                .file(movieImage)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaTypes.HAL_JSON_VALUE)
                .content(objectMapper.writeValueAsString(movieParam)));

        // then
        resultActions
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
                .andExpect(jsonPath("movieId").exists())
                .andExpect(jsonPath("_links.self").exists())
                .andExpect(jsonPath("_links.query-events").exists())
                .andExpect(jsonPath("_links.update-event").exists())
        ;
    }

    @Test
    @DisplayName("Movie 정보 생성 API - 잘못된 요청")
    void createMovie_BadRequest() throws Exception {
        //given
        String requestUri = "/movies";
        ClassPathResource classPathResource = new ClassPathResource("file/test_image/testImage.png");
        MockMultipartFile movieImage = new MockMultipartFile("movieImageMultipartFile", classPathResource.getFilename(), MediaType.IMAGE_PNG_VALUE, classPathResource.getInputStream());

        MovieParam movieParam = MovieParam.builder()
                .title("")
                .titleEnglish("")
                .year(1972)
                .genres(List.of("범죄"))
                .rating(9.18)
                .runtime(-175)
                .summary("마리오 푸조의 소설 《대부》를 원작으로 파라마운트 픽쳐스 사가 제작하고 프랜시스 포드 코폴라가 감독한 3부작 영화. ")
                .description("1947년 돈 코를레오네(Vito Corleone: 말론 브란도 분)의 호화 저택에서는 막내딸 코니(Connie Corleone Rizzi: 탈리아 샤이어 분)와 카를로(Carlo Rizzi: 지아니 루소 분)와의 초호화판 결혼식이 거행되고 있다. 시실리아에서의 이민과 모진 고생 끝에 미국 암흑가의 보스로 군림하는 마피아의 두목 돈 코를레오네. 재력과 조직력을 동원, 갖가지 고민을 호소하는 사람들의 문제를 해결해, 사람들은 그를 ‘대부(代父)’라 부른다. 돈 코를레오네는 9세때 그의 고향인 시실리아에서 가족 모두가 살해 당하고 오직 그만 살아남아 미국으로 도피하여 밑바닥 범죄 세계를 경험하면서 확고한 기반을 다지게 된다. 부모의 복수를 위해 시실리로 돌아와 조직적 범죄를 통해 비약적인 성공을 거두게 된다. 그러던 어느날 돈 코를레오네의 라이벌인 탓타리아 패밀리의 마약 밀매인 소롯소(Sollozzo: 알 레티어리 분)가 돈 코를레오네를 저격, 중상을 입히는데...")
                .build();

        // when
        ResultActions resultActions = mockMvc.perform(multipart(requestUri)
                .file(movieImage)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaTypes.HAL_JSON_VALUE)
                .content(objectMapper.writeValueAsString(movieParam)));

        // then
        resultActions
                .andDo(print())
                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$[0].objectName").exists())
//                .andExpect(jsonPath("$[0].defaultMessage").exists())
//                .andExpect(jsonPath("$[0].code").exists())
        ;
    }
}