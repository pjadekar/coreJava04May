package com.kavita.opration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.kavita.Connection_url.JDBCConnection;
import com.kavita.Model.Employee;

public class Opration {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		Opration op = new Opration();
		int choice;
		do {
			System.out.println("Enter Your choice");
			System.out.println("1 :.Insert The Records");
			System.out.println("2 :.Dispay The Records");
			System.out.println("3 :.Updates The Records");
			System.out.println("4 :.Delete The Records");
			System.out.println("5 :.Exit ");
			choice = sc.nextInt();

			switch (choice) {
			case 1:
				op.insertEmployeeData();
				break;

			case 2:
				op.displayEmployeeData();
				break;

			case 3:
				op.updateEmployeeData();
				break;

			case 4:
				op.deleteEmployeeData();
				break;

			case 5:
				System.out.println("Thank u For visit ");
			default:
				break;
			}
		} while (choice != 5);

	}

	public void insertEmployeeData() {
		Employee emp = new Employee();
		System.out.println("Enter The Employee RollNumber:....");
		int rollNumber = sc.nextInt();
		emp.setRollNumber(rollNumber);
 
		System.out.println("Enter The Employee Name:....");
		String name = sc.next();
		emp.setName(name);

		System.out.println("Enter The Employee Address:....");
		String address = sc.next();
		emp.setAddress(address);

		try {
			Connection con = JDBCConnection.getJDBCConnection();
			PreparedStatement pst = con.prepareStatement("insert into Employee(rollNumber,name,address) values(?,?,?)");
			pst.setInt(1, emp.getRollNumber());
			pst.setString(2, emp.getName());
			pst.setString(3, emp.getAddress());
			int x = pst.executeUpdate();
			pst.close();
			con.close();
			if (x == 1) {
				System.out.println("Record Inserted Successfully");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void displayEmployeeData() {
		try {
			Connection con = JDBCConnection.getJDBCConnection();
			PreparedStatement pst = con.prepareStatement("select  * from Employee");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getLong(1) + " " + rs.getString(2) + "  " + rs.getString(3));
			}
			rs.close();
			pst.close();
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void deleteEmployeeData() {
		try {
			int rollNumber;
			System.out.println("Enter the Record rollNumber Delete");
			rollNumber = sc.nextInt();
			Employee emp = new Employee();
			emp.setRollNumber(rollNumber);
			Connection con = JDBCConnection.getJDBCConnection();
			PreparedStatement pst = con.prepareStatement("Delete from Employee where rollNumber=?");

			pst.setInt(1, emp.getRollNumber());
			int x = pst.executeUpdate();
			pst.close();
			con.close();
			if (x == 1) {
				System.out.println("Record Deleted");
			} else {
				System.out.println("Record Not Found");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void updateEmployeeData() {
		Employee emp = new Employee();
		System.out.println("Enter The Employee RollNumber:....");
		int rollNumber = sc.nextInt();
		emp.setRollNumber(rollNumber);

		System.out.println("Enter The Employee Name:....");
		String name = sc.next();
		emp.setName(name);

		System.out.println("Enter The Employee Address:....");
		String address = sc.next();
		emp.setAddress(address);
		try {
			Connection con = JDBCConnection.getJDBCConnection();
			String s = "update Employee set name=?,address=? where rollNumber=?";
            PreparedStatement pst=con.prepareStatement(s);
            pst.setString(1, emp.getName());
			pst.setString(2, emp.getAddress());
            pst.setInt(3, emp.getRollNumber());
			
			//System.out.println(s);
			int x = pst.executeUpdate();
			pst.close();
			con.close();
			if (x == 1) {
				System.out.println("Update Successfully");
			} else {
				System.out.println("not updated");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
