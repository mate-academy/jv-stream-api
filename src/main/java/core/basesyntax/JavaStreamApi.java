package core.basesyntax;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public class JavaStreamApi {

    public Integer oddSum(List<Integer> numbers) {
        return numbers.stream().filter(number -> number % 2 == 1).mapToInt(Integer::intValue).sum();
    }

    public Long elementCount(List<String> elements, String element) {
        return elements.stream().filter(s -> s.equals(element)).count();
    }

    public Optional<String> firstElement(List<String> elements) {
        return elements.isEmpty() ? Optional.empty() : Optional.ofNullable(elements.get(0));
    }

    public String findElement(List<String> elements, String element) {
        if (elements.indexOf(element) == -1) {
            throw new NoSuchElementException();
        }
        return element;
    }

    public Double averageSumOdd(List<Integer> numbers) {
        if (numbers.isEmpty()) {
            throw new NoSuchElementException();
        }
        int[] index = {0};
        return numbers.stream()
                .mapToInt(x -> index[0]++ % 2 == 1 ? x - 1 : x)
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
        return peopleList.stream().filter(people -> {
            if (people.getSex().equals(People.Sex.MAN)
                    && people.getAge() >= fromAge
                    && people.getAge() <= maleToAge) {
                return true;
            }
            return people.getSex().equals(People.Sex.WOMEN)
                    && people.getAge() >= fromAge
                    && people.getAge() <= femaleToAge;
        }).collect(Collectors.toList());
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
