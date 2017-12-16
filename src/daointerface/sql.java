package daointerface;

import java.util.LinkedList;

import domain.Text;
import domain.User;

public interface sql {
	/*
	 * 登录验证
	 * */
	public User Login(String username, String password);
	
	/*
	 * 得到指定学生的成绩
	 * */
	public User Getgreads(User u,int many);
	
	/*
	 * 得到总体试卷
	 * */
	public LinkedList<Text> Getalltext(int many);
}
