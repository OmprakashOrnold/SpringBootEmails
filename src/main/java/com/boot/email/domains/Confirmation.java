package com.boot.email.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
@Entity
@Table(name = "confirmation")
public class Confirmation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long  Id;

    private  String token;

    @Temporal( TemporalType.TIMESTAMP )
    @CreatedDate
    private LocalDateTime creadtedDate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private  User user;


    public  Confirmation(User user){
        this.user = user;
        this.creadtedDate = LocalDateTime.now();
        this.token = UUID.randomUUID().toString();
    }

}
