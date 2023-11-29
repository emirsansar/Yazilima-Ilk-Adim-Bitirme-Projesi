package javaIJ.MineSweeper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int row, column;

        System.out.println("Kaça kaçlık alanda oynamak istediğnizi giriniz.");
        System.out.print("Satır: ");
        row = scan.nextInt();
        System.out.print("Sütun: ");
        column = scan.nextInt();

        MineSweeper game = new MineSweeper(row, column);
        game.run();

        scan.close();
    }
}
