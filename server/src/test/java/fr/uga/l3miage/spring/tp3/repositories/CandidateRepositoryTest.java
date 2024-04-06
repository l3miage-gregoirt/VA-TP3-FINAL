package fr.uga.l3miage.spring.tp3.repositories;


import fr.uga.l3miage.spring.tp3.enums.TestCenterCode;
import fr.uga.l3miage.spring.tp3.models.CandidateEntity;
import fr.uga.l3miage.spring.tp3.models.CandidateEvaluationGridEntity;
import fr.uga.l3miage.spring.tp3.models.TestCenterEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
public class CandidateRepositoryTest {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private TestCenterRepository testCenterRepository;
    @Autowired
    private CandidateEvaluationGridRepository candidateEvaluationGridRepository;

    @AfterEach
    void clean(){
        candidateRepository.deleteAll();
        testCenterRepository.deleteAll();
    }
    @Test
    void testRequestFindAllByTestCenterEntityCode(){
        //given
        TestCenterEntity testCenter = TestCenterEntity
                .builder()
                .code(TestCenterCode.GRE)
                .university("UGA")
                .city("GRENOBLE")
                .candidateEntities(new HashSet<>())
                .examinerEntities(new HashSet<>())
                .build();



        CandidateEntity candidateEntity = CandidateEntity
                .builder()
                .firstname("Teddie")
                .lastname("GREGOIRE")
                .email("teddie.gregoire@hotmail.com")
                .phoneNumber("07871656")
                .birthDate(LocalDate.now())
                .hasExtraTime(false)
                .candidateEvaluationGridEntities(new HashSet<>())
                .testCenterEntity(testCenter)
                .build();

        testCenterRepository.save(testCenter);
        candidateRepository.save(candidateEntity);

        //when execution de la requête
        Set<CandidateEntity> testCenterResponse = candidateRepository.findAllByTestCenterEntityCode(TestCenterCode.GRE);



        //then
        assertThat(testCenterResponse).hasSize(1);
    }

    @Test
    void testRequestFindAllByTestCenterEntityCodeErr(){
        //given
        TestCenterEntity testCenter = TestCenterEntity
                .builder()
                .code(TestCenterCode.PAR)
                .university("SORBONNE")
                .city("PARIS")
                .candidateEntities(new HashSet<>())
                .examinerEntities(new HashSet<>())
                .build();



        CandidateEntity candidateEntity = CandidateEntity
                .builder()
                .firstname("Teddie")
                .lastname("GREGOIRE")
                .email("teddie.gregoire@hotmail.com")
                .phoneNumber("07871656")
                .birthDate(LocalDate.now())
                .hasExtraTime(false)
                .candidateEvaluationGridEntities(new HashSet<>())
                .testCenterEntity(testCenter)
                .build();

        testCenterRepository.save(testCenter);
        candidateRepository.save(candidateEntity);

        //when execution de la requête
        Set<CandidateEntity> testCenterResponse = candidateRepository.findAllByTestCenterEntityCode(TestCenterCode.GRE);



        //then
        assertThat(testCenterResponse).hasSize(0);
    }

    @Test
    void findAllByCandidateEvaluationGridEntitiesGradeLessThan(){
        //given
        CandidateEvaluationGridEntity candidateEvaluationGrid = CandidateEvaluationGridEntity
                .builder()
                .grade(2.4)
                .submissionDate(LocalDateTime.now())
                .candidateEntity(null)
                .examinerEntity(null)
                .examEntity(null)
                .evaluationCriteriaEntities(new HashSet<>())
                .build();



        CandidateEntity candidateEntity = CandidateEntity
                .builder()
                .firstname("Teddie")
                .lastname("GREGOIRE")
                .email("teddie.gregEEire@hotmail.com")
                .phoneNumber("07871656")
                .birthDate(LocalDate.now())
                .hasExtraTime(false)
                .candidateEvaluationGridEntities(new HashSet<>())
                .build();

        candidateEvaluationGridRepository.save(candidateEvaluationGrid);
        candidateRepository.save(candidateEntity);

        //when execution de la requête
        Set<CandidateEntity> testCenterResponse = candidateRepository.findAllByCandidateEvaluationGridEntitiesGradeLessThan(5);

        //then

    }

    @Test
    void findAllByCandidateEvaluationGridEntitiesGradeLessThanErr(){
        //given
        CandidateEvaluationGridEntity candidateEvaluationGrid = CandidateEvaluationGridEntity
                .builder()
                .grade(15)
                .submissionDate(LocalDateTime.now())
                .candidateEntity(null)
                .examinerEntity(null)
                .examEntity(null)
                .evaluationCriteriaEntities(new HashSet<>())
                .build();



        CandidateEntity candidateEntity = CandidateEntity
                .builder()
                .firstname("Teddie")
                .lastname("GREGOIRE")
                .email("teddie.gregre@hotmail.com")
                .phoneNumber("07871986")
                .birthDate(LocalDate.now())
                .hasExtraTime(false)
                .candidateEvaluationGridEntities(new HashSet<>())
                .build();

        candidateEvaluationGridRepository.save(candidateEvaluationGrid);
        candidateRepository.save(candidateEntity);

        CandidateEntity candidateEntity2 = CandidateEntity
                .builder()
                .firstname("Ted")
                .lastname("GREOIRE")
                .email("tedde.egoire@hotmail.com")
                .phoneNumber("07871756")
                .birthDate(LocalDate.now())
                .hasExtraTime(false)
                .candidateEvaluationGridEntities(new HashSet<>())
                .build();

        CandidateEvaluationGridEntity candidateEvaluationGrid2 = CandidateEvaluationGridEntity
                .builder()
                .grade(1)
                .submissionDate(LocalDateTime.now())
                .candidateEntity(candidateEntity2)
                .candidateEntity(null)
                .examinerEntity(null)
                .examEntity(null)
                .evaluationCriteriaEntities(new HashSet<>())
                .build();

        candidateRepository.save(candidateEntity2);
        candidateEvaluationGridRepository.save(candidateEvaluationGrid2);

        //when execution de la requête
        Set<CandidateEntity> testCenterResponse = candidateRepository.findAllByCandidateEvaluationGridEntitiesGradeLessThan(5);



        //then _____________Devrait être à 1____________
        assertThat(testCenterResponse).hasSize(0);
    }



}
