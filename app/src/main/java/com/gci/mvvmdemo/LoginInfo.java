package com.gci.mvvmdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;


import java.io.Serializable;
import java.util.Objects;

/**
 * 登陆基本信息
 *
 * @author 黄朝翔
 *
 */
public class LoginInfo extends BaseObservable implements Serializable {

	/**
	 * 主键
	 */
	private int id;
	private String mUserRealName;
	private String mIslogin;
	private String mAutoLogin;
	private String account;
	private String password;
	private String mSaveTime;
	private String mLoginTime;
	private String mLogoutTime;
	private String mUserPortraitPath;
	private String mServerIpAndPort;
	private String lockPassWord;
	private String userAddress;
	private String userPhone;
	private String userid;
	private String userDept;
	private String headUrl;

	//-------------水厂项目登录后返回的数据
	private String access_token;
	private String token_type;
	private String refresh_token;
	private int expires_in;
	private String scope;

	public LoginInfo(String userName, String password) {
		setAccount(userName);
		setPassword(password);
	}

	public LoginInfo() {

	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 用户真实姓名
	 *
	 * @return
	 */
	public String getUserRealName() {
		return this.mUserRealName;
	}

	/**
	 * 用户真实姓名
	 *
	 * @param p_UserRealName
	 */
	public void setUserRealName(String p_UserRealName) {
		this.mUserRealName = p_UserRealName;
	}

	/**
	 * 是否登陆
	 */
	public String getIslogin() {
		return this.mIslogin;
	}

	/**
	 * 设置是否登陆
	 */
	public void setIslogin(String p_Islogin) {
		this.mIslogin = p_Islogin;
	}

	/**
	 * 是否自动登陆
	 */
	public String getAutoLogin() {
		return this.mAutoLogin;
	}

	/**
	 * 设置是否自动登陆
	 */
	public void setAutoLogin(String p_AutoLogin) {
		this.mAutoLogin = p_AutoLogin;
	}

	/**
	 * 用户名
	 */
	@Bindable
	public String getAccount() {
		return this.account;
	}

	/**
	 * 用户名
	 */
	public void setAccount(String p_UserName) {
		this.account = p_UserName;
	}

	/**
	 * 密码
	 */
	@Bindable
	public String getPassword() {
		return this.password;
	}

	/**
	 * 密码
	 */
	public void setPassword(String p_Password) {
		this.password = p_Password;
	}

//	/**
//	 * 自动登陆的时间
//	 */
//	public String getDays() {
//		return this.mDays;
//	}
//
//	/**
//	 * 自动登陆的时间
//	 */
//	public void setDays(String p_Days) {
//		this.mDays = p_Days;
//	}

	/**
	 * 自动登陆的起始日
	 */
	public String getSaveTime() {
		return this.mSaveTime;
	}

	/**
	 * 自动登陆的起始日
	 */
	public void setSaveTime(String p_SaveTime) {
		this.mSaveTime = p_SaveTime;
	}

	public String getLoginTime() {
		return this.mLoginTime;
	}

	public void setLoginTime(String p_LoginTime) {
		this.mLoginTime = p_LoginTime;
	}

	public String getLogoutTime() {
		return this.mLogoutTime;
	}

	public void setLogoutTime(String p_LogoutTime) {
		this.mLogoutTime = p_LogoutTime;
	}

	public String getServerIpAndPort() {
		return mServerIpAndPort;
	}

	public void setServerIpAndPort(String mServerIpAndPort) {
		this.mServerIpAndPort = mServerIpAndPort;
	}

	public String getLockPassWord() {
		return lockPassWord;
	}

	public void setLockPassWord(String lockPassWord) {
		this.lockPassWord = lockPassWord;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public Bitmap getUserPortrait() {
//		if (StringUtil.isEmpty(mUserPortraitPath)) {
//			return null;
//		}
		BitmapFactory.Options opts = new BitmapFactory.Options();
		Bitmap headimg = BitmapFactory.decodeFile(mUserPortraitPath, opts);
		return headimg;
	}

	public void setUserPortraitPath(String mUserPortraitPath) {
		this.mUserPortraitPath = mUserPortraitPath;
	}

	public String getUserPortraitPath() {
		return mUserPortraitPath;
	}


	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserDept() {
		return userDept;
	}

	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LoginInfo loginInfo = (LoginInfo) o;
		return account .equals(loginInfo.account);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
