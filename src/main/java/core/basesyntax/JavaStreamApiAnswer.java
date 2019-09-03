package core.basesyntax;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JavaStreamApiAnswer {
    /**
     * 1. Дано: List<Integer> numbers.
     * Вернуть сумму нечетных числел или 0, если таких несуществует
     **/
    public Integer oddSum(List<Integer> numbers) {
        return numbers.stream().filter(number -> number % 2 != 0).mapToInt((a) -> a).sum();
    }
    /**
     * 2. Дана коллекция строк Arrays.asList(«a1», «a2», «a3», «a1»).
     * Вернуть количество вхождений объекта «a1»
     **/
    public Long elementCount(List<String> elements, String element) {
        return elements.stream().filter(e -> e.equals(element)).count();
    }
    /**
     * 3. Дана коллекция строк Arrays.asList(«a1», «a2», «a3», «a1»).
     * Вернуть Optional первого элемента коллекции
     **/
    public Optional<String> firstElement(List<String> elements) {
        return elements.stream()
                .findFirst();
    }
    /**
     * 4. Дана коллекция строк Arrays.asList(«a1», «a2», «a3», «a1»).
     * Найти элемент в коллекции равный «a3» или кинуть ошибку
     **/
    public String findElement(List<String> elements, String element) {
        return elements.stream()
                .filter(e -> e.equals(element))
                .findAny()
                .orElseThrow();
    }
    /**
     * 5. Дана коллекция чисел Arrays.asList(6, 2, 3, 7, 2, 5)
     * Отнимите от каждого элемента, который стоит на непарной позиции
     * (имеет не парный индекс) 1
     * и верните среднее арифметическое всех нечетных чисел или киньте ошибку
     **/
    public Double averageSumOdd(List<Integer> numbers) {
        IntStream.range(0, numbers.size())
                .filter(index -> index % 2 != 0)
                .forEach(index -> numbers.set(index, numbers.get(index) - 1));
        return numbers.stream()
                .filter(number -> number % 2 != 0)
                .mapToInt(number -> number)
                .average()
                .orElseThrow();
    }
    /**
     * 6. Дана коллекция класс People (с полями name — имя, age — возраст, sex — пол),
     * вида Arrays.asList( new People(«Вася», 16, Sex.MAN),
     * new People(«Петя», 23, Sex.MAN),
     * new People(«Елена», 42, Sex.WOMEN),
     * new People(«Иван Иванович», 69, Sex.MAN)).
     * Давайте посмотрим примеры как работать с таким классом:
     * - Выбрать мужчин-военнообязанных (от 18 до 27 лет)
     **/
    public List<People> manSelectByAge(List<People> peopleList, int fromAge, int toAge) {
        return peopleList.stream()
                .filter(person ->
                        person.getSex().equals(People.Sex.MAN) &&
                                person.getAge() >= fromAge &&
                                person.getAge() <= toAge)
                .collect(Collectors.toList());
    }
    /**
     * 6. Дана коллекция класс People (с полями name — имя, age — возраст, sex — пол),
     * вида Arrays.asList( new People(«Вася», 16, Sex.MAN),
     * new People(«Петя», 23, Sex.MAN),
     * new People(«Елена», 42, Sex.WOMEN),
     * new People(«Иван Иванович», 69, Sex.MAN)).
     * Давайте посмотрим примеры как работать с таким классом:
     * - Найти всех потенциально работоспособных людей в выборке
     * (т.е. от 18 лет и учитывая что женщины выходят в 55 лет, а мужчина в 60)
     **/
    public List<People> workablePeople(int fromAge, int femaleToAge,
                                       int maleToAge, List<People> peopleList) {
        return peopleList.stream()
                .filter(person ->
                        (person.getSex().equals(People.Sex.MAN) &&
                                person.getAge() >= fromAge &&
                                person.getAge() <= maleToAge) ||
                                (person.getSex().equals(People.Sex.WOMEN) &&
                                        person.getAge() >= fromAge &&
                                        person.getAge() <= femaleToAge))
                .collect(Collectors.toList());
    }
    /**
     * 7. Дано коллекцию Collection<People> peoples.
     * Класс People (с полями name — имя, age — возраст, sex — пол, List<Cat> cats -  кошки этого человека).
     * Дано класс Cat (name- имя кошки, age - возраст кошки).
     * Задача: вивести все имена кошек в которых хозяева это девушки старше 18 лет
     **/
    public List<String> getCatsNames(List<People> peopleList, int femaleAge) {
        return peopleList.stream()
                .filter(person ->
                        person.getSex().equals(People.Sex.WOMEN) &&
                                person.getAge() >= femaleAge &&
                                !person.getCatList().isEmpty())
                .flatMap(people -> people.getCatList().stream().map(Cat::getName))
                .collect(Collectors.toList());
    }
}