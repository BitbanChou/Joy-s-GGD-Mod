//package net.joy187.joyggd.recipe;
//
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//import net.joy187.joyggd.Main;
//import net.minecraft.core.NonNullList;
//import net.minecraft.network.FriendlyByteBuf;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.util.GsonHelper;
//import net.minecraft.world.SimpleContainer;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.crafting.*;
//import net.minecraft.world.level.Level;
//
//
//public class StoveRecipe implements Recipe<SimpleContainer> {
//    private final ResourceLocation id;
//    private final ItemStack output;
//    private final NonNullList<Ingredient> recipeItems;
//    private final int cookingTime;
//
//    public StoveRecipe(ResourceLocation id, ItemStack output,
//                       NonNullList<Ingredient> recipeItems, int ckt) {
//        this.id = id;
//        this.output = output;
//        this.recipeItems = recipeItems;
//        this.cookingTime = ckt;
//    }
//
//    @Override
//    public boolean matches(SimpleContainer pContainer, Level pLevel) {
//        return recipeItems.get(0).test(pContainer.getItem(0));
//    }
//
//    @Override
//    public NonNullList<Ingredient> getIngredients() {
//        return recipeItems;
//    }
//
//    @Override
//    public ItemStack assemble(SimpleContainer pContainer) {
//        return output;
//    }
//
//    @Override
//    public boolean canCraftInDimensions(int pWidth, int pHeight) {
//        return true;
//    }
//
//    @Override
//    public ItemStack getResultItem() {
//        return output.copy();
//    }
//
//    @Override
//    public ResourceLocation getId() {
//        return id;
//    }
//
//    @Override
//    public RecipeSerializer<?> getSerializer() {
//        return Serializer.INSTANCE;
//    }
//
//    @Override
//    public RecipeType<?> getType() {
//        return Type.INSTANCE;
//    }
//
//    public static class Type implements RecipeType<StoveRecipe> {
//        private Type() { }
//        public static final Type INSTANCE = new Type();
//        public static final String ID = "dying_table";
//    }
//
//    public static class Serializer implements RecipeSerializer<StoveRecipe> {
//        public static final Serializer INSTANCE = new Serializer();
//        public static final ResourceLocation ID = new ResourceLocation(Main.MOD_ID,"dying_table");
//
//        @Override
//        public StoveRecipe fromJson(ResourceLocation id, JsonObject json) {
//            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
//            int cookingTime = GsonHelper.getAsInt(json, "cookingtime");
//            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
//
//            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);
//
//            for (int i = 0; i < inputs.size(); i++) {
//                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
//            }
//
//            return new StoveRecipe(id, output, inputs, cookingTime);
//        }
//
//        @Override
//        public StoveRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
//            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);
//
//            for (int i = 0; i < inputs.size(); i++) {
//                inputs.set(i, Ingredient.fromNetwork(buf));
//            }
//
//            ItemStack output = buf.readItem();
//
//            return new StoveRecipe(id, output, inputs,100);
//        }
//
//        @Override
//        public void toNetwork(FriendlyByteBuf buf, StoveRecipe recipe) {
//            buf.writeInt(recipe.getIngredients().size());
//            for (Ingredient ing : recipe.getIngredients()) {
//                ing.toNetwork(buf);
//            }
//            buf.writeItemStack(recipe.getResultItem(), false);
//        }
//
////        @Override
////        public RecipeSerializer<?> setRegistryName(ResourceLocation name) {
////            return INSTANCE;
////        }
////
////        @Nullable
////        @Override
////        public ResourceLocation getRegistryName() {
////            return ID;
////        }
////
////        @Override
////        public Class<RecipeSerializer<?>> getRegistryType() {
////            return Serializer.castClass(RecipeSerializer.class);
////        }
//
//        @SuppressWarnings("unchecked") // Need this wrapper, because generics
//        private static <G> Class<G> castClass(Class<?> cls) {
//            return (Class<G>)cls;
//        }
//    }
//
//    public int getCookingTime() {
//        return this.cookingTime;
//    }
//
//}
