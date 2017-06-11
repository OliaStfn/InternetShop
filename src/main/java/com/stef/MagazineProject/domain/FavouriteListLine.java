package com.stef.MagazineProject.domain;

import com.stef.MagazineProject.DAO.Identifacator;

public class FavouriteListLine implements Identifacator<Integer>{
    private Goods goods;
    private int favoriteListId;

    public FavouriteListLine(Goods goods, int favoriteListId) {
        this.goods = goods;
        this.favoriteListId=favoriteListId;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Override
    public int getId() {
        return favoriteListId;
    }

    @Override
    public String toString() {
        return "FavouriteListLine: " +
                "\n |\tGoods: \t\t\t|\t" + goods.toString() +
                "\n |\tFavoriteListId: \t\t\t|\t" + favoriteListId;
    }
}