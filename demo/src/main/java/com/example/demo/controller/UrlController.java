package com.example.demo.controller;

import com.example.demo.dto.UrlDto;
import com.example.demo.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("api/url")
public class UrlController {
    @Autowired
    private UrlService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<String> createShortUrl(@RequestBody UrlDto urlDto){
        String shortUrl = urlService.createShortUrl(urlDto);
        return ResponseEntity.ok(shortUrl);
    }

    @GetMapping("/{shortUrl}")
    public RedirectView redirectToOriginalUrl(@PathVariable String shortUrl){
        String originalUrl = urlService.getOriginalUrl(shortUrl);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(originalUrl);
        return redirectView;
    }

    @GetMapping("/info/{shortUrl}")
    public ResponseEntity<UrlDto> getUrlInfo(@PathVariable String shortUrl){
        String originalUrl = urlService.getOriginalUrl(shortUrl);
        UrlDto urlDto = new UrlDto(originalUrl,null);
        return ResponseEntity.ok(urlDto);
    }
}
