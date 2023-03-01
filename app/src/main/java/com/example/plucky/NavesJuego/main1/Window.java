package com.example.plucky.NavesJuego.main1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.plucky.Clases.Jugador;
import com.example.plucky.Juego;
import com.example.plucky.NavesJuego.graphics.Assets;
import com.example.plucky.NavesJuego.input.KeyBoard;
import com.example.plucky.NavesJuego.input.MouseInput;
import com.example.plucky.NavesJuego.states.CargarEstado;
import com.example.plucky.NavesJuego.states.Estado;

import java.util.List;


public class Window  extends SurfaceView implements Runnable {

	public static final int WIDTH=800,HEIGHT=600;
	private Thread thread;
    private SurfaceHolder surfaceHolder;
	private boolean running= false;
	private Context context;
	private String jugador;
	private List<Jugador> enemigos;


	
	private final int FPS=60;// fotogramas por segundo
	private double TARGETTIME=1000000000/FPS;//tiempo en nanosegundos
	private double  delta=0;//tiempo que va pasando 
	private int AVERAGEFPS=FPS; // fotogramas por segundo promedio
	
	private KeyBoard keyBoard;
	private MouseInput mouseInput;

	public Window(Context context, String jugador, List<Jugador> enemigos) {
		super(context);
		keyBoard= new KeyBoard();
        surfaceHolder= getHolder();
        this.jugador=jugador;
        System.out.println("holiiiis dfjdksf"+ enemigos.size());
        this.enemigos= enemigos;
        this.context=context;
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                start();
            }
            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
            }
            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
            stop();
            }
        });
	}

	private void update() {
		keyBoard.update();
		Estado.getEstadoActual().update();
	}

	protected void Draw(Canvas canvas) {

		try {
			Thread.sleep(15);
			Estado.getEstadoActual().draw(canvas);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}



	public void borrar(Canvas c){
		c.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
	}




	
	private void init() {
		Thread loaThread= new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Assets.init(getResources(),context);
			}
		});
		Estado.cambiarEstado(new CargarEstado(loaThread, context, jugador, enemigos));
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		long now=0;//Registro del tiempo
		long  lastTime=System.nanoTime();//hora actual en nanosegundos 
		int frames=0;
		long time=0;
		
		init();
		
		while (running) 
		{
			Canvas c= null;
			now=System.nanoTime();
			delta+=(now-lastTime)/TARGETTIME;
			time +=(now-lastTime);
			lastTime= now;
			update();
			try {
				c= surfaceHolder.lockCanvas();
				synchronized (surfaceHolder){
					borrar(c);
					Draw(c);

				}

			}finally {
				if(c!=null){
					surfaceHolder.unlockCanvasAndPost(
							c
					);
				}
			}
			if(delta>=1) {
				delta--;
				frames++;
			}	
			
			if(time>=1000000000) {

				AVERAGEFPS=frames;
				frames=0;
				time=0;
			}
		}
		stop();
	}
	
	private void start() {
		thread= new Thread(this);
		thread.start();
		running= true;
	}
	
	private void stop() {
		try {
			thread.join();
			running= false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
