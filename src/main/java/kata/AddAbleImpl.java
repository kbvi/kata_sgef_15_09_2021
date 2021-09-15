package kata;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AddAbleImpl implements AddAble {

    public final static Pattern delimitersMatchPattern = Pattern.compile("\\A//(.+)\\n(.*)");
    public static final String  withBracketsRegex = "(?<=\\[)(.+?)(?=\\])";
    public final static Pattern withBracketsPattern = Pattern.compile(withBracketsRegex);
    final int maxNumberValue = 1000;

    public int Add(String numbers) throws IllegalArgumentException {
        if (Objects.nonNull(numbers)) {

            Matcher m = delimitersMatchPattern.matcher(numbers);
            final boolean delimitersFound = m.find();
            final String thinDelimitersBlock = delimitersFound ? m.group(1) : "";

            Matcher mBrackets = withBracketsPattern.matcher(thinDelimitersBlock);
            final List<String> largDelimiters = new ArrayList<>();
            while (mBrackets.find()) {
                largDelimiters.add(mBrackets.group(1));
            }

            final String pattern = delimitersFound
                    ? String.join("|", (
                            largDelimiters.isEmpty()
                                    ? thinDelimitersBlock.split("")
                                    : largDelimiters.stream().map(Pattern::quote).toArray(String[]::new)
                    ))
                    : ",|\\n";
            final String numbersPart = delimitersFound ? m.group(2) : numbers;

            final List<Integer> negatives = new ArrayList<>();

            final int sum = Stream.of(numbersPart)
                    .flatMap(Pattern.compile(pattern)::splitAsStream)
                    .map(s -> {
                        if(s.trim().length() == 0) {
                            throw new IllegalArgumentException("each delimiter must have a number as direct neighbour");
                        };
                        return s; })
                    .mapToInt(Integer::valueOf)
                    .reduce(0, (i,j) -> {
                        if(j < 0)
                            negatives.add(j);
                        if(i > 0 && Integer.MAX_VALUE - i < j)
                            throw new IllegalArgumentException("`numbers` exceeded max int value");
                        return negatives.size() > 0 || maxNumberValue < j ? i : i + j;
                    });
            if(!negatives.isEmpty()) {
                throw new IllegalArgumentException("negatives not allowed " +
                        negatives.stream().map(String::valueOf).collect(Collectors.joining(" "))
                );
            }
            return sum;
        } else
            throw new IllegalArgumentException(
                    "numbers shouldn't be null (Odd case, handled arbitrary by this exception)"
            );
    }
}
