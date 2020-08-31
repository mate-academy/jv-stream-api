package core.basesyntax;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JavaStreamApi {

    /**
     * Given a List of Integer numbers,
     * return a sum of odd numbers or 0, if there are no odd numbers in the List.
     */
    public Integer oddSum(List<Integer> numbers) {
        return numbers.stream()
                .filter(number -> number % 2 != 0)
                .mapToInt(Integer::intValue)
                .sum();
    }

    /**
     * Given a List of Strings,
     * return a number of times the `element` String occurs in the List.
     */
    public Long elementCount(List<String> elements, String element) {
        return elements.stream()
                .filter(value -> value.equals(element))
                .count();
    }

    /**
     * Given a List of Strings, return the Optional of its first element.
     */
    public Optional<String> firstElement(List<String> elements) {
        return elements.stream()
                .findFirst();
    }

    /**
     * Given a List of Strings,
     * find the String equal to the passed `element` or throw NoSuchElementException.
     */
    public String findElement(List<String> elements, String element) {
        return elements.stream()
                .filter(value -> value.equals(element))
                .findFirst()
                .orElseThrow();
    }

    /**
     * Given a List of Integer numbers,
     * subtract 1 from each element on an odd position (having the odd index).
     * Then return the average of all odd numbers or throw NoSuchElementException.
     */
    public Double averageSumOdd(List<Integer> numbers) {
        return IntStream.range(0, numbers.size())
                .map(index -> index % 2 != 0 ? numbers.get(index) - 1 : numbers.get(index))
                .filter(number -> number % 2 != 0)
                .average()
                .orElseThrow();
    }

    /**
     * Given a List of `People` instances (having `name`, `age` and `sex` fields),
     * for example, `Arrays.asList( new People(«Вася», 16, Sex.MAN),
     *                              new People(«Елена», 42, Sex.WOMEN))`,
     * select from the List only men whose age is from `fromAge` to `toAge` inclusively.
     *
     * Example: select men who can be recruited to army (from 18 to 27 years old inclusively).
     */
    public List<People> manSelectByAge(List<People> peopleList, int fromAge, int toAge) {
        return peopleList.stream()
                .filter(man -> man.getAge() >= fromAge
                        && man.getAge() <= toAge
                        && man.getSex().equals(People.Sex.MAN))
                .collect(Collectors.toList());
    }

    /**
     * Given a List of `People` instances (having `name`, `age` and `sex` fields),
     * for example, `Arrays.asList( new People(«Вася», 16, Sex.MAN),
     *                              new People(«Елена», 42, Sex.WOMEN))`,
     * select from the List only people whose age is from `fromAge` and to `maleToAge` (for men)
     * or to `femaleToAge` (for women) inclusively.
     *
     * Example: select people of working age
     * (from 18 y.o. and to 60 y.o. for men and to 55 y.o. for women inclusively).
     */
    public List<People> workablePeople(int fromAge, int femaleToAge,
                                       int maleToAge, List<People> peopleList) {
        return peopleList.stream()
                .filter(workable -> workable.getAge() >= fromAge
                        && (workable.getSex().equals(People.Sex.MAN)
                        && workable.getAge() <= maleToAge)
                        || (workable.getSex().equals(People.Sex.WOMEN)
                        && workable.getAge() <= femaleToAge))
                .collect(Collectors.toList());
    }

    /**
     * Given a List of `People` instances (having `name`, `age`, `sex` and `List<Cat> cats` fields,
     * and each `Cat` having a `name` and `age`),
     * return the names of all cats whose owners are women from `femaleAge` years old inclusively.
     */
    public List<String> getCatsNames(List<People> peopleList, int femaleAge) {
        return peopleList.stream()
                .filter(owner -> owner.getAge() >= femaleAge
                        && owner.getSex().equals(People.Sex.WOMEN))
                .flatMap(cat -> cat.getCatList().stream())
                .map(Cat::getName)
                .collect(Collectors.toList());
    }
}
