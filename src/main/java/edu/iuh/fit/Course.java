/**
 * @description: Stores information about a course
 * @author: duy
 * @version: 1.0
 * @created: 5/9/25 09:10
 */

package edu.iuh.fit;

public class Course {
    private int credit;
    private String department;
    private String id;
    private String title;

    public Course() {
        this("OOP", "Object-oriented programming", 4, "IUH");
    }

    /**
     * @param id course id
     * @param title course title
     * @param credit number of credits
     * @param department the department
     */
    public Course(String id, String title, int credit, String department) {
        setId(id);
        setTitle(title);
        setCredit(credit);
        setDepartment(department);
    }

    /**
     * @return credit
     */
    public int getCredit() {
        return credit;
    }

    /**
     * @param credit number of credits
     */
    public void setCredit(int credit) {
        if(credit < 1) {
            throw new IllegalArgumentException("Credit must be greater than 0");
        }
        this.credit = credit;
    }

    /**
     * @return department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title course title
     */
    public void setTitle(String title) {
        if(title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title must not be empty");
        }
        this.title = title;
    }

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id course id
     */
    public void setId(String id) {
        if(id == null || id.length() < 3) {
            throw new IllegalArgumentException("ID must have at least 3 characters");
        } else if(!id.matches("^[a-zA-Z0-9]+$")) {
            throw new IllegalArgumentException("ID must contain only letters or digits");
        }
        this.id = id;
    }
}
