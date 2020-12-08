package core.basesyntax;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JavaStreamApi {

    /**
     * Given a List of Integer numbers,
     * return a sum of odd numbers or 0, if there are no odd numbers in the List.
     */
    public Integer getOddNumsSum(List<Integer> numbers) {
        return numbers.stream()
            .filter(n -> n % 2 != 0)
            .reduce(Integer::sum)
            .orElse(0);
    }

    /**
     * Given a List of Strings,
     * return a number of times the `element` String occurs in the List.
     */
    public Long calculateOccurrences(List<String> elements, String element) {
        return elements.stream()
            .filter(e -> e.equals(element))
            .count();
    }

    /**
     * Given list of strings where each element contains 1+ numbers:
     * input = {"5,30,100", "0, 22, 7", ...}
     * return min integer value. One more thing - we're interested in even numbers.
     * If there is no throw RuntimeException with message "Can't get min value from list: method_input_list"
     */

    public int findMinEvenNumber(List<String> numbers) {
        return numbers.stream()
            .flatMap(n -> Arrays.stream(n.split(",")))
            .map(Integer::valueOf)
            .filter(n -> n % 2 == 0)
            .min(Integer::compareTo)
            .orElseThrow((() -> new RuntimeException("Can't get min value from list " + numbers)));
    }

    /**
     * Given List of string where each element represents person and its' age:
     * {"99:Johny", "20:Brad", ...} return the age of the oldest person
     */

    public static long getOldestPersonAge(List<String> peoples) {
        return peoples.stream()
            .map(p -> Integer.valueOf(p.split(":")[0]))
            .max(Integer::compareTo)
            .get();
    }

    /**
     * Given string value. Your task is
     * to increment char value of each symbol from the string. Amount to increment is
     * passed with the second input param - 'increment'
     */

    public String charsIncrementation(String string, int increment) {
        return string.chars()
            .mapToObj(c -> (char) (c += increment))
            .map(String::valueOf)
            .collect(Collectors.joining());
    }

    /**
     * Given a List of Strings, return the Optional of its first element.
     */
    public Optional<String> getFirstElement(List<String> elements) {
        return elements.stream()
            .findFirst();
    }

    /**
     * Given a arrays of ints, return three smallest numbers as list in sorted manner.
     * For example for input {4, 1, 10, 20, 11, 3} output will be {1, 3, 4};
     */

    public List<Integer> getThreeSmallestNumbers(int[] numbers) {
        return Arrays.stream(numbers)
            .boxed()
            .sorted()
            .limit(3)
            .collect(Collectors.toList());
    }

    /**
     * Given a list of integer numbers, convert each integer into its' binary representation in string format
     * and join all of them into a single string and putting each value into brackets, it will look like this:
     * Input: {1, 20, 33}
     * Output: [1]
     * [10100]
     * [100001]
     */

    public String convertAndModifyNumbers(List<Integer> numbers) {
        return numbers.stream()
            .map(Integer::toBinaryString)
            .collect(Collectors.joining("]\n[", "[", "]"));
    }


    /**
     * Given list strings representing records of patient visits to Hospital
     * {"John Stevenson - 2020", "Andrew Ferguson - 2012", "Andrew Ferguson - 2013"} and year in integer value
     * Return list of persons who visited hospital during given year. Be careful one person
     * may visit a hospital several times per year and for each visit new record will be generated.
     * Result shouldn't contain duplicates.
     */
    public long getRecordsPerYear(List<String> records, int year) {
        return records.stream()
            .map(r -> r.split("-"))
            .filter(r -> Integer.parseInt(r[1]) == year)
            .distinct()
            .count();
    }

    /**
     * Given a map with the following view : "company name" - "monthly income delta"  (String/Double)
     * Return list of the companies with positive delta. Their names should be sorted alphabetically
     * Example input : {"Sun.ltd" : 20_000}, {"Micro" : -5_200}, {"Clarity": 0.00}, {"Odyssey": 9_640};
     * Output : {"Sun.ltd", "Odyssey"}
     */

    public List<String> getCompanies(Map<String, Double> input) {
        return input.entrySet()
            .stream()
            .filter(e -> e.getValue() > 0)
            .map(Map.Entry::getKey)
            .sorted()
            .collect(Collectors.toList());
    }

    /**
     * Given a List of Strings,
     * find the String equal to the passed `element` or throw NoSuchElementException.
     */
    public String findElement(List<String> elements, String element) {
        return elements.stream()
            .filter(e -> e.equals(element))
            .findAny()
            .get();
    }

    /**
     * Given a List of Integer numbers,
     * subtract 1 from each element on an odd position (having the odd index).
     * Then return the average of all odd numbers or throw NoSuchElementException.
     */
    public Double getOddNumsAverage(List<Integer> numbers) {
        return IntStream.range(0, numbers.size())
            .map(i -> i % 2 != 0 ? (numbers.get(i) - 1) : numbers.get(i))
            .filter(n -> n % 2 != 0)
            .average()
            .getAsDouble();
    }

    /**
     * Given a List of `People` instances (having `name`, `age` and `sex` fields),
     * for example, `Arrays.asList( new People(«Victor», 16, Sex.MAN),
     * new People(«Helen», 42, Sex.WOMEN))`,
     * select from the List only men whose age is from `fromAge` to `toAge` inclusively.
     * <p>
     * Example: select men who can be recruited to army (from 18 to 27 years old inclusively).
     */
    public List<People> selectMenByAge(List<People> peopleList, int fromAge, int toAge) {
        return peopleList.stream()
            .filter(p -> p.getSex() == People.Sex.MAN && p.getAge() >= fromAge
                && p.getAge() <= toAge)
            .collect(Collectors.toList());
    }

    /**
     * Given a List of `People` instances (having `name`, `age` and `sex` fields),
     * for example, `Arrays.asList( new People(«Victor», 16, Sex.MAN),
     * new People(«Helen», 42, Sex.WOMEN))`,
     * select from the List only people whose age is from `fromAge` and to `maleToAge` (for men)
     * or to `femaleToAge` (for women) inclusively.
     * <p>
     * Example: select people of working age
     * (from 18 y.o. and to 60 y.o. for men and to 55 y.o. for women inclusively).
     */
    public List<People> getWorkablePeople(int fromAge, int femaleToAge,
                                          int maleToAge, List<People> peopleList) {
        return peopleList.stream()
            .filter(p -> p.getSex() == People.Sex.MAN && p.getAge() >= fromAge
                && p.getAge() <= maleToAge
                || p.getSex() == People.Sex.WOMEN && p.getAge() >= fromAge
                && p.getAge() <= femaleToAge)
            .collect(Collectors.toList());
    }

    /**
     * Given a List of `People` instances (having `name`, `age`, `sex` and `List<Cat> cats` fields,
     * and each `Cat` having a `name` and `age`),
     * return the names of all cats whose owners are women from `femaleAge` years old inclusively.
     */
    public List<String> getCatsNames(List<People> peopleList, int femaleAge) {
        return peopleList.stream()
            .filter(p -> p.getSex() == People.Sex.WOMEN && p.getAge() >= femaleAge)
            .flatMap(p -> p.getCatList().stream())
            .map(Cat::getName)
            .collect(Collectors.toList());
    }

    /**
     * Given list of candidates, where each element has Candidate.class type. Check which candidates are
     * eligible to apply for president position. The requirements are: person should be older than 35 y, should be
     * allowed to vote, have nationality - 'Ukrainian' and live in urk for 10 years. For the last requirement use
     * field periodsInUkr, which has following view: "2002-2015" For now we don't care if that was last 10 or not.
     * We want to reuse our validation in future, so let's write our own impl of Predicate<Candidate> in CandidateValidator
     */

    public static List<String> validateCandidates(List<Candidate> candidates) {
        return candidates.stream()
            .filter(new CandidateValidator())
            .map(Candidate::getName)
            .sorted()
            .collect(Collectors.toList());
    }

    /**
     * We want to gather a bit of statistics: we have list of people and we want to know
     * distribution among the age of women who have cats and are older than 18
     * Result should have the following view: Map.of(19 - List.of(person1, person2, ...),
     *                                               21 - List.of(person3, person7, ...);
     */

    public Map<Integer, List<People>> groupByAge(List<People> people) {
        return people.stream()
            .filter(p -> p.getSex() == People.Sex.WOMEN && p.getAge() >= 18
            && !p.getCatList().isEmpty())
            .collect(Collectors.groupingBy(People::getAge));
    }

    /**
     * Given a list of random strings, group all of them by the last letter from the
     * string. Skip punctuation and numbers.
     */

    public Map<Character, List<String>> groupWordsByLastChar(List<String> words) {
        return words.stream()
            .filter(w -> Character.isAlphabetic(w.charAt(w.length() - 1)))
            .collect(Collectors.groupingBy(w -> w.charAt(w.length() - 1)));
    }

    /**
     * Given array of numbers, your task is to sort them in the reverse order and return only those
     * numbers that can be divided by 5 without remainder.
     */

    public List<Integer> filterAndReverse(int[] inputNumbers) {
        return Arrays.stream(inputNumbers)
            .filter(n -> n % 5 == 0)
            .boxed()
            .sorted(Collections.reverseOrder())
            .collect(Collectors.toList());
    }
}
