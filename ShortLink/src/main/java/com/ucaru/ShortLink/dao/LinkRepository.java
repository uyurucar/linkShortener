package com.ucaru.ShortLink.dao;

import com.ucaru.ShortLink.entity.ShortLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LinkRepository extends JpaRepository<ShortLink,Integer> {

    List<ShortLink> findByShortLink(String shortLink);
    List<ShortLink> findByActualLink(String shortLink);
}
