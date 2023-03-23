package com.company.jewelrystore.repository;

import com.company.jewelrystore.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image,String> {
    Optional<Image> findById(String id);
}
