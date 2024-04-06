package fr.uga.l3miage.spring.tp3.components;

import fr.uga.l3miage.spring.tp3.repositories.CandidateRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class CandidateComponentTest {
    @Autowired
    private CandidateComponent candidateComponent;
    @MockBean
    private CandidateRepository candidateRepository;

    @Test
    void getCandidateAverage(){
        //Given
        PlaylistEntity playlistEntity = PlaylistEntity.builder()
                .songEntities(Set.of())
                .description("test")
                .build();

        when(playlistRepository.findById(anyString())).thenReturn(Optional.of(playlistEntity));

        // when - then
        assertDoesNotThrow(()->playlistComponent.getPlaylist("test"));
    }
}
