package com.example.bug_tracker.repository;

import com.example.bug_tracker.model.bug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BugRepository extends JpaRepository<bug, Long> {}

