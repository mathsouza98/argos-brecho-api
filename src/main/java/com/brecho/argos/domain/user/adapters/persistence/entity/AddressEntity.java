package com.brecho.argos.domain.user.adapters.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
public class AddressEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "state_code", length = 2)
    private String stateCode;

    @Column(name = "street_name", length = 50)
    private String streetName;

    @Column(name = "number")
    private int number;

    @Column(name = "zipcode", length = 50)
    private String zipcode;

    @Column(name = "complement", length = 50)
    private String complement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users")
    private UserEntity user;
}
