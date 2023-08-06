package app;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import core.util.HibernateUtil;
import web.emp.entity.Dept;
import web.emp.entity.Emp;
import web.member.entity.Member;

public class TestApp {
	
	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session =sessionFactory.openSession();
		
		Emp emp =session.get(Emp.class, 7369);
		Dept dept =emp.getDept();
		System.out.println(dept.getDeptno());
		List<Emp> emps = dept.getEmps();
		for (Emp tmp :emps) {
			System.out.println(tmp.getEname());
		}
			
		
	}
//		Class clazz = Member.class;
//		用Mmeber找到Member類別的物件 取他的型態，再指定給clazz
//		TestApp app = new TestApp();
//		Member member = new Member();
		//Test Case
//		member.setId(1);
//		member.setPass(true);
//		member.setRoleId(1);
//		member.setUsername("使用者名稱");
//		member.setPassword("密碼");
//		member.setNickname("暱稱");
//		
//		TestApp app = new TestApp();
//		app.insert(member);
//		System.out.println(member.getId());
//		System.out.println(app.deleteById(3));
//		System.out.println(app.updateById(member));
//		System.out.println(app.selectById(2).getNickname());
//	
	public Integer insert(Member member) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction =session.beginTransaction();
			session.persist(member);
			transaction.commit();
			return member.getId();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
//		
//		
//		
//		
	}

	public int deleteById(Integer id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction =session.beginTransaction();
			Member member = session.get(Member.class, id);
			session.remove(member);
			transaction.commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return -1;
		}
		
		
	}
//	public int updateById(Member newMember) {	//參數有可能會變動 茶裡王裝袋子
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
//		try {
//			Transaction transaction =session.beginTransaction();
//			Member oldMember = session.get(Member.class, newMember.getId());
//			//若有傳pass就將此pass設定給oldMember
//			final Boolean pass = newMember.getPass();
//			if(pass != null) {
//				oldMember.setPass(pass);//修改屬性值>呼叫setter把值改掉
//			}
//			//若有傳roleId就將此roleId設定給oldMember
//			final Integer roldId = newMember.getRoleId();
//			if(roldId != null) {
//				oldMember.setRoleId(roldId);
//			}
//			
//			transaction.commit();
//			return 1;
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//			return -1;
//		}
//	}
	public Member selectById(Integer id) {	//Integer 只有一個值 用
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction =session.beginTransaction();
			Member member = session.get(Member.class, id);
			transaction.commit();
			return member;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
		
		
	}
	public List<Member> selectAll() {	//Integer 只有一個值 用
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction =session.beginTransaction();
			Query<Member> query= session.createQuery(
				"SELECT new web.member.pojo.Member(username, nickname) FROM Member, Member.class");
			List<Member> list =query.getResultList();
			transaction.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
		
		
	}
}
