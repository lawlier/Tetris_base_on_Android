package cn.jczhuang.tetris2.model;

import java.util.ArrayList;

import cn.jczhuang.tetris2.view.TetrisView;

/**
 * Created by Terence on 2016/2/3.
 */
public class TetrisBlock {
    //方块种类总数
    public final static int TYPESUM = 7;
    //每个方块有四个方向
    public final static int DIRECTIONSUM = 4;
    private  int blockType,blockDirection;
    private int color;
    //方块坐标
    private float x, y;
    //方块组成部分
    private ArrayList<BlockUnit> units = new ArrayList<>();
    private ArrayList<TetrisBlock> blocks = new ArrayList<>();

    public ArrayList<TetrisBlock> getBlocks() {
        return blocks;
    }

    public void setBlocks(ArrayList<TetrisBlock> blocks) {
        this.blocks = blocks;
    }

    public ArrayList<BlockUnit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<BlockUnit> units) {
        this.units = units;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
//        if(checkCollision_X()!=0)
//            return;
        float dif_x = x - this.x;
        for (BlockUnit u:units){
            u.setX(u.getX() + dif_x);
        }
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        if(checkCollision_Y())
            return;
        float dif_y = y - this.y;
        for (BlockUnit u:units){
            u.setY(u.getY() + dif_y);
    }
        this.y = y;
    }

    public int getColor() {
        return color;
    }
    public void remove(int j){

        for(int i=units.size()-1;i>=0;i--){
            if((int)((units.get(i).getY()- TetrisView.beginPoint)/50) == j)
                units.remove(i);
        }
    }
    public void setColor(int color) {
        this.color = color;
    }
    public void move(int x){
        if(checkCollision_X() <0 && x<0||checkCollision_X()>0&&x>0)
            return;
        if(x > 0)
            setX(getX() + BlockUnit.UNITSIZE);
        else {

            setX(getX() - BlockUnit.UNITSIZE);

        }

        /*
        左右移动
         */
    }
    public void type1(){
        switch (blockDirection) {
            case 1:case 2:
                int i = -1;
                for (BlockUnit u : units) {
                    u.setX(x);
                    u.setY(i * BlockUnit.UNITSIZE + y);
                    i++;
                }
                blockDirection += 2;
                break;
            case 3:case 4:
                int j = -1;
                for (BlockUnit u : units) {
                    u.setY(y);
                    u.setX(j * BlockUnit.UNITSIZE + x);
                    j++;
                }
                blockDirection -= 2;
                break;
        }
    }
    public void type2(){
        switch (blockDirection){
            case 1:
                for(int i=0;i<units.size()-1;i++){
                    units.get(i).setX(x);
                    units.get(i).setY(y + i * BlockUnit.UNITSIZE);
                }
                units.get(3).setX(x + BlockUnit.UNITSIZE);
                units.get(3).setY(y + BlockUnit.UNITSIZE);
                break;
            case 2:
                for(int i=0;i<units.size()-1;i++){
                    units.get(i).setY(y);
                    units.get(i).setX(x + (i - 1) * BlockUnit.UNITSIZE);
                }
                units.get(3).setX(x);
                units.get(3).setY(y + BlockUnit.UNITSIZE);
                break;
            case 3:
                for(int i=0;i<units.size()-1;i++){
                    units.get(i).setX(x);
                    units.get(i).setY(y + i * BlockUnit.UNITSIZE);
                }
                units.get(3).setX(x - BlockUnit.UNITSIZE);
                units.get(3).setY(y + BlockUnit.UNITSIZE);

                break;
            case 4:
                for(int i=0;i<units.size()-1;i++){
                    units.get(i).setX(x + (i - 1) * BlockUnit.UNITSIZE);
                    units.get(i).setY(y + BlockUnit.UNITSIZE);
                }
                units.get(3).setX(x);
                units.get(3).setY(y);

                break;
        }
        blockDirection = blockDirection % 4 + 1;
    }
    public void type3(){

    }
    public void type4(){
        switch (blockDirection){
            case 1:
                for(int i=0;i<units.size()-1;i++){
                    units.get(i).setX(x);
                    units.get(i).setY(y + i * BlockUnit.UNITSIZE);
                }
                units.get(3).setX(x + BlockUnit.UNITSIZE);
                units.get(3).setY(y);
                break;
            case 2:
                for(int i=0;i<units.size()-1;i++){
                    units.get(i).setY(y);
                    units.get(i).setX(x + (i - 1) * BlockUnit.UNITSIZE);
                }
                units.get(3).setX(x + BlockUnit.UNITSIZE);
                units.get(3).setY(y + BlockUnit.UNITSIZE);
                break;
            case 3:
                for(int i=0;i<units.size()-1;i++){
                    units.get(i).setX(x);
                    units.get(i).setY(y + i * BlockUnit.UNITSIZE);
                }
                units.get(3).setX(x - BlockUnit.UNITSIZE);
                units.get(3).setY(y + 2 * BlockUnit.UNITSIZE);

                break;
            case 4:
                for(int i=0;i<units.size()-1;i++){
                    units.get(i).setX(x + (i - 1) * BlockUnit.UNITSIZE);
                    units.get(i).setY(y + BlockUnit.UNITSIZE);
                }
                units.get(3).setX(x - BlockUnit.UNITSIZE);
                units.get(3).setY(y);

                break;
        }
        blockDirection = blockDirection % 4 + 1;
    }
    public void type5(){
        switch (blockDirection){
            case 1:
                for(int i=0;i<units.size()-1;i++){
                    units.get(i).setX(x);
                    units.get(i).setY(y + i * BlockUnit.UNITSIZE);
                }
                units.get(3).setX(x + BlockUnit.UNITSIZE);
                units.get(3).setY(y + 2 * BlockUnit.UNITSIZE);
                break;
            case 2:
                for(int i=0;i<units.size()-1;i++){
                    units.get(i).setY(y);
                    units.get(i).setX(x + (i - 1) * BlockUnit.UNITSIZE);
                }
                units.get(3).setX(x - BlockUnit.UNITSIZE);
                units.get(3).setY(y + BlockUnit.UNITSIZE);
                break;
            case 3:
                for(int i=0;i<units.size()-1;i++){
                    units.get(i).setX(x);
                    units.get(i).setY(y + i * BlockUnit.UNITSIZE);
                }
                units.get(3).setX(x - BlockUnit.UNITSIZE);
                units.get(3).setY(y );

                break;
            case 4:
                for(int i=0;i<units.size()-1;i++){
                    units.get(i).setX(x + (i - 1) * BlockUnit.UNITSIZE);
                    units.get(i).setY(y + BlockUnit.UNITSIZE);
                }
                units.get(3).setX(x + BlockUnit.UNITSIZE);
                units.get(3).setY(y);

                break;
        }
        blockDirection = blockDirection % 4 + 1;
    }
    public void type6(){
        switch (blockDirection) {
            case 1:case 2:
                for(int i=0;i<2;i++){
                    units.get(i).setX((i - 1) * BlockUnit.UNITSIZE + x);
                    units.get(i).setY(y);
                }
                for(int i=0;i<2;i++){
                    units.get(i + 2).setX(i * BlockUnit.UNITSIZE + x);
                    units.get(i + 2).setY(y + BlockUnit.UNITSIZE);
                }
                blockDirection += 2;
                break;
            case 3:case 4:
                for(int i=0;i<2;i++){
                    units.get(i).setY((i - 1) * BlockUnit.UNITSIZE + y);
                    units.get(i).setX(x + BlockUnit.UNITSIZE);
                }
                for(int i=0;i<2;i++){
                    units.get(i + 2).setY(i * BlockUnit.UNITSIZE + y);
                    units.get(i + 2).setX(x);
                }

                blockDirection -= 2;
                break;
        }
    }
    public void type7(){
        switch (blockDirection) {
            case 1:case 2:
                for(int i=0;i<2;i++){
                    units.get(i).setX(i  * BlockUnit.UNITSIZE + x);
                    units.get(i).setY(y);
                }
                for(int i=0;i<2;i++){
                    units.get(i + 2).setX((i - 1) * BlockUnit.UNITSIZE + x);
                    units.get(i + 2).setY(y + BlockUnit.UNITSIZE);
                }
                blockDirection += 2;
                break;
            case 3:case 4:
                for(int i=0;i<2;i++){
                    units.get(i).setY(i * BlockUnit.UNITSIZE + y);
                    units.get(i).setX(x + BlockUnit.UNITSIZE);
                }
                for(int i=0;i<2;i++){
                    units.get(i + 2).setY((i - 1) * BlockUnit.UNITSIZE + y);
                    units.get(i + 2).setX(x);
                }

                blockDirection -= 2;
                break;
        }
    }
    public void rotate(){
        if(checkCollision_X()!=0 && checkCollision_Y())
            return;
        switch (blockType) {
            case 1:
                type1();
                break;
            case 2:
                type2();
                break;
            case 3:
                type3();
                break;
            case 4:
                type4();
                break;
            case 5:
                type5();
                break;
            case 6:
                type6();
                break;
            case 7:
                type7();
                break;
        }
        /*
        按顺时针旋转
         */
    }
    public boolean checkCollision_Y() {
        for(TetrisBlock block:blocks){
            if(this == block) {
                for(BlockUnit u:units){
                    if(u.checkOutOfBoundary_Y())
                        return true;
                }
                continue;
            }
            if(checkCollision_Y(block))
                return true;
        }
        return false;
    }
    public int checkCollision_X() {
        for(TetrisBlock block:blocks){
            if(this == block) {
                for(BlockUnit u:units){
                    if(u.checkOutOfBoundary_X() != 0)
                        return u.checkOutOfBoundary_X();
                }
                continue;
            }
            if(checkCollision_X(block) != 0)
                return checkCollision_X(block);
        }
        return 0;
    }
    public boolean checkCollision_Y(TetrisBlock other){
        for(BlockUnit i: units){
            for(BlockUnit j:other.units){
                if(i == j) {
                    continue;
                }
                if(i.checkVerticalCollision(j))
                    return true;
            }
        }
        return false;
    }
    public int checkCollision_X(TetrisBlock other){
        for(BlockUnit i: units){
            for(BlockUnit j:other.units){
                if(i == j) {
                    continue;
                }
                if(i.checkHorizontalCollision(j)!=0)
                    return i.checkHorizontalCollision(j);
            }
        }
        return 0;
    }
    public TetrisBlock(float x,float y){

        this.x = x;

        this.y = y;
        //随机生成一个种类和一个方向
        blockType = (int)(Math.random() * TYPESUM) + 1;
       // blockDirection = (int)(Math.random() * DIRECTIONSUM) + 1;
        blockDirection = 1;
        color = (int)(Math.random() * 5) + 1;

        switch(blockType){
            case 1:
                for(int i=0;i<4;i++){
                    units.add(new BlockUnit(x + (-1 + i ) * BlockUnit.UNITSIZE , y + BlockUnit.UNITSIZE));
                }
                break;
            case 2:
                units.add(new BlockUnit(x + (-1 + 1 ) * BlockUnit.UNITSIZE , y));
                for(int i=0;i<3;i++){
                    units.add(new BlockUnit(x + (-1 + i ) * BlockUnit.UNITSIZE , y + BlockUnit.UNITSIZE));
                }
                break;
            case 3:
                for(int i=0;i<2;i++){
                    units.add(new BlockUnit(x + i * BlockUnit.UNITSIZE,y ));
                    units.add(new BlockUnit(x + i * BlockUnit.UNITSIZE,y + BlockUnit.UNITSIZE ));
                }
                break;
            case 4:
                units.add(new BlockUnit(x + (-1 + 0 ) * BlockUnit.UNITSIZE , y));
                for(int i=0;i<3;i++){
                    units.add(new BlockUnit(x + (-1 + i ) * BlockUnit.UNITSIZE , y + BlockUnit.UNITSIZE));
                }
                break;
            case 5:
                units.add(new BlockUnit(x + (-1 + 2 ) * BlockUnit.UNITSIZE , y));
                for(int i=0;i<3;i++){
                    units.add(new BlockUnit(x + (-1 + i ) * BlockUnit.UNITSIZE , y + BlockUnit.UNITSIZE));
                }
                break;
            case 6:
                for(int i=0;i<2;i++){
                    units.add(new BlockUnit(x + (-1+i) * BlockUnit.UNITSIZE,y ));
                    units.add(new BlockUnit(x + i * BlockUnit.UNITSIZE,y + BlockUnit.UNITSIZE ));
                }
                break;
            case 7:
                for(int i=0;i<2;i++){
                    units.add(new BlockUnit(x + i * BlockUnit.UNITSIZE,y ));
                    units.add(new BlockUnit(x + ( -1 + i )* BlockUnit.UNITSIZE,y + BlockUnit.UNITSIZE ));
                }
                break;
        }

    }

}
