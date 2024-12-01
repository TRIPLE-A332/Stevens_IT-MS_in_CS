package PracJava;

class Parent
{
    Parent()
    {
        System.out.println("Non-Param of parent");
    }
    Parent(int x)
    {   
        x= 10;
        System.out.println("Param of parent "+x);
    }
}

class Child extends Parent
{
    private static int x;
        Child()
        {
            System.out.println("Non-Param of child");
        }
        Child(int y)
        {   
            super(x);
        System.out.println("Param of child");
    }
    // Child(int x,int y)
    // {
    //     super(x);
    //     System.out.println("2 param of child "+y);
    // }
}

public class main {

    public static void main(String[] args) {
        //Child c=new Child();
        //Child c=new Child(20);
        Child c=new Child(20);
    }
    
}





// class Subject
// {
//     private String subID;
//     private String name;
//     private int maxMarks;
//     private int marksObtains;
    
//     public Subject(String subID,String name,int maxMarks)
//     {
//         this.subID=subID;
//         this.name=name;
//         this.maxMarks=maxMarks;
//     }
    
//     public String getSubID(){return subID;}
//     public String getName(){return name;}
//     public int getMaxMarks(){return maxMarks;}
//     public int getMarksObtains(){return marksObtains;}
    
//     public void setMaxMarks(int mm)
//     {
//         maxMarks=mm;
//     }
    
//     public void setMarksObtain(int m)
//     {
//         marksObtains=m;
//     }
    
//     boolean isQualified()
//     {
//         return marksObtains>=maxMarks/10*4;
//     }
    
//     public String toString()
//     {
//         return"\n SubjectID: "+subID+"\n Name "+name+"\n MarksObtained "+marksObtains;
//     }
    
// }

// public class main {

//     public static void main(String[] args) 
//     {
//         Subject subs[]=new Subject[3];
//         subs[0]=new Subject("s101","DS",100);
//         subs[1]=new Subject("s102","Algorithms",100);
//         subs[2]=new Subject("s103","Operating Systems",100);
        
//         for(Subject s:subs)
//             System.out.println(s);
//     }
    
// }