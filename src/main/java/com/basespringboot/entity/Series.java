package com.basespringboot.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "series")
@Getter
@Setter
public class Series {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "energies")
    private String energies;

    @OneToMany(mappedBy = "series")
    private List<CardSet> sets = new ArrayList<>();

    // Keep both sides of the bidirectional relation in sync
    public void addSet(CardSet set) {
        sets.add(set);
        set.setSeries(this);
    }

    public void removeSet(CardSet set) {
        sets.remove(set);
        set.setSeries(null);
    }
}
