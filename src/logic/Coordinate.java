package logic;

public class Coordinate {
    public int x;
    public int y;

    public Coordinate(){
        x = 0;
        y = 0;
    }

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int distance(int x, int y, boolean max){
        if(max){
            return Math.max(Math.abs(this.x - x), Math.abs(this.y - y));
        }
        return Math.abs(this.x - x) + Math.abs(this.y - y);
    }
}
