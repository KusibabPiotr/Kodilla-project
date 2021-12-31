package com.crud.tasks.domain;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Task {
    @Id
    @GeneratedValue
    @Column(name = "id",unique = true)
    private Long id;
    @Column(name = "name")
    private String title;
    @Column(name = "description")
    private String content;
}

