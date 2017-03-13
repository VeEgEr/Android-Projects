package com.example.veyisegemenerden.wehavequiz;

/**
 * Created by veyisegemenerden on 29.12.2016.
 */

public class ScoreData {
    private int score;
    private String name;

    public ScoreData(int score,String name) {
        this.score = score;

        this.name=name;
    }

    public ScoreData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    @Override
    public String toString() {
        return "ScoreData{" +
                "score=" + score +

                ", name='" + name + '\'' +
                '}';
    }
}
