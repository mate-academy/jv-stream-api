package core.basesyntax;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JavaStreamApi {

    /**
     * <p>1. Дано: List of Integer numbers.
     * Вернуть сумму нечетных числел или 0, если таких несуществует</p>
     **/
    public Integer oddSum(List<Integer> numbers) {
        return numbers.stream()
                .filter(p -> p % 2 == 1)
                .reduce(0, Integer::sum);
    }

    /**
     * <p>2. Дана коллекция строк List of String elements
     * (пример: Arrays.asList(«a1», «a2», «a3», «a1»)).
     * Вернуть количество вхождений объекта `element`</p>
     **/
    public Long elementCount(List<String> elements, String element) {
        return elements.stream()
                .filter(element::equals)
                .count();
    }

    /**
     * <p>3. Дана коллекция строк List of String elements
     * (пример: Arrays.asList(«a1», «a2», «a3», «a1»)).
     * Вернуть Optional первого элемента коллекции</p>
     **/
    public Optional<String> firstElement(List<String> elements) {
        return elements.stream()
                .findFirst();
    }

    /**
     * <p>4. Дана коллекция строк List of String elements
     * (приметр: Arrays.asList(«a1», «a2», «a3», «a1»)).
     * Найти элемент в коллекции равный `element` или кинуть ошибку NoSuchElementException</p>
     **/
    public String findElement(List<String> elements, String element) {
        return elements.stream()
                .filter(element::equals)
                .findFirst()
                .orElseThrow();
    }

    /**
     * <p>5. Дана коллекция чисел List of Integer numbers (пример: Arrays.asList(6, 2, 3, 7, 2, 5))
     * Отнимите от каждого элемента, который стоит на непарной позиции (имеет не парный индекс) 1
     * и верните среднее арифметическое всех нечетных чисел или киньте ошибку
     * NoSuchElementException</p>
     **/
    public Double averageSumOdd(List<Integer> numbers) {
        return IntStream.range(0, numbers.size())
                .map(n -> n % 2 == 1 ? numbers.get(n) - 1 : numbers.get(n))
                .filter(n -> n % 2 == 1)
                .average()
                .orElseThrow();
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
        return peopleList.stream()
                .filter(p -> p.getSex() == People.Sex.MAN
                        && p.getAge() <= toAge
                        && p.getAge() >= fromAge)
                .collect(Collectors.toList());
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
        return peopleList.stream()
                .filter(p -> p.getAge() >= fromAge
                        && (p.getSex() == People.Sex.MAN && p.getAge() <= maleToAge
                        || p.getSex() == People.Sex.WOMEN && p.getAge() <= femaleToAge))
                .collect(Collectors.toList());
    }

    /**
     * <p>7. Дано коллекцию List of peoples. Класс People (с полями name — имя, age — возраст,
     * sex — пол, List of Cats -  кошки этого человека).
     * Дано класс Cat (name - имя кошки, age - возраст кошки).
     * Задача: вивести все имена кошек в которых хозяева это девушки старше 18 лет</p>
     **/
    public List<String> getCatsNames(List<People> peopleList, int femaleAge) {
        return peopleList.stream()
                .filter(p -> p.getSex().equals(People.Sex.WOMEN) && p.getAge() >= femaleAge)
                .flatMap(p -> p.getCatList().stream())
                .map(Cat::getName)
                .collect(Collectors.toList());
    }
}
