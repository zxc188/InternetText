package daointerface;

import java.util.LinkedList;

import domain.Text;
import domain.User;

public interface sql {
	/*
	 * ��¼��֤
	 * */
	public User Login(String username, String password);
	
	/*
	 * �õ�ָ��ѧ���ĳɼ�
	 * */
	public User Getgreads(User u,int many);
	
	/*
	 * �õ������Ծ�
	 * */
	public LinkedList<Text> Getalltext(int many);
}
