package com.web.scholarship.controllers;

import com.web.scholarship.models.Candidate;
import com.web.scholarship.models.mapper.models.Whoami;
import com.web.scholarship.services.CandidateService;
import lombok.AllArgsConstructor;
import org.springframework.security.config.web.servlet.headers.HeadersSecurityMarker;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class Security {
    CandidateService candidateService;

    @GetMapping("ping")
    public String ping(){
        return "pong";
    }

    @GetMapping("whoami")
    public Whoami whoami(@HeadersSecurityMarker Authentication auth){
        String username = auth.getName();
        return candidateService.auth(username);
    }
}
