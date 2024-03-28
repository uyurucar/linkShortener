package com.ucaru.ShortLink.service;

import com.ucaru.ShortLink.dao.LinkRepository;
import com.ucaru.ShortLink.entity.ShortLink;
import com.ucaru.ShortLink.util.ShortLinkGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ucaru.ShortLink.util.ShortLinkGenerator.generate;

@Service
public class LinkService {

    private LinkRepository linkRepository;

    @Autowired
    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public ShortLink save(ShortLink link)
    {
        List<ShortLink> dbLink = linkRepository.findByActualLink(link.getActualLink());
        //todo: create cache and check it first
        if(dbLink.isEmpty())
        {
            System.out.println("### db is empty ###");
            String shortenedLink = generate(link.getActualLink());
            link.setShortLink(shortenedLink);
            linkRepository.save(link);
        }
        else
        {
            System.out.println("### db is not empty ###");
            link.setShortLink(dbLink.get(0).getShortLink());
        }
        return link;
    }

    //i actually want to return single object instead of a list
    //
    public List<ShortLink> find(String shortLink)
    {
        //todo: create cache
        List<ShortLink> links = linkRepository.findByShortLink(shortLink);
        return links;
    }
}
