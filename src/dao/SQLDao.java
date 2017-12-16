package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import daointerface.sql;
import domain.Problem;
import domain.Text;
import domain.User;
import util.Md5;
import util.Replaces;
import util.SQlUtil;

public class SQLDao implements sql {
	/*
	 * 表：users,problems,greads 视图: text1,text2,text3,text4
	 */
	private SQlUtil util;
	private Connection conn;
	private Replaces reap;
	private Md5 md5;

	public SQLDao() {
		util = new SQlUtil();
		reap = new Replaces();
	}

	@Override
	public User Login(String username, String password) {
		conn = util.Con();
		User user = null;
		md5 = new Md5();
		try {
			String sql = "select * from users where username=? and password=?";
			PreparedStatement pre = conn.prepareStatement(sql);
			password = md5.GetMD5Code(password);
			pre.setString(1, username);
			pre.setString(2, password);
			ResultSet result = pre.executeQuery();
			while (result.next()) {
				user = new User();
				user.setUsername(result.getString(result.findColumn("username")));
				user.setPassword(result.getString(result.findColumn("password")));
				user.setRealname(result.getString(result.findColumn("realname")));
				user.setRoot(result.getString(result.findColumn("root")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public User Getgreads(User u, int many) {
		conn = util.Con();
		boolean istext;
		User user = u;
		LinkedList<String> list = new LinkedList<>();
		try {
			for (int i = 0; i < many; i++) {
				istext = false;
				String sql = "select * from greads where username=? and which=?";
				PreparedStatement pre = conn.prepareStatement(sql);
				pre.setString(1, user.getUsername());
				pre.setString(2, i + 1 + "");
				ResultSet result = pre.executeQuery();
				while (result.next()) {
					String gread = result.getString(result.findColumn("gread"));
					list.add(gread);
					istext = true;
				}
				if (!istext) {
					list.add(null);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		user.setList(list);
		return user;
	}

	@Override
	public LinkedList<Text> Getalltext(int many) {
		conn = util.Con();
		LinkedList<Text> list = new LinkedList<>();
		for (int i = 0; i < many; i++) {
			Text text = new Text();
			LinkedList<Problem> link = new LinkedList<>();
			try {
				String sql = "select * from problems where which=? order by inde";
				PreparedStatement pre = conn.prepareStatement(sql);
				pre.setString(1, i + 1 + "");
				ResultSet result = pre.executeQuery();
				while (result.next()) {
					Problem pro = new Problem();
					pro.setPro(result.getString(result.findColumn("pro")));
					pro.setOne(result.getString(result.findColumn("one")));
					pro.setTwo(result.getString(result.findColumn("two")));
					pro.setThree(result.getString(result.findColumn("three")));
					pro.setFour(result.getString(result.findColumn("four")));
					pro.setIndex(result.getString(result.findColumn("inde")));
					pro.setResult(result.getString(result.findColumn("result")));
					pro = reap.reapproblem(pro);
					link.add(pro);
				}
				text.setList(link);
				list.add(text);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public Text GetText(String which) {
		conn = util.Con();
		Text text = new Text();
		LinkedList<Problem> list = new LinkedList<>();
		try {
			String sql = "select * from problems where which=? order by inde";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, which);
			ResultSet result = pre.executeQuery();
			while (result.next()) {
				Problem pro = new Problem();
				pro.setPro(result.getString(result.findColumn("pro")));
				pro.setOne(result.getString(result.findColumn("one")));
				pro.setTwo(result.getString(result.findColumn("two")));
				pro.setThree(result.getString(result.findColumn("three")));
				pro.setFour(result.getString(result.findColumn("four")));
				pro.setIndex(result.getString(result.findColumn("inde")));
				pro.setResult(result.getString(result.findColumn("result")));
				pro = reap.reapproblem(pro);
				list.add(pro);
			}
			text.setList(list);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return text;
	}

	public void Updategread(String name, String which, int gread) {
		conn = util.Con();
		try {
			String sql = "insert into greads values(?,?,?)";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, name);
			pre.setString(2, which);
			pre.setString(3, gread + "");
			pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public LinkedList<User> Admingetgreads(int many) {
		conn = util.Con();
		LinkedList<User> list = new LinkedList<>();
		try {
			String sql = "select * from users where root=?";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, "0");
			ResultSet result = pre.executeQuery();
			while (result.next()) {
				User user = new User();
				user = new User();
				user.setUsername(result.getString(result.findColumn("username")));
				user.setRealname(result.getString(result.findColumn("realname")));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		for (int i = 0; i < list.size(); i++) {
			User thisuser = list.get(i);
			thisuser = Getgreads(thisuser, many);
			list.set(i, thisuser);
		}
		return list;
	}

	/*
	 * 查找试题
	 */
	public Problem GetProblem(int which, int index) {
		Problem pro = new Problem();
		conn = util.Con();
		try {
			String sql = "select * from problems where which=? and inde=?";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, which + "");
			pre.setString(2, index + "");
			ResultSet result = pre.executeQuery();
			while (result.next()) {
				pro.setPro(result.getString(result.findColumn("pro")));
				pro.setOne(result.getString(result.findColumn("one")));
				pro.setTwo(result.getString(result.findColumn("two")));
				pro.setThree(result.getString(result.findColumn("three")));
				pro.setFour(result.getString(result.findColumn("four")));
				pro.setIndex(result.getString(result.findColumn("inde")));
				pro.setResult(result.getString(result.findColumn("result")));
				pro = reap.reapproblem(pro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return pro;
	}

	/*
	 * 删除问题表中的数据
	 */
	public boolean DeletePro(String which, String index) {
		boolean isnull = false;
		int f = -100;
		conn = util.Con();
		try {
			conn.setAutoCommit(false);
			String sql = "delete from problems where which=? and inde=?";
			String sql2 = "delete from greads where which=?";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, which);
			pre.setString(2, index);
			pre.executeUpdate();
			PreparedStatement pre2 = conn.prepareStatement(sql2);
			pre2.setString(1, which);
			f = pre2.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		isnull = isnull(which);
		System.out.println(f);
		return isnull;
	}

	/*
	 * 判断是否本套试卷中还有剩余的题
	 */
	public boolean isnull(String which) {
		boolean isnotnull = false;
		try {
			String sql = "select * from problems where which=?";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, which);
			ResultSet result = pre.executeQuery();
			while (result.next()) {
				isnotnull = true;
			}
			if (!isnotnull) {
				System.out.println("111111111");
				deleteindex(which);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return isnotnull;
	}

	/*
	 * 删除表中的问题index
	 */
	public void deleteindex(String which) {
		try {
			String sql = "delete from problemnum where which=?";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, which + "");
			int g=pre.executeUpdate();
			System.out.println("asdjijoixzjciojr"+g);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
	}

	public int ChangePro(String which, String index, Problem pro) {
		int f = -100;
		String problem = pro.getPro();
		String one = pro.getOne();
		String two = pro.getTwo();
		String three = pro.getThree();
		String four = pro.getFour();
		String result = pro.getResult();
		conn = util.Con();
		try {
			conn.setAutoCommit(false);
			String sql = "delete from problems where which=? and inde=?";
			String sql2 = "insert into problems values(?,?,?,?,?,?,?,?)";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, which);
			pre.setString(2, index);
			pre.executeUpdate();
			PreparedStatement pre2 = conn.prepareStatement(sql2);
			pre2.setString(1, problem);
			pre2.setString(2, one);
			pre2.setString(3, two);
			pre2.setString(4, three);
			pre2.setString(5, four);
			pre2.setString(6, which);
			pre2.setString(7, index);
			pre2.setString(8, result);
			f = pre2.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return f;
	}

	/*
	 * 将试题插入到响应的表中
	 */
	public int Inserttotext(String which,Problem pro){
		int f=-100;
		conn = util.Con();
		try {
			conn.setAutoCommit(false);
			String sql = "insert into problems values(?,?,?,?,?,?,?,?)";
			String sql2="update problemnum set num=? where which=?";
			int index=Getnumtext(which)+1;
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1,pro.getPro());
			pre.setString(2, pro.getOne());
			pre.setString(3, pro.getTwo());
			pre.setString(4,pro.getThree());
			pre.setString(5, pro.getFour());
			pre.setString(6, which);
			pre.setString(7, index+"");
			pre.setString(8, pro.getResult());
			PreparedStatement pre2 = conn.prepareStatement(sql2);
			pre2.setString(1, index+"");
			pre2.setString(2, which);
			pre2.executeUpdate();
			f=pre.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return f;
	}

	/*
	 * 得到指定试卷中的题的数量
	 */
	public int Getnumtext(String which) {
		int num = 0;
		conn = util.Con();
		try {
			String sql = "select * from problemnum where which=?";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, which);
			ResultSet result = pre.executeQuery();
			while (result.next()) {
				String nums = result.getString(result.findColumn("num"));
				nums=nums.replace(" ", "");
				num = Integer.parseInt(nums);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
		return num;
	}
	/*
	 * 添加试卷
	 * */
	public int Addtext(int many,LinkedList<Problem> list){
		int f=-100;
		conn = util.Con();
		many=many+1;
		try {
			conn.setAutoCommit(false);
			String sql="insert into problems values(?,?,?,?,?,?,?,?)";
			String sql2="insert into problemnum values(?,'2')";
			PreparedStatement pre = conn.prepareStatement(sql);
			PreparedStatement pre2 = conn.prepareStatement(sql2);
			for(int i=0;i<2;i++){
				Problem pro=list.get(i);
				pre.setString(1,pro.getPro());
				pre.setString(2, pro.getOne());
				pre.setString(3, pro.getTwo());
				pre.setString(4,pro.getThree());
				pre.setString(5, pro.getFour());
				pre.setString(6, many+"");
				pre.setString(7, i+"");
				pre.setString(8, pro.getResult());
				pre.executeUpdate();
			}
			pre2.setString(1, many+"");
			f=pre2.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return f;
	}
	/*
	 * 执行查询 try { String sql=""; PreparedStatement pre =
	 * conn.prepareStatement(sql); ResultSet result = pre.executeQuery(); while
	 * (result.next()) {
	 * 
	 * }
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); }finally{ try {
	 * conn.close(); } catch (SQLException e1) { e1.printStackTrace(); } }
	 */

	/*
	 * 执行更新 try { String sql=""; PreparedStatement pre =
	 * conn.prepareStatement(sql); ResultSet result = pre.executeQuery(); while
	 * (result.next()) {
	 * 
	 * }
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); }finally{ try {
	 * conn.close(); } catch (SQLException e1) { e1.printStackTrace(); } }
	 */

	/*
	 * 执行事务 conn.setAutoCommit(false); try { 事务的执行过程 conn.commit(); } catch
	 * (SQLException e) { conn.rollback(); }finally{ try {
	 * conn.setAutoCommit(true); conn.close(); } catch (SQLException e1) {
	 * e1.printStackTrace(); } }
	 */

}
