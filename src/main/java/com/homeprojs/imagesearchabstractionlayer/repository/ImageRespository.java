package com.homeprojs.imagesearchabstractionlayer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yao Zhang
 * @date 2023-11-15
 * # @apiNote
 */
@Repository
public interface ImageRespository<Image, Long> extends JpaRepository<Image, Long> {

}
