package com.stef.MagazineProject.support;

import java.util.ArrayList;

/*public class SelectionSort<T extends ISort> {

    public static ArrayList<?> selectionSort(ArrayList<? extends ISort> sort, String word) {
        for (int index = 0; index < sort.size() - 1; index++) {
            int indexMin = index;
            for (int i = index + 1; i < sort.size(); i++) {
                if (word.equalsIgnoreCase("name")){
                    if (sort.get(i).getWordForSort() < sort.get(indexMin).getWordForSort()) {
                        indexMin = i;
                    }
                }else if(word.equalsIgnoreCase("price") || word.equalsIgnoreCase("salary")){
                    if (sort.get(i).getPriceForSort() < sort.get(indexMin).getPriceForSort()) {
                        indexMin = i;
                    }
                }
            }

            T temp = sort.get(indexMin);
            sort.set(indexMin,sort.get(index));
            sort.set(index,temp);
        }

        return sort;
    }
}*\
