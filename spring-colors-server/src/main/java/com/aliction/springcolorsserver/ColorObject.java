package com.aliction.springcolorsserver;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ColorObject {

    private int id;
    private int boardId;
    private String color;

    public ColorObject(int id, int boardId, String color) {
        this.id = id;
        this.boardId = boardId;
        this.color = color;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
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
