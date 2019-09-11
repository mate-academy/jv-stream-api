package core.basesyntax;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public class JavaStreamApi {

    /**
     * <p>1. Дано: List of Integer numbers.
     * Вернуть сумму нечетных числел или 0, если таких несуществует</p>
     **/
    public Integer oddSum(List<Integer> numbers) {
        int result = numbers.stream().filter(p -> p % 2 != 0).mapToInt(p -> p).sum();
        return result;
    }

    /**
     * <p>2. Дана коллекция строк List of String elements
     * (пример: Arrays.asList(«a1», «a2», «a3», «a1»)).
     * Вернуть количество вхождений объекта `element`</p>
     **/
    public Long elementCount(List<String> elements, String element) {
        int result = Collections.frequency(elements, element);
        return Long.valueOf(result);
    }

    /**
     * <p>3. Дана коллекция строк List of String elements
     * (пример: Arrays.asList(«a1», «a2», «a3», «a1»)).
     * Вернуть Optional первого элемента коллекции</p>
     **/
    public Optional<String> firstElement(List<String> elements) {
        Optional<String> firstElement = elements.stream().findFirst();
        return firstElement;
    }

    /**
     * <p>4. Дана коллекция строк List of String elements
     * (приметр: Arrays.asList(«a1», «a2», «a3», «a1»)).
     * Найти элемент в коллекции равный `element`
     * или кинуть ошибку NoSuchElementException</p>
     **/
    public String findElement(List<String> elements, String element) {
        String result = "";
        if (elements.contains(element)) {
            result = element;
        } else {
            throw new NoSuchElementException();
        }
        return result;
    }

    /**
     * <p>5. Дана коллекция чисел List of Integer numbers (пример: Arrays.asList(6, 2, 3, 7, 2, 5))
     * Отнимите от каждого элемента, который стоит на непарной позиции (имеет не парный индекс) 1
     * и верните среднее арифметическое всех нечетных чисел или киньте ошибку
     * NoSuchElementException</p>
     **/
    public Double averageSumOdd(List<Integer> numbers) {
        for (int i = 1; i < numbers.size(); i += 2) {
            Integer value = numbers.get(i);
            numbers.set(i, value - 1);
        }
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .filter(n -> n % 2 != 0)
                .average()
                .orElseThrow(NoSuchElementException::new);
    }

    /**
     * <p>6. Дана коллекция класс People (с полями name — имя, age — возраст, sex — пол),
     * вида Arrays.asList( new People(«Вася», 16, Sex.MAN),
     * new People(«Петя», 23, Sex.MAN),
     * new People(«Елена», 42, Sex.WOMEN),
     * new People(«Иван Иванович», 69, Sex.MAN)).
     * Задача: Выбрать мужчин-военнообязанных (от `fromAge` до `toAge` лет)</p>
     **/
    public List<People> manSelectByAge(List<People> peopleList, int fromAge, int toAge) {
        peopleList = peopleList.stream()
                .filter(p -> p.getAge() >= fromAge && p.getAge() <= toAge
                        && p.getSex() == People.Sex.MAN).collect(Collectors.toList());
        return peopleList;
    }

    /**
     * <p>6. Дана коллекция класс People (с полями name — имя, age — возраст, sex — пол),
     * вида Arrays.asList( new People(«Вася», 16, Sex.MAN),
     * new People(«Петя», 23, Sex.MAN),
     * new People(«Елена», 42, Sex.WOMEN),
     * new People(«Иван Иванович», 69, Sex.MAN)).
     * Задача: Найти всех потенциально работоспособных людей в выборке от fromAge до
     * femaleToAge для женщин, и от fromAge до maleToAge для мужчин
     * Пример: от 18 лет и учитывая что женщины выходят в 55 лет, а мужчина в 60</p>
     **/
    public List<People> workablePeople(int fromAge, int femaleToAge,
                                       int maleToAge, List<People> peopleList) {
        peopleList = peopleList.stream().filter(p -> p.getAge() >= fromAge)
                .filter(p -> (p.getAge() <= maleToAge) && (p.getSex() == People.Sex.MAN)
                        || (p.getAge() <= femaleToAge && p.getSex() == People.Sex.WOMEN))
                .collect(Collectors.toList());
        return peopleList;
    }

    /**
     * <p>7. Дано коллекцию List of peoples. Класс People (с полями name — имя, age — возраст,
     * sex — пол, List of Cats -  кошки этого человека).
     * Дано класс Cat (name - имя кошки, age - возраст кошки).
     * Задача: вивести все имена кошек в которых хозяева это девушки старше 18 лет</p>
     **/
    public List<String> getCatsNames(List<People> peopleList, int femaleAge) {
        List<String> catList =
                peopleList.stream()
                        .filter(p -> p.getAge() >= femaleAge && p.getSex() == People.Sex.WOMEN)
                        .map(p -> p.getCatList())
                        .flatMap(Collection::stream)
                        .map(p -> p.getName())
                        .collect(Collectors.toList());
        return catList;
    }
}
