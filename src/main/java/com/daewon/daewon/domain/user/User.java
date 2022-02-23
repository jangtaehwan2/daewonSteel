package com.daewon.daewon.domain.user;

import lombok.*;

import javax.persistence.*;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(nullable = false)
    private String userName;

    @NonNull
    @Column(nullable = false)
    private String userPassword;

    @Column(columnDefinition = "tinyint(1) default false")
    private boolean isAdmin;
}
