package io.github.saft_bytecrafter.bytesskyblockutils.itemtracking;

import net.minecraft.item.ItemStack;

import java.util.*;

public class CompareInventories {

    private List<ItemStack> oldInventory;
    List<DifferentItems> differentItems = new ArrayList<>();

    public CompareInventories(){
        resetOldInventory(); //resets the old inv at the beginning of minecraft
    }

    public List<ItemStack> getNewInventory(ItemStack[] inventory){
        List<ItemStack> nextInventory = new ArrayList<>(inventory.length); //the items of the player-inv get saved into nextinventory
        for(ItemStack stack: inventory){
            if(stack != null){
                nextInventory.add(ItemStack.copyItemStack(stack));
            }
            else {
                nextInventory.add(null); //if there is a null in it it gets added like this and not as a stack-copy
            }
        }
        return nextInventory;
    }

    public void getNewItems(ItemStack[] inventory){
        List<ItemStack> currentInventory = getNewInventory(inventory); //the new inv of the player is acquired
        final HashMap<String, Integer> oldInventoryMap = new HashMap<>();
        HashMap<String, Integer> currentInventoryMap = new HashMap<>();
        if(oldInventory != null){
            differentItems.clear();
            for(int i = 0; i < currentInventory.size()-1; i++){ //goes through the list of items in the inv
                if(i == 8) continue;
                ItemStack oldStack = oldInventory.get(i);
                ItemStack currentStack = currentInventory.get(i);
                if(oldStack == null && currentStack == null) continue;
                if(oldStack != null && currentStack == null) {
                    addInMap(oldInventoryMap, oldStack.getDisplayName(), oldStack.stackSize);
                }
                else if(oldStack == null && currentStack != null){
                    addInMap(currentInventoryMap, currentStack.getDisplayName(), currentStack.stackSize);
                }
                else if(!oldStack.getIsItemStackEqual(currentStack)) { //if it's not the same item:
                    addInMap(oldInventoryMap, oldStack.getDisplayName(), oldStack.stackSize);
                    addInMap(currentInventoryMap, currentStack.getDisplayName(), currentStack.stackSize);
                }
            }

            Set<String> keys = new HashSet<>(oldInventoryMap.keySet());
            keys.addAll(currentInventoryMap.keySet());

            for(String key: keys){
                int oldAmount = oldInventoryMap.getOrDefault(key, 0);
                int currentAmount = currentInventoryMap.getOrDefault(key, 0);
                int difference = currentAmount-oldAmount;
                if(difference != 0) differentItems.add(new DifferentItems(key, difference));
            }
        }
        oldInventory = currentInventory;
    }

    public void resetOldInventory(){
        oldInventory = null;
    }

    public List<DifferentItems> getDifferentItems(){
        return differentItems;
    }

    private void addInMap(HashMap<String, Integer> map, String key, int value){
        if(map.containsKey(key)){ //checks if key is already preasent
            map.put(key, map.get(key) + value);
        } //^adds the new value to the old one, so that we have the amount of them combined
        else{
            map.put(key, value);
        }
    }
}
