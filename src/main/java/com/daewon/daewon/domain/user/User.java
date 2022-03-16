package com.daewon.daewon.domain.user;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(nullable = false, unique = true)
    private String userName;

    @NonNull
    @Column(nullable = false)
    private String userPassword;

    @Column(columnDefinition = "tinyint(1) default false")
    private boolean isAdmin;

    public void updatePassword(String newPassword) {
        this.userPassword = newPassword;
    }
    public boolean isAdmin() { return this.isAdmin; }
}
