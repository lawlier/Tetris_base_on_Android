package cn.jczhuang.tetris2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.jczhuang.tetris2.view.TetrisView;

public class Main extends Activity {
    Button left, right, rotate, start;
    TetrisView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        view = (TetrisView)findViewById(R.id.tetrisView);
        left = (Button)findViewById(R.id.left);
        right = (Button)findViewById(R.id.right);
        rotate = (Button)findViewById(R.id.rotate);
        start = (Button)findViewById(R.id.start);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.getFallingBlock().move(-1);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.invalidate();
                    }
                });
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.getFallingBlock().move(1);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.invalidate();
                    }
                });
            }
        });
        rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.getFallingBlock().rotate();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.invalidate();
                    }
                });
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.dropThread.interrupt();
                view.clear();
                view.init();
            }
        });
        view.setFather(this);
        view.invalidate();
        view.init();

    }

}
