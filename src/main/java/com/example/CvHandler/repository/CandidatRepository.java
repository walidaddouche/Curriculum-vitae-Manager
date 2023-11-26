package com.example.CvHandler.repository;

import com.example.CvHandler.model.Candidat;
import com.example.CvHandler.summary.CandidatProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidatRepository extends PagingAndSortingRepository< Candidat,Long> , CrudRepository<Candidat,Long> {
    @Query("SELECT c.id as id, c.nom as nom, c.prenom as prenom, c.email as email FROM Candidat c")
    Page<CandidatProjection> getAllCandidatWithoutDetails(Pageable pageable);

    Page<CandidatProjection> findByPrenomContainingIgnoreCase(Pageable pageable,String Prenom);
    Page<CandidatProjection> findByNomContainingAndPrenomContainingIgnoreCase(Pageable pageable,String Nom , String Prenom);
    Page<CandidatProjection> findByNomContainingIgnoreCase(Pageable pageable,String Nom);
    Optional<Candidat> findByEmail(String email);

    Optional<Candidat> deleteByEmail(String email);




}
