/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ganesh
 */
public class RemoveEmptyList {
    
    public static List<List<Integer>> getRemoveEmptyList(List<List<Integer>> practicalDataset){
        practicalDataset.removeIf(p->p.isEmpty());
        return practicalDataset;
    } 
    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> i1 = new ArrayList<>();
        list.add(i1);
        List<Integer> i2 = new ArrayList<>();
        i2.add(2);
        i2.add(5);
        list.add(i2);
        List<Integer> i3 = new ArrayList<>();
        list.add(i3);
        System.out.println("TOTAL List : "+list);
        List<List<Integer>> record = getRemoveEmptyList(list);
        System.out.println("List : "+record);
    }
}
