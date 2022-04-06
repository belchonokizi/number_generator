package com.ilzirabalobanova.inovus.numbergenerator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "numbers")
@NoArgsConstructor
@AllArgsConstructor
public class Number {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "number_value", nullable = false, unique = true)
    private String numberValue;

    public Number(String numberValue) {
        this.numberValue = numberValue;
    }
}
