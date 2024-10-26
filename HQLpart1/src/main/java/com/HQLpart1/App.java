package com.HQLpart1;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;



public class App 
{
    public static void main( String[] args )
    {
        
        StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();  

        Metadata meta=new MetadataSources(ssr).getMetadataBuilder().build();  
          
        SessionFactory sf=meta.getSessionFactoryBuilder().build();  
        
        Session session=sf.openSession();
        session.beginTransaction();
        
       /* 
        //inserting multiple details
        Random rand=new Random();
        for(int i=1;i<=50;i++)
        {
        	Student student=new Student();
        	student.setId(i);
        	student.setName("Name "+i);
        	student.setMarks(rand.nextInt(100));
        	session.persist(student);
        }
        */
        
        /*
        //fetching multiple details compeletly
        //Query query=session.createQuery("from Student");
        Query query=session.createQuery("from Student where marks>50");
        List<Student>  students=query.list();
        for(Student s:students)
        {
        	System.out.println(s);
        }
       */
        
        /*
        //fetch particular value
        Query query=session.createQuery("from Student where id=50");
        Student student=(Student) query.uniqueResult();
        System.out.println(student);
        */
        
        /*
        //fetch column based value
        Query query=session.createQuery("select id,name,marks from Student where id=50");
        Object[] student=(Object[]) query.uniqueResult();
        //first way
        /*
        for(Object o:student)
        System.out.println(o);
        */
        //second way
       /*
      System.out.print(student[0]+" : "+student[1]+" : "+student[2]); 
       */
        
        /*
        //fetch whole data in table column wise
        Query query=session.createQuery("select id,name,marks from Student");
       List<Object[]> students=(List<Object[]>) query.list();
       
        for(Object[] student:students)
        {
           System.out.println(student[0]+" : "+student[1]+" : "+student[2]); 
     
        }
         */
        /*
        //sum in query
        
        Query query1=session.createQuery("select sum(marks) from Student where marks>60");
          Long studentmark1=(Long)query1.uniqueResult();
       
            System.out.println(studentmark1); 
        int b=60;
        Query query2=session.createQuery("select sum(marks) from Student where marks>"+60);
        Long studentmark2=(Long)query2.uniqueResult();
     
          System.out.println(studentmark2); 
          int bb=60;
          Query query3=session.createQuery("select sum(marks) from Student where marks> :bb");
          query3.setParameter("bb",bb);
          Long studentmark3=(Long)query3.uniqueResult();
       
            System.out.println(studentmark3); 
            
          */
        /*
        //sqlQuery in hibernate
        SQLQuery query=session.createSQLQuery("select * from Student where marks > 60");
        query.addEntity(Student.class);
        List student=query.list();
        for(Object o:student)
        	 System.out.println(o);
        	 */
        
        
        //sqlquery for columns 
        //This is a native Queries
        SQLQuery query=session.createSQLQuery("select id,name from Student where marks > 60");
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List student=query.list();
        for(Object o:student)
        {
        	Map m=(Map)o;
        	 System.out.println(m.get("id")+" : "+m.get("name"));
        }
        session.getTransaction().commit();
        session.close();
        
       
    }
}
