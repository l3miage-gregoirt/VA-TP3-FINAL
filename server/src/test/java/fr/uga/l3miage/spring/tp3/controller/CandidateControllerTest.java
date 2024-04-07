package fr.uga.l3miage.spring.tp3.controller;

import fr.uga.l3miage.spring.tp3.enums.TestCenterCode;
import fr.uga.l3miage.spring.tp3.models.CandidateEntity;
import fr.uga.l3miage.spring.tp3.models.CandidateEvaluationGridEntity;
import fr.uga.l3miage.spring.tp3.models.ExamEntity;
import fr.uga.l3miage.spring.tp3.models.TestCenterEntity;
import fr.uga.l3miage.spring.tp3.repositories.CandidateEvaluationGridRepository;
import fr.uga.l3miage.spring.tp3.repositories.CandidateRepository;
import fr.uga.l3miage.spring.tp3.repositories.ExamRepository;
import fr.uga.l3miage.spring.tp3.repositories.TestCenterRepository;
import fr.uga.l3miage.spring.tp3.services.CandidateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class CandidateControllerTest {


    @MockBean
    private CandidateRepository candidateRepository;
    @MockBean
    private ExamRepository examRepository;
    @MockBean
    private TestCenterRepository testCenterRepository;
    @MockBean
    private CandidateEvaluationGridRepository candidateEvaluationGridRepository;
    @MockBean
    private CandidateController candidateController;


    @Autowired
    private CandidateService candidateService;



    @Test
    void getCandidateAverageTest(){
        //given

        TestCenterEntity testCenter = TestCenterEntity
                .builder()
                .code(TestCenterCode.GRE)
                .university("UGA")
                .city("GRENOBLE")
                .candidateEntities(new HashSet<>())
                .examinerEntities(new HashSet<>())
                .build();
        testCenterRepository.save(testCenter);

        CandidateEntity candidateEnti = CandidateEntity
                .builder()
                .id(12L)
                .firstname("Teddie")
                .lastname("GREGOIRE")
                .email("teddie.gregoire@hotmail.com")
                .phoneNumber("07871656")
                .birthDate(LocalDate.now())
                .hasExtraTime(false)
                .candidateEvaluationGridEntities(new HashSet<>())
                .testCenterEntity(testCenter)
                .build();
        candidateRepository.save(candidateEnti);

        ExamEntity examEnti = ExamEntity
                .builder()
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .name("JPA")
                .weight(2)
                .build();
        examRepository.save(examEnti);

        CandidateEvaluationGridEntity candidateEvaluationGridEntity = CandidateEvaluationGridEntity
                .builder()
                .grade(5)
                .submissionDate(LocalDateTime.now())
                .candidateEntity(candidateEnti)
                .examEntity(examEnti)
                .build();
        candidateEvaluationGridRepository.save(candidateEvaluationGridEntity);

        // Mocking candidateService.getCandidateAverage(candidateId) to return the expected average
        when(candidateService.getCandidateAverage(12L)).thenReturn(5.0);

        // When
        Double actualAverage = candidateController.getCandidateAverage(12L);

        // Then
        assertEquals(5.0, actualAverage);
    }
}
