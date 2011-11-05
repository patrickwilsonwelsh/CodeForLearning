package com.adaptionsoft.triviagame.game;

public class Place {
    private int number;

    private Category.CategoryNames categoryName;

    public Place(int number, Category.CategoryNames categoryName) {
	this.number = number;
	this.categoryName = categoryName;
    }

    public int getNumber() {
	return number;
    }

    public Category.CategoryNames getCategoryName() {
	return categoryName;
    }
}
