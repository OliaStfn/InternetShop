package com.stef.MagazineProject.domain;

import com.stef.MagazineProject.DAO.Identifacator;

public class FavouriteListLine implements Identifacator<Integer>{
    private Goods goods;
    private int favoriteListId;
    private int lineId;

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
        return lineId;
    }

    public void setId(int lineId){
        this.lineId=lineId;
    }

    public int getFavoriteListId() {
        return favoriteListId;
    }

    public void setFavoriteListId(int favoriteListId) {
        this.favoriteListId = favoriteListId;
    }

    @Override
    public String toString() {
        return "FavouriteListLine: " +
                "\n |\tGoods: \t\t\t|\t" + goods.toString();
    }
}