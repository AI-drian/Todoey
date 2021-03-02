//No Setters needed for this task? therefore removed.
package com.company;

public class Item {

    private int id;
    private String text;
    private boolean checked;

    //Empty constructor. Why is this needed?, program wont start without it
    public Item(){ }

    public Item (String text, boolean checked) {
        this.text = text;
        this.checked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", checked=" + checked +
                '}';
    }
}
