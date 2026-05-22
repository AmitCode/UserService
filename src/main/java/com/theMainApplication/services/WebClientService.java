package com.theMainApplication.services;

import com.theMainApplication.dtos.request.EmailRequest;
import com.theMainApplication.dtos.response.EmailServiceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientService {
    public static ResponseEntity<EmailServiceResponse> callNotificationServiceEmail(EmailRequest request,
                                                                                    WebClient client){
        return client.post()
                .uri("email/sendMail")
                .bodyValue(request)
                .retrieve()
                .toEntity(EmailServiceResponse.class)
                .block();
    }
}
