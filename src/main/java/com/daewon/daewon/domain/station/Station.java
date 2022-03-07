package com.daewon.daewon.domain.station;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(nullable = false, unique = true)
    private String name;

    public void updateName(String newName) {
        this.name = newName;
    }
}
