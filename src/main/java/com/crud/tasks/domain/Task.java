package com.crud.tasks.domain;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "tasks")
@Table(name = "TASKS")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",unique = true)
    private Long id;
    @Column(name = "NAME")
    private String title;
    @Column(name = "DESCRIPTION")
    private String content;
}

