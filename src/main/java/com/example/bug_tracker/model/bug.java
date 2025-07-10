package com.example.bug_tracker.model;

import jakarta.persistence.*;

@Entity
public class bug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String correctedLine;

    // Getters and Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getCorrectedLine() { return correctedLine; }

    public void setCorrectedLine(String correctedLine) { this.correctedLine = correctedLine; }
}
