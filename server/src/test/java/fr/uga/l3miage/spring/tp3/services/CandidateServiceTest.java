package fr.uga.l3miage.spring.tp3.services;

import fr.uga.l3miage.spring.tp3.components.CandidateComponent;
import fr.uga.l3miage.spring.tp3.enums.TestCenterCode;
import fr.uga.l3miage.spring.tp3.models.*;
import fr.uga.l3miage.spring.tp3.repositories.CandidateRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class CandidateServiceTest {
    @Autowired
    private CandidateComponent candidateComponent;
    @MockBean
    private CandidateService candidateService;
    @MockBean
    private CandidateRepository candidateRepository;


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

        CandidateEntity candidateEnti = CandidateEntity
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

        when(candidateRepository.findById(anyLong())).thenReturn(Optional.of(candidateEnti));

        // When
        Double optionalCandidateEntity = candidateService.getCandidateAverage(anyLong());

        // Then
        assertThat(optionalCandidateEntity).isEqualTo(15);
    }
}

