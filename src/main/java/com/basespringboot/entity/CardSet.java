package com.basespringboot.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sets")
@Getter
@Setter
public class CardSet {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    // CardSet (N) -> (1) Series
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "serie_id", nullable = false)
    private Series series;

    @Column(name = "tcg_online")
    private String tcgOnline;

    @Column(name = "card_count_official", nullable = false)
    private Integer cardCountOfficial = 0;

    @Column(name = "release_date")
    private String releaseDate;

    @Column(name = "tcgplayer_id")
    private Integer tcgplayerId;

    @Column(name = "cardmarket_id")
    private Integer cardmarketId;

    @Column(name = "cardtrader_id")
    private Integer cardtraderId;

    // CardSet (1) -> (N) Card
    @OneToMany(mappedBy = "cardSet")
    private List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
        card.setCardSet(this);
    }

    public void removeCard(Card card) {
        cards.remove(card);
        card.setCardSet(null);
    }
}
