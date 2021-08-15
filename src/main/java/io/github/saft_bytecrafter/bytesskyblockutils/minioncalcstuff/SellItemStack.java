package io.github.saft_bytecrafter.bytesskyblockutils.minioncalcstuff;

public class SellItemStack {

    private String name;
    private int amount;
    private double npcPricePerPiece;
    private double npcPrice;
    private double bazaarPricePerPiece;
    private double bazaarPrice;

    public SellItemStack(String name, int amount){
        this.name = name;
        this.amount = amount;
        this.npcPricePerPiece =
        this.bazaarPricePerPiece =
        this.npcPrice = this.npcPricePerPiece*amount;
        this.bazaarPrice = this.bazaarPricePerPiece*amount;
    }

}
