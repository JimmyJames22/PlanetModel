package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Main implements MouseListener, KeyListener {

    public JFrame frame;
    public JPanel panel;
    public Canvas canvas;

    public int WIDTH = 600;
    public int HEIGHT = 600;

    public BufferStrategy bufferStrategy;

    public ArrayList<Planet> planets;

    public static void main(String[] args) {
        Main m = new Main();
        m.run();
    }

    public void run(){
        setUpGraphics();
        setUpPlanets();
        while(true){
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            calcGravity();
            render();
        }
    }

    public void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.
        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);
        canvas.addMouseListener(this);// adds the canvas to the panel.
        canvas.addKeyListener(this);

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");
    }

    public void setUpPlanets(){
        planets = new ArrayList<Planet>();
        Planet sun = new Planet("Sun", 1988500, 20, 150, 175, Color.YELLOW, 0, 0);
        planets.add(sun);
        Planet earth = new Planet("Earth", 5.97, 10,125, 200, Color.BLUE, 0, 0);
        planets.add(earth);
        Planet mars = new Planet("Mars", 7, 10, 175, 150, Color.RED, 0, 0);
        planets.add(mars);
    }

    public void calcGravity(){
        for(int i=0; i<planets.size(); i++){
            for(int j=0; j<planets.size(); j++){
                if(i==j){
                    continue;
                }
                double dx = (planets.get(i).xPos+planets.get(i).size/2) - (planets.get(j).xPos+planets.get(j).size/2);
                double dy = (planets.get(i).yPos+planets.get(i).size/2) - (planets.get(j).yPos+planets.get(j).size/2);
                double r = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

                double force = crunchGrav(planets.get(i).mass, planets.get(j).mass, r);
                double rad;

                rad = Math.atan(dy/dx);
                if(dx>0) {
                    rad += Math.PI;
                }

                Vector vect = new Vector(rad, force);
                planets.get(i).vectors.add(vect);
            }
        }
    }

    public void render(){
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        for(int i=0; i<planets.size(); i++){
            planets.get(i).move();
            g.setColor(planets.get(i).color);
            g.drawString(planets.get(i).name, (int) (planets.get(i).xPos), (int) (planets.get(i).yPos-10));
            g.fillOval((int) (planets.get(i).xPos), (int) (planets.get(i).yPos), planets.get(i).size, planets.get(i).size);
            g.setColor(Color.YELLOW);
            g.drawLine((int) (planets.get(i).xPos + (planets.get(i).size/2)), (int) (planets.get(i).yPos + (planets.get(i).size/2)), (int) ((planets.get(i).xPos + (planets.get(i).size/2)) + ((Math.cos(planets.get(i).rad)*Math.abs(planets.get(i).xAccel))*100000000)), (int) ((planets.get(i).yPos + (planets.get(i).size/2) + (Math.sin(planets.get(i).rad)*Math.abs(planets.get(i).yAccel))*100000000)));
            g.drawString( planets.get(i).name + " x: " + planets.get(i).xPos + " y: " + planets.get(i).yPos, 20,  20+20*i);
        }

        g.dispose();
        bufferStrategy.show();
    }

    public double crunchGrav(double m1, double m2, double r){
        double G = 0.00000003;
        return(G*((m1*m2)/Math.pow(r, 2)));
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {

    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
