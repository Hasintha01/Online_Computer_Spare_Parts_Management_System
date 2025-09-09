// Source code is decompiled from a .class file using FernFlower decompiler.
package model;

public class Category {
   private int categoryId;
   private String categoryName;

   public Category() {
   }

   public Category(int categoryId, String categoryName) {
      this.categoryId = categoryId;
      this.categoryName = categoryName;
   }

   public int getCategoryId() {
      return this.categoryId;
   }

   public void setCategoryId(int categoryId) {
      this.categoryId = categoryId;
   }

   public String getCategoryName() {
      return this.categoryName;
   }

   public void setCategoryName(String categoryName) {
      this.categoryName = categoryName;
   }
}
