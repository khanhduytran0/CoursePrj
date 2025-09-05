/**
 * @description: A list to manage course objects
 * @author: duy
 * @version: 1.0
 * @created: 5/9/25 09:19
 */

package edu.iuh.fit;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class CourseList {
 private int count;
 private final Course[] courses;

 /**
  * @param max maximum number of courses
  */
 public CourseList(int max) {
  if(max < 1) {
   throw new IllegalArgumentException("Length of the array must be greater than 0");
  }
  this.count = 0;
  this.courses = new Course[max];
 }

 /**
  * @param c course object
  * @return true if course was successfully added
  */
 public boolean addCourse(Course c) {
  if(c == null || count == courses.length) {
   return false;
  } else if(exists(c)) {
   throw new IllegalArgumentException("The course is already in the list");
  }
  courses[count++] = c;
  return true;
 }

 /**
  * @param id course id to remove
  * @return true if course was successfully removed
  */
 public boolean removeCourse(String id) {
  Course c = searchCourseById(id);
  if(c == null) {
   throw new IllegalArgumentException("The course doesn't exist in the list");
  }

  // Shift elements to the left
  for (int i = Arrays.asList(this.courses).indexOf(c); i < this.count-1; i++) {
   this.courses[i] = this.courses[i + 1];
  }

  // Set the last element to null
  this.courses[--this.count] = null;
  return true;
 }

 /**
  * @return courses
  */
 public Course[] getCourses() {
  return Arrays.copyOf(courses, count);
 }

 /**
  * @param c course to check
  * @return true if it exisrs
  */
 private boolean exists(Course c) {
  return searchCourseById(c.getId()) != null;
 }

 /**
  * @return department with most courses
  */
 public String findDepartmentWithMostCourses() {
  return Arrays.stream(getCourses())
          .collect(Collectors.groupingBy(Course::getDepartment, Collectors.counting()))
          .entrySet().stream()
          .max(Map.Entry.comparingByValue())
          .map(Map.Entry::getKey)
          .orElse(null);
 }

 /**
  * @return courses with the most number of credits
  */
 public Course[] findMaxCreditCourses() {
  int maxCredits = Arrays.stream(getCourses())
          .mapToInt(Course::getCredit)
          .max()
          .orElse(0);
  return Arrays.stream(getCourses())
          .filter(c -> c.getCredit() == maxCredits)
          .toArray(Course[]::new);
 }

 /**
  * @param title keyword to search
  * @return courses whose title contain the keyword
  */
 public Course[] searchCourse(String title) {
  return Arrays.stream(getCourses())
          .filter(c -> c.getTitle().contains(title))
          .toArray(Course[]::new);
 }

 /**
  * @param department department to search
  * @return courses
  */
 public Course[] searchCourseByDepartment(String department) {
  Course[] result = Arrays.stream(getCourses())
          .filter(c -> c.getDepartment().equals(department))
          .toArray(Course[]::new);
  return result.length == 0 ? null : result;
 }

 /**
  * @param id course id
  * @return course matching id
  */
 public Course searchCourseById(String id) {
  for (int i = 0; i < this.count; i++) {
   Course c = this.courses[i];
   if(c.getId().equals(id)) {
    return c;
   }
  }
  return null;
 }

 /**
  * @return sorted copy of courses
  */
 public Course[] sortCourses() {
  return Arrays.stream(getCourses())
          .sorted((l, r) -> l.getTitle().compareTo(r.getTitle()))
          .toArray(Course[]::new);
 }
}
