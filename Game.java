package OOPs.TicTacToe;
import java.util.*;
public class Game {
    Board board;
    Player[] players;
    int turn;  // => even -> p1 ; odd-> p2;
    int noOfMoves;
    //boolean gameOver;
    String zeroPattern;
    String crossPattern;

    public static Scanner sc = new Scanner(System.in);

    Game(Board board , Player[] players){
        this.board=board;
        this.players = players;
        this.turn = 0;
        this.noOfMoves = 0;
        //this.gameOver = false;
        zeroPattern = "";
        crossPattern = "";
        for(int i = 0 ; i < board.size; i++){
            zeroPattern += 'O';
            crossPattern += 'X';
        }
    }

    public void play(){
        System.out.println(board);
        while(true){
            this.noOfMoves++;

            if(this.noOfMoves == board.size * board.size){
                System.out.println("Match Draw");
                return;
            }
            int[] idxs = getIndex();
            int rn = idxs[0], cn = idxs[1];
            board.board[rn][cn]= players[turn].symbol;
            if(this.noOfMoves >= 2 * board.size-1 && checkIfIndexIsEnded()){
                System.out.println(board);
                System.out.println(players[turn].name+" has won !!" );
                return;
            }
            turn = (turn+1)%2;
            System.out.println(board);
        }
    }
    public int[] getIndex(){
        while(true){
            System.out.println(players[turn].name + "'s turn , give index: ");
            int idx = sc.nextInt()-1;
            int rn = idx / board.size, cn = idx % board.size;
            if(rn < 0 || cn < 0 || rn >= board.size || cn >= board.size){
                System.out.println("Out of bound index");
                continue;
            }
            if(board.board[rn][cn]!='-'){
                System.out.println("Position aleready filled , try again");
                continue;
            }
            return new int[]{rn,cn};
        }
    }

    public boolean checkIfIndexIsEnded(){
        StringBuilder sb;
        //rows
        for(int i = 0 ; i < board.size; i++){
            sb = new StringBuilder();
            for(int j = 0 ; j<board.size; j++) 
            sb.append(board.board[i][j]);
            if(getResult(sb)) return true;
        }

        //coloums
        for(int i = 0 ; i < board.size; i++){
            sb = new StringBuilder();
            for(int j = 0 ; j < board.size; j++) 
            sb.append(board.board[j][i]);
            if(getResult(sb)) return true;
        }

        //major diagonal
        int i = 0; int j = 0;
        sb = new StringBuilder();
        while(i < board.size){
            sb.append(board.board[i++][j++]);
        }
        if(getResult(sb)) return true;
    
       //minor diagonal
       i = 0;  j = board.size -1;
       sb = new StringBuilder();
       while(i < board.size){
         sb.append(board.board[i++][j--]);
       }
       if(getResult(sb)) return true;
       return false;
 } 
    
    public boolean getResult(StringBuilder sb){
        if(sb.toString().equals(zeroPattern) || sb.toString().equals(crossPattern))
        return true;
     return false;
    }

}
