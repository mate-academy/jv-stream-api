package core.basesyntax;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JavaStreamApi {

    public Integer oddSum(List<Integer> numbers) {
        return numbers.stream()
                .filter(number -> number % 2 != 0)
                .reduce(0,Integer::sum);
    }

    public Long elementCount(List<String> elements, String element) {
        return elements.stream()
                .filter(item -> element.equals(item))
                .count();
    }

    public Optional<String> firstElement(List<String> elements) {
        return elements.stream()
                .findFirst();
    }

    public String findElement(List<String> elements, String element) {
        return elements.stream()
               .filter(item -> item.equals(element))
               .findFirst()
               .orElseThrow();
    }

    public Double averageSumOdd(List<Integer> numbers) {
        return IntStream.range(0, numbers.size())
                .map(x -> x % 2 == 0 ? numbers.get(x) : numbers.get(x) - 1)
                .filter(x -> x % 2 == 1)
                .average()
                .orElseThrow();
    }

    public List<People> manSelectByAge(List<People> peopleList, int fromAge, int toAge) {
        return peopleList.stream()
                .filter(people -> people.getSex().equals(People.Sex.MAN)
                        && people.getAge() >= fromAge
                        && people.getAge() <= toAge)
                .collect(Collectors.toList());
    }

    public List<People> workablePeople(int fromAge, int femaleToAge,
                                       int maleToAge, List<People> peopleList) {
        return peopleList.stream()
                .filter(people -> people.getSex().equals(People.Sex.WOMEN)
                        ? people.getAge() >= fromAge && people.getAge() <= femaleToAge
                        : people.getAge() >= fromAge && people.getAge() <= maleToAge)
                .collect(Collectors.toList());
    }

    public List<String> getCatsNames(List<People> peopleList, int femaleAge) {
        return peopleList.stream()
                .filter(people -> people.getSex().equals(People.Sex.WOMEN)
                        && people.getAge() >= femaleAge)
                .flatMap(people -> people.getCatList().stream())
                .map(Cat::getName)
                .collect(Collectors.toList());
    }
}
