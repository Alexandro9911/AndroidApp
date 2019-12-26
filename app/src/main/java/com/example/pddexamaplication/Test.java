package com.example.pddexamaplication;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Test implements Serializable {

    private List<Question> test = new ArrayList<Question>();
    private List<Question> doptest = new ArrayList<Question>();

    private int[] answrs = new int[20];
    private int[] partialAnswrs = new int[20];

    private int[] dopAnswrs = new int[10];
    private int[] dopPartialAnswrs = new int[10];

    public void setAnswrs(int[] answrs) {
        this.answrs = answrs;
    }
    public void fillInUnused(){
        for(int i = 0; i < 20; i++){
            partialAnswrs[i] = -1;
        }
        for(int i =0; i < 10; i++){
            dopPartialAnswrs[i] = -1;
        }
    }


    public void setPartialAnswrs(int[] partAnswrs) {
        int j = 0;
        for(int i = 0;i < 20; i++){
            partialAnswrs[i] = partAnswrs[j];
            j++;
        }
    }

    public void setDopAnswrs(int[] dopAnswrs) {
        this.dopAnswrs = dopAnswrs;
    }

    public void setDopPartialAnswrs(int[] dopPartAnswrs) {
        int j = 0;
        for(int i = 0;i < 10; i++){
            dopPartialAnswrs[i] = dopPartAnswrs[j];
            j++;
        }
    }

    public int[] getAnswrs() {
        return answrs;
    }

    public int[] getPartialAnswrs() {
        return partialAnswrs;
    }

    public int[] getDopAnswrs() {
        return dopAnswrs;
    }

    public int[] getDopPartialAnswrs() {
        return dopPartialAnswrs;
    }

    public void setTest(List<Question> test) {
        this.test = test;
    }

    public void setDopTest(List<Question> test) {
        this.doptest = test;
    }

    public List<Question> getTest() {
        return test;
    }

    public List<Question> getDoptest() {
        return doptest;
    }

    void setpartialAnswr(int index, int answer){
        answrs[index] = answer;
    }

    void setDopPartialAnswr(int index, int answer){
        dopAnswrs[index] = answer;
    }
}
