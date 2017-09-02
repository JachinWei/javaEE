package util;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";	
	private static final ThreadLocal<Session> tl = new ThreadLocal<Session>();
	private static Configuration configuration = new Configuration();
	private static SessionFactory sessionFactory;	

	static {
		try {
			configuration.configure(CONFIG_FILE_LOCATION);
			sessionFactory = configuration.buildSessionFactory();
		} catch (HibernateException e) {
			e.printStackTrace();
			System.out.println("产生sessionFactory错误！！");
		}
	}	
	
	public static SessionFactory getSessionFactory (){
		return sessionFactory;
	}

	public static Session getSession() {
		Session session = tl.get();	//从当前的线程的map中取。key是tl
		if (session == null || !session.isOpen()) {
			session = sessionFactory .openSession();
			tl.set(session);		//放到当前线程中的map里。key:tl value:session	
		}
		
		return session;
	}
	
	public static void closeSession() {
		Session session = tl.get();
		tl.set(null);
		
		if (session != null) {
			session.close();			
		}
		
	}
	
}
