package university.scholarship;

import university.Database;

import javax.xml.crypto.Data;
import java.util.Objects;

public abstract class Scholarship {

    private String name;

    private RewardCondition rewardCondition;

    private Database db = Database.getInstance();

    public Scholarship(String name) {
        this.name = name;
    }

    public void setName(String name) {
        db.deleteScholarship(this.name);
        this.name = name;
        db.addScholarship(this);
    }

    public String getName() {
        return name;
    }

    public void setRewardCondition(RewardCondition rewardCondition) {
        this.rewardCondition = rewardCondition;
    }

    public RewardCondition getRewardCondition() {
        return rewardCondition;
    }

    public abstract void reward(String studentName);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scholarship that = (Scholarship) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Scholarship{" +
                "name='" + name + '\'' +
                ", rewardCondition=" + rewardCondition +
                '}';
    }
}
