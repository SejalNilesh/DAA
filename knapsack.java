package knapsack;

import java.util.*;

class Item {
    int totalCost, weight;
    double perUnit;
    public Item(int tc, int w) 
    {
        this.totalCost = tc;
        this.weight = w;
        this.perUnit = (double) totalCost / weight;
    }
}

public class knapsack {
    public static void accept(Scanner sc, Item[] items) 
    {
        for (int i = 0; i < items.length; i++) 
        {
            int tC = -1, w = -1;
            while (tC < 0) 
            {
                System.out.println("Enter Total profit of item " + (i + 1));
                tC = sc.nextInt();
                if (tC < 0) 
                {
                    System.out.println("Invalid cost of item " + (i + 1) + " Enter value Again");
                }
            }
            while (w < 0) 
            {
                System.out.println("Enter Total weight of item " + (i + 1));
                w = sc.nextInt();
                if (w < 0) 
                {
                    System.out.println("Invalid weight of item " + (i + 1) + " Enter value Again");
                }
            }
            items[i] = new Item(tC, w);
        }
    }

    public static int currentMax(Item[] items) 
    {
        double r = -1;
        int o = -1;
        for (int i = 0; i < items.length; i++) 
        {
            if (items[i].weight > 0 && items[i].perUnit > r) 
            {
                r = items[i].perUnit;
                o = i;
            }
        }
        return o;
    }

    public static double maxProfit(Item[] items, int cap) 
    {
        double mx = 0;
        while (cap > 0) 
        {
            int curr = currentMax(items);
            if (curr == -1) break;

            if (items[curr].weight <= cap) 
            {
                cap -= items[curr].weight;
                mx += items[curr].totalCost;
                items[curr].weight = 0;
            } 
            else 
            {
                mx += cap * items[curr].perUnit;
                items[curr].weight -= cap;
                cap = 0;
            }
        }
        return mx;
    }

    public static void printMenu()
    {
        System.out.println("----MENU----");
        System.out.println("1.Enter data of available items");
        System.out.println("2.Find out Total Profit");
        System.out.println("3.Exit");
        System.out.println("Enter Your choice");
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        Item[] items = new Item[0];
        int cap = 0, n = 0;
        int ch = 0;
        do
        {
            printMenu();
            ch = sc.nextInt();
            switch(ch)
            {
               case 1:
                 do 
                 {
                     System.out.println("Enter number of items:");
                     n = sc.nextInt();
                     if (n <= 0) 
                     {
                         System.out.println("Invalid number of items. Enter value Again");
                     }
                 }while (n <= 0);
                 items = new Item[n];
                 accept(sc, items);
                 System.out.println("Items added successfully");
                 break;
               case 2:
                 if(items.length == 0)
                 {
                     System.out.println("No items Exist.Enter items first");
                 }
                 else
                 {
                     System.out.println("Enter Capacity:");
                     cap = sc.nextInt();
                     double result = maxProfit(items, cap);
                     System.out.println("Maximum profit: " + result);
                 }
                 break;
               case 3:
                 System.out.println("EXITING----");
                 break;
               default:
                 System.out.println("Invalid Choice enter again:");
                 break;
            }
        }while(ch != 3);
        sc.close();
    }
}
