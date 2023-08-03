package com.tutorial.ecommercebackend.repository;

import com.tutorial.ecommercebackend.entity.product.Track;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
@Transactional
public interface TrackRepository extends JpaRepository<Track, Long> {
    //List<Track> findByProductId(Product product);
    //List<Track> findByProductId(Long productId);
    List<Track> findByProductId(Long product_id);


     void removeByIdIn(Collection<Long> ids);
}
