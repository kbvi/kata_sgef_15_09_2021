package kata;

import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.*;

public class AddAbleImplTest {

    private AddAble addAble;
    @BeforeEach
    public void setUp() {
        addAble = new AddAbleImpl();
    }

    @DisplayName("Step 1: Create a simple String calculator")
    @Nested
    @Order(1)
    public class StepOne {

        @Test
        @DisplayName("The method can take up to two numbers, separated by commas, and will return their sum")
        public void addTwoNumbers() {
            assertAll(
                    () -> assertDoesNotThrow(() -> addAble.Add("0,1")),
                    () -> assertEquals(addAble.Add("0,1"), 1)

            );
        }

        @Test
        @DisplayName("For example “” or “1” or “1,2” as input")
        public void addExamples() {
            assertAll(
                    () -> assertDoesNotThrow(() -> addAble.Add(""   )),
                    () -> assertDoesNotThrow(() -> addAble.Add("1"  )),
                    () -> assertDoesNotThrow(() -> addAble.Add("1,2")),
                    () -> assertEquals(addAble.Add("1"), 1),
                    () -> assertEquals(addAble.Add("1,2"), 3)
            );
        }

        @Test
        @DisplayName("For an empty string it will return 0")
        public void addNothing() {
            assertEquals(addAble.Add(""), 0);
        }
    }

    @DisplayName("Step 2: Allow the Add method to handle an unknown amount of numbers")
    @Nested
    @Order(2)
    @RunWith(JUnitQuickcheck.class)
    public class StepTwo {

        @Test
        @DisplayName("For five numbers")
        public void upToFiveNumbers() {
            assertEquals(addAble.Add("1,3,6,9,44"), 63);
        }

        @Test
        @DisplayName("For thirteen numbers")
        public void upToThirteenNumbers() {
            assertEquals(addAble.Add("1,3,6,9,44,567,78,90,12,38,61,73,144"), 1126);
        }

/* TODO make it work
        @Property
        @Test
        public void addUnknowAmountOfNumbers(@From(NumbersStringGenerator.class) String numbers) {
            assertDoesNotThrow(() -> addAble.Add(numbers));
        }*/
    }

    @DisplayName("Step 3: Allow the Add method to handle new lines between numbers (instead of commas)")
    @Nested
    @Order(3)
    public class StepThree {

        @Test
        @DisplayName("The following input is ok: “1\\n2,3” (will equal 6)")
        public void okNewLineExample() {
            assertEquals(addAble.Add("1\n2,3"), 6);
        }

    }

    @DisplayName("Step 4: Support different delimiters")
    @Nested
    @Order(4)
    public class StepFour {

        @Test
        @DisplayName("“//;\\n1;2” should return three where the default delimiter is ‘;’")
        public void oneDelimiterAsInput() {
            assertEquals(addAble.Add("//;\n1;2"), 3);
        }

        @Test
        @DisplayName("The first line is optional. All existing scenarios should still be supported")
        public void noFirstLineOfDelimiters() {
            assertEquals(addAble.Add("1\n2,31"), 34);
        }

    }

    @DisplayName("Step 5: Calling Add with a negative number will throw an exception")
    @Nested
    @Order(5)
    public class StepFive {

        @Test
        @DisplayName("throw an exception “negatives not allowed” - and the negative that was passed")
        public void oneDelimiterAsInput() {
            assertThrows(IllegalArgumentException.class, () -> addAble.Add("1,-2"), "negatives not allowed -2");
        }

        @Test
        @DisplayName("If there are multiple negatives, show all of them in the exception message")
        public void severalDelimitersAsInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> addAble.Add("-1,-2,-3"),
                    "negatives not allowed -1 -2 -3");
        }
    }

    @DisplayName("Step 6: Numbers bigger than 1000 should be ignored")
    @Nested
    @Order(6)
    public class StepSix {

        @Test
        @DisplayName("adding 2 + 1001 = 2")
        public void oneDelimiterAsInput() {
            assertEquals(addAble.Add("0,2,1001"), 2);
        }
    }

    @DisplayName("Step 7: Delimiters can be of any length with the following format “//[delimiter]\\n”")
    @Nested
    @Order(7)
    public class StepSeven {

        @Test
        @DisplayName("“//[***]\\n1***2***3” should return 6")
        public void oneDelimiterAsInput() {
            assertEquals(addAble.Add("//[***]\n1***2***3"), 6);
        }
    }

    @DisplayName("Step 8: Allow multiple delimiters like this: “//[delim1][delim2]\\n”")
    @Nested
    @Order(8)
    public class StepEight {

        @Test
        @DisplayName("“//[*][%]\\n1*2%3” should return 6")
        public void severalDelimiterAsInput() {
            assertEquals(addAble.Add("//[*][%]\n1*2%3"), 6);
        }
    }

    @DisplayName("Step 9: Make sure you can also handle multiple delimiters with length longer than one char")
    @Nested
    @Order(9)
    public class StepNine {

        @Test
        @DisplayName("“//[h*][%p][ço^]\\n1h*2%p3ço^4” should return 10")
        public void severalLargeDelimiterAsInput() {
            assertEquals(addAble.Add("//[h*][%p][ço^]\n1h*2%p3ço^4"), 10);
        }
    }
/*
    @AfterEach
    public void tearDown() {
    }*/
}