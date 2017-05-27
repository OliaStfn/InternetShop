package com.stef.MagazineProject.support;

public interface ISort {
    String getWordForSort();
    default double getPriceForSort(){
        System.out.println("Not found implementation");
        return 0.0;
    }
}
