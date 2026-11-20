package com.basespringboot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "cards")
@Getter
@Setter
public class Card {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    // Card (N) -> (1) CardSet
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "set_id", nullable = false)
    private CardSet cardSet;

    @Column(name = "local_id", nullable = false)
    private String localId;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "rarity")
    private String rarity;

    @Column(name = "level")
    private String level;

    @Column(name = "hp")
    private Integer hp;

    @Column(name = "stage")
    private String stage;

    @Column(name = "suffix")
    private String suffix;

    @Column(name = "evolve_from_default")
    private String evolveFromDefault;

    @Column(name = "retreat")
    private Integer retreat;

    @Column(name = "dex_id")
    private Integer dexId;

    @Column(name = "illustrator")
    private String illustrator;

    @Column(name = "trainer_type")
    private String trainerType;

    @Column(name = "energy_type")
    private String energyType;

    @Column(name = "weight")
    private String weight;

    @Column(name = "item_default")
    private String itemDefault;

    @Column(name = "regulation_mark")
    private String regulationMark;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "variants", columnDefinition = "jsonb")
    private CardVariants variants;

    @Column(name = "file_path", nullable = false)
    private String filePath;
}
