package com.example.veyisegemenerden.wehavequiz;

/**
 * Created by veyisegemenerden on 24.12.2016.
 */

public class Questions {
    private String question;
    private String A;
    private String B;
    private String C;
    private String D;
    private String E;
    private String answer;

    public Questions() {
    }

    public Questions(String question, String a, String b, String c, String d, String e, String answer) {
        this.question = question;
        A = a;
        B = b;
        C = c;
        D = d;
        E=e;

        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    public String getC() {
        return C;
    }

    public void setC(String c) {
        C = c;
    }

    public String getD() {
        return D;
    }

    public void setD(String d) {
        D = d;
    }

    public String getE() {
        return E;
    }

    public void setE(String e) {
        E = e;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "question='" + question + '\'' +
                ", A='" + A + '\'' +
                ", B='" + B + '\'' +
                ", C='" + C + '\'' +
                ", D='" + D + '\'' +
                ", E='" + E + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }


}
