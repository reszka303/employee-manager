package com.github.employeemanager.components.domain;

import lombok.*;

import javax.persistence.*;

@ToString
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 70)
    private String name;

    @Column(unique = true, length = 70)
    private String email;

    @Column(nullable = false, length = 70)
    private String jobTitle;

    @Column(length = 15)
    private String phone;

    private String imageUrl;

    @Column(unique = true, updatable = false, length = 36)
    private String employeeCode;
}
