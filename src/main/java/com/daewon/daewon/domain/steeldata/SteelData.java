package com.daewon.daewon.domain.steeldata;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class SteelData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Column(nullable = false)
    private String station;

    @NonNull
    @Column(nullable = false)
    private long weight;

    @NonNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(columnDefinition = "tinyint(1) default false")
    private boolean deleted;
}
