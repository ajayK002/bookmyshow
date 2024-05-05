package com.bookmyshow.bookmyshow.models;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Movie extends BaseModel{

    private String name;

//    @Enumerated(EnumType.ORDINAL)
//    @ElementCollection
//    private List<Feature> features;
}
