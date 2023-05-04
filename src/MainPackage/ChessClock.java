package MainPackage;
import java.awt.*;

import java.awt.event.*;
import javax.swing.*;

public class ChessClock {
	
	JFrame frame = new JFrame();
	JLabel timeLabel = new JLabel();

	//Initial values of the chess clock inputted by the player and the elapsed time from that timer
	private long elapsedMinutes = 0;
	private long elapsedSeconds = 0;
	private long minutes;
	private long seconds;
	private long startMinutes;
	private long startSeconds;

	//Formats the mintues and seconds to appear as 2 digits
	String minutes_string = String.format("%02d", minutes);
	String seconds_string = String.format("%02d", seconds);
	
	//1000ms which is 1s is the frequency in which the clock updates
	Timer timer = new Timer(1000, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			seconds--;
			elapsedSeconds++;
			if(seconds == -1) {
				seconds = 59;
				minutes--;
				elapsedMinutes++;
			}
			if (minutes == 0 && seconds == 0) {
				timer.stop();
			}
			minutes_string = String.format("%02d", minutes);
			seconds_string = String.format("%02d", seconds);
			timeLabel.setText(minutes_string + ":" + seconds_string);
		}
	});

	ChessClock(long m, long s) {
		
		this.minutes = m;
		this.seconds = s;
		startMinutes = m;
		startSeconds = s;
		minutes_string = String.format("%02d", this.minutes);
		seconds_string = String.format("%02d", this.seconds);

		timeLabel.setText(minutes_string + ":" + seconds_string);
		timeLabel.setBounds(100,100,200,100);
		timeLabel.setFont(new Font("Verdana",Font.PLAIN,35));
		timeLabel.setBorder(BorderFactory.createBevelBorder(1));
		timeLabel.setOpaque(true);
		timeLabel.setHorizontalAlignment(JTextField.CENTER);
		
		frame.add(timeLabel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420,420);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	
	public void start() {
		timer.start();
	}
	
	public void stop() {
		timer.stop();
	}
	
	public void reset() {
		timer.stop();
		minutes = startMinutes;
		seconds = startSeconds;
		elapsedMinutes = 0;
		elapsedSeconds = 0;
		minutes_string = String.format("%02d", minutes);
		seconds_string = String.format("%02d", seconds);
		timeLabel.setText(minutes_string + ":" + seconds_string);
	}

	//Checks if the clock has run out
	public boolean finishedCheck() {
		if ((elapsedMinutes == startMinutes) && ((elapsedSeconds % 60) == startSeconds)) {
			return true;
		}
		return false;
	}

	//Returns total time elapsed by the clock in seconds
	public long timeElapsed() {
		return elapsedSeconds;
	}
	
	//Checks if the timer is running
	boolean runningCheck() {
		if(timer.isRunning()) {
			return true;
		}
		return false;
	}
}
