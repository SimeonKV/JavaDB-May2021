package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String country;
    @NonNull
    private String city;
    @NonNull
    private String details;
}
