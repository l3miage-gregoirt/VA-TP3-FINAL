package fr.uga.l3miage.spring.tp3.services;

import fr.uga.l3miage.spring.tp3.enums.TestCenterCode;
import fr.uga.l3miage.spring.tp3.models.*;
import fr.uga.l3miage.spring.tp3.repositories.CandidateEvaluationGridRepository;
import fr.uga.l3miage.spring.tp3.repositories.CandidateRepository;
import fr.uga.l3miage.spring.tp3.repositories.ExamRepository;
import fr.uga.l3miage.spring.tp3.repositories.TestCenterRepository;
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
class CandidateServiceTest {

    @MockBean
    private CandidateRepository candidateRepository;
    @MockBean
    private ExamRepository examRepository;
    @MockBean
    private TestCenterRepository testCenterRepository;
    @MockBean
    private CandidateEvaluationGridRepository candidateEvaluationGridRepository;


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

        when(candidateRepository.findById(12L)).thenReturn(Optional.of(candidateEnti));

        // When
        Double average = candidateService.getCandidateAverage(12L);

        // Then
        //assertEquals(5, average, 0.01);
    }
}


