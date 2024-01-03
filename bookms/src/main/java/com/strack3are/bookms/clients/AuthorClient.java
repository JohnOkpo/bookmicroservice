package com.strack3are.bookms.clients;

import com.strack3are.bookms.external.AuthorRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

//@FeignClient(name="AUTHOR-SERVICE")
@FeignClient( name="AUTHOR-SERVICE",  url="http://localhost:8082" )
public interface AuthorClient {

    @GetMapping("api/v1/author/authorForBook/{id}")
    AuthorRequest getAuthorForBook(@PathVariable("id") UUID authorId);
}
