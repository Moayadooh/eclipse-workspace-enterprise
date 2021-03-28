package MyPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManageEmployeesFrame {
	
	private JFrame frame;
	private JTextField txtFname;
	private JTextField txtLname;
	private JTextField txtMobileNo;
	private JTextField txtSalary;
	private JTable table;
	private DefaultTableModel model;
	private Object[] row;
	private Connection con;
	private int id;
	
	private void DisplayTableRows() {
		try {
			String sql = "SELECT * FROM EMPLOYEES";
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			while (result.next()) {
				row[0] = result.getString(1);
				row[1] = result.getString(2);
				row[2] = result.getString(3);
				row[3] = result.getString(4);
				row[4] = result.getString(5);
				model.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void ClearTableRows() {
		int rowCount = table.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
		    model.removeRow(i);
		}
	}
	
	private void ClearTextFields() {
		txtFname.setText(null);
		txtLname.setText(null);
		txtMobileNo.setText(null);
		txtSalary.setText(null);
	}
	
	public static boolean isDigit(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	public static boolean isDouble(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageEmployeesFrame window = new ManageEmployeesFrame();
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
	public ManageEmployeesFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 903, 758);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFirstName.setBounds(35, 157, 114, 33);
		frame.getContentPane().add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLastName.setBounds(35, 211, 114, 33);
		frame.getContentPane().add(lblLastName);
		
		JLabel lblMobileNo = new JLabel("Mobile No:");
		lblMobileNo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMobileNo.setBounds(47, 269, 114, 33);
		frame.getContentPane().add(lblMobileNo);
		
		JLabel lblSalary = new JLabel("Salary:");
		lblSalary.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSalary.setBounds(80, 320, 114, 33);
		frame.getContentPane().add(lblSalary);
		
		txtFname = new JTextField();
		txtFname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtFname.setBounds(148, 157, 191, 41);
		frame.getContentPane().add(txtFname);
		txtFname.setColumns(10);
		
		txtLname = new JTextField();
		txtLname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtLname.setColumns(10);
		txtLname.setBounds(148, 211, 191, 41);
		frame.getContentPane().add(txtLname);
		
		txtMobileNo = new JTextField();
		txtMobileNo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtMobileNo.setColumns(10);
		txtMobileNo.setBounds(148, 269, 191, 41);
		frame.getContentPane().add(txtMobileNo);
		
		txtSalary = new JTextField();
		txtSalary.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSalary.setColumns(10);
		txtSalary.setBounds(148, 318, 191, 41);
		frame.getContentPane().add(txtSalary);
		
		//Scroll Pane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(349, 20, 530, 656);
		frame.getContentPane().add(scrollPane);
		
		//Table
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				id = Integer.parseInt(model.getValueAt(i, 0).toString());
				txtFname.setText(model.getValueAt(i, 1).toString());
				txtLname.setText(model.getValueAt(i, 2).toString());
				txtMobileNo.setText(model.getValueAt(i, 3).toString());
				txtSalary.setText(model.getValueAt(i, 4).toString());
			}
		});
		model = new DefaultTableModel();
		Object[] column = {"ID","First Name","Last Name","Mobile No","Salary"};
		row = new Object[5];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","Mm123121");
			DisplayTableRows();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		//Add Button
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtFname.getText().trim().equals("")||txtLname.getText().trim().equals("")||txtMobileNo.getText().trim().equals("")||txtSalary.getText().trim().equals(""))
					JOptionPane.showMessageDialog(null, "Please fill all the fields!");
				else
				{
					if(isDigit(txtMobileNo.getText().toString()) && txtMobileNo.getText().toString().length()==8)
					{
						if(isDouble(txtSalary.getText().toString()))
						{
							/*row[0] = txtFname.getText();
							row[1] = txtLname.getText();
							row[2] = txtMobileNo.getText();
							row[3] = txtSalary.getText();
							model.addRow(row);*/
							try {
								String query = "{call sp_addEmp(?,?,?,?)}"; 
								CallableStatement callStmt = con.prepareCall(query);  
								callStmt.setString(1, txtFname.getText().toString());
								callStmt.setString(2, txtLname.getText().toString());
								callStmt.setInt(3, Integer.parseInt(txtMobileNo.getText().toString()));
								callStmt.setDouble(4, Double.parseDouble(txtSalary.getText().toString()));
								callStmt.execute();
							} catch (SQLException ex) {
								ex.printStackTrace();
							}
							
							ClearTableRows();
							DisplayTableRows();
							ClearTextFields();
						}
						else
							JOptionPane.showMessageDialog(null, "Please Enter Valid Salary!\ne.g:123.12");
					}
					else
						JOptionPane.showMessageDialog(null, "Please Enter Valid MobileNo!\ne.g:12345678");
				}
			}
		});
		btnAdd.setBounds(35, 383, 304, 41);
		frame.getContentPane().add(btnAdd);
		
		//Update Button
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				if(i>=0)
				{
					if(txtFname.getText().trim().equals("")||txtLname.getText().trim().equals("")||txtMobileNo.getText().trim().equals("")||txtSalary.getText().trim().equals(""))
						JOptionPane.showMessageDialog(null, "Please fill all the fields!");
					else
					{
						/*model.setValueAt(txtFname.getText(), i, 0);
						model.setValueAt(txtLname.getText(), i, 1);
						model.setValueAt(txtMobileNo.getText(), i, 2);
						model.setValueAt(txtSalary.getText(), i, 3);*/
						try {
							String query = "{call sp_updateEmp(?,?,?,?,?)}"; 
							CallableStatement callStmt = con.prepareCall(query);
							callStmt.setInt(1, id);
							callStmt.setString(2, txtFname.getText().toString());
							callStmt.setString(3, txtLname.getText().toString());
							callStmt.setInt(4, Integer.parseInt(txtMobileNo.getText().toString()));
							callStmt.setDouble(5, Double.parseDouble(txtSalary.getText().toString()));
							callStmt.execute();
						} catch (SQLException ex) {
							ex.printStackTrace();
						}
						
						ClearTableRows();
						DisplayTableRows();
						JOptionPane.showMessageDialog(null, "Updated Successfuly.");
					}
				}
				else
					JOptionPane.showMessageDialog(null, "No Row Selected!");
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnUpdate.setBounds(35, 434, 304, 41);
		frame.getContentPane().add(btnUpdate);
		
		//Delete Button
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				if(i>=0)
				{
					if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "CONFIRMATION", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
					{
						//model.removeRow(i);
						try {
							String query = "{call sp_deleteEmp(?)}"; 
							CallableStatement callStmt = con.prepareCall(query);
							callStmt.setInt(1, id);
							callStmt.execute();
						} catch (SQLException ex) {
							ex.printStackTrace();
						}
						
						ClearTableRows();
						DisplayTableRows();
						ClearTextFields();
						JOptionPane.showMessageDialog(null, "Deleted Successfuly.");
					}
				}
				else
					JOptionPane.showMessageDialog(null, "No Row Selected!");
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDelete.setBounds(35, 485, 304, 41);
		frame.getContentPane().add(btnDelete);
		
		//Clear Button
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClearTextFields();
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnClear.setBounds(35, 536, 304, 41);
		frame.getContentPane().add(btnClear);
	}
}
