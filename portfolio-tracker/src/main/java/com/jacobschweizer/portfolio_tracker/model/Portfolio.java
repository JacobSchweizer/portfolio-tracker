package com.jacobschweizer.portfolio_tracker.model;
import java.util.*;
import jakarta.persistence.*; 

@Entity
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // A portfolio has many positions
    @OneToMany(
            mappedBy = "portfolio",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Position> positions = new ArrayList<>();

    // Constructors
    public Portfolio() {
    }

    public Portfolio(String name) {
        this.name = name;
    }   

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    } 
    public String getName() {
        return name;
    } 
    public void setName(String name) {
        this.name = name;
    }
    public List<Position> getPositions() {
        return positions;
    }
    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }
    // Helper methods to manage bi-directional relationship
    public void addPosition(Position position) {
        positions.add(position);
        position.setPortfolio(this);
    }
    public void removePosition(Position position) {
        positions.remove(position);
        position.setPortfolio(null);
    }

    