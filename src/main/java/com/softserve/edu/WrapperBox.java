package com.softserve.edu;

public class WrapperBox {
    private Box box;

    public WrapperBox() {
        box = new Box();
    }

    public String get() {
        return (String) box.get();
    }

    public void set(String data) {
        box.set(data);
    }

}
