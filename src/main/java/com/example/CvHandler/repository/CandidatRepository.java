package com.example.CvHandler.repository;

import com.example.CvHandler.model.Candidat;
import com.example.CvHandler.summary.CandidatProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidatRepository extends PagingAndSortingRepository< Candidat,Long> , CrudRepository<Candidat,Long> {
    @Query("SELECT c.id as id, c.nom as nom, c.prenom as prenom, c.email as email FROM Candidat c")
    Page<CandidatProjection> getAllCandidatWithoutDetails(Pageable pageable);

    @Query("SELECT c FROM Candidat c WHERE " +
            "LOWER(c.nom) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(c.prenom) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Candidat> searchCandidats(String search, Pageable pageable);

    List<Candidat> findByPrenomContainingIgnoreCase(String Prenom);
    List<Candidat> findByNomContainingAndPrenomContainingIgnoreCase(String Nom , String Prenom);
    List<Candidat> findByNomContainingIgnoreCase(String Nom);

}
