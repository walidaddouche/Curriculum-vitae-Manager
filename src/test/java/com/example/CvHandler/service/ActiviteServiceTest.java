package com.example.CvHandler.service;

import com.example.CvHandler.model.Activite;
import com.example.CvHandler.repository.ActiviteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ActiviteServiceTest {

    @InjectMocks
    private ActiviteService activiteService;

    @Mock
    private ActiviteRepository activiteRepository;

    @Test
    public void testSaveActivite() {
        Activite activite = new Activite();
        activite.setId(1L);
        when(activiteRepository.save(activite)).thenReturn(activite);

        Activite savedActivite = activiteService.saveActivite(activite);
        assertEquals(1L, savedActivite.getId());
    }

    @Test
    public void testGetActiviteById() {
        Long activiteId = 1L;
        Activite activite = new Activite();
        activite.setId(activiteId);
        when(activiteRepository.findById(activiteId)).thenReturn(Optional.of(activite));

        Optional<Activite> foundActivite = activiteService.getActiviteById(activiteId);
        assertEquals(activiteId, foundActivite.get().getId());
    }

    @Test
    public void testGetAllActivites() {
        List<Activite> activites = new ArrayList<>();
        activites.add(new Activite());
        activites.add(new Activite());
        when(activiteRepository.findAll()).thenReturn(activites);

        Iterable<Activite> allActivites = activiteService.getAllActivites();
        assertNotNull(allActivites);
        assertEquals(2, ((List<Activite>) allActivites).size());
    }

    @Test
    public void testDeleteActivite() {
        Long activiteId = 1L;
        activiteService.deleteActivite(activiteId);
        Mockito.verify(activiteRepository).deleteById(activiteId);
    }
}
