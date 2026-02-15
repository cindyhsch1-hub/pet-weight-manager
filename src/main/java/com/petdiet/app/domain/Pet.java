package com.petdiet.app.domain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Pet {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private Long id;

    private String name;
    private String breed;
    private Double weight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
    private List<PetWeight> weights = new ArrayList<>();

    public void setMember(Member member) {
        this.member = member;
        member.getPets().add(this);
    }

    public void updateWeight(Double weightValue) {
        this.weight = weightValue;
        weights.add(PetWeight.createPetWeight(this, weightValue));
    }
}