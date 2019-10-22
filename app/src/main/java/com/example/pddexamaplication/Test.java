package com.example.pddexamaplication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Test implements Serializable {

    private List<Question> test = new ArrayList<Question>();

    public void setTest(List<Question> test) {
        this.test = test;
    }

    public List<Question> getTest() {
        return test;
    }

    public int[] getGroups() {
        return groups;
    }

    private int[] groups = {0, 0};

    public void setDop(int[] group){
        groups[0] = group[0];
        groups[1] = group[1];
    }


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


    /** Во время решения экзамена возможны исходы:
     *      1. Допущено 3 ошибки в неважно каких категориях - тест не сдан
     *      2. Допущено две ошибки в одной категории - тест не сдан
     *      3. Допущено две ошибки в разных категориях - генерация 10 доп вопросов из этих категорий
     *      4. Допущена одна ошибка - генерация 5 доп вопросов из этой категории
     *      5. Ошибок нет - тест сдан
     * формат вывода функции:
     * @param quantity  - выводит количество ошибок. По умолчанию там 0
     * @param group1 - выводит первую группу в которой допущена ошибка
     * @param group2 - выводит вторую группу в которой допущена ошибка
     * @param result - тест сдан\не сдан
     *
     */
    int[] globlCheck(int quantity, int group1, int group2, int result){
            int[] res = {0,0,0,0};
            res[0] = quantity;
            res[1] = group1;
            res[2] = group2;
            res[3] = result;

        for (Question quest : test){
            int partialAnsw = quest.getPartialAnsw();
            int rightAnsw = quest.getRighAnsw();
            if( partialAnsw != rightAnsw && partialAnsw != -1){
                res[0]++;
                if(quantity == 1){
                    res[1] = quest.getGroup();
                    res[3] = 0;
                }
                if(quantity == 2){
                    if(group1 == quest.getGroup()){
                        res[2] = quest.getGroup();
                        res[3] = -1;
                        break;
                    } else {
                        res[2] = quest.getGroup();
                        res[3] = 0;
                    }
                }
                if(quantity >=3){
                    res[3] = -1;
                    break;
                }
            }
        }
        return res;
    }


}
