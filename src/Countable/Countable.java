package Countable;

public abstract class Countable {
   private static long instanceCount = 1;
   
   public Countable() {
      instanceCount++;
   }
   
   public long getInstanceCount() {
      return instanceCount;
   }
   
   public void decrement() {
       instanceCount--;
   }
   
   public void increment() {
       instanceCount++;
   }
}