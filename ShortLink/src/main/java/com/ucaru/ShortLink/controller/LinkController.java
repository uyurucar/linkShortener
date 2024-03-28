package com.ucaru.ShortLink.controller;

import com.ucaru.ShortLink.dao.LinkRepository;
import com.ucaru.ShortLink.entity.ShortLink;
import com.ucaru.ShortLink.service.LinkService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Controller
public class LinkController {

    private String currentUrl;
    private LinkService linkService;

    @Autowired
    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/")
    //@ResponseBody
    public String firstPage(Model theModel, HttpServletRequest request)
    {
        currentUrl = request.getRequestURL().toString();
        ShortLink link = new ShortLink();
        theModel.addAttribute("shortLink",link);
        return "first-page";
    }

    @PostMapping("/saved")
    public String savedPage(@ModelAttribute("shortLink") ShortLink link, Model theModel)
    {
        theModel.addAttribute("currentURL",currentUrl);
        link = linkService.save(link);
        return "saved-page";
    }

    @GetMapping("/{shortLink}")
    public ResponseEntity<Object> goToActualLink(@PathVariable String shortLink) throws URISyntaxException {
        
        List<ShortLink> link = linkService.find(shortLink);
        if(link.size() == 0) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        String tempLink = link.get(0).getActualLink();
        URI actualLink = new URI(tempLink);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(actualLink);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }
}
