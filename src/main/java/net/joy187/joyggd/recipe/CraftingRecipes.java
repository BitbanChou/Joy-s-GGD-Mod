//package net.joy187.joyggd.recipe;
//
//import net.joy187.joyggd.init.BlockInit;
//import net.minecraft.advancements.critereon.InventoryChangeTrigger;
//import net.minecraft.data.recipes.FinishedRecipe;
//import net.minecraft.data.recipes.ShapedRecipeBuilder;
//import net.minecraft.world.level.block.Blocks;
//import net.minecraftforge.common.Tags;
//
//import java.util.function.Consumer;
//
//public class CraftingRecipes
//{
//	public static void register(Consumer<FinishedRecipe> consumer) {
//		recipesVanillaAlternatives(consumer);
//		recipesBlocks(consumer);
//		recipesCanvasSigns(consumer);
//		recipesTools(consumer);
//		recipesMaterials(consumer);
//		recipesFoodstuffs(consumer);
//		recipesFoodBlocks(consumer);
//		recipesCraftedMeals(consumer);
//		//SpecialRecipeBuilder.special(ModRecipeSerializers.FOOD_SERVING.get()).save(consumer, "food_serving");
//	}
//
//
//	/**
//	 * The following recipes should ALWAYS define a custom save location.
//	 * If not, they fall on the minecraft namespace, overriding vanilla recipes instead of being alternatives.
//	 */
//	private static void recipesVanillaAlternatives(Consumer<FinishedRecipe> consumer) {
//
//	}
//
//	private static void recipesCanvasSigns(Consumer<FinishedRecipe> consumer) {
//
//	}
//
//	private static void recipesBlocks(Consumer<FinishedRecipe> consumer) {
//		ShapedRecipeBuilder.shaped(BlockInit.STOVE.get())
//				.pattern("iii")
//				.pattern("B B")
//				.pattern("BCB")
//				.define('i', Tags.Items.INGOTS_IRON)
//				.define('B', Blocks.BRICKS)
//				.define('C', Blocks.CAMPFIRE)
//				.unlockedBy("has_campfire", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.CAMPFIRE))
//				.save(consumer);
//	}
//
//	private static void recipesTools(Consumer<FinishedRecipe> consumer) {
//
//	}
//
//	private static void recipesMaterials(Consumer<FinishedRecipe> consumer) {
//	}
//
//	private static void recipesFoodstuffs(Consumer<FinishedRecipe> consumer) {
//
//	}
//
//	private static void recipesFoodBlocks(Consumer<FinishedRecipe> consumer) {
//
//	}
//
//	private static void recipesCraftedMeals(Consumer<FinishedRecipe> consumer) {
//
//	}
//}
