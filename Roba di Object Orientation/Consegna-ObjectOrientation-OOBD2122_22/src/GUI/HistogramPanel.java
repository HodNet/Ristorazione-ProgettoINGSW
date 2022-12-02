package GUI;

import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HistogramPanel extends JPanel {
	
	private class Bin {
		public JLabel title;
		public JLabel background;
		public JLabel rectangle;
		public int frequency = 0;
		
		public Bin() {
			title = new JLabel("");
			title.setFont(new Font("Tahoma", Font.PLAIN, 10));
			title.setHorizontalAlignment(SwingConstants.CENTER);
			background = new JLabel("");
			rectangle = new JLabel("");
		}
		
		public Bin(int frequency) {
			title = new JLabel("");
			title.setFont(new Font("Tahoma", Font.PLAIN, 10));
			title.setHorizontalAlignment(SwingConstants.CENTER);
			background = new JLabel("");
			rectangle = new JLabel("");
			this.frequency = frequency;
		}
		
		public Bin(String binValue) {
			title = new JLabel(binValue);
			title.setFont(new Font("Tahoma", Font.PLAIN, 10));
			title.setHorizontalAlignment(SwingConstants.CENTER);
			background = new JLabel("");
			rectangle = new JLabel("");
		}
	}
	
	private class Frequency {
		public JLabel title;
		
		public Frequency() {
			title = new JLabel("");
			title.setFont(new Font("Tahoma", Font.PLAIN, 10));
		}
	}
	
	private static final int axisWidth = 15;
	private static final int axisHeight = 13;
	
	private static final int numberOfFrequencies = 20;
	private int numberOfBins = 29;
	
	private static final int imageFrequencyAxisWidth = 29;
	private static final int imageBinAxisHeight = 26;
	private static final int imageTopBorderHeight = 26;
	private static final int imageBinWidth = 63;
	private static final int imageWidth = 1919;
	private static final int imageHeight = 1116; 
	private int frequencyAxisWidth = imageFrequencyAxisWidth;
	private int binAxisHeight = imageBinAxisHeight;
	private int binWidth = imageBinWidth;
	private int width = imageWidth;
	private int height = imageHeight;
	
	private LinkedList<String> dataList;
	private LinkedList<Bin> bins;
	private LinkedList<Frequency> frequencies;
	
	private JLabel frequencyAxis;
	private int maxFrequency = numberOfFrequencies;
	
	private GroupLayout gl;
	
	/*
	 * Constructors:
	 * 
	 */
	public HistogramPanel(int numberOfBins) {
		Init(numberOfBins);
		for(int i=0; i<numberOfBins; i++) bins.add(new Bin());
		Resize(width, height);
		addResizeListener();
		createHistogram();
	}
	
	public HistogramPanel(int numberOfBins, int width, int height) {
		Init(numberOfBins);
		for(int i=0; i<numberOfBins; i++) bins.add(new Bin());
		Resize(width, height);
		addResizeListener();
		createHistogram();
	}
	
	public HistogramPanel(int numberOfBins, int width, int height, String bin[]) {
		Init(numberOfBins);
		
		for(int i=0; i<numberOfBins; i++) {
			try {
				dataList.add( new String(bin[i]) );
			}
			catch(ArrayIndexOutOfBoundsException e) {}
		}

		Resize(width, height);
		addResizeListener();
		createHistogram();
	}
	
	public HistogramPanel(int numberOfBins, int width, int height, String bin[], int frequency[]) {
		Init(numberOfBins);
		
		for(int i=0; i<numberOfBins; i++) {
			try {
				dataList.add( new String(bin[i]) );
				bins.add( new Bin(frequency[i]) );
			}
			catch(ArrayIndexOutOfBoundsException e) {}
		}

		Resize(width, height);
		addResizeListener();
		createHistogram();
	}
	
	public HistogramPanel(int numberOfBins, String bin[]) {
		Init(numberOfBins);
		
		for(int i=0; i<numberOfBins; i++) {
			try {
				dataList.add( new String(bin[i]) );
			}
			catch(ArrayIndexOutOfBoundsException e) {}
		}

		Resize(width, height);
		addResizeListener();
		createHistogram();
	}
	
	public HistogramPanel(int numberOfBins, String bin[], int frequency[]) {
		Init(numberOfBins);
		
		for(int i=0; i<numberOfBins; i++) {
			try {
				dataList.add( new String(bin[i]) );
				bins.add( new Bin(frequency[i]) );
			}
			catch(ArrayIndexOutOfBoundsException e) {}
		}
		
		Resize(width, height);
		addResizeListener();
		createHistogram();
	}
	
	public HistogramPanel(int width, int height, final ArrayList<String> bin) {
		Init(bin.size());
		
		for(int i=0; i<numberOfBins; i++) {
			try {
				dataList.add( new String(bin.get(i)) );
			}
			catch(ArrayIndexOutOfBoundsException e) {}
		}

		Resize(width, height);
		addResizeListener();
		createHistogram();
	}
	
	public HistogramPanel(int width, int height, final ArrayList<String> bin, final ArrayList<Integer> frequency) {
		Init(bin.size());
		
		for(int i=0; i<numberOfBins; i++) {
			try {
				dataList.add( new String(bin.get(i)) );
				bins.add( new Bin(frequency.get(i)) );
			}
			catch(ArrayIndexOutOfBoundsException e) {}
		}

		Resize(width, height);
		addResizeListener();
		createHistogram();
	}
	
	public HistogramPanel(final ArrayList<String> bin) {
		Init(bin.size());
		
		for(int i=0; i<numberOfBins; i++) {
			try {
				dataList.add( new String(bin.get(i)) );
			}
			catch(ArrayIndexOutOfBoundsException e) {}
		}

		Resize(width, height);
		addResizeListener();
		createHistogram();
	}
	
	public HistogramPanel(final ArrayList<String> bin, final ArrayList<Integer> frequency) {
		Init(bin.size());
		
		for(int i=0; i<numberOfBins; i++) {
			try {
				dataList.add( new String(bin.get(i)) );
				bins.add( new Bin(frequency.get(i)) );
			}
			catch(ArrayIndexOutOfBoundsException e) {}
		}
		
		Resize(width, height);
		addResizeListener();
		createHistogram();
	}
	
	/*
	 * Specific methods:
	 * 
	 */
	public void add(String data) {
		if( !dataList.contains(data) ) {
			dataList.add(data);
			int index = dataList.size()-1;
			if(index < bins.size())
				bins.get(index).title.setText(data);
			else {
				numberOfBins++;
				bins.add(new Bin(data));
			}
		}
		bins.get(dataList.indexOf(data)).frequency++;
		//update();
		Resize(width, height);
		createHistogram();
	}
	
	/*
	 * Auxiliary functions:
	 * 
	 */
	private void Init(int numberOfBins) {
		this.numberOfBins = numberOfBins;
		
		dataList = new LinkedList<String>();
		bins = new LinkedList<Bin>();
		frequencies = new LinkedList<Frequency>();
		
		frequencyAxis = new JLabel("");
		
		for(int i=0; i<numberOfFrequencies + 1; i++) { //uno in più per lo zero
			frequencies.add(new Frequency());
		}
	}
	
	private void Resize(int width, int height) {
		width = width - axisWidth;
		height = height - axisHeight;
		frequencyAxisWidth = imageFrequencyAxisWidth * width/imageWidth;
		binAxisHeight = imageBinAxisHeight * height/imageHeight;
		binWidth = imageBinWidth * width/imageWidth * 30/numberOfBins;
		binWidth = Integer.max(binWidth, maxBinTitleWidth());
		this.width = frequencyAxisWidth + binWidth*numberOfBins;
		this.height = height;
		
		frequencyAxis.setBounds(axisWidth, 0, frequencyAxisWidth, this.height);
		scaleImage(frequencyAxis, "histogram frequency axis.jpg");
		
		int i=0;
		for(Bin bin : bins) {
			bin.background.setBounds(axisWidth + frequencyAxisWidth + i*binWidth, 0, binWidth, this.height);
			scaleImage(bin.background, "histogram blank bin.jpg");
			i++;
		}
		
		update();
	}
	
	private int maxBinTitleWidth() {
		int max = 0;
		for(Bin bin : bins) {
			max = Integer.max(max, bin.title.getWidth());
		}
		return max;
	}
	
	private void addResizeListener() {
		addComponentListener( new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int w = ((Component)e.getSource()).getWidth();
				int h = ((Component)e.getSource()).getHeight();
				Resize(w, h);
			}
		});
	}
	
	private void createHistogram() {
		gl = new GroupLayout(this);
		
		ParallelGroup parallelY = gl.createParallelGroup(Alignment.LEADING);
		SequentialGroup sequentialY = gl.createSequentialGroup();
		int i=numberOfFrequencies;
		for(Frequency frequency : frequencies) {
			addFrequency(parallelY, sequentialY, i, frequency);
			i--;
		}
		
		ParallelGroup parallelX = gl.createParallelGroup(Alignment.LEADING);
		SequentialGroup sequentialXandBins = gl.createSequentialGroup();
		ParallelGroup parallelBins = gl.createParallelGroup(Alignment.TRAILING);
		i=0;
		for(Bin bin : bins) {
			addBin(parallelX, sequentialXandBins, parallelBins, i, bin);
			i++;
		}
		
		gl.setHorizontalGroup(
				gl.createSequentialGroup()
				.addGroup(parallelY) //all y axis texts
				.addComponent(frequencyAxis)
				.addGroup(sequentialXandBins) //all x axis texts and histogram bins
		);
		gl.setVerticalGroup(
			gl.createSequentialGroup()
			.addGroup(gl.createParallelGroup(Alignment.LEADING)
					.addGroup(sequentialY) //all y axis texts
					.addComponent(frequencyAxis)
					.addGroup(parallelBins)) //all histogram Bins
			.addGroup(parallelX) //all x axis texts
		);
		setLayout(gl);
	}
	
	private void addFrequency(ParallelGroup parallel, SequentialGroup sequential, int i, Frequency frequency) {
		int frequencyValue = (i==numberOfFrequencies) ? (maxFrequency) : (i * maxFrequency/numberOfFrequencies);
		frequency.title.setText(String.valueOf(frequencyValue));
		parallel.addComponent(frequency.title);
		sequential.addComponent(frequency.title, GroupLayout.PREFERRED_SIZE, (i%3==0 ? 14 : 12), Short.MAX_VALUE);
	}
	
	private void addBin(ParallelGroup parallelX, SequentialGroup sequential, ParallelGroup parallelBins, int i, Bin bin) {
		if(i < dataList.size()) {
			bin.title.setText(dataList.get(i));
		}
		
		sequential.addGroup(gl.createParallelGroup(Alignment.LEADING)
				.addComponent(bin.rectangle)
				.addComponent(bin.background)
				.addComponent(bin.title));
		parallelBins.addComponent(bin.rectangle);
		parallelBins.addComponent(bin.background);
		parallelX.addComponent(bin.title);
	}
	
	private void update() {
		int gap = 2;
		int topBorderHeight = imageTopBorderHeight * height/imageHeight;
		
		int max = maxFrequency();
		if(max>numberOfFrequencies) {
			maxFrequency = generateNextMultipleOf_NumberOfFrequencies_Of(max);
		}
		
		for(int i=0; i<numberOfBins; i++) {
			int binHeight = (height-binAxisHeight-topBorderHeight) * bins.get(i).frequency / maxFrequency;
			bins.get(i).rectangle.setBounds(axisWidth + frequencyAxisWidth + i*binWidth, 0, binWidth-gap, binAxisHeight + binHeight);
			scaleImage(bins.get(i).rectangle, "rectangle selected.jpg");
		}
	}
	
	private int maxFrequency() {
		int max = 0;
		for(Bin bin : bins) {
			max = Integer.max(max, bin.frequency);
		}
		return max;
	}
	
	private int generateNextMultipleOf_NumberOfFrequencies_Of(int n) {
		return numberOfFrequencies * (int)Math.ceil( (double)n / (double)numberOfFrequencies );
	}
	
	private void scaleImage(JLabel label, String file_name) {
		ImageIcon icon = new ImageIcon(HistogramPanel.class.getResource("/images/" + file_name));
		Image img = icon.getImage();
		Image scaledImg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImg);
		label.setIcon(scaledIcon);
	}
	

}
