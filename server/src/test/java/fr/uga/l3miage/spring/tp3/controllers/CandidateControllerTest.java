package fr.uga.l3miage.spring.tp3.controllers;
import fr.uga.l3miage.spring.tp3.components.CandidateComponent;
import org.springframework.http.HttpHeaders;
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

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class CandidateControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private CandidateRepository candidateRepository;

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
    }


}
