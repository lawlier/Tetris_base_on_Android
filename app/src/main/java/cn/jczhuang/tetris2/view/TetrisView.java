package cn.jczhuang.tetris2.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;

import cn.jczhuang.tetris2.model.BlockUnit;
import cn.jczhuang.tetris2.model.TetrisBlock;
/**
 * Created by Terence on 2016/2/3.
 */
public class TetrisView extends View {
    boolean isRun = true;
    public Thread dropThread ;
    int[] map = new int[100];
    private Activity father;
    private TetrisBlock fallingBlock;
    public static int beginPoint = 10;
    private TetrisView self;
    Thread thread = new Thread();
    float x1, y1, x2, y2;
    private float h = getHeight();
    private float w = getWidth();
    public static int max_x, max_y;
    public void clear(){
        isRun = false;
        blocks.clear();
        thread = new Thread();
        fallingBlock = null;
    }
    public TetrisBlock getFallingBlock() {
        return fallingBlock;
    }

    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
    }

    public void setFallingBlock(TetrisBlock fallingBlock) {
        this.fallingBlock = fallingBlock;

    }

    public Activity getFather() {
        return father;
    }

    public void setFather(Activity father) {
        this.father = father;
    }

    private ArrayList<TetrisBlock> blocks = new ArrayList<>();

    public TetrisView(Context context) {
        super(context);
        self = this;

    }

    public TetrisView(Context context, AttributeSet attrs) {
        super(context, attrs);
        self = this;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            x1 = event.getX();
            y1 = event.getY();
        }
        if(event.getAction() == MotionEvent.ACTION_UP ){
            x2 = event.getX();
            y2 = event.getY();
            float tx = fallingBlock.getX();
            float ty = fallingBlock.getY();
            if(x1 - x2 > 50){
                //left
                fallingBlock.move(-1);
                father.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        self.invalidate();
                    }
                });
            }else if(x2 - x1 > 50){
                //right
                fallingBlock.move(1);
                father.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        self.invalidate();
                    }
                });
            }else if(y1 - y2 > 50){
                fallingBlock.rotate();
                father.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        self.invalidate();
                    }
                });
            }
        }

        return true;
    }
    public void init(){

        final float tx =300+beginPoint;
        Arrays.fill(map,0);
        isRun = true;
        dropThread=new Thread(new Runnable() {

            @Override
            public void run() {
                while(isRun) {
                    try {
                        Thread.sleep(12000);
                        h = getHeight();
                        w = getWidth();
                        fallingBlock = new TetrisBlock(tx, beginPoint);
                        fallingBlock.setBlocks(blocks);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(thread.getState() == Thread.State.TERMINATED || thread.getState() == Thread.State.NEW) {
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                float ty = fallingBlock.getY();
                                float tx = fallingBlock.getX();
                                int end = (int) ((h - 50 - beginPoint) / BlockUnit.UNITSIZE);
                                int k = 1;
                                while (k++ < end) {
                                    try {
                                        Thread.sleep(200);
                                        ty = fallingBlock.getY();
                                        ty = ty + BlockUnit.UNITSIZE;
                                        fallingBlock.setY(ty);
                                        father.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                self.invalidate();
                                            }
                                        });
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    //Log.e("ty", ty + " h," + h);
                                    //                                if(ty > fallingBlock.getY())
                                    //                                    break;
                                }
                                blocks.add(fallingBlock);
                                TetrisBlock temp = fallingBlock;
                                for (BlockUnit u : temp.getUnits()) {
                                    int index = (int) ((u.getY() - beginPoint) / 50);
                                    map[index]++;
                                }
                                //                            for(BlockUnit u:fallingBlock.getUnits()){
                                //                                int index = (int)((u.getY() - beginPoint)/50);
                                //                                map[index] ++;
                                //                            }

                                int full = (int) ((w - 50 - beginPoint) / BlockUnit.UNITSIZE) + 1;

                                Log.e("Lawliex full", full + " end:" + end);

                                for (int i = 0; i <= end; i++) {
                                    if (map[i] != 0)
                                        Log.e("Lawliex full", "i:" + i + " map[i]:" + map[i] + "");
                                    if (map[i] >= full) {
                                        map[i] = 0;
                                        for (int j = i; j > 0; j--) {
                                            map[j] = map[j - 1];
                                        }
                                        map[0] = 0;
                                        for (TetrisBlock b : blocks) {
                                            b.remove(i);
                                        }
                                        for (int j = blocks.size()-1; j>=0; j--) {
                                            if (blocks.get(j).getUnits().isEmpty()) {
                                                blocks.remove(j);
                                                continue;
                                            }
                                            for (BlockUnit u : blocks.get(j).getUnits()) {
                                                if ((int) ((u.getY() - beginPoint) / 50) < i)
                                                    u.setY(u.getY() + BlockUnit.UNITSIZE);
                                            }

                                        }

                                        father.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                self.invalidate();
                                            }
                                        });
                                    }

                                }
                            }
                        });
                    }
                    thread.start();
                }
            }
        });
        dropThread.start();
    }
    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        max_x = getWidth();
        max_y = getHeight();
        h = max_y;
        //Log.e("max_y", max_y + " " + h);
        canvas.drawColor(Color.WHITE);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        RectF rel;
        float size = BlockUnit.UNITSIZE;
        int color[] = {0,Color.BLUE,Color.RED,Color.YELLOW,Color.GREEN,Color.GRAY};
        if(!blocks.isEmpty()){


            for(TetrisBlock block:blocks){
                paint.setColor(color[block.getColor()]);
                for(BlockUnit u:block.getUnits()){
                    float tx = u.getX();
                    float ty = u.getY();
                    rel = new RectF(tx, ty, tx + size, ty + size);

                    canvas.drawRoundRect(rel, 8, 8, paint);
                }
            }
        }
        if(fallingBlock!=null) {
            paint.setColor(color[fallingBlock.getColor()]);
            for (BlockUnit u : fallingBlock.getUnits()) {
                float tx = u.getX();
                float ty = u.getY();
                rel = new RectF(tx, ty, tx + size, ty + size);
                canvas.drawRoundRect(rel, 8, 8, paint);
            }
        }

        //canvas.drawRoundRect(rel, 10, 10, paint);
        paint.setColor(Color.LTGRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        //绘制网格
        for(int i=beginPoint; i<max_x-50; i+= 50){
            for(int j=beginPoint; j<max_y-50; j+= 50) {
                rel = new RectF(i, j, i + 50, j + 50);
                canvas.drawRoundRect(rel, 8, 8, paint);
            }
        }
    }

}
