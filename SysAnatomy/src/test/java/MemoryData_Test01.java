import static org.junit.Assert.*;

import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MemoryData_Test01 {
  private static Sigar sigar = new Sigar();
  public MemoryData_Test01() {
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
      System.out.println("Test Case for free memory are being executed ");
      Mem mem = null;
      int x=0;       
          try {
          
            mem = sigar.getMem();
    
  } catch (Exception se) {
    se.printStackTrace();
    assertTrue(mem.getActualFree() > x);
    assertTrue(mem.getActualUsed() > x);
    assertTrue(mem.getFree() > x);
    assertTrue(mem.getRam() > x);
    assertTrue(mem.getTotal() > x);
    assertTrue(mem.getUsedPercent() > x);
    assertTrue(mem.getFreePercent() > x);
  }
      

      
  }    
}
