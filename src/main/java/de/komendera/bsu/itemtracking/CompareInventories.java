package de.komendera.bsu.itemtracking;

import net.minecraft.item.ItemStack;

import java.util.*;

public class CompareInventories {

    private List<ItemStack> oldInventory;
    List<DifferentItems> differentItems = new ArrayList<DifferentItems>();
//    private List<ItemStack> differentItems;

    public CompareInventories(){
//        differentItems = new ArrayList<ItemStack>();
        resetOldInventory(); //resets the old inv at the beginning of minecraft
    }

    private List<ItemStack> getNewInventory(ItemStack[] inventory){
        List<ItemStack> nextInventory = new ArrayList<ItemStack>(inventory.length); //the items of the player-inv get saved into nextinventory
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
        final Map<String, Integer> oldInventoryMap = new HashMap<String, Integer>();
        Map<String, Integer> currentInventoryMap = new HashMap<String, Integer>();
        if(oldInventory != null){
            differentItems.clear();
            for(int i = 0; i < currentInventory.size()-1; i++){ //goes through the list of items in the inv
                if(i == 8) continue;
                ItemStack oldStack = oldInventory.get(i);
                ItemStack currentStack = currentInventory.get(i);
                if(oldStack == null && currentStack == null) continue;
                if(oldStack != null && currentStack == null) {
                    addInMap((HashMap<String, Integer>) oldInventoryMap, oldStack.getDisplayName(), oldStack.stackSize);
                }
                else if(oldStack == null && currentStack != null){
                    addInMap((HashMap<String, Integer>) currentInventoryMap, currentStack.getDisplayName(), currentStack.stackSize);
        /*            differentItems.add(currentStack);
                    continue;
        */        }
                else if(!oldStack.getIsItemStackEqual(currentStack)) { //if it's not the same item:
                    addInMap((HashMap<String, Integer>) oldInventoryMap, oldStack.getDisplayName(), oldStack.stackSize);
                    addInMap((HashMap<String, Integer>) currentInventoryMap, currentStack.getDisplayName(), currentStack.stackSize);
        /*            if(!(oldStack.getTagCompound().equals(currentStack.getTagCompound()))){

                        differentItems.add(currentStack);
                    }
                    else{ //if it is the same item but the amount has changed:
                        differentItems.add(currentStack.splitStack(oldStack.stackSize));
                    }
         */       }
                //                  oldInventory.set(i, currentStack);
            }

            Set<String> keys = new HashSet<String>(oldInventoryMap.keySet());
            keys.addAll(currentInventoryMap.keySet());

            //           keys.forEach(key-> {
            for(String key: keys){
                int oldAmount = oldInventoryMap.getOrDefault(key, 0);
                int currentAmount = currentInventoryMap.getOrDefault(key, 0);
                int difference = currentAmount-oldAmount;
                if(difference != 0) differentItems.add(new DifferentItems(key, difference));
            }
            //           });
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
