package core.basesyntax;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JavaStreamApi {

    public Integer oddSum(List<Integer> numbers) {
        return numbers.stream()
                .filter(x -> x % 2 != 0)
                .mapToInt(Integer::intValue)
                .sum();
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
                .filter(element::equals)
                .findFirst()
                .orElseThrow();
    }

    public Double averageSumOdd(List<Integer> numbers) {
        return IntStream.range(0, numbers.size())
                .map(index -> index % 2 != 0 ? numbers.get(index) - 1 : numbers.get(index))
                .filter(n -> n % 2 != 0)
                .average()
                .orElseThrow();
    }

    public List<People> manSelectByAge(List<People> peopleList, int fromAge, int toAge) {
        return peopleList.stream()
                .filter(x -> x.getSex() == x.getSex().MAN
                        && x.getAge() > fromAge && x.getAge() < toAge)
                .collect(Collectors.toList());
    }

    public List<People> workablePeople(int fromAge, int femaleToAge,
                                       int maleToAge, List<People> peopleList) {
        return peopleList.stream()
                .filter(x -> x.getAge() >= fromAge
                        && x.getSex() == x.getSex().MAN
                        && x.getAge() <= maleToAge
                        || x.getSex() == x.getSex().WOMEN && x.getAge() <= femaleToAge)
                .collect(Collectors.toList());
    }

    public List<String> getCatsNames(List<People> peopleList, int femaleAge) {
        return peopleList.stream()
                .filter(x -> x.getSex() == x.getSex().WOMEN && x.getAge() > femaleAge)
                .flatMap(x -> x.getCatList().stream())
                .map(x -> x.getName())
                .collect(Collectors.toList());
    }
}
