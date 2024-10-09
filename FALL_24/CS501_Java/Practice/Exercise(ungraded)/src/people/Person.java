package people;

public class Person {
    
    private String name, address ;
    private int yearOfBirth;

    public Person(){
        name = "NA";
        address = "NA";
        yearOfBirth = 0;
    }

    public Person(String n , String addre , int yearOfBirth){
        n = name;
        addre = address;
        this.yearOfBirth = yearOfBirth;
    }

    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }
    public int  getYearOfBirth(){
        return yearOfBirth;
    }

    public void setName(String n){
        n = name;
    }
    public void setAddress(String addre){
        addre = address;
    }
    public void setYearOfBirth(int yearOfBirth){
        this.yearOfBirth = yearOfBirth;
    }

    public boolean canVote(){
        return (2024 - yearOfBirth) >= 18;
    }

}
