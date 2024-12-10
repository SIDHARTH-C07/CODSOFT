import java.util.Scanner;

public class gradecalc {
    public static void main(String[]args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Student Grade Calculator");
        System.out.println("Number of Subject");
        int nos = scan.nextInt();
        int total = 0;
        for (int i = 1;i<=nos;i++){
            System.out.println("Enter marks scored in " +i+" subject : ");
            int marks = scan.nextInt();
            total += marks;
        }
        scan.close();
        double avg = (double)total/nos;
        char Grade;
        if(avg>=90)
        {
            Grade = 'S'; 
        }
        else if(avg>=80)
        {
            Grade = 'A'; 
        }
        else if(avg>=70)
        {
            Grade = 'B'; 
        }
        else if(avg>=60)
        {
            Grade = 'C'; 
        }
        else if(avg>=50)
        {
            Grade = 'D'; 
        }
        else if(avg>=40)
        {
            Grade = 'E'; 
        }
        else
        {
            Grade = 'F';
        }
        System.out.println("Total Marks is "+ total+ "\nAverage percentage is "+ avg + " %\n"+"Grade is "+ Grade );
    }
}
