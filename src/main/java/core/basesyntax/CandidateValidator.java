package core.basesyntax;

import java.util.function.Predicate;

public class CandidateValidator implements Predicate<Candidate> {
    private static int MIN_AGE = 35;
    private static int MIN_YEARS_IN_COUNTRY = 10;
    private static String REQUIRED_NATIONALITY = "Ukrainian";
    private static String DATE_SPLIT_REGEX = "-";
    private static int YEAR_FROM_INDEX = 0;
    private static int YEAR_TO_INDEX = 1;

    @Override
    public boolean test(Candidate person) {
        boolean hasProperAge = person.getAge() >= MIN_AGE;
        String[] livedEnoughInUkr = person.getPeriodsInUkr().split(DATE_SPLIT_REGEX);
        int periodDuration = Integer.parseInt(livedEnoughInUkr[YEAR_TO_INDEX])
            - Integer.parseInt(livedEnoughInUkr[YEAR_FROM_INDEX]);
        boolean livedInUrkEnough = periodDuration >= MIN_YEARS_IN_COUNTRY;
        boolean nationalityFit = person.getNationality().equals(REQUIRED_NATIONALITY);
        return hasProperAge && livedInUrkEnough
            && nationalityFit && person.isAllowedToVote();
    }
}
