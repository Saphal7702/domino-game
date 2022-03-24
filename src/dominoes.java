/*
This is the domino class where each domino is created.
We can also rotate domino here.
 */
public class dominoes {
    private int l;
    private int r;
    public dominoes (int l, int r){
        this.l =l;
        this.r =r;
    }
    /*
    This returns left end value of domino.
     */
    public int getL() {return l;}
    /*
    This return right end of domino
     */
    public int getR() {return r;}
    public void setL(int l) {this.l=l;}
    public void setR(int r) {this.r=r;}
    /*
    It rotates domino.
     */
    public static dominoes rotate(dominoes piece){
        int temp = piece.getR();
        piece.setR(piece.getL());
        piece.setL(temp);
        return piece;
    }
}
