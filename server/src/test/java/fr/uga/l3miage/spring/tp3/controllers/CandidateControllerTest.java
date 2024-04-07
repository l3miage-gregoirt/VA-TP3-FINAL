package fr.uga.l3miage.spring.tp3.controllers;
import fr.uga.l3miage.spring.tp3.components.CandidateComponent;
import fr.uga.l3miage.spring.tp3.repositories.ExamRepository;
import fr.uga.l3miage.spring.tp3.responses.EcosSessionProgrammationResponse;
import org.springframework.http.*;
import fr.uga.l3miage.spring.tp3.repositories.CandidateRepository;
import fr.uga.l3miage.spring.tp3.request.SessionCreationRequest;
import fr.uga.l3miage.spring.tp3.request.SessionProgrammationCreationRequest;
import fr.uga.l3miage.spring.tp3.request.SessionProgrammationStepCreationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.time.LocalDateTime;
import java.util.HashSet;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class CandidateControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ExamRepository examRepository;

    @SpyBean
    private CandidateComponent candidateComponent;

    @Test
    void canCreateSessionExam() {
        //given
        final HttpHeaders headers = new HttpHeaders();

        final SessionProgrammationStepCreationRequest request1 = SessionProgrammationStepCreationRequest
                .builder()
                .id(12L)
                .code("123456")
                .description("consigne examen")
                .dateTime(LocalDateTime.now())
                .build();


        final SessionProgrammationCreationRequest request2 = SessionProgrammationCreationRequest
                .builder()
                .id(12L)
                .label("Greg")
                .steps(new HashSet<>())
                .build();

        final SessionCreationRequest request3 = SessionCreationRequest
                .builder()
                .name("session1")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .examsId(new HashSet<>())
                .ecosSessionProgrammation(request2)
                .build();





        // when
        ResponseEntity<EcosSessionProgrammationResponse> response = testRestTemplate.exchange("/api/session/create", HttpMethod.POST, new HttpEntity<>(request3, headers), EcosSessionProgrammationResponse.class);


        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);// ne fonctionne pas


    }


}
