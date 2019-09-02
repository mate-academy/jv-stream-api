package core.basesyntax;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class JavaStreamApiTest {
    private List<Integer> numbers;
    private List<String> elements;
    private List<Integer> digits;
    private List<People> peopleList;
    private JavaStreamApi javaStreamApi;

    @Before
    public void setUp() {
        numbers = Arrays.asList(1, 2, 3, 4, 5);
        elements = Arrays.asList("a1", "a2", "a3", "a1");
        digits = Arrays.asList(6, 2, 3, 7, 2, 5);
        peopleList =
                Arrays.asList(new People("Вася", 16, People.Sex.MAN),
                        new People("Петя", 23, People.Sex.MAN),
                        new People("Елена", 42, People.Sex.WOMEN,
                                Arrays.asList(new Cat("Матроскин", 2),
                                        new Cat("Барсик", 3))),
                        new People("Иван Иванович", 69, People.Sex.MAN));
        javaStreamApi = new JavaStreamApi();
    }

    @Test
    public void oddSum() {
        Integer expected = 9;
        Integer result = javaStreamApi.oddSum(numbers);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void elementCount() {
        Long expected = 2L;
        Long result = javaStreamApi.elementCount(elements, "a1");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void firstElement() {
        String expected = "a1";
        String result = javaStreamApi.firstElement(elements);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void firstElementEmptyList() {
        String expected = "0";
        String result = javaStreamApi.firstElement(Collections.emptyList());
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
    public void averageSumOdd() {
        Double expected = 2.0;
        Double result = javaStreamApi.averageSumOdd(digits);
        Assert.assertEquals(expected, result);
    }

    @Test(expected = NoSuchElementException.class)
    public void averageSumOddOptionalEmpty() {
        Double expected = 2.0;
        List<Integer> digitsCurrent = digits; //Arrays.asList(6, 1, 2, 7, 2, 5);
        digitsCurrent.set(1, 1);
        digitsCurrent.set(2, 2);
        Double result = javaStreamApi.averageSumOdd(digitsCurrent);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void manSelectByAge() {
        List<People> expected = new ArrayList<>();
        expected.add(new People("Петя", 23, People.Sex.MAN));
        List<People> result = javaStreamApi.manSelectByAge(peopleList, 18, 27);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void workablePeople() {
        List<People> expected = new ArrayList<>();
        expected.add(new People("Петя", 23, People.Sex.MAN));
        expected.add(new People("Елена", 42, People.Sex.WOMEN,
                Arrays.asList(new Cat("Матроскин", 2),
                        new Cat("Барсик", 3))));
        List<People> result = javaStreamApi.workablePeople(18, 55, 18, 60, peopleList);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void femaleWithCat() {
        List<People> expected = new ArrayList<>();
        expected.add(new People("Елена", 42, People.Sex.WOMEN,
                Arrays.asList(new Cat("Матроскин", 2),
                        new Cat("Барсик", 3))));
        List<People> result = javaStreamApi.femaleWithCat(peopleList, 18);
        Assert.assertEquals(expected, result);
    }
}