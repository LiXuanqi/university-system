package university.scholarship;

import university.people.Student;

import java.util.HashSet;
import java.util.Set;

public class CountryCondition implements RewardCondition {

    private Set<String> targetCountries;

    public CountryCondition() {
        targetCountries = new HashSet<>();
    }

    public void addTargetCountry(String country) {
        targetCountries.add(country);
    }

    public void deleteTargetCountry(String country) {
        targetCountries.remove(country);
    }

    @Override
    public boolean isMatched(Student student) {
        String country = student.getCountry();
        return targetCountries.contains(country);
    }
}
