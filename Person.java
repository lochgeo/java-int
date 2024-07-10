class Person {
    private String name;
    private int age;
    private int salary;

    public Person setName(String name) {
        this.name = name;
        return this; 
    }

    public Person setAge(int age) {
        this.age = age;
        return this;
    }

    public Person setSalary(int salary) {
        this.salary = salary;
        return this;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}