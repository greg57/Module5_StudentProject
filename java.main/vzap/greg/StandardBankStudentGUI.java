package vzap.greg;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JTabbedPane;

import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.JList;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;

import java.awt.Color;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.ScrollPaneConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class StandardBankStudentGUI.
 */
public class StandardBankStudentGUI extends JFrame
{

	private JPanel contentPane;
	private JTextField studentIdJTF;
	private JTextField fnameJTF;
	private JTextField surnameJTF;
	private JTabbedPane tabbedPane;
	private JComboBox<String> citiesComboBox;
	private JPanel addStudentPanel;
	private JPanel editStudentPanel, addCityPanel, editCityPanel;
	private MySQL_Connection mysqlConnection;
	private Connection connection;
	private Vector<String> citiesList;
	private Vector<String> studentList;
	private Vector<String> studentActiveVectorNew;
	private Vector<String> studentActiveVectorEdit;
	private Statement stmt;
	private ResultSet rs;
	private JPanel panel;
	private JButton btnAdd;
	private JButton btnCancel;
	private JButton btnExit;
	private JScrollPane scrollPaneEdit;
	private JList studentListEdit;
	private JList<String> cityList;
	private JPanel southPanel;
	private JPanel centrePanel;
	private JButton btnAddCity;
	private JButton btnCancel_1;
	private JLabel lblCityID;
	private JLabel lblCityName_1;
	private JTextField cityIdJTF;
	private JTextField cityNameJTF;
	private MySQL_CityDAO cityDAO;
	private MySQL_StudentDAO studentDAO;
	private JButton btnDeleteEdit;
	private JButton btnUpdateEdit;
	private JLabel label;
	private JLabel lblFnameEdit;
	private JLabel lblSurnameEdit;
	private JLabel lblSalaryEdit;
	private JLabel lblCityNameEdit;
	private JComboBox<String> citiesComboBoxEdit;
	private JTextField salaryJTF_Edit;
	private JTextField surnameJTF_Edit;
	private JTextField firstNameJTF_Edit;
	private JTextField studentID_JTF_Edit;
	private JLabel lblSelectStudentEdit;
	private StudentListEditListSelection sles;
	private JFormattedTextField salaryJTF;
	private int dotCount;
	private int charCount;
	private int posOfFirstDot;
	private JLabel lblActive;
	private JLabel lblStudentActive;
	private JComboBox<String> studentEditActiveJComboBox;
	private JComboBox<String> addStudentActiveJComboBox;
	private Vector<Student> listOfStudents;

	/** The list of cities. */
	private Vector<City> listOfCities;
	private JLabel lblCityId;
	private JLabel lblCityName_2;
	private JTextField cityID_JTF;
	private JTextField cityNameEditJTF;
	private JScrollPane scrollPane;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnCancel_2;

