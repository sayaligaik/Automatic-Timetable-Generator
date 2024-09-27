package com.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * 
 */
public class Randmization {

    public int getRandomNumber(int startNumber, int endNumber) {
        int randomNumber = 0;
        try {
            Random random = new Random();
            randomNumber = random.nextInt(endNumber);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
        return randomNumber;
    }

    public static void main(String[] args) {
        Randmization randmization = new Randmization();
        List<Integer> list = new ArrayList<>();
        for (int count = 0; count < 50; count++) {
            list.add(count);
        }
        for (int count = 0; count < 50; count++) {
            int number = randmization.getRandomNumber(0, list.size());
            System.out.println("Number : " + number + " list element : " + list.get(number) + " Size : " + list.size());
            list.remove(number);
        }
    }
}
