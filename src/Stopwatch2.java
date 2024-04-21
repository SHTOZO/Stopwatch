import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Stopwatch2  implements ActionListener {

    JFrame frame = new JFrame();
    JButton startButton = new JButton("Start");
    JButton resetButton = new JButton("Reset");
    JLabel timeLabel = new JLabel();
    long startTime;
    long pauseTime = 0;
    int elapsedTime = 0;
    int milliseconds = 0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    boolean started = false;
    String milliseconds_string = String.format("%02d", milliseconds);
    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);
    String hours_string = String.format("%02d", hours);

    Timer timer = new Timer(10, new ActionListener(){

        public void actionPerformed(ActionEvent e) {

            elapsedTime = (int) (System.currentTimeMillis() - startTime);
            elapsedTime -= pauseTime;
            milliseconds = (elapsedTime % 1000) / 10;
            hours = (elapsedTime / 3600000);
            minutes = (elapsedTime / 60000) % 60;
            seconds = (elapsedTime / 1000) % 60;
            milliseconds_string = String.format("%02d", milliseconds);
            seconds_string = String.format("%02d", seconds);
            minutes_string = String.format("%02d", minutes);
            hours_string = String.format("%02d", hours);
            timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string+":"+milliseconds_string);

        }
    });


    Stopwatch2(){

        timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string+":"+milliseconds_string);
        timeLabel.setBounds(50,100,300,100);
        timeLabel.setFont(new Font("Arial", Font.PLAIN,35));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        startButton.setBounds(100,200,100,50);
        startButton.setFont(new Font("Arial", Font.PLAIN,20));
        startButton.setFocusable(false);
        startButton.addActionListener(this);

        resetButton.setBounds(200,200,100,50);
        resetButton.setFont(new Font("Arial", Font.PLAIN,20));
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        frame.add(startButton);
        frame.add(resetButton);
        frame.add(timeLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == startButton){
            if(started==false){
                started = true;
                startButton.setText("Stop");
                start();
            }
            else{
                started = false;
                startButton.setText("Start");
                stop();
            }
        }
        if(e.getSource() == resetButton){
            started=false;
            startButton.setText("Start");
            reset();
        }
    }

    void start(){
        startTime = System.currentTimeMillis();
        pauseTime = elapsedTime * -1;
        timer.start();
    }

    void stop(){
        pauseTime = elapsedTime;
        timer.stop();
    }

    void reset(){
        timer.stop();
        elapsedTime = 0;
        milliseconds = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;
        milliseconds_string = String.format("%02d", milliseconds);
        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        hours_string = String.format("%02d", hours);
        timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string+":"+milliseconds_string);
    }

}

