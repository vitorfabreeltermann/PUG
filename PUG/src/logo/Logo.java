package logo;

import java.awt.Color; 
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Logo extends JPanel {

	private Image[] images = new Image[Timer.FRAME_COUNT];
	
	private static final boolean CREATE_NEW_IMAGES = true;
	
	private static final int FUR_LENGTH = 30;
	
	private static final long serialVersionUID = -6657812409427281483L;

	private static Timer timer;

	private static Logo logo;
	private static JFrame frame;

	public static final Color FUR_1 = new Color(0xda, 0xcc, 0xa8);
	public static final Color FUR_2 = new Color(0x24, 0x1a, 0x15);
	public static final Color BACKGROUND_1_1 = new Color(0x0, 0x50, 0x70);
	public static final Color BACKGROUND_1_2 = new Color(0x90, 0xef, 0xff);
	public static final Color BACKGROUND_2_1 = new Color(0x30, 0x0, 0x0);
	public static final Color BACKGROUND_2_2 = new Color(0x10, 0x0, 0x0);
	public static final Color EYES_COLOR_1 = new Color(0xff, 0xff, 0xff);
	public static final Color EYES_COLOR_2_1 = new Color(0x60, 0x0, 0x0);
	public static final Color EYES_COLOR_2_2 = new Color(0x0, 0x50, 0xa2);
	public static final Color EYES_COLOR_3 = new Color(0x0, 0x0, 0x0);
	public static final Color TONGUE_COLOR = new Color(0xc0, 0x50, 0x60);
	public static final Color TOPHAT_COLOR_1 = new Color(0x20, 0x20, 0x20);
	public static final Color TOPHAT_COLOR_2 = new Color(0x90, 0x0, 0x0);
	public static final Color MONOCLE_COLOR_1 = new Color(0x90, 0x90, 0x90);
	public static final Color MONOCLE_COLOR_2 = new Color(0x90, 0xa8, 0xff);
	public static final Color MONOCLE_FUR_1_COLOR = new Color(
			(FUR_1.getRed() * 70 + MONOCLE_COLOR_2.getRed() * 30) / 100,
			(FUR_1.getGreen() * 70 + MONOCLE_COLOR_2.getGreen() * 30) / 100,
			(FUR_1.getBlue() * 70 + MONOCLE_COLOR_2.getBlue() * 30) / 100);
	public static final Color MONOCLE_FUR_2_COLOR = new Color(
			(FUR_2.getRed() * 70 + MONOCLE_COLOR_2.getRed() * 30) / 100,
			(FUR_2.getGreen() * 70 + MONOCLE_COLOR_2.getGreen() * 30) / 100,
			(FUR_2.getBlue() * 70 + MONOCLE_COLOR_2.getBlue() * 30) / 100);
	public static final Color MONOCLE_EYES_1_COLOR = new Color(
			(EYES_COLOR_1.getRed() * 70 + MONOCLE_COLOR_2.getRed() * 30) / 100,
			(EYES_COLOR_1.getGreen() * 70 + MONOCLE_COLOR_2.getGreen() * 30) / 100,
			(EYES_COLOR_1.getBlue() * 70 + MONOCLE_COLOR_2.getBlue() * 30) / 100);
	public static final Color MONOCLE_EYES_2_1_COLOR = new Color(
			(EYES_COLOR_2_1.getRed() * 70 + MONOCLE_COLOR_2.getRed() * 30) / 100,
			(EYES_COLOR_2_1.getGreen() * 70 + MONOCLE_COLOR_2.getGreen() * 30) / 100,
			(EYES_COLOR_2_1.getBlue() * 70 + MONOCLE_COLOR_2.getBlue() * 30) / 100);
	public static final Color MONOCLE_EYES_2_2_COLOR = new Color(
			(EYES_COLOR_2_2.getRed() * 70 + MONOCLE_COLOR_2.getRed() * 30) / 100,
			(EYES_COLOR_2_2.getGreen() * 70 + MONOCLE_COLOR_2.getGreen() * 30) / 100,
			(EYES_COLOR_2_2.getBlue() * 70 + MONOCLE_COLOR_2.getBlue() * 30) / 100);
	public static final Color MONOCLE_EYES_3_COLOR = new Color(
			(EYES_COLOR_3.getRed() * 70 + MONOCLE_COLOR_2.getRed() * 30) / 100,
			(EYES_COLOR_3.getGreen() * 70 + MONOCLE_COLOR_2.getGreen() * 30) / 100,
			(EYES_COLOR_3.getBlue() * 70 + MONOCLE_COLOR_2.getBlue() * 30) / 100);
	private static int currentFrame;

	public static void main(String[] args) {
		timer = new Timer();
		SwingUtilities.invokeLater(new Runnable() { 
			@Override
			public void run() {
				frame = new JFrame("PUG");
				logo = new Logo();
				frame.setContentPane(logo);
				timer.setLogo(frame);
				frame.setBounds(0, 0, 1600, 1044);
				logo.setBounds(0, 0, 1600, 1000);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				if(CREATE_NEW_IMAGES){
					for (int i = 1; i <= Timer.FRAME_COUNT; i++) {
						timer.setCurrentFrame(i);
						logo.savePicture();
					}
				}
				try {
					logo.loadImages();
				} catch (IOException e) {
					e.printStackTrace();
				}
				frame.setVisible(true);
				timer.start();
			}
		});
		
	}
	
	private void loadImages() throws IOException{
		for (int i = 0; i < Timer.FRAME_COUNT; i++) {
			BufferedImage img = ImageIO.read(new File("PUG"+(i+1)+".png"));
			images[i] = img;
		}
	}
	
	public void savePicture(){
		updateCurrentFrame();
		BufferedImage img = new BufferedImage(1600, 1000, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2i = img.createGraphics();
        logo.paintPicture(g2i);
        g2i.dispose();
        try {
          ImageIO.write(img, "png", new File("PUG"+timer.getCurrentFrame()+".png"));
        }
        catch(IOException ioe) {
          ioe.printStackTrace();
        }
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		updateCurrentFrame();
		//paintPicture(g);
		g.drawImage(images[currentFrame-1], 0, 0, null);
	}
	
	public strictfp void paintPicture(Graphics g){
		//background
		g.setColor(BACKGROUND_2_2);
		Color backgroundAux1, backgroundAux2;
		if(currentFrame < Timer.FRAME_COUNT/2){
			backgroundAux1 = new Color(
					(BACKGROUND_2_1.getRed()*(200/Timer.FRAME_COUNT)*currentFrame + BACKGROUND_1_1.getRed()*(100-((200/Timer.FRAME_COUNT)*currentFrame)))/100,
					(BACKGROUND_2_1.getGreen()*(200/Timer.FRAME_COUNT)*currentFrame + BACKGROUND_1_1.getGreen()*(100-((200/Timer.FRAME_COUNT)*currentFrame)))/100,
					(BACKGROUND_2_1.getBlue()*(200/Timer.FRAME_COUNT)*currentFrame + BACKGROUND_1_1.getBlue()*(100-((200/Timer.FRAME_COUNT)*currentFrame)))/100);
			backgroundAux2 = new Color(
					(BACKGROUND_2_2.getRed()*(200/Timer.FRAME_COUNT)*currentFrame + BACKGROUND_1_2.getRed()*(100-((200/Timer.FRAME_COUNT)*currentFrame)))/100,
					(BACKGROUND_2_2.getGreen()*(200/Timer.FRAME_COUNT)*currentFrame + BACKGROUND_1_2.getGreen()*(100-((200/Timer.FRAME_COUNT)*currentFrame)))/100,
					(BACKGROUND_2_2.getBlue()*(200/Timer.FRAME_COUNT)*currentFrame + BACKGROUND_1_2.getBlue()*(100-((200/Timer.FRAME_COUNT)*currentFrame)))/100);
		}
		else{
			backgroundAux1 = new Color(
					(BACKGROUND_1_1.getRed()*(200/Timer.FRAME_COUNT)*(currentFrame-50) + BACKGROUND_2_1.getRed()*(100-((200/Timer.FRAME_COUNT)*(currentFrame-50))))/100,
					(BACKGROUND_1_1.getGreen()*(200/Timer.FRAME_COUNT)*(currentFrame-50) + BACKGROUND_2_1.getGreen()*(100-((200/Timer.FRAME_COUNT)*(currentFrame-50))))/100,
					(BACKGROUND_1_1.getBlue()*(200/Timer.FRAME_COUNT)*(currentFrame-50) + BACKGROUND_2_1.getBlue()*(100-((200/Timer.FRAME_COUNT)*(currentFrame-50))))/100);
			backgroundAux2 = new Color(
					(BACKGROUND_1_2.getRed()*(200/Timer.FRAME_COUNT)*(currentFrame-50) + BACKGROUND_2_2.getRed()*(100-((200/Timer.FRAME_COUNT)*(currentFrame-50))))/100,
					(BACKGROUND_1_2.getGreen()*(200/Timer.FRAME_COUNT)*(currentFrame-50) + BACKGROUND_2_2.getGreen()*(100-((200/Timer.FRAME_COUNT)*(currentFrame-50))))/100,
					(BACKGROUND_1_2.getBlue()*(200/Timer.FRAME_COUNT)*(currentFrame-50) + BACKGROUND_2_2.getBlue()*(100-((200/Timer.FRAME_COUNT)*(currentFrame-50))))/100);
		}
		for (int i = 0; i < 1000; i++) {
			g.setColor(new Color(
					(backgroundAux1.getRed()*(i/10) + backgroundAux2.getRed()*(100-i/10))/100,
					(backgroundAux1.getGreen()*(i/10) + backgroundAux2.getGreen()*(100-i/10))/100,
					(backgroundAux1.getBlue()*(i/10) + backgroundAux2.getBlue()*(100-i/10))/100));
			g.drawLine(0, i, 1600, i);
		}
		
		
		//head
		for (int i = 0; i < FUR_LENGTH; i++) {
			g.setColor(new Color(FUR_1.getRed(), FUR_1.getGreen(), FUR_1.getBlue(), 0xff-(int)(((float)0xff)*(((float)i)/((float)FUR_LENGTH)))));
			g.fillOval(500+FUR_LENGTH-i, 350+FUR_LENGTH-i, 600-FUR_LENGTH*2+2*i, 600-FUR_LENGTH*2+2*i);
		}
		
		//ears
		for (int i = 0; i < FUR_LENGTH; i++) {
			g.setColor(new Color(FUR_2.getRed(), FUR_2.getGreen(), FUR_2.getBlue(), 0xff-(int)(((float)0xff)*(((float)i)/((float)FUR_LENGTH)))));
			//left
			g.fillArc(470+FUR_LENGTH-i, 390+FUR_LENGTH-i, 200-FUR_LENGTH*2+2*i, 60-FUR_LENGTH*2+2*i, 0, 180);
			g.fillPolygon(new int[]{520, 470+FUR_LENGTH-i, 670-FUR_LENGTH+i},
						  new int[]{570-FUR_LENGTH+i, 420, 420},
						  3);
			//right
			g.fillArc(930+FUR_LENGTH-i, 390+FUR_LENGTH-i, 200-FUR_LENGTH*2+2*i, 60-FUR_LENGTH*2+2*i, 0, 180);
			g.fillPolygon(new int[]{1070, 1130-FUR_LENGTH+i, 930+FUR_LENGTH-i},
						  new int[]{570-FUR_LENGTH+i, 420, 420},
						  3);
		}
		
		//snout
		for (int i = 0; i < FUR_LENGTH; i++) {
			g.setColor(new Color(FUR_2.getRed(), FUR_2.getGreen(), FUR_2.getBlue(), 0xff-(int)(((float)0xff)*(((float)i)/((float)FUR_LENGTH)))));
			g.fillPolygon(new int[]{800, 700+FUR_LENGTH-i, 650+FUR_LENGTH-i, 660+FUR_LENGTH-i, 750+FUR_LENGTH-i, 850-FUR_LENGTH+i, 940-FUR_LENGTH+i, 950-FUR_LENGTH+i, 900-FUR_LENGTH+i},
					  	  new int[]{810-FUR_LENGTH+i, 870-FUR_LENGTH+i, 830-FUR_LENGTH+i, 740+FUR_LENGTH-i, 700+FUR_LENGTH-i, 700+FUR_LENGTH-i, 740+FUR_LENGTH-i, 830-FUR_LENGTH+i, 870-FUR_LENGTH+i},
					  	  9);
			g.fillOval(647+FUR_LENGTH-i, 805+FUR_LENGTH-i, 77-FUR_LENGTH*2+2*i, 70-FUR_LENGTH*2+2*i);
			g.fillOval(875+FUR_LENGTH-i, 805+FUR_LENGTH-i, 77-FUR_LENGTH*2+2*i, 70-FUR_LENGTH*2+2*i);
			g.fillArc(600+FUR_LENGTH-i, 490+FUR_LENGTH-i, 400-FUR_LENGTH*2+2*i, 400-FUR_LENGTH*2+2*i, 225, 90);
		}
		
		for (int i = 0; i < FUR_LENGTH; i++) {
			g.setColor(new Color(0x0, 0x0, 0x0, 0xff-(int)(((float)0xff)*(((float)i)/((float)FUR_LENGTH)))));
			g.fillOval(750+FUR_LENGTH-i, 673+FUR_LENGTH-i, 100-FUR_LENGTH*2+2*i, 74-FUR_LENGTH*2+2*i);
		}
		
		
		
		//mouth
		g.setColor(TONGUE_COLOR);
		int tongueAux = currentFrame < Timer.FRAME_COUNT/2 ? currentFrame*100/Timer.FRAME_COUNT : 50-(currentFrame-Timer.FRAME_COUNT/2)*100/Timer.FRAME_COUNT;
		g.fillArc(750, 750-tongueAux, 100, 80, 240, 60);
		
		g.setColor(FUR_2);
		g.fillArc(531, 720, 270, 145, 295, 64);
		g.fillArc(799, 720, 270, 145, 180, 64);
		g.fillRect(775, 743, 60, 52);
		
		g.setColor(Color.BLACK);
		
		g.drawLine(800, 700, 800, 800);
		g.drawLine(801, 700, 801, 800);
		g.drawLine(799, 700, 799, 800);
		
		
		g.drawArc(499, 720, 300, 145, 295, 64);
		g.drawArc(500, 720, 300, 145, 295, 64);
		g.drawArc(501, 720, 300, 145, 295, 64);
		
		g.drawArc(800, 720, 300, 145, 180, 64);
		g.drawArc(801, 720, 300, 145, 180, 64);
		g.drawArc(802, 720, 300, 145, 180, 64);
		
		g.fillOval(700, 800, 5, 5);
		g.fillOval(669, 832, 5, 5);
		g.fillOval(769, 798, 5, 5);
		g.fillOval(689, 754, 5, 5);
		g.fillOval(730, 840, 5, 5);
		g.fillOval(743, 756, 5, 5);
		
		g.fillOval(895, 800, 5, 5);
		g.fillOval(926, 832, 5, 5);
		g.fillOval(826, 798, 5, 5);
		g.fillOval(906, 754, 5, 5);
		g.fillOval(865, 840, 5, 5);
		g.fillOval(852, 756, 5, 5);
		
		
		//tophat
		g.setColor(TOPHAT_COLOR_1);
		g.fillArc(515, 340, 570, 140, 180, 180);
		g.fillArc(515, 360, 570, 100, 0, 180);
		g.fillRect(600, 90, 401, 350);
		g.fillArc(600, 60, 400, 60, 0, 180);
		g.setColor(Color.BLACK);
		g.drawArc(600, 60, 400, 60, 180, 180);
		g.drawArc(600, 61, 400, 60, 180, 180);
		g.drawArc(600, 62, 400, 60, 180, 180);
		
		g.setColor(TOPHAT_COLOR_2);
		for (int i = 0; i < 50; i++) {
			g.drawArc(600, 350-i, 400, 100, 180, 180);
		}
		
		//monocle
		g.setColor(MONOCLE_COLOR_1);
		g.fillOval(819, 489, 222, 222);
		g.fillRect(1036, 590, 15, 20);
		
		g.setColor(Color.BLACK);
		g.fillRect(1041, 610, 5, 500);
		
		g.setColor(MONOCLE_FUR_1_COLOR);
		g.fillOval(829, 499, 202, 202);
		
		
		//eyes
		//left
		Color currentEyesColor;
		if(currentFrame < Timer.FRAME_COUNT/2){
			currentEyesColor = new Color(
					(EYES_COLOR_2_1.getRed()*(200/Timer.FRAME_COUNT)*currentFrame + EYES_COLOR_2_2.getRed()*(100-((200/Timer.FRAME_COUNT)*currentFrame)))/100,
					(EYES_COLOR_2_1.getGreen()*(200/Timer.FRAME_COUNT)*currentFrame + EYES_COLOR_2_2.getGreen()*(100-((200/Timer.FRAME_COUNT)*currentFrame)))/100,
					(EYES_COLOR_2_1.getBlue()*(200/Timer.FRAME_COUNT)*currentFrame + EYES_COLOR_2_2.getBlue()*(100-((200/Timer.FRAME_COUNT)*currentFrame)))/100);
		}
		else{
			currentEyesColor = new Color(
					(EYES_COLOR_2_2.getRed()*(200/Timer.FRAME_COUNT)*(currentFrame-50) + EYES_COLOR_2_1.getRed()*(100-((200/Timer.FRAME_COUNT)*(currentFrame-50))))/100,
					(EYES_COLOR_2_2.getGreen()*(200/Timer.FRAME_COUNT)*(currentFrame-50) + EYES_COLOR_2_1.getGreen()*(100-((200/Timer.FRAME_COUNT)*(currentFrame-50))))/100,
					(EYES_COLOR_2_2.getBlue()*(200/Timer.FRAME_COUNT)*(currentFrame-50) + EYES_COLOR_2_1.getBlue()*(100-((200/Timer.FRAME_COUNT)*(currentFrame-50))))/100);
		}
		
		
		for (int i = 0; i < 80; i++) {
			if(i < 40)
				g.setColor(new Color(
						(EYES_COLOR_1.getRed()*(1000-i*25) + currentEyesColor.getRed()*(i*25))/1000,
						(EYES_COLOR_1.getGreen()*(1000-i*25) + currentEyesColor.getGreen()*(i*25))/1000,
						(EYES_COLOR_1.getBlue()*(1000-i*25) + currentEyesColor.getBlue()*(i*25))/1000));
			else
				g.setColor(new Color(
						(currentEyesColor.getRed()*(1000-(i-40)*25) + EYES_COLOR_3.getRed()*((i-40)*25))/1000,
						(currentEyesColor.getGreen()*(1000-(i-40)*25) + EYES_COLOR_3.getGreen()*((i-40)*25))/1000,
						(currentEyesColor.getBlue()*(1000-(i-40)*25) + EYES_COLOR_3.getBlue()*((i-40)*25))/1000));
			g.fillOval(590+(i/2), 500+(i/2), 160-i, 200-i);
		}
		
		//right
		Color currentMonocleEyesColor;
		if(currentFrame < Timer.FRAME_COUNT/2){
			currentMonocleEyesColor = new Color(
					(MONOCLE_EYES_2_1_COLOR.getRed()*(200/Timer.FRAME_COUNT)*currentFrame + MONOCLE_EYES_2_2_COLOR.getRed()*(100-((200/Timer.FRAME_COUNT)*currentFrame)))/100,
					(MONOCLE_EYES_2_1_COLOR.getGreen()*(200/Timer.FRAME_COUNT)*currentFrame + MONOCLE_EYES_2_2_COLOR.getGreen()*(100-((200/Timer.FRAME_COUNT)*currentFrame)))/100,
					(MONOCLE_EYES_2_1_COLOR.getBlue()*(200/Timer.FRAME_COUNT)*currentFrame + MONOCLE_EYES_2_2_COLOR.getBlue()*(100-((200/Timer.FRAME_COUNT)*currentFrame)))/100);
		}
		else{
			currentMonocleEyesColor = new Color(
					(MONOCLE_EYES_2_2_COLOR.getRed()*(200/Timer.FRAME_COUNT)*(currentFrame-50) + MONOCLE_EYES_2_1_COLOR.getRed()*(100-((200/Timer.FRAME_COUNT)*(currentFrame-50))))/100,
					(MONOCLE_EYES_2_2_COLOR.getGreen()*(200/Timer.FRAME_COUNT)*(currentFrame-50) + MONOCLE_EYES_2_1_COLOR.getGreen()*(100-((200/Timer.FRAME_COUNT)*(currentFrame-50))))/100,
					(MONOCLE_EYES_2_2_COLOR.getBlue()*(200/Timer.FRAME_COUNT)*(currentFrame-50) + MONOCLE_EYES_2_1_COLOR.getBlue()*(100-((200/Timer.FRAME_COUNT)*(currentFrame-50))))/100);
		}
		
		for (int i = 0; i < 80; i++) {
			if(i < 40)
				g.setColor(new Color(
						(MONOCLE_EYES_1_COLOR.getRed()*(1000-i*25) + currentMonocleEyesColor.getRed()*(i*25))/1000,
						(MONOCLE_EYES_1_COLOR.getGreen()*(1000-i*25) + currentMonocleEyesColor.getGreen()*(i*25))/1000,
						(MONOCLE_EYES_1_COLOR.getBlue()*(1000-i*25) + currentMonocleEyesColor.getBlue()*(i*25))/1000));
			else
				g.setColor(new Color(
						(currentMonocleEyesColor.getRed()*(1000-(i-40)*25) + MONOCLE_EYES_3_COLOR.getRed()*((i-40)*25))/1000,
						(currentMonocleEyesColor.getGreen()*(1000-(i-40)*25) + MONOCLE_EYES_3_COLOR.getGreen()*((i-40)*25))/1000,
						(currentMonocleEyesColor.getBlue()*(1000-(i-40)*25) + MONOCLE_EYES_3_COLOR.getBlue()*((i-40)*25))/1000));
			g.fillOval(850+(i/2), 500+(i/2), 160-i, 200-i);
		}
				
		
		//eyelid
		//left
		g.setColor(FUR_1);
		int eyelidAux = currentFrame < Timer.FRAME_COUNT/2 ? currentFrame*100/Timer.FRAME_COUNT : 50-(currentFrame-Timer.FRAME_COUNT/2)*100/Timer.FRAME_COUNT;
		g.fillPolygon(new int[]{600, 600, 750, 750},
					  new int[]{530, 500, 500, 535+eyelidAux/2},
		     		  4);
		g.setColor(FUR_2);
		g.fillPolygon(new int[]{600, 750-(50-eyelidAux)/10, 750-(50-eyelidAux)/10, 600},
					  new int[]{530, 535+eyelidAux/2, 530+eyelidAux/2, 525},
					  4);
		//right
		g.setColor(MONOCLE_FUR_1_COLOR);
		g.fillPolygon(new int[]{850, 880, 980, 990},
				      new int[]{535+eyelidAux/2, 520, 520, 530},
				      4);
		g.fillArc(880, 499, 100, 42, 0, 180);
		g.setColor(MONOCLE_FUR_2_COLOR);
		g.fillPolygon(new int[]{1000, 850+(50-eyelidAux)/10, 850+(50-eyelidAux)/10, 1000},
				  	  new int[]{530, 535+eyelidAux/2, 530+eyelidAux/2, 525},
				  	  4);
		
		//glass broke
		g.setColor(Color.BLACK);
		
		if(currentFrame >= Timer.FRAME_COUNT/2-4*Timer.FRAME_DURATION/25 && currentFrame <= Timer.FRAME_COUNT/2+4*Timer.FRAME_DURATION/25){
			g.fillPolygon(new int[]{844, 882, 935, 989, 1011, 1013, 991, 937, 884, 846},
					  	  new int[]{654, 612, 623, 600, 540, 542, 602, 625, 614, 656},
					  	  10);
			if(currentFrame >= Timer.FRAME_COUNT/2-3*Timer.FRAME_DURATION/25 && currentFrame <= Timer.FRAME_COUNT/2+3*Timer.FRAME_DURATION/25){
				g.fillPolygon(new int[]{920, 918, 926, 935, 916, 920, 922, 918, 937, 928, 920, 922},
						  	  new int[]{500, 542, 557, 623, 685, 701, 701, 685, 623, 557, 542, 500},
						  	  12);
				g.fillPolygon(new int[]{918, 920, 1013, 1011},
							  new int[]{542, 544, 542, 540},
							  4);
				if(currentFrame >= Timer.FRAME_COUNT/2-2*Timer.FRAME_DURATION/25 && currentFrame <= Timer.FRAME_COUNT/2+2*Timer.FRAME_DURATION/25){
					g.fillPolygon(new int[]{1013, 916, 880, 926, 846, 848, 930, 884, 918, 1015},
							  	  new int[]{658, 685, 614, 559, 544, 542, 557, 612, 683, 656},
							  	  10);
					g.fillPolygon(new int[]{989, 991, 1015, 1013},
							      new int[]{602, 600, 656, 658},
							      4);
					if(currentFrame >= Timer.FRAME_COUNT/2-Timer.FRAME_DURATION/25 && currentFrame <= Timer.FRAME_COUNT/2+Timer.FRAME_DURATION/25){
						g.fillPolygon(new int[]{830, 858, 882, 957, 989, 1031, 1031, 989, 957, 880, 856, 830},
							  	  	  new int[]{590, 582, 612, 572, 600, 590, 592, 602, 574, 614, 584, 592},
							  	  	  12);
						g.fillPolygon(new int[]{926, 928, 957, 955},
							   	  	  new int[]{559, 557, 572, 574},
							   	  	  4);
						g.fillPolygon(new int[]{858, 856, 918, 920},
						   	  	  	  new int[]{584, 582, 542, 544},
						   	  	  	  4);
					}
				}
			}
		}
		/**/
	}
	
	public void updateCurrentFrame(){
		currentFrame = timer.getCurrentFrame();
	}
}
