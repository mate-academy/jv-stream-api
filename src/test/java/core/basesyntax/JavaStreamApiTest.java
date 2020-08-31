package core.basesyntax;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class JavaStreamApiTest {
    private List<String> elements;
    private List<People> peopleList;
    private List<People> peopleListWithoutCat;
    private JavaStreamApi javaStreamApi;

    @Before
    public void setUp() {
        javaStreamApi = new JavaStreamApi();
        elements = Arrays.asList("a1", "a2", "a3", "a1");
        peopleList = new ArrayList<>();
        peopleList.add(new People("Victor", 16, People.Sex.MAN));
        peopleList.add(new People("Peter", 23, People.Sex.MAN));
        peopleList.add(new People("Helen", 42, People.Sex.WOMEN, new ArrayList<>()));
        peopleList.get(2).getCatList().add(new Cat("Tom", 2));
        peopleList.get(2).getCatList().add(new Cat("Leo", 3));
        peopleList.add(new People("Jack Johnson", 69, People.Sex.MAN));
        peopleList.add(new People("Rick", 37, People.Sex.MAN, new ArrayList<>()));
        peopleList.get(4).getCatList().add(new Cat("Chloe", 1));
        peopleList.add(new People("Mary", 25, People.Sex.WOMEN, new ArrayList<>()));
        peopleList.get(5).getCatList().add(new Cat("Sunny", 1));
        peopleList.add(new People("Emma Stuart", 55, People.Sex.WOMEN, new ArrayList<>()));
        peopleList.add(new People("Alice Stone", 57, People.Sex.WOMEN, new ArrayList<>()));
        peopleList.get(6).getCatList().add(new Cat("Kitty", 3));
        peopleList.get(6).getCatList().add(new Cat("Fluffy", 4));
        peopleList.add(new People("Janice Dean", 18, People.Sex.WOMEN, new ArrayList<>()));
        peopleList.get(7).getCatList().add(new Cat("Jackie", 2));
        peopleList.add(new People("Roman", 25, People.Sex.MAN));
        peopleList.add(new People("Carlos", 60, People.Sex.MAN));

        peopleListWithoutCat = new ArrayList<>();
        peopleListWithoutCat.add(new People("Helen", 16, People.Sex.WOMEN));
        peopleListWithoutCat.add(new People("Mary", 25, People.Sex.WOMEN));
        peopleListWithoutCat.add(new People("Emma Stuart", 20, People.Sex.WOMEN));
        peopleListWithoutCat.add(new People("Victor", 23, People.Sex.MAN));
    }

    @Test
    public void oddSum() {
        Integer expected = 9;
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Integer result = javaStreamApi.getOddNumsSum(numbers);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void oddSumOneNumber() {
        Integer expected = 9;
        Integer result = javaStreamApi.getOddNumsSum(Arrays.asList(9));
        Assert.assertEquals(expected, result);
    }

    @Test
    public void oddSumOddAbsent() {
        Integer expected = 0;
        Integer result = javaStreamApi.getOddNumsSum(Arrays.asList(6, 2, 8, 4, 12));
        Assert.assertEquals(expected, result);
    }

    @Test
    public void elementCount() {
        Long expected = 2L;
        Long result = javaStreamApi.calculateOccurrences(elements, "a1");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void elementCountAbsent() {
        Long expected = 0L;
        Long result = javaStreamApi.calculateOccurrences(elements, "a4");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void firstElement() {
        Optional<String> expected = Optional.of("a1");
        Optional<String> result = javaStreamApi.getFirstElement(elements);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void firstElementEmptyList() {
        Optional<String> expected = Optional.empty();
        Optional<String> result = javaStreamApi.getFirstElement(Collections.emptyList());
        Assert.assertEquals(expected, result);
    }

    @Test
    public void findElement() {
        String expected = "a3";
        String result = javaStreamApi.findElement(elements, expected);
        Assert.assertEquals(expected, result);
    }

    @Test(expected = NoSuchElementException.class)
    public void findElementIsNotInList() {
        String expected = "a4";
        String result = javaStreamApi.findElement(elements, expected);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void averageSumOddPartOdd() {
        Double expected = 2.0;
        List<Integer> digits = Arrays.asList(6, 2, 3, 7, 2, 5);
        Double result = javaStreamApi.getOddNumsAverage(digits);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void averageSumOddAllOdd() {
        Double expected = 4.0;
        List<Integer> digits = Arrays.asList(7, 2, 3, 4, 5, 6);
        Double result = javaStreamApi.getOddNumsAverage(digits);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void averageSumOddOneOdd() {
        Double expected = 3.0;
        List<Integer> digits = Arrays.asList(6, 4, 8, 7, 2, 5);
        Double result = javaStreamApi.getOddNumsAverage(digits);
        Assert.assertEquals(expected, result);
    }

    @Test(expected = NoSuchElementException.class)
    public void averageSumOddOptionalEmpty() {
        Double expected = 2.0;
        List<Integer> digits = Arrays.asList(6, 1, 2, 7, 2, 5);
        Double result = javaStreamApi.getOddNumsAverage(digits);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void manSelectByAge() {
        List<People> expected = new ArrayList<>();
        expected.add(new People("Peter", 23, People.Sex.MAN));
        expected.add(new People("Rick", 37, People.Sex.MAN));
        expected.get(1).getCatList().add(new Cat("Chloe", 1));
        expected.add(new People("Roman", 25, People.Sex.MAN));
        expected.add(new People("Roman", 37, People.Sex.MAN));
        List<People> result = javaStreamApi.selectMenByAge(peopleList, 18, 37);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void manSelectByAgeAbsent() {
        List<People> expected = new ArrayList<>();
        List<People> result = javaStreamApi.selectMenByAge(peopleList, 14, 15);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void workablePeople() {
        List<People> expected = new ArrayList<>();
        expected.add(new People("Peter", 23, People.Sex.MAN));
        expected.add(new People("Helen", 42, People.Sex.WOMEN, new ArrayList<>()));
        expected.get(1).getCatList().add(new Cat("Tom", 2));
        expected.get(1).getCatList().add(new Cat("Leo", 3));
        expected.add(new People("Rick", 37, People.Sex.MAN, new ArrayList<>()));
        expected.get(2).getCatList().add(new Cat("Chloe", 1));
        expected.add(new People("Mary", 25, People.Sex.WOMEN, new ArrayList<>()));
        expected.get(3).getCatList().add(new Cat("Sunny", 1));
        expected.add(new People("Emma Stuart", 55, People.Sex.WOMEN, new ArrayList<>()));
        expected.get(4).getCatList().add(new Cat("Kitty", 3));
        expected.get(4).getCatList().add(new Cat("Fluffy", 4));
        expected.add(new People("Janice Dean", 18, People.Sex.WOMEN, new ArrayList<>()));
        expected.add(new People("Roman", 25, People.Sex.MAN));
        expected.add(new People("Carlos", 60, People.Sex.MAN));
        List<People> result = javaStreamApi.getWorkablePeople(18, 55, 60, peopleList);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void workablePeopleAbsent() {
        List<People> expected = new ArrayList<>();
        List<People> result = javaStreamApi.getWorkablePeople(12, 14, 15, peopleList);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void getCatsNames() {
        List<String> expected = Arrays.asList("Tom", "Leo", "Sunny", "Kitty", "Fluffy", "Jackie");
        List<String> result = javaStreamApi.getCatsNames(peopleList, 18);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void getEmptyListCatsNames() {
        List<String> expected = new ArrayList<>();
        List<String> result = javaStreamApi.getCatsNames(peopleListWithoutCat, 18);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void getCatsNamesAbsent() {
        List<String> expected = new ArrayList<>();
        List<String> result = javaStreamApi.getCatsNames(peopleList, 60);
        Assert.assertEquals(expected, result);
    }
}
