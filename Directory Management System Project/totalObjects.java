public class totalObjects
{

   private int numObjects = 0; // Changed to instance variable

   public totalObjects()
   {
      //numObjects=0;  //already initialized
   }

   public void objectAdded()
   {
      numObjects++;
   }

   public int getTotalObjects()
   {
      return numObjects;
   }




}