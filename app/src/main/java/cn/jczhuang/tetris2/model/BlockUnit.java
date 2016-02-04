package cn.jczhuang.tetris2.model;

import cn.jczhuang.tetris2.view.TetrisView;

/**
 * Created by Terence on 2016/2/3.
 */
public class BlockUnit {
    public final static float UNITSIZE = 50;
    private int a,b;
    private float x,y; // x = a * 50, y = b * 50;
    public static float max_x, max_y;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public BlockUnit(float x,float y){

        this.x = x;
        this.y = y;
    }
    public boolean checkOutOfBoundary_Y(){
        if(y >= TetrisView.max_y - UNITSIZE * 2)
            return true;
        else
            return false;
    }
    public int checkOutOfBoundary_X(){
        if(x<=50 )
            return -1;
        else if(x >= TetrisView.max_x - UNITSIZE * 2)
            return 1;
        else
            return 0;
    }
    public boolean checkVerticalCollision(BlockUnit other){
        //Log.e("how how","" + (y ));
        if(y >= TetrisView.max_y - UNITSIZE * 2)
            return true;
        if(Math.abs(x - other.x) >= UNITSIZE)
            return false;
        else{
            if(Math.abs(y - other.y) > UNITSIZE)
                return false;
            return true;
        }
    }
    public int checkHorizontalCollision(BlockUnit other){
        if(x <= TetrisView.beginPoint || x > TetrisView.max_x - UNITSIZE * 2)
            return checkOutOfBoundary_X();
        if(Math.abs(y - other.y )>= UNITSIZE)
            return 0;
        else{
            if(x - other.x > UNITSIZE)
                return 0;
            else if(x - other.x == UNITSIZE)
                return -1;
            else
                return 1;

        }
    }
}
