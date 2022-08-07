package de.yusuf.controller.employeeManager;
import javax.persistence.*;
import java.util.Objects;

@Entity
public class User {

	public User() {}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String position;
	private Role role;
	private boolean enable;

	public User(String email, String password, String firstName, String lastName, String position, Role role) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.position = position;
		this.role = role;
		enable = true;
	}

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail( String email ) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword( String password ) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName( String firstName ) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName( String lastName ) {
		this.lastName = lastName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition( String position ) {
		this.position = position;
	}

	public Role getRole() {
		return role;
	}

	public void setRole( Role role ) {
		this.role = role;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable( boolean enable ) {
		this.enable = enable;
	}

	@Override public boolean equals( Object o ) {
		if ( this == o )
			return true;
		if ( o == null || getClass() != o.getClass() )
			return false;
		User user = (User) o;
		return id == user.id && enable == user.enable && email.equals(
				user.email ) && Objects.equals( password,
																				user.password ) && Objects.equals(
				firstName, user.firstName ) && Objects.equals( lastName,
																											 user.lastName ) && Objects.equals(
				position, user.position ) && role == user.role;
	}

	@Override public int hashCode() {
		return Objects.hash( id, email, password, firstName, lastName, position, role, enable );
	}

	@Override public String toString() {
		return "User{" +
				"id=" + id +
				", email='" + email + '\'' +
				", firstName='" + firstName + '\'' +
				", role=" + role +
				'}';
	}
}

enum Role {
	ADMIN("ADMINISTRATOR"), USER("BENUTZER");
	private String label;
	Role(String label) {
		this.label = label;
	}
	public String getLabel() {
		return label;
	}
}
