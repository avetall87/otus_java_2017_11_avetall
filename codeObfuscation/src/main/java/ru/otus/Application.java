package ru.otus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by avetall  19.11.17.
 */
public class Application {

  public static void main(String... args) {
      List<Integer> example = new ArrayList<>();
      int min = 0;
      int max = 999_999;
      for (int i = min; i < max + 1; i++) {
          example.add(i);
      }
        ListUtil.getReverseTime(example);
    }

}
