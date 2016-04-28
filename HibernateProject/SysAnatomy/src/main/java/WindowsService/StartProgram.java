package WindowsService;

import org.hibernate.Session;

import WindowsService.persistence.HibernateUtil;

public class StartProgram {

  //private static final Logger LOGGER = Logger.getLogger();
      

  public static void main(String[] args) {
    try {
      //PropertyConfigurator.configure("log4j.properties");

      System.out.println("Maven + Hibernate + MySQL");
      Session session = HibernateUtil.getSessionFactory().openSession();
      
      session.beginTransaction();
      Request req = new Request();
      //Stock stock = new Stock();
      
      req.setrq_token_id(20);
      req.setrq_system_id("40");
      
      session.save(req);
      session.getTransaction().commit();
    } catch (Exception ex) {
     ex.printStackTrace();
    }
  }

}
