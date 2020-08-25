package core.basesyntax;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class JavaStreamApi {

    /**
     * Given a List of Integer numbers,
     * return a sum of odd numbers or 0, if there are no odd numbers in the List.
     */
    public Integer oddSum(List<Integer> numbers) {
        return null;
    }

    /**
     * Given a List of Strings,
     * return a number of times the `element` String occurs in the List.
     */
    public Long elementCount(List<String> elements, String element) {
        return null;
    }

    /**
     * Given a List of Strings, return the Optional of its first element.
     */
    public Optional<String> firstElement(List<String> elements) {
        return null;
    }

    /**
     * Given a List of Strings,
     * find the String equal to the passed `element` or throw NoSuchElementException.
     */
    public String findElement(List<String> elements, String element) {
        return null;
    }

    /**
     * Given a List of Integer numbers,
     * subtract 1 from each element on an odd position (having the odd index).
     * Then return the average of all odd numbers or throw NoSuchElementException.
     */
    public Double averageSumOdd(List<Integer> numbers) {
        return null;
    }

    /**
     * Given a List of `People` instances (having `name`, `age` and `sex` fields),
     * for example, `Arrays.asList( new People(«Вася», 16, Sex.MAN),
     *                              new People(«Елена», 42, Sex.WOMEN))`,
     * select from the List only men whose age is from `fromAge` to `toAge` inclusively.
     *
     * Example: select men of military age (from 18 to 27 years old inclusively).
     */
    public List<People> manSelectByAge(List<People> peopleList, int fromAge, int toAge) {
        return Collections.emptyList();
    }

    /**
     * Given a List of `People` instances (having `name`, `age` and `sex` fields),
     * for example, `Arrays.asList( new People(«Вася», 16, Sex.MAN),
     *                              new People(«Елена», 42, Sex.WOMEN))`,
     * select from the List only people whose age is from `fromAge` and to `maleToAge` (for men)
     * or to `femaleToAge` (for women) inclusively.
     *
     * Example: select potential working-age people
     * (from 18 y.o. and to 55 y.o. for men and to 60 y.o. for women inclusively).
     */
    public List<People> workablePeople(int fromAge, int femaleToAge,
                                       int maleToAge, List<People> peopleList) {
        return Collections.emptyList();
    }

    /**
     * Given a List of `People` instances (having `name`, `age`, `sex` and `List<Cat> cats` fields,
     * and each `Cat` having a `name` and `age`),
     * return the names of all cats whose owners are women from `femaleAge` years old inclusively.
     */
    public List<String> getCatsNames(List<People> peopleList, int femaleAge) {
        return Collections.emptyList();
    }
}
