package com.example.pddexamaplication;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Question implements Serializable {
   private int number;
   private int group;
   private int id = 0;
   private int righAnsw = 0;
   private int partialAnsw = -1;
   private String textQuest = "";
   private String variantsAnsw = "";
   private int quantity = -1;
   private String[] variants;
   private String explainText = "";
    int containsImage = 1;

   public String getExplainText(){
       return explainText;
   }

    public void setExplainText(InputStream stream, int ident) {
        Scanner scan = new Scanner(stream);
        int id = ident;
        int counter = -1;
        while (scan.hasNextLine() && counter <= id) {
            counter++;
            if (id == counter) {
                explainText = scan.nextLine();
            } else {
                scan.nextLine();
            }

        }
    }

public void setPartialAnsw(int answ ){
       partialAnsw = answ;
}


   String[] getVariantsAnsw(){
       return variants;
   }

    void setNumber(int n) {
        number = n;
    }

    void setRighAnsw(InputStream stream, int ident){
        Scanner scan = new Scanner(stream);
        int id = ident;
        int counter = -1;
        while (scan.hasNextLine() && counter <= id ){
            counter++;
            if(id == counter){
                String str = scan.nextLine();
                righAnsw = Integer.parseInt(str);
            } else {
                scan.nextLine();
            }

        }
    }

    public int getRighAnsw() {
        return righAnsw;
    }

    public int getPartialAnsw() {
        return partialAnsw;
    }

    public String getTextQuest() {
        return textQuest;
    }

    int getNumber() {
        return number;
    }

    int getGroup() {
        return group;
    }

    void setGroup(int g) {
        group = g;
    }

    int getId() {
        return id;
    }
    int getQuantity(){
        return quantity;
    }

    void setId(int partialId) {
        id = partialId;
    }

    void setVariants(InputStream stream, int ident){
        Scanner scan = new Scanner(stream);
        int counter = -1;
        List<String> answ = new ArrayList<>();
        while(scan.hasNextLine() && counter <= ident){
            String string = "";
            counter++;
            if(ident == counter){
                string = scan.nextLine();
                if(string.length() !=0){
                    variantsAnsw = string;
                }
            } else {
                scan.nextLine();
            }
        }
        variants = variantsParser();
    }

    void setTextQuest(InputStream stream, int ident) {
        Scanner scan = new Scanner(stream);
        int id = ident;
        int counter = -1;
        while (scan.hasNextLine() && counter <= id ){
            counter++;
            if(id == counter){
                textQuest = scan.nextLine();
            } else {
                scan.nextLine();
            }

        }
    }

    private String[] variantsParser(){
        String[] arr = variantsAnsw.split("#");
        quantity = arr.length ;
        return arr;
    }
    boolean checkAnsw(){ // проверка ответа на текущий вопрос
        boolean answ = false;
        if(partialAnsw == righAnsw){
            answ = true;
        }
        return answ;
    }
}
