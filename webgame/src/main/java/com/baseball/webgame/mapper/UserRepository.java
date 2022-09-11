package com.baseball.webgame.mapper;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<com.baseball.webgame.model.CustomUser, Long> {
}
