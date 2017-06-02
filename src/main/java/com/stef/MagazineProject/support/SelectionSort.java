package com.stef.MagazineProject.support;

import java.util.ArrayList;

public class SelectionSort<T extends ISort> {
    private ArrayList<T> list;

    public SelectionSort(ArrayList<T> list) {
        this.list = list;
    }

    public ArrayList<T> selectionSort(String word) {
        for (int index = 0; index < list.size() - 1; index++) {
            int indexMin = index;
            for (int i = index + 1; i < list.size(); i++) {
                if (word.equalsIgnoreCase("name")) {
                    if (list.get(i).getWordForSort().compareTo(list.get(indexMin).getWordForSort())<0) {
                        indexMin = i;
                    }
                } else if (word.equalsIgnoreCase("price") || word.equalsIgnoreCase("salary")) {
                    if (list.get(i).getPriceForSort() < list.get(indexMin).getPriceForSort()) {
                        indexMin = i;
                    }
                }
            }
            T temp = list.get(indexMin);
            list.set(indexMin, list.get(index));
            list.set(index, temp);
        }

        return list;
    }
}