package logo;


import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Timer extends Thread{

	public static final int FRAME_COUNT = 100;
	public static final int FRAME_DURATION = 50;
	
	private int currentFrame;
	
	private static JFrame logo;
	
	@Override
	public void run() {
		currentFrame = 0;
		long accumulator = 0;
		while(true){
			long firstUpdate = System.currentTimeMillis();
			try {
				SwingUtilities.invokeAndWait(new Runnable() {
					
					@Override
					public void run() {
						currentFrame = 50;
						logo.repaint();
					}
				});
			} catch (InvocationTargetException | InterruptedException e1) {
				e1.printStackTrace();
			}
			try {
				Thread.sleep(FRAME_DURATION - accumulator);
				long secondUpdate = System.currentTimeMillis();
				accumulator += secondUpdate - firstUpdate;
				if(accumulator >= FRAME_DURATION){
					currentFrame += accumulator / FRAME_DURATION;
					if(currentFrame > FRAME_COUNT)
						currentFrame %= FRAME_COUNT;
					accumulator %= FRAME_DURATION;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public int getCurrentFrame(){ return currentFrame; }
	public void setLogo(JFrame logo){ Timer.logo = logo; }
}
