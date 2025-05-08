package model;

import java.sql.Timestamp;

public class UserProfile extends BaseEntity {
    private Integer idUserProfile;
    private String email;
    private int isAdmin;
    private RoleType roleType = RoleType.AMBOS; // valor por defecto
    private User user;

    public UserProfile() {
    }

    public UserProfile(User idUserCreate, User idUserUpdate, Timestamp dateCreate, Timestamp dateUpdate,
                       Integer idUserProfile, String email, int isAdmin, RoleType roleType, User user) {
        super(idUserCreate, idUserUpdate, dateCreate, dateUpdate);
        this.idUserProfile = idUserProfile;
        this.email = email;
        this.isAdmin = isAdmin;
        this.roleType = roleType;
        this.user = user;
    }

    public Integer getIdUserProfile() {
        return idUserProfile;
    }

    public void setIdUserProfile(Integer idUserProfile) {
        this.idUserProfile = idUserProfile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
