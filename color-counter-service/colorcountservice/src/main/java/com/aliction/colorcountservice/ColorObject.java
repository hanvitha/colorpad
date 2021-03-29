package com.aliction.colorcountservice;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Tuple;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class ColorObject extends PanacheEntity {

    private Long id;
    private int boardId;
    private String color;

    public ColorObject() {
    };

    public ColorObject(Long id, int boardId, String color) {
        this.id = id;
        this.boardId = boardId;
        this.color = color;
    }


    public Uni<Long> save(PgPool client) {
        return client.preparedQuery("INSERT INTO squares (id, boardid, name) VALUES ($1, $2, $3) RETURNING id")
                .execute(Tuple.of(id, boardId, color)).onItem()
                .transform(pgRowSet -> pgRowSet.iterator().next().getLong("id"));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
