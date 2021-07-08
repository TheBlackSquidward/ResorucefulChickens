package io.github.TheBlackSquidward.resourcechickens.recipes;

import com.ibm.icu.text.ArabicShaping;
import io.github.TheBlackSquidward.resourcechickens.api.CustomChickenData;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RoostRecipe {

    CustomChickenData customChickenData;

    public RoostRecipe(CustomChickenData customChickenData) {
        this.customChickenData = customChickenData;
    }

    public List<ItemStack> getOutputs() {
        ArrayList<ItemStack> arrayList = new ArrayList<>();
        customChickenData.getRoostData().getRoostOutputs().forEach((roostOutputData) -> {
            arrayList.add(roostOutputData.getItemOutputData().getDisplayItem());
        });
        return arrayList;
    }

    public List<ItemStack> getInput() {
        return Collections.singletonList((customChickenData.getChickenItem()));
    }

    public CustomChickenData getCustomChickenData() {
        return customChickenData;
    }
}