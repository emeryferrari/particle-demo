package com.emeryferrari.particle;
import javax.swing.*;
import java.awt.*;
public class ParticleDemo {
	private int time = 500;
	private int amount = 50000;
	private int lifetime = 250;
	private int counter = 0;
	private int velocityRandomness = 5;
	private int[] particleArrayX = new int[100000];
	private int[] particleArrayY = new int[100000];
	private int[] lifetimeArray = new int[100000];
	private double[] velocityArrayX = new double[100000];
	private double[] velocityArrayY = new double[100000];
	private int mouseXInit = 0;
	private int mouseYInit = 0;
	private double velocityAmount = 0.25;
	private int lifetimeRandomness = 5;
	private double[] lifetimeRandomnessArray = new double[100000];
	public static void main(String[] args) {
		JFrame frame = new JFrame("Particle Demo");
		frame.setSize(512, 512);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(BorderLayout.CENTER, new ParticleDemo().new Renderer());
		frame.setVisible(true);
	}
	protected class Renderer extends JComponent {
		private static final long serialVersionUID = 1L;
		@Override
		public void paintComponent(Graphics graphics) {
			Point mouse = MouseInfo.getPointerInfo().getLocation();
			for (double i = 0; i < 1.0; i += (double)time/(double)amount) {
				if (counter < amount) {
					particleArrayX[counter+(int)i*amount/time] = mouse.x;
					particleArrayY[counter+(int)i*amount/time] = mouse.y;
					double theta = Math.random()*Math.PI*2.0;
					double magnitude = Math.random();
					velocityArrayX[counter+(int)i*amount/time] = mouse.x-mouseXInit+Math.cos(theta)*magnitude*velocityRandomness;
					velocityArrayY[counter+(int)i*amount/time] = mouse.y-mouseYInit+Math.sin(theta)*magnitude*velocityRandomness;
					lifetimeArray[counter+(int)i*amount/time] = 0;
					lifetimeRandomnessArray[counter+(int)i*amount/time] = (Math.random()*2.0)-1.0;
				}
			}
			for (int i = counter-(lifetime+lifetimeRandomness)*amount/time < 0 ? 0 : counter-(lifetime+lifetimeRandomness)*amount/time; i < counter-1; i++) {
				System.out.println(1);
				if (lifetimeArray[i] < lifetime + lifetimeRandomnessArray[i]*lifetimeRandomness) {
					graphics.drawRect(particleArrayX[i], particleArrayY[i], 5, 5);
					particleArrayX[i] += velocityArrayX[i]*velocityAmount;
					particleArrayY[i] += velocityArrayY[i]*velocityAmount;
					velocityArrayY[i] += 0.5;
				}
				lifetimeArray[i]++;
			}
			if (counter <= amount) {
				counter += amount/time;
			}
			mouseXInit = mouse.x;
			mouseYInit = mouse.y;
			repaint();
		}
	}
}