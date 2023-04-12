import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
/*
 *This class takes an user's input base 10 value and converts it to a number with any other base value. Includes GUI with JTextBox and JSpinner.
 *Very useful for quick conversion to binary
 */
public class BaseConverter
{
	public static void main(String[] args)
   {
		new AppFrame();
	}
}
class AppFrame extends JFrame
					implements ActionListener, ChangeListener, DocumentListener
{
	JButton				exitButton;
	JLabel				textLabel;
	JSpinner			spinner;
	SpinnerNumberModel		spinnerTwoToTwentyModel;
	JTextField			inputTF;
	JTextField			outputTF;
	String				inStr;
	int				spinNum=2;

	AppFrame()
	{
		JPanel myMainPanel;

		exitButton= new JButton("Exit");
		exitButton.setActionCommand("EXIT");
		exitButton.addActionListener(this);
		getRootPane().setDefaultButton(exitButton);


		spinnerTwoToTwentyModel= new SpinnerNumberModel(2,2,20,1);
		spinner= new JSpinner(spinnerTwoToTwentyModel);
		spinner.addChangeListener(this);

		textLabel= new JLabel("Enter a number and select a new base value!");
		inputTF=new JTextField(10);
		outputTF=new JTextField (10);
		outputTF.setEditable(false);
		inputTF.getDocument().addDocumentListener(this);

		myMainPanel= new JPanel(new FlowLayout());
		myMainPanel.setBackground(Color.cyan);
		myMainPanel.add(textLabel);
		myMainPanel.add(inputTF);
		myMainPanel.add(spinner);
		myMainPanel.add(outputTF);
		myMainPanel.add(exitButton);

		add(myMainPanel, BorderLayout.CENTER);

		setupMainFrame();
	}
	void setupMainFrame()
	{
		Toolkit tk;
		Dimension d;

		tk=Toolkit.getDefaultToolkit();
		d=tk.getScreenSize();
		setSize(d.width/4, d.height/4);
		setLocation(d.width/3, d.height/3);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Base Converter");
		setVisible(true);
	}
	public static String convertBase(String inStr, int spinNum)
	{
		return Integer.toString(Integer.parseInt(inStr, 10), spinNum);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("EXIT"))
		{
			System.exit(0);
		}
	}
	public void stateChanged(ChangeEvent e)
	{
		spinNum=(Integer)spinnerTwoToTwentyModel.getNumber();
		System.out.println(spinNum);
		outputTF.setText(convertBase(inStr,spinNum));
	}
	public void insertUpdate(DocumentEvent e)
	{
		try
		{
			inStr=inputTF.getText();
			System.out.println(inStr);
			outputTF.setText(convertBase(inStr,spinNum));
		}
		catch(NumberFormatException nfe)
		{
			JOptionPane.showMessageDialog(null, "please insert a valid number only");
		}
	}
	public void removeUpdate(DocumentEvent e)
	{
		try
		{
			if(inputTF.getText().length()>0)
			{
				inStr=inputTF.getText();
				System.out.println(inStr);
				outputTF.setText(convertBase(inStr,spinNum));
			}

		}
		catch(NumberFormatException nfe)
		{
			JOptionPane.showMessageDialog(null, "please insert a valid number only");
		}

	}
	public void changedUpdate(DocumentEvent e)
	{
	}

}