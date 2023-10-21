package com.example.CvHandler.service;

import com.example.CvHandler.model.CV;
import com.example.CvHandler.repository.CvRepository;
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
public class CVServiceTest {

    @InjectMocks
    private CVService cvService;

    @Mock
    private CvRepository cvRepository;

    @Test
    public void testSaveCV() {
        CV cv = new CV();
        cv.setId(1L);
        when(cvRepository.save(cv)).thenReturn(cv);

        CV savedCV = cvService.saveCV(cv);
        assertEquals(1L, savedCV.getId());
    }

    @Test
    public void testGetCVById() {
        Long cvId = 1L;
        CV cv = new CV();
        cv.setId(cvId);
        when(cvRepository.findById(cvId)).thenReturn(Optional.of(cv));

        Optional<CV> foundCV = cvService.getCVById(cvId);
        assertEquals(cvId, foundCV.get().getId());
    }

    @Test
    public void testGetAllCVs() {
        List<CV> cvs = new ArrayList<>();
        cvs.add(new CV());
        cvs.add(new CV());
        when(cvRepository.findAll()).thenReturn(cvs);

        Iterable<CV> allCVs = cvService.getAllCVs();
        assertNotNull(allCVs);
        assertEquals(2, ((List<CV>) allCVs).size());
    }

    @Test
    public void testDeleteCV() {
        Long cvId = 1L;
        cvService.deleteCV(cvId);
        Mockito.verify(cvRepository).deleteById(cvId);
    }
}
