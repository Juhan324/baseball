package com.baseball.webgame.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="public", name="user")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CustomUser {
    @Id
    private String id;
    private String username;
    private String password;
    private String role;
}
