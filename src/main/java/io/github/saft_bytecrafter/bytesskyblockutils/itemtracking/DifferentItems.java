package io.github.saft_bytecrafter.bytesskyblockutils.itemtracking;

public class DifferentItems {

    private String itemName;
    private int itemAmount;

    public DifferentItems(String itemName, int itemAmount) {
        this.itemName = itemName;
        this.itemAmount = itemAmount;
    }

    public String getItemName(){
        return itemName;
    }

    public int getItemAmount(){
        return itemAmount;
    }

}
