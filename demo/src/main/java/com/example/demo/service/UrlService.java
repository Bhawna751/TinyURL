package com.example.demo.service;

import com.example.demo.dto.UrlDto;
import com.example.demo.exception.UrlNotFoundException;
import com.example.demo.model.Url;
import com.example.demo.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;

@Service
public class UrlService {
    @Autowired
    private UrlRepository urlRepository;

    @Autowired(required = false)
    private CounterService counterService;

    public String getOriginalUrl(String shortUrl){
        Url url = urlRepository.findByShortUrl(shortUrl);
        if(url == null){
            throw new UrlNotFoundException("Short url not found");
        }
        //checking for expiration
        if(url.getExpirationDate().isBefore(LocalDateTime.now())){
            urlRepository.delete(url);
            throw new UrlNotFoundException("Short URL has expired");
        }

        //update access count
        url.incrementAccessCount();
        urlRepository.save(url);
        return url.getOriginalUrl();
    }

    public String createShortUrl(UrlDto urlDto){
        String originalUrl = urlDto.getUrl();
        Url existingUrl = urlRepository.findByOriginalUrl(originalUrl);
        if(existingUrl != null){
            return existingUrl.getShortUrl();
        }

        long counter = counterService.getNextCount();
        //short url generation
        String shortUrl = Base64.getUrlEncoder().withoutPadding().encodeToString(Long.toString(counter).getBytes());

        //set expiration date (default:30 days from now)
        LocalDateTime expirationDate = urlDto.getExpirationDate();
        if(expirationDate == null){
            expirationDate = LocalDateTime.now().plusDays(30);
        }

        //saving to db
        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortUrl(shortUrl);
        url.setCreationDate(LocalDateTime.now());
        url.setExpirationDate(expirationDate);
        urlRepository.save(url);

        return shortUrl;
    }

}
