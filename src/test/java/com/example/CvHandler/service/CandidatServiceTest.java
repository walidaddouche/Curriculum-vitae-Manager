package com.example.CvHandler.service;

import com.example.CvHandler.model.Candidat;
import com.example.CvHandler.repository.CandidatRepository;
import org.junit.jupiter.api.Assertions;
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
        Candidat candidat = Candidat.builder().id(1L).build();
        when(candidatRepository.save(candidat)).thenReturn(candidat);

        Candidat savedCandidat = candidatService.saveCandidat(candidat);
        assertEquals(1L, savedCandidat.getId());
        assertEquals(candidat, savedCandidat);

    }

    @Test
    public void testUpdateCandidat() {
        Candidat existingCandidat = Candidat.builder()
                .id(1L)
                .nom("AncienNom")
                .prenom("AncienPrenom").email("mail@gmail.com")
                .build();


        Candidat updatedCandidat = Candidat.builder()
                .nom("NouveauNom")
                .prenom("NouveauPrenom")
                .build();

        // Configurez le repository mock pour retourner le candidat existant lorsqu'il est recherché par ID
        when(candidatRepository.findById(1L)).thenReturn(Optional.of(existingCandidat));

        // Appelez la méthode updateCandidat pour mettre à jour le candidat existant
        candidatService.updateCandidatAttributes(1L, updatedCandidat);

        // Vérifiez que la méthode save du repository a été appelée avec le candidat mis à jour
        Mockito.verify(candidatRepository).save(existingCandidat);

        assertEquals("NouveauNom", existingCandidat.getNom());
        assertEquals("NouveauPrenom", existingCandidat.getPrenom());
    }

    @Test
    public void testGetCandidatById() {
        Long candidatId = 1L;
        Candidat candidat = Candidat.builder().id(candidatId).build();
        when(candidatRepository.findById(candidatId)).thenReturn(Optional.of(candidat));

        Optional<Candidat> foundCandidat = candidatService.getCandidatById(candidatId);
        assertEquals(candidatId, foundCandidat.get().getId());
    }

    @Test
    public void testGetAllCandidats() {
        List<Candidat> candidats = new ArrayList<>();
        candidats.add(Candidat.builder().build());
        candidats.add(Candidat.builder().build());
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
