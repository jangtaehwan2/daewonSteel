package com.daewon.daewon.entity;

import com.daewon.daewon.domain.station.Station;
import com.daewon.daewon.domain.steeldata.SteelData;
import com.daewon.daewon.domain.user.User;
import com.daewon.daewon.repository.StationRepository;
import com.daewon.daewon.repository.SteelDataRepository;
import com.daewon.daewon.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class RepositoryTest {

    @Autowired
    StationRepository stationRepository;
    @Autowired
    SteelDataRepository steelDataRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("findByName, findById Test")
    public void StationTest() {
        //given
        Station station = new Station("hello");
        stationRepository.save(station);

        //when
        Station newStation = stationRepository.findByName("hello").get();
        Station newStation2 = stationRepository.findById(1L).get();

        //then
        assertThat(newStation.toString()).isEqualTo(newStation2.toString()).isEqualTo(station.toString());
    }

    @Test
    public void SteelDataInputTest() {
        //given
        Station station = new Station("햄토리공장");
        System.out.println(station.toString());
        stationRepository.save(station);
        System.out.println(station.toString());
        // 객체를 리턴받아서 재설정하지 않더라도, save 하며 아이디가 저장되면(DB) 객체가 업데이트 된다.

        //when
        SteelData steelData = new SteelData(station, 100, LocalDate.of(2022,02,28));
        steelDataRepository.save(steelData);

        //then
        SteelData newSteelData = steelDataRepository.findById(1L).get();
        System.out.println(newSteelData.toString());
        assertThat(newSteelData).isNotNull();
    }

    @Test
    public void withoutStationTest() {
        //given
        SteelData steelData = new SteelData(new Station("hello"), 100, LocalDate.of(2022,02,28));

        //when
        //then
        assertThrows(InvalidDataAccessApiUsageException.class, () -> steelDataRepository.save(steelData));
    }

    @Test
    @DisplayName("속성값 변경 테스트")
    public void updateUserTest() {
        //given
        User user = new User("hello", "world");
        userRepository.save(user);

        //when
        user.updatePassword("newPassword");
        userRepository.save(user);
        User ReFoundUser = userRepository.findById(1L).get();

        //then
        System.out.println(ReFoundUser.toString());
        assertThat(ReFoundUser.getUserPassword()).isEqualTo("newPassword");

    }
}
