package core.basesyntax;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JavaStreamApi {

    public Integer oddSum(List<Integer> numbers) {
        return numbers.stream()
                .filter(x -> x % 2 != 0)
                .reduce(0, Integer::sum);
    }

    public Long elementCount(List<String> elements, String element) {
        return elements.stream()
                    .filter(x -> x.equals(element))
                    .count();
    }

    public Optional<String> firstElement(List<String> elements) {
        return elements.stream()
                    .findFirst();
    }

    public String findElement(List<String> elements, String element) {
        return elements.stream()
                .filter(s -> s.equals(element))
                .findAny()
                .orElseThrow();
    }

    public Double averageSumOdd(List<Integer> numbers) {
        return IntStream.range(0, numbers.size())
                .map(i -> (i % 2 == 1) ? numbers.get(i) - 1 : numbers.get(i))
                .filter(i -> i % 2 == 1)
                .average()
                .orElseThrow();
    }

    public List<People> manSelectByAge(List<People> peopleList, int fromAge, int toAge) {
        return peopleList.stream()
                    .filter(people -> people.getSex() == People.Sex.MAN
                            && people.getAge() < toAge
                            && people.getAge() >= fromAge)
                    .collect(Collectors.toList());
    }

    public List<People> workablePeople(int fromAge, int femaleToAge,
                                       int maleToAge, List<People> peopleList) {
        return peopleList.stream()
                    .filter(people -> people.getAge() >= fromAge
                            && (people.getAge() <= maleToAge
                            || people.getAge() <= femaleToAge))
                    .collect(Collectors.toList());
    }

    public List<String> getCatsNames(List<People> peopleList, int femaleAge) {
        return peopleList.stream()
                .filter(people -> people.getAge() >= femaleAge
                        && people.getSex().equals(People.Sex.WOMEN))
                .flatMap(x -> x.getCatList().stream())
                .map(Cat::getName)
                .collect(Collectors.toList());

    }
}
