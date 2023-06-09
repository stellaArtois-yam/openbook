package com.example.openbook.Data;

import android.net.Uri;

import java.net.URL;

public class MenuList {
    String url;
    String menuName;
    int menuPrice;
    int viewType;
    int menuType;


    public MenuList (String url, String name, int price, int menuType ,int viewType){
        this.url = url;
        this.menuName = name;
        this.menuPrice = price;
        this.menuType = menuType;  //1이면 mainMenu, 2면 drink, 3이면 sideMenu
        this.viewType = viewType;   // 0이면 사진 x, 1이면 사진 o
    }

//    public MenuList(String url, String name, int anInt, int price){
//        this.url = url;
//        this.menu_name = name;
//        this.menu_price = price;
//    }

    public int getMenu_price() {
        return menuPrice;
    }

    public void setMenu_price(int menu_price) {
        this.menuPrice = menu_price;
    }

    public String getUrl() {
        return url;
    }

    public void setUri(String url) {
        this.url= url;
    }


    public String getMenu_name() {
        return menuName;
    }

    public void setMenu_name(String menu_name) {
        this.menuName = menu_name;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }


    public int getMenuType() {
        return menuType;
    }

    public void setMenuType(int menuType) {
        this.menuType = menuType;
    }


}
