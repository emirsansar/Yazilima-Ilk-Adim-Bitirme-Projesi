package javaIJ.MineSweeper;

import java.util.Random;
import java.util.Scanner;

public class MineSweeper {

    int rowNum, colNum, size, numMines;
    String[][] mineMap;  // Mayınlar bu map'e yerleştirilecek. Oyuncu görmeyecek.
    String[][] board;  // Oyuncu, bu map üzerinden oynayacak.

    Random rand = new Random();
    Scanner scan = new Scanner(System.in);

    public MineSweeper(int rowNum, int colNum){
        this.rowNum=rowNum; this.colNum=colNum;
        this.size=rowNum*colNum;
        this.numMines=size/4;
        this.mineMap = new String[rowNum][colNum];
        this.board = new String[rowNum][colNum];
    }

    public void run(){
        int row, col; // oyuncunun seçimleri için.
        int success=0;
        prepareGame();

        System.out.println("==================================");
        System.out.println("Mayınların Konumu");
        printBoard(mineMap); // test için.
        System.out.println("==================================");

        System.out.println("Mayın tarlası oyununa hoşgeldiniz.");

        while(true){
            printBoard(board);
            System.out.print("Satır seçiniz: ");
            row = scan.nextInt();
            System.out.print("Sütun seçiniz: ");
            col = scan.nextInt();

            if( !isValidCoordinate(row, col) ){
                System.out.println("Geçersiz koordinat.");
                continue;
            }

            if( board[row][col] != "-" ){  // Daha önceden işaretlenmiş olup olmadığını kontrol eder.
                System.out.println("Bu koordinat zaten işaretlenmiş.");
                continue;
            }

            if( mineMap[row][col] != "*" ){
                check(row,col);
                success++;
                if( success == (size - numMines ) ){
                    System.out.println("Tebrikler, oyunu kazandınız!");
                    break;
                }
            }
            else{
                System.out.println("Oyun bitti! Mayına bastınız!");
                break;
            }
        }
        scan.close();
    }

    public void prepareGame(){
        int randRow, randCol, count=0;

        for (int i = 0; i < rowNum; i++) {  // mineMap, mayınlar olmadan hazırlanır.
            for (int j = 0; j < colNum; j++) {
                mineMap[i][j] = "-";
            }
        }

        while (count != numMines ){  // mayınlar, mineMap'e yerleştirilir.
            randRow = rand.nextInt(rowNum);
            randCol = rand.nextInt(colNum);

            if (mineMap[randRow][randCol] != "*") {
                mineMap[randRow][randCol] = "*";
                count++;
            }
        }

        for (int i = 0; i < rowNum; i++) {    // oyuncunun işaretleyecğei harita hazırlanır.
            for (int j = 0; j < colNum; j++) {
                board[i][j] = "-";
            }
        }
    }

    public void check(int r, int c){  // seçilen koordinatın etrafındaki mayınlar kontrol edilir.
        int numMines=0;

        if( mineMap[r][c] == "-" ){

            int newRow, newCol;
            int[] dx = {0, 0, -1, 1, -1, -1, 1, 1};  // etrafındaki hücreleri kontrol etmesi için.
            int[] dy = {1, -1, 0, 0, -1, 1, -1, 1};

            for(int i=0; i<4; i++){
                newRow = r + dx[i];
                newCol = c + dy[i];

                if( isValidCoordinate(newRow, newCol) ){
                    if( mineMap[newRow][newCol] == "*"){
                        numMines++;
                    }
                }
            }
            board[r][c] = Integer.toString(numMines);
        }
    }

    public void printBoard(String[][] arr){
        for(int i=0; i<arr.length; i++){
            for(int k=0; k<arr[0].length; k++){
                System.out.print(arr[i][k] + " ");
            }
            System.out.println();
        }
    }

    public boolean isValidCoordinate(int row, int col){
        return (row >= 0 && row < rowNum) && (col >= 0 && col < colNum);
    }
}
