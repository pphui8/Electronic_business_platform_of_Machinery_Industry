package com.example.mallwork.dao.impl;

import com.example.mallwork.dao.UserDao;
import com.example.mallwork.Entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
	@Resource
	private QueryRunner queryRunner;
	@Override
	public int checkUserByAccount(String account) {
		String sql="select count(*) as num from users where account=?";
		try {
			List<Long> rs=queryRunner.query(sql, new ColumnListHandler<Long>("num"),account);
			return rs.size()>0?rs.get(0).intValue():0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public User findUserByAccountAndPassword(String account, String password) {
		String sql="select * from users where account=? and password=?";
		try {
			return queryRunner.query(sql, new BeanHandler<User>(User.class),account,password);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int checkUserByEamil(String email) {
		String sql="select count(account) as num from users where email=?";
		try {
			List<Long> rs = queryRunner.query(sql,new ColumnListHandler<Long>("num"), email);
			return rs.size()>0?rs.get(0).intValue():0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int checkUserByPhone(String phone) {
		String sql="select count(account) as num from users where phone=?";
		try {
			List<Long> rs = queryRunner.query(sql,new ColumnListHandler<Long>("num"), phone);
			return rs.size()>0?rs.get(0).intValue():0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int insertUser(User user) {
		String sql = "insert into users(account,password,email,phone,question,asw,role,create_time,update_time)"
				+ " values(?,?,?,?,?,?,?,?,?)";
		Object[] params = {user.getAccount(),user.getPassword(),user.getEmail(),user.getPhone(),user.getQuestion(),
							user.getAsw(),user.getRole(),user.getCreate_time(),user.getUpData_time()};
		try {
			return queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public User findUserByAccount(String account) {
		String sql = "select * from users where account=?";
		try {
			return queryRunner.query(sql, new BeanHandler<User>(User.class), account);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public int checkUserAnswer(String account,String question, String asw) {
		String sql = "select count(account) as num from users"
				+ " where account=? and question = ? and asw = ?";
		try {
			List<Long> rs = queryRunner.query(sql, new ColumnListHandler<Long>("num"),account,question, asw);
			return rs.size()>0?rs.get(0).intValue():0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public User findUserById(Integer userId) {
		String sql = "select * from users where id=?";
		try {
			return queryRunner.query(sql, new BeanHandler<User>(User.class), userId);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int updateUserInfo(User user) {
		String sql = "update users set update_time = ?,password = ?,email = ?,"
				+ "phone = ?,question = ?,asw = ?,name = ?, age = ?,sex = ?,create_time = ?,"
				+ "account = ?,role = ?,del = ? where id=?";
		List<Object> params = new ArrayList<>();
		params.add(user.getUpData_time());
		params.add(user.getPassword());
		params.add(user.getEmail());
		params.add(user.getPhone());
		params.add(user.getQuestion());
		params.add(user.getAsw());
		params.add(user.getName());
		params.add(user.getAge());
		params.add(user.getSex());
		params.add(user.getCreate_time());
		params.add(user.getAccount());
		params.add(user.getRole());
		params.add(user.getDel());
		params.add(user.getId());
		
		try {
			return queryRunner.update(sql, params.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int checkPassword(String account, String oldPassword) {
		String sql ="select count(account) as num from users where account=? and password=?";
		try {
			List<Long> rs = queryRunner.query(sql, new ColumnListHandler<Long>("num"),account,oldPassword);
			return rs.size()>0?rs.get(0).intValue():0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
