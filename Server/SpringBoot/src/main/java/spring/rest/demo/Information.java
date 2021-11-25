package spring.rest.demo;

public class Information {
  private String name;
  private int age;

  public Information(String name, int age){
    this.name = name;
    this.age = age;
  }

  public String getName(){
    return name;
  }

  public int getAge(){
    return age;
  }
}
