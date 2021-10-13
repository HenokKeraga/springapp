package com.example.persistanceone.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {

    @Id
    Long id;
    String name;
}
