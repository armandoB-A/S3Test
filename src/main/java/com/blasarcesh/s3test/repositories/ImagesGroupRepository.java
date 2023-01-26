package com.blasarcesh.s3test.repositories;

import com.blasarcesh.s3test.entities.ImagesGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesGroupRepository extends JpaRepository<ImagesGroup, Integer> {
}