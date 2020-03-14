package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;


import datamodel.IResult;
import datamodel.MeasurementRecord;
import mainengine.IMainEngine;
import mainengine.MainEngineFactory;

import java.awt.GridBagConstraints;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class MainWindow {

	private JFrame frame;
	private IMainEngine mainEngine;
	private IResult result;
	private String path;
	private ArrayList<MeasurementRecord> objCollection = new ArrayList<MeasurementRecord>();
	private HashMap<String, ArrayList<String>> historyMap = new HashMap<String, ArrayList<String>>();
	private ArrayList<String> historyArray;
	private int historyCounter=0;
	private String delimeter;
	private String hasHeader;
	private String fields;
	private String aggType;
	private String function;
	private String description;
	private String resultType;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 633, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		MainEngineFactory factory = new MainEngineFactory();
		mainEngine = factory.createMainEngine("MainEngine");
		
		if (mainEngine == null) {
			System.out.println("Cannot initiate a main engine. Exiting!");
			JOptionPane.showMessageDialog(frame,"Main Engine Null!");  
			System.exit(-1);
		}
		
		JLabel lblFillBelow = new JLabel("Fill below to create collection");
		GridBagConstraints gbc_lblFillBelow = new GridBagConstraints();
		gbc_lblFillBelow.insets = new Insets(0, 0, 5, 5);
		gbc_lblFillBelow.gridx = 1;
		gbc_lblFillBelow.gridy = 0;
		frame.getContentPane().add(lblFillBelow, gbc_lblFillBelow);
		
		JLabel lblFillBelowFor = new JLabel("Fill below for statistics");
		GridBagConstraints gbc_lblFillBelowFor = new GridBagConstraints();
		gbc_lblFillBelowFor.insets = new Insets(0, 0, 5, 5);
		gbc_lblFillBelowFor.gridx = 4;
		gbc_lblFillBelowFor.gridy = 0;
		frame.getContentPane().add(lblFillBelowFor, gbc_lblFillBelowFor);
		
		JLabel lblFillBelowTo = new JLabel("Fill below to create a report");
		GridBagConstraints gbc_lblFillBelowTo = new GridBagConstraints();
		gbc_lblFillBelowTo.insets = new Insets(0, 0, 5, 0);
		gbc_lblFillBelowTo.gridx = 7;
		gbc_lblFillBelowTo.gridy = 0;
		frame.getContentPane().add(lblFillBelowTo, gbc_lblFillBelowTo);
		
		JButton btnFileChooser = new JButton("File chooser");
		GridBagConstraints gbc_btnFileChooser = new GridBagConstraints();
		gbc_btnFileChooser.insets = new Insets(0, 0, 5, 5);
		gbc_btnFileChooser.gridx = 1;
		gbc_btnFileChooser.gridy = 1;
		frame.getContentPane().add(btnFileChooser, gbc_btnFileChooser);
		btnFileChooser.addActionListener(new ActionListener() 
		{
		public void actionPerformed(ActionEvent ch) 
		{
			path=JOptionPane.showInputDialog(frame,"Enter the path");
		}
		});
		
		JButton btnAggregatorType = new JButton("Aggregator Type");
		GridBagConstraints gbc_btnAggregatorType = new GridBagConstraints();
		gbc_btnAggregatorType.insets = new Insets(0, 0, 5, 5);
		gbc_btnAggregatorType.gridx = 4;
		gbc_btnAggregatorType.gridy = 1;
		frame.getContentPane().add(btnAggregatorType, gbc_btnAggregatorType);
		btnAggregatorType.addActionListener(new ActionListener() 
		{
		public void actionPerformed(ActionEvent ch) 
		{
			aggType =JOptionPane.showInputDialog(frame,"Enter Aggregator Type");
			if (!(aggType.equals("month") || aggType.equals("season") || aggType.equals("dayofweek") || aggType.equals("periodofday")) ) {
				JOptionPane.showMessageDialog(frame,"The options are month, season, dayofweek and periodofday, please fill again!");
			}
		}
		});
		
		JButton btnOutputFilePath = new JButton("Output file path");
		GridBagConstraints gbc_btnOutputFilePath = new GridBagConstraints();
		gbc_btnOutputFilePath.insets = new Insets(0, 0, 5, 0);
		gbc_btnOutputFilePath.gridx = 7;
		gbc_btnOutputFilePath.gridy = 1;
		frame.getContentPane().add(btnOutputFilePath, gbc_btnOutputFilePath);
		btnOutputFilePath.addActionListener(new ActionListener() 
		{
		public void actionPerformed(ActionEvent ch) 
		{
			path=JOptionPane.showInputDialog(frame,"Enter the path");
		}
		});
		
		
		JButton btnDelimeter = new JButton("Delimeter ");
		GridBagConstraints gbc_btnDelimeter = new GridBagConstraints();
		gbc_btnDelimeter.insets = new Insets(0, 0, 5, 5);
		gbc_btnDelimeter.gridx = 1;
		gbc_btnDelimeter.gridy = 2;
		frame.getContentPane().add(btnDelimeter, gbc_btnDelimeter);
		btnDelimeter.addActionListener(new ActionListener() 
		{
		public void actionPerformed(ActionEvent ch) 
		{
			delimeter=JOptionPane.showInputDialog(frame,"Enter Delimeter");
		}
		});
		
		JButton btnCreateCollection = new JButton("Create collection");
		GridBagConstraints gbc_btnCreateCollection = new GridBagConstraints();
		gbc_btnCreateCollection.insets = new Insets(0, 0, 5, 5);
		gbc_btnCreateCollection.gridx = 1;
		gbc_btnCreateCollection.gridy = 5;
		frame.getContentPane().add(btnCreateCollection, gbc_btnCreateCollection);
		btnCreateCollection.addActionListener(new ActionListener() 
		{
		public void actionPerformed(ActionEvent ch) 
		{

			int lines = mainEngine.loadData(path, delimeter, Boolean.valueOf(hasHeader), Integer.valueOf(fields), objCollection);
			JOptionPane.showMessageDialog(frame,"New Collection");
			System.out.println(lines);
			
		}
		});
				
		JButton btnFunctionType = new JButton("Function Type");
		GridBagConstraints gbc_btnFunctionType = new GridBagConstraints();
		gbc_btnFunctionType.insets = new Insets(0, 0, 5, 5);
		gbc_btnFunctionType.gridx = 4;
		gbc_btnFunctionType.gridy = 2;
		frame.getContentPane().add(btnFunctionType, gbc_btnFunctionType);
		btnFunctionType.addActionListener(new ActionListener() 
		{
		public void actionPerformed(ActionEvent ch) 
		{
			function =JOptionPane.showInputDialog(frame,"Enter Function");
			if (!(function.equals("sum") || function.equals("avg"))) {
				JOptionPane.showMessageDialog(frame,"The options are sum or avg, please fill again!");
			}
		}
		});
		
		JButton btnResultType = new JButton("Report Type");
		GridBagConstraints gbc_btnResultType = new GridBagConstraints();
		gbc_btnResultType.insets = new Insets(0, 0, 5, 0);
		gbc_btnResultType.gridx = 7;
		gbc_btnResultType.gridy = 2;
		frame.getContentPane().add(btnResultType, gbc_btnResultType);
		btnResultType.addActionListener(new ActionListener() 
		{
		public void actionPerformed(ActionEvent ch) 
		{
			resultType =JOptionPane.showInputDialog(frame,"Enter result type");
			if (!(resultType.equals("text") || resultType.equals("md") || resultType.equals("html"))) {
				JOptionPane.showMessageDialog(frame,"The options are text, md and html, please fill again!");
			}
		}
		});
		
		JButton btnHasHeader = new JButton("Has header");
		GridBagConstraints gbc_btnHasHeader = new GridBagConstraints();
		gbc_btnHasHeader.insets = new Insets(0, 0, 5, 5);
		gbc_btnHasHeader.gridx = 1;
		gbc_btnHasHeader.gridy = 3;
		frame.getContentPane().add(btnHasHeader, gbc_btnHasHeader);
		btnHasHeader.addActionListener(new ActionListener() 
		{
		public void actionPerformed(ActionEvent ch) 
		{
			hasHeader =JOptionPane.showInputDialog(frame,"Enter if has header");
			if (!(hasHeader.equals("true") || hasHeader.equals("false"))) {
				JOptionPane.showMessageDialog(frame,"The options are true or false, please fill again!");
			}
		}
		});
		
		JButton btnDescription = new JButton("Description");
		GridBagConstraints gbc_btnDescription = new GridBagConstraints();
		gbc_btnDescription.insets = new Insets(0, 0, 5, 5);
		gbc_btnDescription.gridx = 4;
		gbc_btnDescription.gridy = 3;
		frame.getContentPane().add(btnDescription, gbc_btnDescription);
		btnDescription.addActionListener(new ActionListener() 
		{
		public void actionPerformed(ActionEvent ch) 
		{
			description=JOptionPane.showInputDialog(frame,"Enter Description");
		}
		});
		
		JButton btnCreateResult = new JButton("Create result");
		GridBagConstraints gbc_btnCreateResult = new GridBagConstraints();
		gbc_btnCreateResult.insets = new Insets(0, 0, 5, 0);
		gbc_btnCreateResult.gridx = 7;
		gbc_btnCreateResult.gridy = 3;
		frame.getContentPane().add(btnCreateResult, gbc_btnCreateResult);
		btnCreateResult.addActionListener(new ActionListener() 
		{
		public void actionPerformed(ActionEvent ch) 
		{

			mainEngine.reportResultInFile(result, resultType, path);
			JOptionPane.showMessageDialog(frame,"New report file");
			
		}
		});
		
		
		JButton btnNumOfFields = new JButton("Num of fields");
		GridBagConstraints gbc_btnNumOfFields = new GridBagConstraints();
		gbc_btnNumOfFields.insets = new Insets(0, 0, 5, 5);
		gbc_btnNumOfFields.gridx = 1;
		gbc_btnNumOfFields.gridy = 4;
		frame.getContentPane().add(btnNumOfFields, gbc_btnNumOfFields);
		btnNumOfFields.addActionListener(new ActionListener() 
		{
		public void actionPerformed(ActionEvent ch) 
		{
			fields=JOptionPane.showInputDialog(frame,"Enter num of fields");
		}
		});
		
		JButton btnAggregate = new JButton("Aggregate");
		GridBagConstraints gbc_btnAggregate = new GridBagConstraints();
		gbc_btnAggregate.insets = new Insets(0, 0, 5, 5);
		gbc_btnAggregate.gridx = 4;
		gbc_btnAggregate.gridy = 4;
		frame.getContentPane().add(btnAggregate, gbc_btnAggregate);
		btnAggregate.addActionListener(new ActionListener() 
		{
		public void actionPerformed(ActionEvent ch) 
		{

			result = mainEngine.aggregateByTimeUnit(objCollection, aggType, function, description);
			JOptionPane.showMessageDialog(frame,"Aggregated");
			historyCounter+=1;
			historyArray= new ArrayList<String>();
			
			historyArray.add(path);
			historyArray.add(aggType);
			historyArray.add(function);
			historyArray.add(description);

			historyMap.put(Integer.toString(historyCounter), historyArray);
			
		}
		});
		
		DefaultListModel dlm = new DefaultListModel();
		JList list_1 = new JList(dlm);
		GridBagConstraints gbc_list_1 = new GridBagConstraints();
		gbc_list_1.insets = new Insets(0, 0, 0, 5);
		gbc_list_1.fill = GridBagConstraints.BOTH;
		gbc_list_1.gridx = 1;
		gbc_list_1.gridy = 8;
		frame.getContentPane().add(list_1, gbc_list_1);
	
		
		JButton btnOpenHistoryReport = new JButton("Open History report");
		GridBagConstraints gbc_btnOpenHistoryReport = new GridBagConstraints();
		gbc_btnOpenHistoryReport.insets = new Insets(0, 0, 0, 5);
		gbc_btnOpenHistoryReport.gridx = 4;
		gbc_btnOpenHistoryReport.gridy = 8;
		frame.getContentPane().add(btnOpenHistoryReport, gbc_btnOpenHistoryReport);
		btnOpenHistoryReport.addActionListener(new ActionListener() 
		{
		public void actionPerformed(ActionEvent ch) 
		{
			dlm.removeAllElements();
			 for(String name: historyMap.keySet()) {
				 dlm.addElement("Path: " + name.toString());
				 for (int i = 0; i < historyMap.get(name).size(); i++) {
					 dlm.addElement(historyMap.get(name).get(i));
				 }
			 }
		}
		});	
		
		JButton btnExit = new JButton("Exit");
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.gridx = 7;
		gbc_btnExit.gridy = 8;
		frame.getContentPane().add(btnExit, gbc_btnExit);
		btnExit.addActionListener(new ActionListener() 
		{
		public void actionPerformed(ActionEvent ch) 
		{
			 int a=JOptionPane.showConfirmDialog(frame,"Are you sure to exit?");  
			 if(a==JOptionPane.YES_OPTION){
				 JOptionPane.showMessageDialog(frame,"Goodbye!!!");
				 System.exit(0);  
			 }  
		}
		});		
		
	}
	
}
