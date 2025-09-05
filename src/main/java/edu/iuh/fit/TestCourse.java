/**
 * @description: Test Course and CourseList functionality
 * @author: duy
 * @version: 1.0
 * @created: 5/9/25 10:59
 */
package edu.iuh.fit;

import java.util.Scanner;

public class TestCourse {
 private static void initData(CourseList list) {
  list.addCourse(new Course("LTHDT", "Lap trinh huong doi tuong", 3, "IUH-CS1"));
  list.addCourse(new Course("CSDL", "He co so du lieu", 4, "IUH-CS1"));
  list.addCourse(new Course("CTDL", "Cau truc du lieu giai thuat", 4, "IUH-CS1"));
  list.addCourse(new Course("TCC", "Toan cao cap", 2, "IUH-CS1"));
  list.addCourse(new Course("PLDC", "Phap luat dai cuong", 2, "IUH-NVD"));
 }

 public static void main(String[] args) {
  Scanner sc = new Scanner(System.in);
  CourseList list = new CourseList(10);
  initData(list);

  while (true) {
   System.out.println("\n===== Menu =====");
   System.out.println("1. Add a course");
   System.out.println("2. Remove a course by ID");
   System.out.println("3. Show all courses");
   System.out.println("4. Search course by ID");
   System.out.println("5. Search courses by title");
   System.out.println("6. Search courses by department");
   System.out.println("7. Find department with most courses");
   System.out.println("8. Find courses with maximum credits");
   System.out.println("9. Sort courses by title");
   System.out.println("0. Exit");
   System.out.print("Choose: ");
   int choice = sc.nextInt();
   sc.nextLine();

   switch (choice) {
    case 1:
     System.out.print("Enter ID: ");
     String id = sc.nextLine();
     System.out.print("Enter course title: ");
     String title = sc.nextLine();
     System.out.print("Enter credits: ");
     int credit = sc.nextInt();
     sc.nextLine();
     System.out.print("Enter department: ");
     String dept = sc.nextLine();
     try {
      Course c = new Course(id, title, credit, dept);
      if (list.addCourse(c)) {
       System.out.println("Course added successfully!");
      } else {
       System.out.println("Course list is full or null!");
      }
     } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
     }
     break;

    case 2:
     System.out.print("Enter course ID to remove: ");
     String delId = sc.nextLine();
     if (list.removeCourse(delId)) {
      System.out.println("Course removed successfully!");
     } else {
      System.out.println("Course not found!");
     }
     break;

    case 3:
     Course[] all = list.getCourses();
     if (all.length == 0) {
      System.out.println("Course list is empty!");
     } else {
      for (Course c : all) {
       System.out.println(c.getId() + " - " + c.getTitle()
               + " (" + c.getCredit() + " credits) - " + c.getDepartment());
      }
     }
     break;

    case 4:
     System.out.print("Enter course ID: ");
     String searchId = sc.nextLine();
     Course found = list.searchCourseById(searchId);
     if (found != null) {
      System.out.println("Found: " + found.getTitle());
     } else {
      System.out.println("Course not found!");
     }
     break;

    case 5:
     System.out.print("Enter keyword in title: ");
     String keyword = sc.nextLine();
     Course[] foundByTitle = list.searchCourse(keyword);
     if (foundByTitle.length == 0) {
      System.out.println("No matching courses found!");
     } else {
      for (Course c : foundByTitle) {
       System.out.println(c.getId() + " - " + c.getTitle());
      }
     }
     break;

    case 6:
     System.out.print("Enter department: ");
     String deptSearch = sc.nextLine();
     Course[] foundByDept = list.searchCourseByDepartment(deptSearch);
     if (foundByDept == null) {
      System.out.println("No matching courses found!");
     } else {
      for (Course c : foundByDept) {
       System.out.println(c.getId() + " - " + c.getTitle());
      }
     }
     break;

    case 7:
     String mostDept = list.findDepartmentWithMostCourses();
     System.out.println("Department with the most courses: " + mostDept);
     break;

    case 8:
     Course[] maxCredit = list.findMaxCreditCourses();
     System.out.println("Courses with maximum credits:");
     for (Course c : maxCredit) {
      System.out.println(c.getId() + " - " + c.getTitle() + " (" + c.getCredit() + ")");
     }
     break;

    case 9:
     Course[] sorted = list.sortCourses();
     System.out.println("Courses sorted by title:");
     for (Course c : sorted) {
      System.out.println(c.getId() + " - " + c.getTitle());
     }
     break;

    case 0:
     System.out.println("Exiting program...");
     sc.close();
     return;

    default:
     System.out.println("Invalid choice!");
   }
  }
 }
}

