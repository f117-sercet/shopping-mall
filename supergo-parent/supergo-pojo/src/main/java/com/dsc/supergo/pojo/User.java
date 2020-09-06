package com.dsc.supergo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="com.dsc.supergo.pojo.User")
@Table(name = "tb_user")
public class User implements Serializable {
    @Id
    @ApiModelProperty(value="id")
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty(value="username用户名")
    private String username;

    /**
     * 密码 md5加密存储
     */
    @ApiModelProperty(value="password密码 md5加密存储")
    private String password;

    /**
     * 注册手机号
     */
    @ApiModelProperty(value="phone注册手机号")
    private String phone;

    /**
     * 注册邮箱
     */
    @ApiModelProperty(value="email注册邮箱")
    private String email;

    @ApiModelProperty(value="sex")
    private String sex;

    @ApiModelProperty(value="address")
    private String address;

    @ApiModelProperty(value="state")
    private Integer state;

    @ApiModelProperty(value="description")
    private String description;

    @Column(name = "role_id")
    @ApiModelProperty(value="roleId")
    private Integer roleId;

    /**
     * 头像
     */
    @ApiModelProperty(value="file头像")
    private String file;

    @ApiModelProperty(value="created")
    private Date created;

    @ApiModelProperty(value="updated")
    private Date updated;

    private static final long serialVersionUID = 1L;

    public User(Long id, String username, String password, String phone, String email, String sex, String address, Integer state, String description, Integer roleId, String file, Date created, Date updated) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.sex = sex;
        this.address = address;
        this.state = state;
        this.description = description;
        this.roleId = roleId;
        this.file = file;
        this.created = created;
        this.updated = updated;
    }

    public User() {
        super();
    }

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码 md5加密存储
     *
     * @return password - 密码 md5加密存储
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码 md5加密存储
     *
     * @param password 密码 md5加密存储
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取注册手机号
     *
     * @return phone - 注册手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置注册手机号
     *
     * @param phone 注册手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取注册邮箱
     *
     * @return email - 注册邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置注册邮箱
     *
     * @param email 注册邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return state
     */
    public Integer getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return role_id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取头像
     *
     * @return file - 头像
     */
    public String getFile() {
        return file;
    }

    /**
     * 设置头像
     *
     * @param file 头像
     */
    public void setFile(String file) {
        this.file = file;
    }

    /**
     * @return created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * @return updated
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * @param updated
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", phone=").append(phone);
        sb.append(", email=").append(email);
        sb.append(", sex=").append(sex);
        sb.append(", address=").append(address);
        sb.append(", state=").append(state);
        sb.append(", description=").append(description);
        sb.append(", roleId=").append(roleId);
        sb.append(", file=").append(file);
        sb.append(", created=").append(created);
        sb.append(", updated=").append(updated);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
