package com.valhalla.tablut;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;

/**
 * Created by IntelliJ IDEA.
 * User: aaniskou
 * Date: 29.02.12
 * Time: 7:24
 * To change this template use File | Settings | File Templates.
 */
public class GameActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new Panel(this));
    }

    private class Panel extends SurfaceView implements SurfaceHolder.Callback{

        public Panel(Context context) {
            super(context);
            getHolder().addCallback(this);
            thread = new GameThread(getHolder(), this);
        }

        @Override
        public void onDraw(Canvas canvas){
            Bitmap scratch = BitmapFactory.decodeResource(getResources(), R.drawable.tablut_board);
            canvas.drawColor(Color.BLACK);
            canvas.drawBitmap(scratch, 10, 10, null);
        }

        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            thread.setRunning(true);
            thread.start();
        }

        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            boolean retry = true;
            thread.setRunning(false);
            while (retry){
                try{
                    thread.join();
                    retry = false;
                } catch (InterruptedException ex){

                }
            }
        }

        private GameThread thread;
    }

    private class GameThread extends Thread{

        public GameThread(SurfaceHolder surfaceHolder, Panel panel){
            this.surfaceHolder = surfaceHolder;
            this.panel = panel;
        }

        public void setRunning(boolean run){
            this.run = run;
        }

        @Override
        public void run(){
            Canvas canvas;
            while (run){
                canvas = null;
                try{
                    canvas = surfaceHolder.lockCanvas(null);
                    synchronized (surfaceHolder){
                        panel.onDraw(canvas);
                    }
                } finally {
                    if (canvas != null){
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }

        private SurfaceHolder surfaceHolder;
        private Panel panel;
        private boolean run = false;
    }
}