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
        List<Question>  tst = new ArrayList<Question>();
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
    // генерация доп вопросов


    Test moreQuest(int[] groups){
        List<Question> tst = new ArrayList<Question>();
        Test test = new Test();
        int counter = 1;
        int c = 0;
        if(groups[0] != 0 && groups[1] !=0){
            c = 2;
        } else {
            c = 1;
        }
        for (int i = 0; i < c; i++){
            int number = groups[i];
            for(int q = 0; q < 5; q++){
                Question question = new Question();
                int id =  getRandomID();
                question.setId(id);
                question.setGroup(number);
                question.setNumber(20 + counter);
                tst.add(question);
                counter++;
            }
        }
        test.setTest(tst);
        return  test;
    }
}
