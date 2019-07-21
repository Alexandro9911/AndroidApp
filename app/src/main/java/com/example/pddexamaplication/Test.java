package com.example.pddexamaplication;

import java.util.ArrayList;
import java.util.List;

public class Test {
    private List<Question> test = new ArrayList<Question>();

    public void setTest(List<Question> test) {
        this.test = test;
    }

    public List<Question> getTest() {
        return test;
    }

    private int[] groups = {0, 0};
    boolean flag = false;

    // проверка теста
    List<Integer> checkTest() { // возвращает массив с отметками на каждый впрос
        List<Integer> listAnsw = new ArrayList<Integer>();
        for (Question question : test) {
            int partial = question.getPartialAnsw();
            boolean answ;
            if (partial != -1) {
                answ = question.checkAnsw();
                if (answ) {
                    listAnsw.add(1);
                } else {
                     listAnsw.add(0);
                }
            } else {
                listAnsw.add(-1);
            }
        }
        return listAnsw;
    }

    int partCheck(List<Integer> results){
        int counter = 0;
        for(int i = 0; i <20; i++){
            int currGroup = test.get(i).getGroup();
            if(results.get(i) != -1){
                if(results.get(i) == test.get(i).getRighAnsw()){
                }
                else {
                    counter++;
                }
            }
        }
        return counter;
    }

    int resultCheck(List<Integer> markedTest) {
        int answ = 0;
        int[] groupArr = {0, 0, 0, 0};
        int counter = 0;
        for (int i = 0; i < 20; i++) {
            int currGroup = test.get(i).getGroup();
            if (markedTest.get(i) == 0) {
                groupArr[currGroup]++;
                counter++;
            }
        }
        if (counter > 2) {
            answ = 1;
        }
        boolean fl = false;
        for (int i = 0; i < 4; i++) {
            if (groupArr[i] > 2) {
                answ = 1;
                fl = true;
            }
        }
        if (!fl && counter <= 2) {
            int coun = 0;
            for (int i = 0; i < 4; i++) {
                if (groupArr[i] != 0) {
                    groups[coun] = i;
                    coun++;
                    flag = true;
                }
            }
            answ = 2;
        }
        return answ;
    }

}
