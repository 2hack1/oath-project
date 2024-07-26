package in.sp.firs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="oath_project")
public class Enty {
       
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private int id;
	 
	   private String name;
	 
	  private String email;
	 
	  private int pass;
	  
	  private String  gender;
       
	  
	  public  Enty(String name,String email,int pass,String gender) {
		  super();
		  this.name=name;
		  this.email=email;
		  this.pass=pass;
		  this.gender=gender;
	  }
	  
		public Enty() {
			
		}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public int getPass() {
		return pass;
	}

	public void setPass(int pass) {
		this.pass = pass;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	  
	}
