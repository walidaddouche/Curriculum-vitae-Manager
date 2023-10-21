package com.example.CvHandler.service;

import com.example.CvHandler.model.Candidat;
import com.example.CvHandler.repository.CandidatRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CandidatServiceTest {

    @InjectMocks
    private CandidatService candidatService;

    @Mock
    private CandidatRepository candidatRepository;

    @Test
    public void testSaveCandidat() {
        Candidat candidat = new Candidat();
        candidat.setId(1L);
        when(candidatRepository.save(candidat)).thenReturn(candidat);

        Candidat savedCandidat = candidatService.saveCandidat(candidat);
        assertEquals(1L, savedCandidat.getId());
    }

    @Test
    public void testGetCandidatById() {
        Long candidatId = 1L;
        Candidat candidat = new Candidat();
        candidat.setId(candidatId);
        when(candidatRepository.findById(candidatId)).thenReturn(Optional.of(candidat));

        Optional<Candidat> foundCandidat = candidatService.getCandidatById(candidatId);
        assertEquals(candidatId, foundCandidat.get().getId());
    }

    @Test
    public void testGetAllCandidats() {
        List<Candidat> candidats = new ArrayList<>();
        candidats.add(new Candidat());
        candidats.add(new Candidat());
        when(candidatRepository.findAll()).thenReturn(candidats);

        Iterable<Candidat> allCandidats = candidatService.getAllCandidats();
        assertNotNull(allCandidats);
        assertEquals(2, ((List<Candidat>) allCandidats).size());
    }

    @Test
    public void testDeleteCandidat() {
        Long candidatId = 1L;
        candidatService.deleteCandidat(candidatId);
        Mockito.verify(candidatRepository).deleteById(candidatId);
    }
}
