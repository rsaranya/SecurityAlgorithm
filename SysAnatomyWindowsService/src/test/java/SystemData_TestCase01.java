package test.java;
import static org.junit.Assert.*;

import org.hyperic.sigar.SysInfo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 */

/**
 * @author siddhartha
 *
 */
public class SystemData_TestCase01 {

  public SystemData_TestCase01() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of advise method
   */
  @Test
  public void test() {
       System.out.println("Test Case for System Data are being executed ");
       SysInfo lsysInfoInstance = new SysInfo();
       
       assertTrue(lsysInfoInstance.getArch()!= null );
       assertTrue(lsysInfoInstance.getDescription()!= null );
       assertTrue(lsysInfoInstance.getMachine()!= null );
       assertTrue(lsysInfoInstance.getName()!= null );
       assertTrue(lsysInfoInstance.getVendor()!= null );
       assertTrue(lsysInfoInstance.getVendorName()!= null );
       
     

  }    

}
