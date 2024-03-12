package com.ucaru.ShortLink.controller;

import com.ucaru.ShortLink.dao.LinkRepository;
import com.ucaru.ShortLink.entity.ShortLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Controller
public class LinkController {

    LinkRepository repo;

    @Autowired
    public LinkController(LinkRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    @ResponseBody
    public String Test()
    {
        ShortLink link = new ShortLink();

        link.setActualLink("http://www.google.com");
        link.setShortLink("h23T13aBc");
        repo.save(link);
        return "hello";
    }

    @GetMapping("/test")
    @ResponseBody
    public String Test2()
    {
        List<ShortLink> links = repo.findByShortLink("dfgf");

        return String.valueOf(links.size());
    }

    @GetMapping("/{shortLink}")
    public ResponseEntity<Object> goToActualLink(@PathVariable String shortLink) throws URISyntaxException {
        List<ShortLink> link = repo.findByShortLink(shortLink);
        if(link.size() == 0) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        String tempLink = link.get(0).getActualLink();
        URI actualLink = new URI(tempLink);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(actualLink);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }
}
