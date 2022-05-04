package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the STUDENT database table.
 * 
 */
@Entity
@NamedQuery(name="Student.findAll", query="SELECT s FROM Student s")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int avgrade;

	private String name;

	private int yearuni;

	//bi-directional many-to-one association to Teacher
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name="IDTEACHER")
	private Teacher teacher;

	public Student() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAvgrade() {
		return this.avgrade;
	}

	public void setAvgrade(int avgrade) {
		this.avgrade = avgrade;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYearuni() {
		return this.yearuni;
	}

	public void setYearuni(int yearuni) {
		this.yearuni = yearuni;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

}