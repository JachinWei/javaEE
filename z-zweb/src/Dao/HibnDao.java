package Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;







import bean.User;



public class HibnDao {

	private Configuration cfg =null;
    private ServiceRegistry sr =null;
    
    private SessionFactory sessionFactory=null;
    private Session session =null;
    private Transaction transaction =null;
	
   
	public HibnDao()
    {
        //初始化Hibernate
        cfg =  new Configuration().configure();
        
        sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties())
                .buildServiceRegistry();
        
        //单例模式
       //sessionFactory = HibernateUtils.getInstance().getSessionFactory();
         
		
        
        
    }
    
    private void init()
    {
    	
    	sessionFactory= cfg.buildSessionFactory(sr);
        session = sessionFactory.openSession();
        
        transaction= session.beginTransaction();
    }
    
   private void destory()
    {
    	transaction.commit();
        session.close();
        sessionFactory.close();
        
    }
    
   /* public void getSession() {
        //sessionFactory= HibernateUtils.getSessionFactory();
        //session = HibernateUtils.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }*/
	
   
    //保存User操作
  public void  insert(User user) {
	  
	  try{
		  
	 init();
	 
	  System.out.println(user);
	  
	 session.save(user);
	 
	
	 }catch (Exception e) {
		 
         e.printStackTrace();
         
         
         }finally {
             destory();
         }
  }
  
//邮箱查询操作
  public int emlSelect(User user){
	 
	    int flag=0;
	  
	    init();
	  
		List<String> list =null;
		
		String hql="select id FROM d_d_user WHERE email=?";
		String pwd=null;
		
		try { 
		Query query=session.createSQLQuery(hql);
		query.setParameter(0, user.getEmail());
		
		list=query.list();
        if(list.size()==0){
        	flag=1;
        }
			 
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally{
				  destory();
			}
		System.out.println(" emlSelect:"+flag);			
 return flag;
	  
  }
  
  //名字查询
  public int ninSelect(User user){
		 
	    int flag=0;
	  
	    init();
	  
		List<String> list =null;
		
		String hql="select id FROM d_d_user WHERE nickname=?";
		
		
		try { 
		Query query=session.createSQLQuery(hql);
		query.setParameter(0, user.getNickname());
		
		list=query.list();
      if(list.size()==0){
      	flag=1;
      }
			 
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally{
				  destory();
			}
		System.out.println(" ninSelect:"+flag);	
return flag;
	  
}
  
  
  //密码查询操作
  public String pwdSelect(User user){
	  
	 
		init();
		  
		List<String> list =null;
		
		String hql="select password FROM d_d_user WHERE email=?";
		String pwd=null;
		
		try { 
		Query query=session.createSQLQuery(hql);
		query.setParameter(0, user.getEmail());
		
		list=query.list();
        if(list.size()==0){
        	pwd="w";
        }else {
        	pwd=list.get(0);
		}
		
			 
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally{
				  destory();
			}
			
   return pwd; 
  }
  
  //验证码
  public int checking(User user){
		 
	    int flag=0;
	  
	    String ck=user.getVerifyCode();
	    System.out.println(ck);
	    if(ck.equals("qq")){
	    	flag=1;
	    }
	    
	   // init();
	  
		//List<String> list =null;
		
		//String hql="select id FROM d_d_user WHERE nickname=?";
		
		
		/*try { 
		Query query=session.createSQLQuery(hql);
		query.setParameter(0, user.getNickname());
		
		list=query.list();
    if(list.size()==0){
    	flag=1;
    }
			 
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally{
				  destory();
			}*/
		//System.out.println(" ninSelect:"+flag);	
return flag;
	  
}
}
