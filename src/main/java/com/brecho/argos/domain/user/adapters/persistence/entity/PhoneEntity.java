package com.brecho.argos.domain.user.adapters.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "phones")
@Getter
@Setter
@NoArgsConstructor
public class PhoneEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "region_code", length = 2)
    private String regionCode;

    @Column(name = "value", length = 15)
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users")
    private UserEntity user;
}
