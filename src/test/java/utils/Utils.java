package utils;

public class Utils {

    public static void sleep(int sec){

      try {
          Thread.sleep(sec*1000);

      } catch(InterruptedException e){

            throw new RuntimeException(e);
        }


    }
}
