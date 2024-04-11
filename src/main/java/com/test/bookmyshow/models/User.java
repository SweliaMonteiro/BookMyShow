package com.test.bookmyshow.models;

import com.test.bookmyshow.enums.UserType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity(name = "users")
public class User extends BaseModel {

    private String name;

    private String email;

    private String password;

    @Enumerated(EnumType.ORDINAL)
    private UserType userType;

    @OneToMany
    private List<Booking> bookings;

}
