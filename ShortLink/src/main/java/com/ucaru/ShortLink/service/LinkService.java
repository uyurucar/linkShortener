package com.ucaru.ShortLink.service;

import com.ucaru.ShortLink.dao.LinkRepository;
import com.ucaru.ShortLink.entity.ShortLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkService {

    private LinkRepository linkRepository;

    @Autowired
    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public void save(ShortLink link)
    {
        linkRepository.save(link);
    }

    //i actually want to return single object instead of a list
    //
    public List<ShortLink> find(String shortLink)
    {
        List<ShortLink> links = linkRepository.findByShortLink(shortLink);
        return links;
    }
}
