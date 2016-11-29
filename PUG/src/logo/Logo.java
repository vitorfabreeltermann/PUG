package logo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Logo extends JPanel {

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
				frame.setBounds(0, 0, 1600, 1044);
				logo.setBounds(0, 0, 1600, 1000);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(logo);
				frame.setVisible(true);
				timer.setLogo(frame);
				timer.start();
				MouseListener ml = new MouseListener() {
					@Override
					public void mousePressed(MouseEvent e) {
						BufferedImage img = new BufferedImage(1600, 1000, BufferedImage.TYPE_INT_RGB);
		                Graphics2D g2i = img.createGraphics();
		                logo.paintComponent(g2i);
		                g2i.dispose();
		                try {
		                  ImageIO.write(img, "png", new File("PUG.png"));
		                }
		                catch(IOException ioe) {
		                  ioe.printStackTrace();
		                }
					}
					@Override
					public void mouseReleased(MouseEvent e) {}
					@Override
					public void mouseExited(MouseEvent e) {}
					@Override
					public void mouseEntered(MouseEvent e) {}
					@Override
					public void mouseClicked(MouseEvent e) {}
				};
				logo.addMouseListener(ml);
			}
		});
	}

	private void setBackGround(Graphics g) {
		g.setColor(BACKGROUND_2_2);
		Color backgroundAux1, backgroundAux2;
		if (currentFrame < Timer.FRAME_COUNT / 2) {
			backgroundAux1 = new Color(
					(BACKGROUND_2_1.getRed() * (200 / Timer.FRAME_COUNT)
							* currentFrame + BACKGROUND_1_1.getRed()
							* (100 - ((200 / Timer.FRAME_COUNT) * currentFrame))) / 100,
					(BACKGROUND_2_1.getGreen() * (200 / Timer.FRAME_COUNT)
							* currentFrame + BACKGROUND_1_1.getGreen()
							* (100 - ((200 / Timer.FRAME_COUNT) * currentFrame))) / 100,
					(BACKGROUND_2_1.getBlue() * (200 / Timer.FRAME_COUNT)
							* currentFrame + BACKGROUND_1_1.getBlue()
							* (100 - ((200 / Timer.FRAME_COUNT) * currentFrame))) / 100);
			backgroundAux2 = new Color(
					(BACKGROUND_2_2.getRed() * (200 / Timer.FRAME_COUNT)
							* currentFrame + BACKGROUND_1_2.getRed()
							* (100 - ((200 / Timer.FRAME_COUNT) * currentFrame))) / 100,
					(BACKGROUND_2_2.getGreen() * (200 / Timer.FRAME_COUNT)
							* currentFrame + BACKGROUND_1_2.getGreen()
							* (100 - ((200 / Timer.FRAME_COUNT) * currentFrame))) / 100,
					(BACKGROUND_2_2.getBlue() * (200 / Timer.FRAME_COUNT)
							* currentFrame + BACKGROUND_1_2.getBlue()
							* (100 - ((200 / Timer.FRAME_COUNT) * currentFrame))) / 100);
		} else {
			backgroundAux1 = new Color(
					(BACKGROUND_1_1.getRed() * (200 / Timer.FRAME_COUNT)
							* (currentFrame - 50) + BACKGROUND_2_1.getRed()
							* (100 - ((200 / Timer.FRAME_COUNT) * (currentFrame - 50)))) / 100,
					(BACKGROUND_1_1.getGreen() * (200 / Timer.FRAME_COUNT)
							* (currentFrame - 50) + BACKGROUND_2_1.getGreen()
							* (100 - ((200 / Timer.FRAME_COUNT) * (currentFrame - 50)))) / 100,
					(BACKGROUND_1_1.getBlue() * (200 / Timer.FRAME_COUNT)
							* (currentFrame - 50) + BACKGROUND_2_1.getBlue()
							* (100 - ((200 / Timer.FRAME_COUNT) * (currentFrame - 50)))) / 100);
			backgroundAux2 = new Color(
					(BACKGROUND_1_2.getRed() * (200 / Timer.FRAME_COUNT)
							* (currentFrame - 50) + BACKGROUND_2_2.getRed()
							* (100 - ((200 / Timer.FRAME_COUNT) * (currentFrame - 50)))) / 100,
					(BACKGROUND_1_2.getGreen() * (200 / Timer.FRAME_COUNT)
							* (currentFrame - 50) + BACKGROUND_2_2.getGreen()
							* (100 - ((200 / Timer.FRAME_COUNT) * (currentFrame - 50)))) / 100,
					(BACKGROUND_1_2.getBlue() * (200 / Timer.FRAME_COUNT)
							* (currentFrame - 50) + BACKGROUND_2_2.getBlue()
							* (100 - ((200 / Timer.FRAME_COUNT) * (currentFrame - 50)))) / 100);
		}
		for (int i = 0; i < 1000; i++) {
			g.setColor(new Color(
					(backgroundAux1.getRed() * (i / 10) + backgroundAux2
							.getRed() * (100 - i / 10)) / 100, (backgroundAux1
							.getGreen() * (i / 10) + backgroundAux2.getGreen()
							* (100 - i / 10)) / 100, (backgroundAux1.getBlue()
							* (i / 10) + backgroundAux2.getBlue()
							* (100 - i / 10)) / 100));
			g.drawLine(0, i, 1600, i);
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		currentFrame = timer.getCurrentFrame();
		super.paintComponent(g);
		setBackGround(g);
		showHead(g);
	}

	private void showHead(Graphics g) {
		g.setColor(FUR_1);
		g.fillOval(500, 350, 600, 600); // Head
		showEars(g);
		showSnout(g);
		showMouth(g);
		showTopHat(g);
		showMonocle(g);
		showEyes(g);
		showEyelid(g);
		showBrokenGlasses(g);
	}

	private void showEars(Graphics g) {
		// left
		g.setColor(FUR_2);
		g.fillArc(470, 390, 200, 60, 0, 180);
		g.fillPolygon(new int[] { 520, 470, 670 }, new int[] { 570, 420, 420 },
				3);
		// right
		g.fillArc(930, 390, 200, 60, 0, 180);
		g.fillPolygon(new int[] { 1070, 1130, 930 },
				new int[] { 570, 420, 420 }, 3);
	}

	private void showSnout(Graphics g) {
		// snout
		g.setColor(FUR_2);
		g.fillPolygon(
				new int[] { 800, 700, 650, 660, 750, 850, 940, 950, 900 },
				new int[] { 810, 880, 830, 740, 700, 700, 740, 830, 880 }, 9);
		g.fillOval(646, 810, 75, 75);
		g.fillOval(881, 810, 75, 75);

		g.fillArc(600, 490, 400, 400, 225, 90);

		g.setColor(Color.BLACK);

		g.fillOval(750, 673, 100, 74);
	}

	private void showMouth(Graphics g) {
		g.setColor(TONGUE_COLOR);
		int tongueAux = currentFrame < Timer.FRAME_COUNT / 2 ? currentFrame
				* 100 / Timer.FRAME_COUNT : 50
				- (currentFrame - Timer.FRAME_COUNT / 2) * 100
				/ Timer.FRAME_COUNT;
		g.fillArc(750, 750 - tongueAux, 100, 100, 240, 60);

		g.setColor(FUR_2);
		g.fillArc(501, 730, 300, 145, 295, 64);
		g.fillArc(799, 730, 300, 145, 180, 64);
		g.fillRect(775, 753, 50, 52);

		g.setColor(Color.BLACK);

		g.drawLine(800, 700, 800, 805);
		g.drawLine(801, 700, 801, 805);
		g.drawLine(799, 700, 799, 805);

		g.drawArc(499, 730, 300, 145, 295, 64);
		g.drawArc(500, 730, 300, 145, 295, 64);
		g.drawArc(501, 730, 300, 145, 295, 64);

		g.drawArc(800, 730, 300, 145, 180, 64);
		g.drawArc(801, 730, 300, 145, 180, 64);
		g.drawArc(802, 730, 300, 145, 180, 64);

		g.fillOval(700, 800, 5, 5);
		g.fillOval(659, 832, 5, 5);
		g.fillOval(769, 798, 5, 5);
		g.fillOval(689, 744, 5, 5);
		g.fillOval(730, 850, 5, 5);
		g.fillOval(743, 756, 5, 5);
		g.fillOval(683, 863, 5, 5);

		g.fillOval(895, 800, 5, 5);
		g.fillOval(936, 832, 5, 5);
		g.fillOval(826, 798, 5, 5);
		g.fillOval(906, 744, 5, 5);
		g.fillOval(865, 850, 5, 5);
		g.fillOval(852, 756, 5, 5);
		g.fillOval(912, 863, 5, 5);
	}

	private void showTopHat(Graphics g) {
		g.setColor(TOPHAT_COLOR_1);
		g.fillOval(500, 340, 600, 140);
		g.fillRect(600, 90, 400, 300);
		g.fillArc(600, 40, 400, 100, 0, 180);
		g.setColor(Color.BLACK);
		g.drawArc(600, 40, 400, 100, 180, 180);
		g.drawArc(600, 41, 400, 100, 180, 180);
		g.drawArc(600, 42, 400, 100, 180, 180);

		g.setColor(TOPHAT_COLOR_2);
		for (int i = 0; i < 50; i++) {
			g.drawArc(600, 320 - i, 400, 100, 180, 180);
		}
	}

	private void showMonocle(Graphics g) {
		g.setColor(MONOCLE_COLOR_1);
		g.fillOval(819, 489, 222, 222);
		g.fillRect(1036, 590, 15, 20);

		g.setColor(Color.BLACK);
		g.fillRect(1041, 610, 5, 500);

		g.setColor(MONOCLE_FUR_1_COLOR);
		g.fillOval(829, 499, 202, 202);
	}

	private void showEyes(Graphics g) {
		// left
		Color currentEyesColor;
		if (currentFrame < Timer.FRAME_COUNT / 2) {
			currentEyesColor = new Color(
					(EYES_COLOR_2_1.getRed() * (200 / Timer.FRAME_COUNT)
							* currentFrame + EYES_COLOR_2_2.getRed()
							* (100 - ((200 / Timer.FRAME_COUNT) * currentFrame))) / 100,
					(EYES_COLOR_2_1.getGreen() * (200 / Timer.FRAME_COUNT)
							* currentFrame + EYES_COLOR_2_2.getGreen()
							* (100 - ((200 / Timer.FRAME_COUNT) * currentFrame))) / 100,
					(EYES_COLOR_2_1.getBlue() * (200 / Timer.FRAME_COUNT)
							* currentFrame + EYES_COLOR_2_2.getBlue()
							* (100 - ((200 / Timer.FRAME_COUNT) * currentFrame))) / 100);
		} else {
			currentEyesColor = new Color(
					(EYES_COLOR_2_2.getRed() * (200 / Timer.FRAME_COUNT)
							* (currentFrame - 50) + EYES_COLOR_2_1.getRed()
							* (100 - ((200 / Timer.FRAME_COUNT) * (currentFrame - 50)))) / 100,
					(EYES_COLOR_2_2.getGreen() * (200 / Timer.FRAME_COUNT)
							* (currentFrame - 50) + EYES_COLOR_2_1.getGreen()
							* (100 - ((200 / Timer.FRAME_COUNT) * (currentFrame - 50)))) / 100,
					(EYES_COLOR_2_2.getBlue() * (200 / Timer.FRAME_COUNT)
							* (currentFrame - 50) + EYES_COLOR_2_1.getBlue()
							* (100 - ((200 / Timer.FRAME_COUNT) * (currentFrame - 50)))) / 100);
		}

		for (int i = 0; i < 80; i++) {
			if (i < 40)
				g.setColor(new Color(
						(EYES_COLOR_1.getRed() * (1000 - i * 25) + currentEyesColor
								.getRed() * (i * 25)) / 1000,
						(EYES_COLOR_1.getGreen() * (1000 - i * 25) + currentEyesColor
								.getGreen() * (i * 25)) / 1000, (EYES_COLOR_1
								.getBlue() * (1000 - i * 25) + currentEyesColor
								.getBlue() * (i * 25)) / 1000));
			else
				g.setColor(new Color((currentEyesColor.getRed()
						* (1000 - (i - 40) * 25) + EYES_COLOR_3.getRed()
						* ((i - 40) * 25)) / 1000, (currentEyesColor.getGreen()
						* (1000 - (i - 40) * 25) + EYES_COLOR_3.getGreen()
						* ((i - 40) * 25)) / 1000, (currentEyesColor.getBlue()
						* (1000 - (i - 40) * 25) + EYES_COLOR_3.getBlue()
						* ((i - 40) * 25)) / 1000));
			g.fillOval(590 + (i / 2), 500 + (i / 2), 160 - i, 200 - i);
		}

		// right
		Color currentMonocleEyesColor;
		if (currentFrame < Timer.FRAME_COUNT / 2) {
			currentMonocleEyesColor = new Color(
					(MONOCLE_EYES_2_1_COLOR.getRed()
							* (200 / Timer.FRAME_COUNT) * currentFrame + MONOCLE_EYES_2_2_COLOR
							.getRed()
							* (100 - ((200 / Timer.FRAME_COUNT) * currentFrame))) / 100,
					(MONOCLE_EYES_2_1_COLOR.getGreen()
							* (200 / Timer.FRAME_COUNT) * currentFrame + MONOCLE_EYES_2_2_COLOR
							.getGreen()
							* (100 - ((200 / Timer.FRAME_COUNT) * currentFrame))) / 100,
					(MONOCLE_EYES_2_1_COLOR.getBlue()
							* (200 / Timer.FRAME_COUNT) * currentFrame + MONOCLE_EYES_2_2_COLOR
							.getBlue()
							* (100 - ((200 / Timer.FRAME_COUNT) * currentFrame))) / 100);
		} else {
			currentMonocleEyesColor = new Color(
					(MONOCLE_EYES_2_2_COLOR.getRed()
							* (200 / Timer.FRAME_COUNT) * (currentFrame - 50) + MONOCLE_EYES_2_1_COLOR
							.getRed()
							* (100 - ((200 / Timer.FRAME_COUNT) * (currentFrame - 50)))) / 100,
					(MONOCLE_EYES_2_2_COLOR.getGreen()
							* (200 / Timer.FRAME_COUNT) * (currentFrame - 50) + MONOCLE_EYES_2_1_COLOR
							.getGreen()
							* (100 - ((200 / Timer.FRAME_COUNT) * (currentFrame - 50)))) / 100,
					(MONOCLE_EYES_2_2_COLOR.getBlue()
							* (200 / Timer.FRAME_COUNT) * (currentFrame - 50) + MONOCLE_EYES_2_1_COLOR
							.getBlue()
							* (100 - ((200 / Timer.FRAME_COUNT) * (currentFrame - 50)))) / 100);
		}

		for (int i = 0; i < 80; i++) {
			if (i < 40)
				g.setColor(new Color((MONOCLE_EYES_1_COLOR.getRed()
						* (1000 - i * 25) + currentMonocleEyesColor.getRed()
						* (i * 25)) / 1000, (MONOCLE_EYES_1_COLOR.getGreen()
						* (1000 - i * 25) + currentMonocleEyesColor.getGreen()
						* (i * 25)) / 1000, (MONOCLE_EYES_1_COLOR.getBlue()
						* (1000 - i * 25) + currentMonocleEyesColor.getBlue()
						* (i * 25)) / 1000));
			else
				g.setColor(new Color((currentMonocleEyesColor.getRed()
						* (1000 - (i - 40) * 25) + MONOCLE_EYES_3_COLOR
						.getRed() * ((i - 40) * 25)) / 1000,
						(currentMonocleEyesColor.getGreen()
								* (1000 - (i - 40) * 25) + MONOCLE_EYES_3_COLOR
								.getGreen() * ((i - 40) * 25)) / 1000,
						(currentMonocleEyesColor.getBlue()
								* (1000 - (i - 40) * 25) + MONOCLE_EYES_3_COLOR
								.getBlue() * ((i - 40) * 25)) / 1000));
			g.fillOval(850 + (i / 2), 500 + (i / 2), 160 - i, 200 - i);
		}
	}

	private void showEyelid(Graphics g) {
		// eyelid
		// left
		g.setColor(FUR_1);
		int eyelidAux = currentFrame < Timer.FRAME_COUNT / 2 ? currentFrame
				* 100 / Timer.FRAME_COUNT : 50
				- (currentFrame - Timer.FRAME_COUNT / 2) * 100
				/ Timer.FRAME_COUNT;
		g.fillPolygon(new int[] { 600, 600, 750, 750 }, new int[] { 530, 500,
				500, 535 + eyelidAux / 2 }, 4);
		g.setColor(FUR_2);
		g.fillPolygon(new int[] { 600, 750 - (50 - eyelidAux) / 10,
				750 - (50 - eyelidAux) / 10, 600 }, new int[] { 530,
				535 + eyelidAux / 2, 530 + eyelidAux / 2, 525 }, 4);
		// right
		g.setColor(MONOCLE_FUR_1_COLOR);
		g.fillPolygon(new int[] { 850, 880, 980, 990 }, new int[] {
				535 + eyelidAux / 2, 520, 520, 530 }, 4);
		g.fillArc(880, 499, 100, 42, 0, 180);
		g.setColor(MONOCLE_FUR_2_COLOR);
		g.fillPolygon(new int[] { 1000, 850 + (50 - eyelidAux) / 10,
				850 + (50 - eyelidAux) / 10, 1000 }, new int[] { 530,
				535 + eyelidAux / 2, 530 + eyelidAux / 2, 525 }, 4);
	}

	private void showBrokenGlasses(Graphics g) {
		g.setColor(Color.BLACK);

		if (currentFrame >= Timer.FRAME_COUNT / 2 - 4 * Timer.FRAME_DURATION
				/ 25
				&& currentFrame <= Timer.FRAME_COUNT / 2 + 4
						* Timer.FRAME_DURATION / 25) {
			g.fillPolygon(new int[] { 844, 882, 935, 989, 1011, 1013, 991, 937,
					884, 846 }, new int[] { 654, 612, 623, 600, 540, 542, 602,
					625, 614, 656 }, 10);
			if (currentFrame >= Timer.FRAME_COUNT / 2 - 3
					* Timer.FRAME_DURATION / 25
					&& currentFrame <= Timer.FRAME_COUNT / 2 + 3
							* Timer.FRAME_DURATION / 25) {
				g.fillPolygon(new int[] { 920, 918, 926, 935, 916, 920, 922,
						918, 937, 928, 920, 922 }, new int[] { 500, 542, 557,
						623, 685, 701, 701, 685, 623, 557, 542, 500 }, 12);
				g.fillPolygon(new int[] { 918, 920, 1013, 1011 }, new int[] {
						542, 544, 542, 540 }, 4);
				if (currentFrame >= Timer.FRAME_COUNT / 2 - 2
						* Timer.FRAME_DURATION / 25
						&& currentFrame <= Timer.FRAME_COUNT / 2 + 2
								* Timer.FRAME_DURATION / 25) {
					g.fillPolygon(new int[] { 1013, 916, 880, 926, 846, 848,
							930, 884, 918, 1015 }, new int[] { 658, 685, 614,
							559, 544, 542, 557, 612, 683, 656 }, 10);
					g.fillPolygon(new int[] { 989, 991, 1015, 1013 },
							new int[] { 602, 600, 656, 658 }, 4);
					if (currentFrame >= Timer.FRAME_COUNT / 2
							- Timer.FRAME_DURATION / 25
							&& currentFrame <= Timer.FRAME_COUNT / 2
									+ Timer.FRAME_DURATION / 25) {
						g.fillPolygon(new int[] { 830, 858, 882, 957, 989,
								1031, 1031, 989, 957, 880, 856, 830 },
								new int[] { 590, 582, 612, 572, 600, 590, 592,
										602, 574, 614, 584, 592 }, 12);
						g.fillPolygon(new int[] { 926, 928, 957, 955 },
								new int[] { 559, 557, 572, 574 }, 4);
						g.fillPolygon(new int[] { 858, 856, 918, 920 },
								new int[] { 584, 582, 542, 544 }, 4);
					}
				}
			}
		}
	}
}
