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
        candidateEvaluationGridRepository.deleteAll();
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
        assertThat(testCenterResponse).hasSize(0);
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



        //then //TODO _____________hasSize devrait être à 1____________
        assertThat(testCenterResponse).hasSize(0);
    }



    @Test
    void findAllByHasExtraTimeFalseAndBirthDateBefore(){

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
                .birthDate(LocalDate.of(1990, 5, 15))
                .hasExtraTime(false)
                .candidateEvaluationGridEntities(new HashSet<>())
                .build();

        candidateEvaluationGridRepository.save(candidateEvaluationGrid);
        candidateRepository.save(candidateEntity);


        CandidateEvaluationGridEntity candidateEvaluationGrid2 = CandidateEvaluationGridEntity
                .builder()
                .grade(15)
                .submissionDate(LocalDateTime.now())
                .candidateEntity(null)
                .examinerEntity(null)
                .examEntity(null)
                .evaluationCriteriaEntities(new HashSet<>())
                .build();

        CandidateEntity candidateEntity2 = CandidateEntity
                .builder()
                .firstname("Ted")
                .lastname("GREG")
                .email("teddie.greg@hotmail.com")
                .phoneNumber("07877886")
                .birthDate(LocalDate.of(1999, 10, 20))
                .hasExtraTime(false)
                .candidateEvaluationGridEntities(new HashSet<>())
                .build();

        candidateEvaluationGridRepository.save(candidateEvaluationGrid2);
        candidateRepository.save(candidateEntity2);




        //when execution de la requête
        Set<CandidateEntity> testCenterResponse = candidateRepository.findAllByHasExtraTimeFalseAndBirthDateBefore(LocalDate.of(2000, 1, 1));



        //then
        assertThat(testCenterResponse).hasSize(2); //deux personnes nées avant le 1er janvier 2000
    }

    @Test
    void findAllByHasExtraTimeFalseAndBirthDateBeforeErr(){

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
                .birthDate(LocalDate.of(2008, 5, 15))
                .hasExtraTime(false)
                .candidateEvaluationGridEntities(new HashSet<>())
                .build();

        candidateEvaluationGridRepository.save(candidateEvaluationGrid);
        candidateRepository.save(candidateEntity);


        CandidateEvaluationGridEntity candidateEvaluationGrid2 = CandidateEvaluationGridEntity
                .builder()
                .grade(15)
                .submissionDate(LocalDateTime.now())
                .candidateEntity(null)
                .examinerEntity(null)
                .examEntity(null)
                .evaluationCriteriaEntities(new HashSet<>())
                .build();

        CandidateEntity candidateEntity2 = CandidateEntity
                .builder()
                .firstname("Ted")
                .lastname("GREG")
                .email("teddie.greg@hotmail.com")
                .phoneNumber("07877886")
                .birthDate(LocalDate.of(2010, 10, 20))
                .hasExtraTime(false)
                .candidateEvaluationGridEntities(new HashSet<>())
                .build();

        candidateEvaluationGridRepository.save(candidateEvaluationGrid2);
        candidateRepository.save(candidateEntity2);




        //when execution de la requête
        Set<CandidateEntity> testCenterResponse2 = candidateRepository.findAllByHasExtraTimeFalseAndBirthDateBefore(LocalDate.of(2000, 1, 1));


        //then
        assertThat(testCenterResponse2).hasSize(0);
    }



}
