package com.tutorial.ecommercebackend.repository;

import com.tutorial.ecommercebackend.entity.product.Track;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Transactional
public interface TrackRepository extends JpaRepository<Track, Long> {
     void removeByIdIn(Collection<Long> ids);


}