	/**
	 * Create the frame.
	 */
	public StandardBankStudentGUI()
	{
		setTitle("Standard Bank Student");

		mysqlConnection = new MySQL_Connection();
		connection = mysqlConnection.getConnection();

		cityDAO = new MySQL_CityDAO();
		studentDAO = new MySQL_StudentDAO();

		studentActiveVectorNew = new Vector<String>();
		studentActiveVectorNew.add("Yes");
		studentActiveVectorNew.add("No");
		studentActiveVectorEdit = new Vector<String>();
		studentActiveVectorEdit.add("Yes");
		studentActiveVectorEdit.add("No");

		sles = new StudentListEditListSelection();
		dotCount = 0;
		charCount = 0;
		posOfFirstDot = 0;

		listOfCities = cityDAO.getCities();
		System.out.println("listOf Cities size = " + listOfCities.size());
		citiesList = new Vector<String>();
		for (int i = 0; i < listOfCities.size(); i++)
		{
			citiesList.add(listOfCities.get(i).getCityName());
		}
		Collections.sort(citiesList);
		// try
		// {
		// stmt = connection.createStatement();
		// String query = "Select cityName from citytable;";
		// citiesList = new Vector<String>();

		// rs = stmt.executeQuery(query);
		// while (rs.next())
		// {
		// citiesList.addElement(rs.getString(1));
		// }
		// Collections.sort(citiesList);

		listOfStudents = studentDAO.getAllStudents();
		studentList = new Vector<String>();

		for (int i = 0; i < listOfStudents.size(); i++)
		{
			int id = listOfStudents.get(i).getStudentID();
			String firstName = listOfStudents.get(i).getFirstName();
			String surname = listOfStudents.get(i).getSurname();
			String firstNameSurname = id + ":   " + surname + ",   "
					+ firstName;
			studentList.addElement(firstNameSurname);
		}

		// query = "select studentID, firstName, surname from student;";
		// rs = stmt.executeQuery(query);
		// while (rs.next())
		// {
		// int id = rs.getInt(1);
		// String firstName = rs.getString(2);
		// String surname = rs.getString(3);
		// String firstNameSurname = id + ":   " + surname + ",   "
		// + firstName;
		// studentList.addElement(firstNameSurname);
		// }
		// Collections.sort(studentList);
		// stmt.close();
		// rs.close();

		// }
		// catch (SQLException e)
		// {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MyWindowCloser mwc = new MyWindowCloser();
		this.addWindowListener(mwc);

		setBounds(100, 100, 906, 703);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		addStudentPanel = new JPanel();
		tabbedPane.addTab("Add Student", null, addStudentPanel, null);

		JLabel lblStudentID = new JLabel("Student ID");
		lblStudentID.setFont(new Font("Tahoma", Font.BOLD, 20));

		studentIdJTF = new JTextField();
		studentIdJTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		studentIdJTF.setColumns(10);
		studentIdJTF.setEditable(false);

		String query = "Select max(studentID) from student;";
		try
		{
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			int idNo = rs.getInt(1) + 1;
			studentIdJTF.setText("" + idNo);
			stmt.close();
			rs.close();
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JLabel lblStudentFirstName = new JLabel("Student First Name");
		lblStudentFirstName.setFont(new Font("Tahoma", Font.BOLD, 20));

		fnameJTF = new JTextField();
		fnameJTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fnameJTF.setColumns(10);
		fnameJTF.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent ke)
			{
				char ch = ke.getKeyChar();
				System.out.println("ch typed = " + ch);
				if (!(Character.isLetter(ch)))
				{
					ke.consume();
				}
			}
		});

		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setFont(new Font("Tahoma", Font.BOLD, 20));

		surnameJTF = new JTextField();
		surnameJTF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		surnameJTF.setColumns(10);
		surnameJTF.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent ke)
			{
				char ch = ke.getKeyChar();
				System.out.println("ch typed = " + ch);
				if (!(Character.isLetter(ch)))
				{
					ke.consume();
				}
			}
		});

		JLabel lblSalary = new JLabel("Salary");
		lblSalary.setFont(new Font("Tahoma", Font.BOLD, 20));

		JLabel lblCityName = new JLabel("City Name");
		lblCityName.setFont(new Font("Tahoma", Font.BOLD, 20));
		citiesComboBox = new JComboBox<String>(citiesList);
		citiesComboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel = new JPanel();
		salaryJTF = new JFormattedTextField();
		salaryJTF.addFocusListener(new FocusAdapter()
		{
			public void focusGained(FocusEvent fe)
			{
				salaryJTF.select(0, salaryJTF.getText().length());
			}
		});
		salaryJTF.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent ke)
			{

				char ch = ke.getKeyChar();
				if (dotCount == 1)
				{
					System.out.println("in if(dotCount == 1)");
					charCount++;
					System.out.println("charCount = " + charCount);
					if (charCount > 2)
					{
						ke.consume();
						return;
					}
				}
				if (ch == '.')
				{
					dotCount++;
					System.out.println("dotCount = " + dotCount);

					if (dotCount > 1)
					{
						// charCount = 0;
						ke.consume();
					}
				}

				if ((ch < '0' || ch > '9') && ch != '.')
				{
					ke.consume();
				}

			}
		});
		salaryJTF.setText("0.0");
		salaryJTF.setFont(new Font("Tahoma", Font.PLAIN, 20));

		lblActive = new JLabel("Active");
		lblActive.setFont(new Font("Tahoma", Font.BOLD, 20));

		addStudentActiveJComboBox = new JComboBox(studentActiveVectorNew);
		addStudentActiveJComboBox.setSelectedIndex(0);
		addStudentActiveJComboBox.setFont(new Font("Tahoma", Font.BOLD, 20));
		GroupLayout gl_addStudentPanel = new GroupLayout(addStudentPanel);
		gl_addStudentPanel
				.setHorizontalGroup(gl_addStudentPanel
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								Alignment.LEADING,
								gl_addStudentPanel
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(panel,
												GroupLayout.DEFAULT_SIZE, 801,
												Short.MAX_VALUE)
										.addContainerGap())
						.addGroup(
								Alignment.LEADING,
								gl_addStudentPanel
										.createSequentialGroup()
										.addGap(91)
										.addGroup(
												gl_addStudentPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																lblStudentID)
														.addComponent(
																lblStudentFirstName,
																GroupLayout.PREFERRED_SIZE,
																232,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblSurname,
																GroupLayout.PREFERRED_SIZE,
																111,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblSalary,
																GroupLayout.PREFERRED_SIZE,
																111,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblCityName,
																GroupLayout.PREFERRED_SIZE,
																111,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblActive,
																GroupLayout.PREFERRED_SIZE,
																111,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED,
												181, Short.MAX_VALUE)
										.addGroup(
												gl_addStudentPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_addStudentPanel
																		.createParallelGroup(
																				Alignment.TRAILING,
																				false)
																		.addComponent(
																				salaryJTF)
																		.addComponent(
																				citiesComboBox,
																				Alignment.LEADING,
																				0,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				surnameJTF,
																				Alignment.LEADING,
																				GroupLayout.DEFAULT_SIZE,
																				286,
																				Short.MAX_VALUE)
																		.addComponent(
																				fnameJTF,
																				Alignment.LEADING,
																				GroupLayout.DEFAULT_SIZE,
																				286,
																				Short.MAX_VALUE)
																		.addComponent(
																				studentIdJTF,
																				Alignment.LEADING,
																				GroupLayout.DEFAULT_SIZE,
																				286,
																				Short.MAX_VALUE))
														.addComponent(
																addStudentActiveJComboBox,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(53)));
		gl_addStudentPanel
				.setVerticalGroup(gl_addStudentPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_addStudentPanel
										.createSequentialGroup()
										.addGap(53)
										.addGroup(
												gl_addStudentPanel
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																lblStudentID)
														.addComponent(
																studentIdJTF,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(39)
										.addGroup(
												gl_addStudentPanel
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																lblStudentFirstName,
																GroupLayout.PREFERRED_SIZE,
																25,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																fnameJTF,
																GroupLayout.PREFERRED_SIZE,
																31,
																GroupLayout.PREFERRED_SIZE))
										.addGap(41)
										.addGroup(
												gl_addStudentPanel
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																lblSurname,
																GroupLayout.PREFERRED_SIZE,
																25,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																surnameJTF,
																GroupLayout.PREFERRED_SIZE,
																31,
																GroupLayout.PREFERRED_SIZE))
										.addGap(47)
										.addGroup(
												gl_addStudentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblSalary,
																GroupLayout.PREFERRED_SIZE,
																25,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																salaryJTF,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(38)
										.addGroup(
												gl_addStudentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblCityName,
																GroupLayout.PREFERRED_SIZE,
																25,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																citiesComboBox,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(38)
										.addGroup(
												gl_addStudentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblActive,
																GroupLayout.PREFERRED_SIZE,
																25,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																addStudentActiveJComboBox,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED, 68,
												Short.MAX_VALUE)
										.addComponent(panel,
												GroupLayout.PREFERRED_SIZE, 73,
												GroupLayout.PREFERRED_SIZE)));
		{
			btnAdd = new JButton("Add");
			btnAdd.addActionListener(new BtnAddStudentAction());
			btnAdd.setFont(new Font("Tahoma", Font.BOLD, 20));
			panel.add(btnAdd);
		}
		{
			btnCancel = new JButton("Cancel");
			btnCancel.setFont(new Font("Tahoma", Font.BOLD, 20));
			panel.add(btnCancel);
		}
		{
			btnExit = new JButton("Exit");
			btnExit.setFont(new Font("Tahoma", Font.BOLD, 20));
			panel.add(btnExit);
		}
		addStudentPanel.setLayout(gl_addStudentPanel);

		editStudentPanel = new JPanel();
		tabbedPane.addTab("Edit Students", null, editStudentPanel, null);
		studentListEdit = new JList<String>(studentList);
		studentListEdit.addListSelectionListener(sles);
		studentListEdit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPaneEdit = new JScrollPane(studentListEdit,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneEdit.setMinimumSize(new Dimension(200, 200));
		btnDeleteEdit = new JButton("Delete");
		btnDeleteEdit.setEnabled(false);
		btnDeleteEdit.addActionListener(new BtnDeleteAction());
		btnDeleteEdit.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnUpdateEdit = new JButton("Update");
		btnUpdateEdit.setEnabled(false);
		btnUpdateEdit.addActionListener(new BtnUpdateEditActionListener());
		btnUpdateEdit.setFont(new Font("Tahoma", Font.BOLD, 24));
		label = new JLabel("Student ID");
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFnameEdit = new JLabel("First Name");
		lblFnameEdit.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSurnameEdit = new JLabel("Surname");
		lblSurnameEdit.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSalaryEdit = new JLabel("Salary");
		lblSalaryEdit.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCityNameEdit = new JLabel("City Name");
		lblCityNameEdit.setFont(new Font("Tahoma", Font.BOLD, 20));
		citiesComboBoxEdit = new JComboBox<String>(citiesList);
		citiesComboBoxEdit.setEnabled(false);
		citiesComboBoxEdit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		salaryJTF_Edit = new JTextField();
		salaryJTF_Edit.setEnabled(false);
		// salaryJTF_Edit.setText("0.0");
		salaryJTF_Edit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		salaryJTF_Edit.setColumns(10);
		surnameJTF_Edit = new JTextField();
		surnameJTF_Edit.setEnabled(false);
		surnameJTF_Edit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		surnameJTF_Edit.setColumns(10);
		firstNameJTF_Edit = new JTextField();
		firstNameJTF_Edit.setEnabled(false);
		firstNameJTF_Edit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		firstNameJTF_Edit.setColumns(10);
		studentID_JTF_Edit = new JTextField();
		// studentID_JTF_Edit.setText("1");
		studentID_JTF_Edit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		studentID_JTF_Edit.setEditable(false);
		studentID_JTF_Edit.setColumns(10);
		lblSelectStudentEdit = new JLabel("Select Student From List");
		lblSelectStudentEdit.setForeground(Color.RED);
		lblSelectStudentEdit.setFont(new Font("Tahoma", Font.BOLD, 20));

		lblStudentActive = new JLabel("Active");
		lblStudentActive.setFont(new Font("Tahoma", Font.BOLD, 20));

		studentEditActiveJComboBox = new JComboBox<String>(
				studentActiveVectorEdit);
		studentEditActiveJComboBox.setEnabled(false);
		studentEditActiveJComboBox.setSelectedIndex(1);
		studentEditActiveJComboBox.setFont(new Font("Tahoma", Font.BOLD, 20));
		GroupLayout gl_editStudentPanel = new GroupLayout(editStudentPanel);
		gl_editStudentPanel.setHorizontalGroup(
			gl_editStudentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_editStudentPanel.createSequentialGroup()
					.addGroup(gl_editStudentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_editStudentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblSalaryEdit, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addGap(73)
							.addComponent(salaryJTF_Edit, GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE))
						.addGroup(gl_editStudentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblSurnameEdit, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
							.addGap(51)
							.addComponent(surnameJTF_Edit, GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE))
						.addGroup(gl_editStudentPanel.createSequentialGroup()
							.addGroup(gl_editStudentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_editStudentPanel.createSequentialGroup()
									.addGap(34)
									.addComponent(btnDeleteEdit, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_editStudentPanel.createSequentialGroup()
									.addContainerGap()
									.addGroup(gl_editStudentPanel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblFnameEdit, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(label, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))))
							.addGroup(gl_editStudentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_editStudentPanel.createSequentialGroup()
									.addGap(13)
									.addGroup(gl_editStudentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(studentID_JTF_Edit, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
										.addComponent(firstNameJTF_Edit, GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)))
								.addGroup(gl_editStudentPanel.createSequentialGroup()
									.addGap(108)
									.addComponent(btnUpdateEdit, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_editStudentPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_editStudentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCityNameEdit, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStudentActive, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
							.addGap(51)
							.addGroup(gl_editStudentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(studentEditActiveJComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(citiesComboBoxEdit, 0, 341, Short.MAX_VALUE))))
					.addGap(55)
					.addGroup(gl_editStudentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSelectStudentEdit)
						.addComponent(scrollPaneEdit, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_editStudentPanel.setVerticalGroup(
			gl_editStudentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_editStudentPanel.createSequentialGroup()
					.addGroup(gl_editStudentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_editStudentPanel.createSequentialGroup()
							.addGap(51)
							.addGroup(gl_editStudentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(studentID_JTF_Edit, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
							.addGap(35)
							.addGroup(gl_editStudentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFnameEdit, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(firstNameJTF_Edit, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
							.addGap(37)
							.addGroup(gl_editStudentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSurnameEdit, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(surnameJTF_Edit, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
							.addGap(34)
							.addGroup(gl_editStudentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSalaryEdit, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(salaryJTF_Edit, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
							.addGap(34)
							.addGroup(gl_editStudentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCityNameEdit, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(citiesComboBoxEdit, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
							.addGroup(gl_editStudentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblStudentActive, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(studentEditActiveJComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_editStudentPanel.createSequentialGroup()
							.addGap(35)
							.addComponent(scrollPaneEdit, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)))
					.addGap(65)
					.addComponent(lblSelectStudentEdit)
					.addGap(27)
					.addGroup(gl_editStudentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnDeleteEdit)
						.addComponent(btnUpdateEdit))
					.addContainerGap())
		);
		editStudentPanel.setLayout(gl_editStudentPanel);

		addCityPanel = new JPanel();
		tabbedPane.addTab("Add City", null, addCityPanel, null);
		addCityPanel.setLayout(new BorderLayout(0, 0));
		{
			southPanel = new JPanel();
			addCityPanel.add(southPanel, BorderLayout.SOUTH);
			{
				btnAddCity = new JButton("Add City");
				btnAddCity.addActionListener(new BtnAddCityAction());
				btnAddCity.setFont(new Font("Tahoma", Font.PLAIN, 30));
				southPanel.add(btnAddCity);
			}
			{
				btnCancel_1 = new JButton("Cancel");
				btnCancel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
				southPanel.add(btnCancel_1);
			}
		}
		{
			centrePanel = new JPanel();
			centrePanel.setBorder(new CompoundBorder(new EmptyBorder(15, 15,
					15, 15), new BevelBorder(BevelBorder.RAISED, null, null,
					null, null)));
			addCityPanel.add(centrePanel, BorderLayout.CENTER);
			lblCityID = new JLabel("City ID");
			lblCityID.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCityID.setFont(new Font("Tahoma", Font.PLAIN, 30));
			lblCityName_1 = new JLabel("City Name");
			lblCityName_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
			cityIdJTF = new JTextField();
			cityIdJTF.setBackground(new Color(245, 245, 245));
			cityIdJTF.setEditable(false);
			cityIdJTF.setFont(new Font("Tahoma", Font.PLAIN, 30));
			cityIdJTF.setColumns(10);
			// cityIdJTF.setText("" + cityDAO.getNextCityID_Number());
			cityNameJTF = new JTextField();
			cityNameJTF.setFont(new Font("Tahoma", Font.PLAIN, 30));
			cityNameJTF.setColumns(10);
			GroupLayout gl_centrePanel = new GroupLayout(centrePanel);
			gl_centrePanel
					.setHorizontalGroup(gl_centrePanel
							.createParallelGroup(Alignment.LEADING)
							.addGroup(
									gl_centrePanel
											.createSequentialGroup()
											.addGap(100)
											.addGroup(
													gl_centrePanel
															.createParallelGroup(
																	Alignment.LEADING)
															.addComponent(
																	lblCityID,
																	GroupLayout.PREFERRED_SIZE,
																	134,
																	GroupLayout.PREFERRED_SIZE)
															.addComponent(
																	lblCityName_1,
																	GroupLayout.PREFERRED_SIZE,
																	162,
																	GroupLayout.PREFERRED_SIZE))
											.addGap(117)
											.addGroup(
													gl_centrePanel
															.createParallelGroup(
																	Alignment.LEADING)
															.addComponent(
																	cityNameJTF,
																	GroupLayout.PREFERRED_SIZE,
																	256,
																	GroupLayout.PREFERRED_SIZE)
															.addComponent(
																	cityIdJTF,
																	GroupLayout.PREFERRED_SIZE,
																	133,
																	GroupLayout.PREFERRED_SIZE))
											.addContainerGap(54,
													Short.MAX_VALUE)));
			gl_centrePanel
					.setVerticalGroup(gl_centrePanel
							.createParallelGroup(Alignment.LEADING)
							.addGroup(
									gl_centrePanel
											.createSequentialGroup()
											.addGap(93)
											.addGroup(
													gl_centrePanel
															.createParallelGroup(
																	Alignment.TRAILING)
															.addComponent(
																	lblCityID)
															.addComponent(
																	cityIdJTF,
																	GroupLayout.PREFERRED_SIZE,
																	GroupLayout.DEFAULT_SIZE,
																	GroupLayout.PREFERRED_SIZE))
											.addGap(118)
											.addGroup(
													gl_centrePanel
															.createParallelGroup(
																	Alignment.BASELINE)
															.addComponent(
																	cityNameJTF,
																	GroupLayout.PREFERRED_SIZE,
																	43,
																	GroupLayout.PREFERRED_SIZE)
															.addComponent(
																	lblCityName_1,
																	GroupLayout.PREFERRED_SIZE,
																	25,
																	GroupLayout.PREFERRED_SIZE))
											.addContainerGap(84,
													Short.MAX_VALUE)));
			centrePanel.setLayout(gl_centrePanel);
		}

		editCityPanel = new JPanel();
		tabbedPane.addTab("Edit Cities", null, editCityPanel, null);

		lblCityId = new JLabel("City ID");
		lblCityId.setFont(new Font("Dialog", Font.BOLD, 26));

		lblCityName_2 = new JLabel("City Name");
		lblCityName_2.setFont(new Font("Dialog", Font.BOLD, 26));

		cityID_JTF = new JTextField();
		cityID_JTF.setEnabled(false);
		cityID_JTF.setFont(new Font("Tahoma", Font.PLAIN, 26));
		cityID_JTF.setColumns(10);

		cityNameEditJTF = new JTextField();
		cityNameEditJTF.setFont(new Font("Tahoma", Font.PLAIN, 26));
		cityNameEditJTF.setColumns(10);

		cityList = new JList<String>(citiesList);
		cityList.addListSelectionListener(new CityListListSelectionListener());
		cityList.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPane = new JScrollPane(cityList);
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new BtnDeleteActionListener());
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 26));

		btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 26));

		btnCancel_2 = new JButton("Cancel");
		btnCancel_2.setFont(new Font("Tahoma", Font.BOLD, 26));
		GroupLayout gl_editCityPanel = new GroupLayout(editCityPanel);
		gl_editCityPanel
				.setHorizontalGroup(gl_editCityPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								Alignment.TRAILING,
								gl_editCityPanel
										.createSequentialGroup()
										.addGroup(
												gl_editCityPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_editCityPanel
																		.createSequentialGroup()
																		.addContainerGap(
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addGroup(
																				gl_editCityPanel
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								gl_editCityPanel
																										.createSequentialGroup()
																										.addGap(26)
																										.addComponent(
																												lblCityName_2,
																												GroupLayout.PREFERRED_SIZE,
																												141,
																												GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								gl_editCityPanel
																										.createSequentialGroup()
																										.addGap(44)
																										.addComponent(
																												lblCityId)))
																		.addGap(45))
														.addGroup(
																gl_editCityPanel
																		.createSequentialGroup()
																		.addGap(49)
																		.addComponent(
																				btnDelete)
																		.addGap(46)))
										.addGroup(
												gl_editCityPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_editCityPanel
																		.createSequentialGroup()
																		.addGroup(
																				gl_editCityPanel
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								cityNameEditJTF,
																								GroupLayout.PREFERRED_SIZE,
																								226,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								cityID_JTF,
																								GroupLayout.PREFERRED_SIZE,
																								91,
																								GroupLayout.PREFERRED_SIZE))
																		.addGap(146)
																		.addComponent(
																				scrollPane,
																				GroupLayout.PREFERRED_SIZE,
																				223,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_editCityPanel
																		.createSequentialGroup()
																		.addComponent(
																				btnUpdate,
																				GroupLayout.PREFERRED_SIZE,
																				153,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(33)
																		.addComponent(
																				btnCancel_2)))
										.addGap(36)));
		gl_editCityPanel
				.setVerticalGroup(gl_editCityPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_editCityPanel
										.createSequentialGroup()
										.addGroup(
												gl_editCityPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_editCityPanel
																		.createSequentialGroup()
																		.addGap(143)
																		.addGroup(
																				gl_editCityPanel
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								lblCityId)
																						.addComponent(
																								cityID_JTF,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE))
																		.addGap(112)
																		.addGroup(
																				gl_editCityPanel
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								lblCityName_2,
																								GroupLayout.PREFERRED_SIZE,
																								34,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								cityNameEditJTF,
																								GroupLayout.PREFERRED_SIZE,
																								38,
																								GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																gl_editCityPanel
																		.createSequentialGroup()
																		.addGap(84)
																		.addComponent(
																				scrollPane,
																				GroupLayout.PREFERRED_SIZE,
																				325,
																				GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(
												ComponentPlacement.RELATED, 88,
												Short.MAX_VALUE)
										.addGroup(
												gl_editCityPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(btnDelete)
														.addComponent(
																btnUpdate,
																GroupLayout.PREFERRED_SIZE,
																41,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnCancel_2))
										.addGap(55)));
		editCityPanel.setLayout(gl_editCityPanel);
		tabbedPane.addChangeListener(new TabbedPaneChange());
	}

	/**
	 * The Class MyWindowCloser.
	 */
	class MyWindowCloser extends WindowAdapter
	{

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent
		 * )
		 */
		public void windowClosing(WindowEvent we)
		{
			if (connection != null)
			{
				mysqlConnection.closeConnection();
				System.exit(0);
			}
			System.exit(0);
		}
	}

	/**
	 * The Class BtnAddStudentAction.
	 */
	private class BtnAddStudentAction implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{

			if (fnameJTF.getText().equals("") || fnameJTF.getText() == null
					|| surnameJTF.getText().equals("")
					|| surnameJTF.getText() == null
					|| salaryJTF.getText().equals("")
					|| salaryJTF.getText() == null)
			{
				JOptionPane.showMessageDialog(null, "Fill in All Fields");
				return;
			}

			int id = Integer.parseInt(studentIdJTF.getText());
			String firstname = fnameJTF.getText();
			String surname = surnameJTF.getText();
			double salary = Double.parseDouble(salaryJTF.getText());
			System.out.println("cityID = " + citiesComboBox.getSelectedItem());
			String cityID = (String) citiesComboBox.getSelectedItem();
			int cityID_2 = citiesComboBox.getSelectedIndex() + 1;

			String update = "insert into student values(" + id + ", '"
					+ firstname + "','" + surname + "', " + salary + ", "
					+ cityID_2 + ");";
			System.out.println("update = " + update);
			try
			{
				stmt = connection.createStatement();
				int rowsaffected = stmt.executeUpdate(update);
				if (rowsaffected > 0)
				{
					JOptionPane.showMessageDialog(StandardBankStudentGUI.this,
							"New Student Added successfully ");

				}
				String query = "select studentID, firstName, surname from student;";
				studentListEdit.removeListSelectionListener(sles);
				studentList.removeAllElements();
				System.out.println("is list empty? " + studentList.isEmpty());
				rs = stmt.executeQuery(query);
				while (rs.next())
				{
					int studentID = rs.getInt(1);
					firstname = rs.getString(2);
					surname = rs.getString(3);
					String firstNameSurname = studentID + ":   " + surname
							+ ",   " + firstname;
					studentList.addElement(firstNameSurname);
				}
				Collections.sort(studentList);
				System.out.println(studentList.toString());
				studentListEdit.setListData(studentList);
				studentListEdit.addListSelectionListener(sles);
				fnameJTF.setText("");
				surnameJTF.setText("");
				salaryJTF.setText("0.0");
				citiesComboBox.setSelectedIndex(0);
				query = "Select max(studentID) from student;";
				rs = stmt.executeQuery(query);
				rs.next();
				int idNo = rs.getInt(1) + 1;
				studentIdJTF.setText("" + idNo);
				stmt.close();
				rs.close();
			}
			catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	/**
	 * The Class BtnAddCityAction.
	 */
	private class BtnAddCityAction implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			int id = Integer.parseInt(cityIdJTF.getText());
			String name = cityNameJTF.getText();
			City newCity = new City(id, name);
			boolean added = cityDAO.addNewCity(newCity);
			if (added)
			{
				JOptionPane.showMessageDialog(StandardBankStudentGUI.this,
						"New City Added successfully ");
				cityNameJTF.setText("");
				tabbedPane.setSelectedIndex(2);
			}
			else
			{
				JOptionPane.showMessageDialog(StandardBankStudentGUI.this,
						"New City NOT added. ");
			}

		}
	}

	/**
	 * The Class TabbedPaneChange.
	 */
	private class TabbedPaneChange implements ChangeListener
	{
		public void stateChanged(ChangeEvent e)
		{
			int posInTabbedPane = tabbedPane.getSelectedIndex();
			System.out.println("which tab is selected? "
					+ tabbedPane.getSelectedIndex());
			if (posInTabbedPane == 2)
			{
				int cityID = cityDAO.getNextCityID_Number();
				cityIdJTF.setText("" + cityID);
			}
			if (posInTabbedPane == 0)
			{
				String query = "Select max(studentID) from student;";
				try
				{
					stmt = connection.createStatement();
					rs = stmt.executeQuery(query);
					rs.next();
					int idNo = rs.getInt(1) + 1;
					studentIdJTF.setText("" + idNo);
					stmt.close();
					rs.close();
				}
				catch (SQLException se)
				{
					// TODO Auto-generated catch block
					se.printStackTrace();
				}
			}
		}
	}
	private class BtnDeleteAction implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
		}
	}
	private class StudentListEditListSelection implements ListSelectionListener
	{
		public void valueChanged(ListSelectionEvent lse)
		{
			if (lse.getValueIsAdjusting())
			{
				return;
			}
			String selected = (String) studentListEdit.getSelectedValue();
			StringTokenizer st = new StringTokenizer(selected, ":");
			String idString = st.nextToken();
			studentID_JTF_Edit.setText(idString);
			int id = Integer.parseInt(idString);
			try
			{
				stmt = connection.createStatement();
				rs = stmt
						.executeQuery("select * from student where studentID = "
								+ id);
				while (rs.next())
				{
					firstNameJTF_Edit.setText(rs.getString(2));
					surnameJTF_Edit.setText(rs.getString(3));
					salaryJTF_Edit.setText("" + rs.getInt(4));
					int index = rs.getInt(5) - 1;
					System.out.println("Index = " + index);
					citiesComboBoxEdit.setSelectedIndex(index);
					String active = rs.getString(6);
					System.out.println("active = " + active);
					if (active.equals("y"))
					{
						studentEditActiveJComboBox.setSelectedIndex(0);
					}
					else
					{
						studentEditActiveJComboBox.setSelectedIndex(1);
					}
				}
				firstNameJTF_Edit.setEnabled(true);
				surnameJTF_Edit.setEnabled(true);
				salaryJTF_Edit.setEnabled(true);
				citiesComboBoxEdit.setEnabled(true);
				studentEditActiveJComboBox.setEnabled(true);
				btnDeleteEdit.setEnabled(true);
				btnUpdateEdit.setEnabled(true);
				stmt.close();
				rs.close();
			}
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private class CityListListSelectionListener implements
			ListSelectionListener
	{
		public void valueChanged(ListSelectionEvent le)
		{
			if (le.getValueIsAdjusting() == true)
			{
				return;
			}
			String cityName = cityList.getSelectedValue();
			for (int i = 0; i < listOfCities.size(); i++)
			{
				if (listOfCities.get(i).getCityName().equals(cityName))
				{
					cityID_JTF.setText("" + listOfCities.get(i).getCityID());
					cityNameEditJTF.setText(cityName);
					return;
				}
			}
		}
	}

	private class BtnDeleteActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
		}
	}

	private class BtnUpdateEditActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			
		}
	}

	public static void main(String[] args)
	{
		StandardBankStudentGUI frame = new StandardBankStudentGUI();
		frame.setVisible(true);
	}
}
