package kata.generator;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Because book has a constructor with user defined data types (Author and
 * Publisher), the Ctor.class generator will not work.
 *
 * Therefore, we need to create a generator, to generate a book.
 */
public class NumbersStringGenerator extends Generator<String> {

   /* private String numbers;

    public void configure(String numbersAsString) {
        this.numbers = numbersAsString;
    }*/

    private int maxNumberOfNumbers = 90;
    private int maxNumber = 1000;

    public void setMaxNumberOfNumbers(final int maxNumberOfNumbers) {
        this.maxNumberOfNumbers = maxNumberOfNumbers;
    }

    public void setMaxNumber(final int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public NumbersStringGenerator() {
        super(String.class);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("njvfwnjkvlfdwn");
    }

    @Override
    public String generate(SourceOfRandomness random, GenerationStatus generationStatus) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("bchwvbkhswvbhj");
        final int n = random.nextInt(0, maxNumberOfNumbers < 0 ? Integer.MAX_VALUE : maxNumberOfNumbers);
//        int maxToAdd = Integer.MAX_VALUE;
        final IntStream numbers = IntStream.generate(() -> {
            int i = random.nextInt(0, maxNumber);
//            if(maxToAdd < i) {}
//            maxToAdd -= i;
            return i;
        }).limit(n);
        final Stream<String> numbersAsStrings = numbers.mapToObj(Integer::toString);
        return numbersAsStrings.collect(Collectors.joining());
    }
}