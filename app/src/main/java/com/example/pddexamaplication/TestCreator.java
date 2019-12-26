package com.example.pddexamaplication;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestCreator {
    private int min = 0;
    private int max = 19;
    private int diff = max - min;
    private Random random = new Random();
    int getRandomID() {
        int i = random.nextInt(diff + 1);
        i += min;
        return i;
    }

    void setMax(int n) {
        max = n;
    }

    void setMin(int n) {
        min = n;
    }

    Test testCreator() {
        List<List<Integer>> mapId = new ArrayList<List<Integer>>();
        List<Question> tst = new ArrayList<Question>();
        Test test = new Test();
        int counter = 0;
        for (int category = 1; category < 5; category++) {
            List<Integer> group = new ArrayList<Integer>();
            for (int q = 0; q < 5; q++) {
                int potentialId = getRandomID();
                if (!group.contains(potentialId)) {
                    group.add(potentialId);
                    Question quest = new Question();
                    quest.setId(potentialId);
                    quest.setGroup(category);
                    quest.setNumber(counter);
                    tst.add(quest);
                    counter++;
                } else {
                    q--;
                }
            }
            mapId.add(group);
        }
        test.setTest(tst);
        return test;
    }

    Test generateBilet(int number){
        List<Question> tst = new ArrayList<Question>();
        List<Integer> listID = generateListId(number);
        Test bilet = new Test();
        int counter = 0;
        for (int category = 1; category < 5; category++) {
            List<Integer> group = new ArrayList<Integer>();
            for(int i = 0; i < 5; i ++){
                Question quest = new Question();
                int potentialId = listID.get(counter);
                if (!group.contains(potentialId)) {
                    group.add(potentialId);
                    quest.setId(potentialId);
                    quest.setGroup(category);
                    quest.setNumber(counter);
                    tst.add(quest);
                    counter++;
                } else {
                    i--;
                }
            }

        }
        bilet.setTest(tst);
        return bilet;
    }

     private List<Integer> generateListId(int number){
       List<Integer> answ = new ArrayList<>();
       int start = (number * 5) - 6;
       int counter = 1;
       for(int i = 0; i < 20; i++){
           answ.add(start + counter);
           if(counter != 5){
               counter++;
           } else {
               counter = 1;
           }
       }
       return answ;
    }

    List<Question> generateDop(int[] groups){
        List<Question> test = new ArrayList<>();
        if(groups[0] != 0 && groups[1] !=0){
            for(int i = 0; i < 5; i++){
                Question quest = new Question();
                quest.setId(getRandomID());
                quest.setGroup(groups[0]);
                quest.setNumber(20 + i);
                test.add(quest);
            }
            for(int i = 0; i < 5; i++){
                Question quest = new Question();
                quest.setId(getRandomID());
                quest.setGroup(groups[1]);
                quest.setNumber(25 + i);
                test.add(quest);
            }
        } else {
            for(int i = 0; i < 5; i++){
                Question quest = new Question();
                quest.setId(getRandomID());
                quest.setGroup(groups[0]);
                quest.setNumber(20 + i);
                test.add(quest);
            }
        }
        return test;
    }
}
