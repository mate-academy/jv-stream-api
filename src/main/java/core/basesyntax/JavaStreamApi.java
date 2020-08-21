package core.basesyntax;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JavaStreamApi {

    public Integer oddSum(List<Integer> numbers) {
        return numbers.stream()
                .filter(number -> number % 2 == 1)
                .reduce(0, (x, y) -> x + y);
    }

    public Long elementCount(List<String> elements, String element) {
        return elements.stream()
                .filter(s -> s.equals(element))
                .count();
    }

    public Optional<String> firstElement(List<String> elements) {
        return elements.stream()
                .findFirst();
    }

    public String findElement(List<String> elements, String element) {
        return elements.stream()
                .filter(x -> x.equals(element))
                .findFirst()
                .orElseThrow();
    }

    public Double averageSumOdd(List<Integer> numbers) {
        return IntStream.range(0, numbers.size())
                .map(x -> x % 2 == 1 ? numbers.get(x) - 1 : numbers.get(x))
                .filter(x -> x % 2 == 1)
                .average()
                .orElseThrow();
    }

    public List<People> manSelectByAge(List<People> peopleList, int fromAge, int toAge) {
        return peopleList.isEmpty() ? Collections.emptyList()
                : peopleList.stream()
                .filter(people -> (people.getSex()
                        .equals(People.Sex.MAN)
                        && people.getAge() >= fromAge
                        && people.getAge() <= toAge))
                .collect(Collectors.toList());
    }

    public List<People> workablePeople(int fromAge, int femaleToAge,
                                       int maleToAge, List<People> peopleList) {
        if (peopleList.isEmpty()) {
            return Collections.emptyList();
        }
        return peopleList.stream().filter(people -> people.getAge() >= fromAge
                && (people.getSex().equals(People.Sex.MAN)
                && people.getAge() <= maleToAge)
                || (people.getSex().equals(People.Sex.WOMEN)
                && people.getAge() <= femaleToAge))
                .collect(Collectors.toList());
    }

    public List<String> getCatsNames(List<People> peopleList, int femaleAge) {
        return peopleList.isEmpty() ? Collections.emptyList()
                : peopleList.stream()
                .filter(people -> people.getSex()
                        .equals(People.Sex.WOMEN) && people.getAge() > femaleAge)
                .flatMap(people -> people.getCatList().stream())
                .map(Cat::getName)
                .collect(Collectors.toList());
    }
}
