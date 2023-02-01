package com.blasarcesh.s3test.repositories;

import com.blasarcesh.s3test.entities.ImagesGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagesGroupRepository extends JpaRepository<ImagesGroup, Integer> {
    @Query("select i from ImagesGroup i order by i.fecha DESC")
    List<ImagesGroup> findByOrderByFechaDesc();
}