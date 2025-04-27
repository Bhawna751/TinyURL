package com.example.demo.repository;
import com.example.demo.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends MongoRepository<Url,String> {
    Url findByShortUrl(String shortUrl);
    boolean existsByShortUrl(String shortUrl);
    Url findByOriginalUrl(String originalUrl);
}
