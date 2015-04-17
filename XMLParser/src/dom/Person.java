package dom;

/**
 * Created by Hugh on 2015/3/23 0023.
 */
public class Person {
    private StringBuilder info;
    private double income;
    private int age;

    public Person() {
        info = new StringBuilder();
        income = -1;
        age = -1;
    }

    public void addInfo(String s){
        info.append(s);
    }

    public StringBuilder getInfo() {
        return info;
    }

    public void setInfo(StringBuilder info) {
        this.info = info;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
