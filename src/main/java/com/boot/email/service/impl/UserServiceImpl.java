package com.boot.email.service.impl;

import com.boot.email.domains.Confirmation;
import com.boot.email.domains.User;
import com.boot.email.repository.ConfirmationRepository;
import com.boot.email.repository.UserRepository;
import com.boot.email.service.EmailService;
import com.boot.email.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ConfirmationRepository confirmationRepository;

    private  final EmailService emailService;

    @Override
    public User saveUser(User user) {
        if (userRepository.existsByEmail( user.getEmail() )) {
            throw new RuntimeException( "Email already exists " );
        }
        user.setIsEnable( false );
        userRepository.save( user );

        Confirmation confirmation = new Confirmation(user);
        confirmationRepository.save( confirmation );

        //emailService.sendSimpleMailMessage( user.getName() , user.getEmail(), confirmation.getToken());
        emailService.sendMimlMessageWithAtachments( user.getName() , user.getEmail(), confirmation.getToken());
        return user;
    }

    @Override
    public Boolean verifyToken(String token) {
        Confirmation confirmation = confirmationRepository.findByToken( token );
        User user = userRepository.findByEmailIgnoreCase( confirmation.getUser().getEmail() );
        user.setIsEnable( true );
        userRepository.save( user );
        //confirmationRepository.delete( confirmation );
        return Boolean.TRUE;
    }
}
