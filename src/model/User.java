package model;

import java.io.Serializable;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	  private int id;
	   private String name;
	   private String profession;

	   // Constructors
	   public User(){}

	   public User(int id, String name, String profession){
	      this.id = id;
	      this.name = name;
	      this.profession = profession;
	   }
	   
	   public User(String name, String profession){
	      this.name = name;
	      this.profession = profession;
		  }

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	 @XmlElement(name = "id")
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	 @XmlElement(name = "name")
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the profession
	 */
	public String getProfession() {
		return profession;
	}

	/**
	 * @param profession the profession to set
	 */
	 @XmlElement(name = "profession")
	public void setProfession(String profession) {
		this.profession = profession;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", profession=" + profession + "]";
	}
	   
	// Called immediately after the object is created and before the unmarshalling begins.
    // The callback provides an opportunity to initialize JavaBean properties prior to unmarshalling.
    void beforeUnmarshal(Unmarshaller unmarshaller, Object parent) {
        System.out.println("Before Unmarshaller Callback");
    }
 
    // Called after all the properties are unmarshalled for this object,
    // but before this object is set to the parent object.
    void afterUnmarshal(Unmarshaller unmarshaller, Object parent) {
        System.out.println("After Unmarshaller Callback");
    } 

	   

}
