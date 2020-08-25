package core.basesyntax;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JavaStreamApi {

    public Integer oddSum(List<Integer> numbers) {
        return numbers.stream().filter((x) -> x % 2 != 0).reduce(Integer::sum).orElse(0);
    }

    public Long elementCount(List<String> elements, String element) {
        return elements.stream().filter(element::equals).count();
    }

    public Optional<String> firstElement(List<String> elements) {
        return elements.stream().findFirst();
    }

    public String findElement(List<String> elements, String element) {
        return elements.stream().filter(element::equals).findFirst().get();
    }

    public Double averageSumOdd(List<Integer> numbers) {
        return IntStream.range(0, numbers.size())
                .map((index) -> index % 2 != 0 ? numbers.get(index) - 1 : numbers.get(index))
                .filter(number -> number % 2 != 0)
                .average()
                .orElseThrow();
    }

    public List<People> manSelectByAge(List<People> peopleList, int fromAge, int toAge) {
        return peopleList.stream()
                .filter((human) -> human.getSex() == People.Sex.MAN)
                .filter((man) -> man.getAge() > fromAge && man.getAge() < toAge)
                .collect(Collectors.toList());
    }

    public List<People> workablePeople(int fromAge, int femaleToAge,
                                       int maleToAge, List<People> peopleList) {
        return peopleList.stream()
                .filter((human) -> human.getAge() > fromAge)
                .filter((human) -> human.getSex() == People.Sex.WOMEN
                        && human.getAge() <= femaleToAge
                        || human.getSex() == People.Sex.MAN && human.getAge() <= maleToAge)
                .collect(Collectors.toList());
    }

    public List<String> getCatsNames(List<People> peopleList, int femaleAge) {
        return peopleList.stream()
                .filter((girl) -> girl.getSex() == People.Sex.WOMEN && girl.getAge() > femaleAge)
                .flatMap((girl) -> girl.getCatList().stream())
                .map(Cat::getName)
                .collect(Collectors.toList());
    }
}
