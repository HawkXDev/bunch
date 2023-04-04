package dynamicproxy;

/**
 * Imagine that we got class Man as part of a ready-made JAR library and can't just rewrite its code
 */
public class Man implements Person {
    private String name;
    private int age;
    private String city;
    private String country;

    public Man(String name, int age, String city, String country) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.country = country;
    }

    @Override
    public void introduce(String name) {
        System.out.println("My name is " + name);
    }

    @Override
    public void sayAge(int age) {
        System.out.println("I am " + age + " years old");
    }

    @Override
    public void sayFrom(String city, String country) {
        System.out.println("I'm from " + city + ", " + country);
    }

    // getters, setters, etc.

    public String getName() {
        return name;
    }
}
