package cs445.rec03.test;

import java.util.Random;

public class Util {

    /**
     * Implements the Fisher-Yates shuffle on generic array
     * @param array The array to shuffle
     */
    static <T> void shuffle(T[] array) {
        int index;
        T temp;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

}

