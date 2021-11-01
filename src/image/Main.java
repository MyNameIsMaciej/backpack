package image;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) throws IOException {

        File file= new File("E:\\programy\\Intelij IDEA\\IntelliJ IDEA Community Edition 2021.1.1\\backpack\\backpack.txt");
        Scanner read= new Scanner(file);
        String [][] arr= new String[14][3];
        String [][] arrNew= new String[14][3];
        String [] startArr= new String[14];
        int counter=0;
        while(read.hasNext())
        {
            String line = read.nextLine();
            startArr[counter]=line;
            counter++;
        }
        String [] arrName= new String[14];
        int [][] arrBackpack= new int[14][20];
        int[][] arrValue= new int[14][2];


        for(int i=0;i<startArr.length;i++)
        {
            // zamiana na integery i wyznaczanie najbardziej oplacalnej rzeczy w stosunku do wagi
            arr[i]=startArr[i].split(" ");
        }
        for(int x=0; x<arrBackpack.length;x++) {
            int value = Integer.parseInt(arr[x][1]);
            int itemWeight = Integer.parseInt(arr[x][2]);
            arrValue[x][0]=value;
            arrValue[x][1]=itemWeight;
            arrName[x]=(arr[x][0]);
            for (int z = 0; z < arrBackpack[x].length; z++)
            {
                // zapisywanie do indexu 0 maxa dla wszystkich plecakow
                if(x==0)
                {
                    if(z+1>=itemWeight)
                    {
                        arrBackpack[x][z]=value;
                    }
                }
                else {
                    // sprawdzanie czy itemWeight nie przekracza max wagi plecaka
                    //spr czy obiekt zmiesci sie do plecaka od 1 do 20 kg
                    if(z+1>=itemWeight){
                        if(z-itemWeight<0){
                            if(value>arrBackpack[x-1][z]) {
                                arrBackpack[x][z] = value;
                            }else
                            {arrBackpack[x][z] = arrBackpack[x - 1][z];}
                        }else {
                            if (arrBackpack[x-1][z] < (value + arrBackpack[x - 1][z - itemWeight])) {

                                arrBackpack[x][z] = (value + arrBackpack[x - 1][z - itemWeight]);
                            } else
                            {
                                arrBackpack[x][z]=arrBackpack[x-1][z];
                            }
                        }
                    } else
                    {
                        arrBackpack[x][z]=arrBackpack[x-1][z];
                    }
                }
                System.out.print(" "+arrBackpack[x][z]+" ");
            }
            System.out.println();
        }
        int backpackPointer=19;
        int max=  arrBackpack[12][backpackPointer];
        for(int m=arrBackpack.length-2;m>=0;m--)
        {
            if(19<arrValue[m+1][1])
            {
                if(arrValue[m+1][0]>=arrBackpack[m-1][19])
                    System.out.println(arrName[m+1]);
            }else{
                if(max>=arrBackpack[m][backpackPointer])
                {
                    int szybkiWynik=  max-arrValue[m+1][0];
                    if(szybkiWynik==0)
                    {
                        System.out.println(arrName[m + 1]);
                        System.out.println("your items list");
                        System.exit(0);
                    }
                    else {

                        if (arrBackpack[m][backpackPointer - arrValue[m + 1][1]] == szybkiWynik) {
                            System.out.println(arrName[m + 1]);
                            backpackPointer -= arrValue[m + 1][1];
                            max = arrBackpack[m][backpackPointer];
                        }
                    }
                }
            }
        }
        read.close();
    }
}
