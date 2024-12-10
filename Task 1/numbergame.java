import java.util.Scanner;
class range
    {
        public int generate(int max,int min)
        {
            return(int)(Math.random()*(max - min + 1) + min);
        }
    } 
public class numbergame{
        public static void main(String[]args)
        {
            Scanner scan = new Scanner(System.in);
            range rng = new range();
            int totalAttempts = 0;
            int win = 0;

            while(true)
            {
                System.out.println("Enter the maximum number ");
                int max = scan.nextInt();
                System.out.println("Enter the minimum number ");
                int min = scan.nextInt();
                scan.nextLine();

                int cn = rng.generate(max,min);
                int attempts = 0;
                while (true)
                {
                    System.out.println("Guess a number between " +min+" and " +max);
                    int gn = scan.nextInt();
                    attempts++;

                    if (gn>cn)
                    {
                        System.out.println("It's too High ");
                    }
                    else if(gn<cn)
                    {
                        System.out.println("It's too Low ");
                    }
                    else
                    {
                        System.out.println("Guess Number is correct ");
                        win++;
                        break;
                    }
                }
                totalAttempts += attempts;
                System.out.println("No of attempts = " + attempts);
                System.out.println("Total Wins = " + win);

                double winrate = (double) win / totalAttempts*100;
                System.out.printf("Your winrate is %.2f%%\n",winrate);
                System.out.println("Do you want to play again [Yes/No] : ");
                String playAgain = scan.next();
                if(!playAgain.equalsIgnoreCase("yes"))
                {
                    scan.close();
                    System.exit(0);
                }
                scan.nextLine();
            }
        }
    }          