package com.shaofan.spring.pojo;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
@Entity
@Table(name="role")
public class Role {
	
	private long roleId;
	
	private String roleName;
	
	private RoleType roleType;
	
	public  enum RoleType{
		Admin("Admin"),
		User("User"),
		GroupManager("GroupManager");
		private String value;
		private RoleType(String value){
			this.value=value;
		}
		@Override
        public String toString(){
            return this.value;
        }
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ROLE_ID")
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	
	@Column(name="roleType",unique=true)
	@Enumerated(EnumType.STRING)
	public RoleType getRoleType() {
		return roleType;
	}
	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}
	
	
	private Set<User>users=new HashSet<User>();
	@Column(name="rolename")
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@ManyToMany(fetch=FetchType.LAZY,mappedBy="roles")
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	

}
