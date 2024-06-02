package com.boot.email.resource;

import com.boot.email.domains.HttpResponse;
import com.boot.email.domains.User;
import com.boot.email.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserResource {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<HttpResponse> createUser(@RequestBody User user) {
        User newUser = userService.saveUser( user );
        return ResponseEntity.created( URI.create( "" ) ).body( HttpResponse.builder()
                .timestamp( LocalDateTime.now().toString() )
                .data( Map.of( "user", newUser ) )
                .message( "User Created" )
                .status( HttpStatus.CREATED )
                .statusCode( HttpStatus.CREATED.value() )
                .build() );
    }

    @GetMapping
    public ResponseEntity<HttpResponse> confimationUserAccount(@RequestParam("token") String token) {
        Boolean isSuccess = userService.verifyToken( token );
        return ResponseEntity.ok().body( HttpResponse.builder()
                .timestamp( LocalDateTime.now().toString() )
                .data( Map.of( "Success", isSuccess ) )
                .message( "Account verified" )
                .status( HttpStatus.CREATED )
                .statusCode( HttpStatus.CREATED.value() )
                .build() );
    }


}
