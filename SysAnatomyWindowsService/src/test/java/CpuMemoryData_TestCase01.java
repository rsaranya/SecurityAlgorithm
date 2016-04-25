package test.java;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.hyperic.sigar.Cpu;
import org.hyperic.sigar.CpuInfo;


/**
 *
 * @author siddhartha
 */
public class CpuMemoryData_TestCase01 {
    
    public CpuMemoryData_TestCase01() {
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
        System.out.println("Test Case for Cpu Data are being executed  ");
        
            try {
      Cpu lcpuInstance = new Cpu();
      assert(lcpuInstance.getIdle() >=0 );
      assert(lcpuInstance.getIrq() >=0 );
      assert(lcpuInstance.getNice() >=0 );
      assert(lcpuInstance.getSoftIrq() >=0 );
      assert(lcpuInstance.getStolen() >=0 );
      assert(lcpuInstance.getSys() >=0 );
      assert(lcpuInstance.getTotal() >=0 );
      assert(lcpuInstance.getUser() >=0 );
      assert(lcpuInstance.getWait() >=0 );
      CpuInfo lcpuInfoInstance = new CpuInfo();
      assert(lcpuInfoInstance.getCacheSize() >=0 );
      
      
    } catch (Exception se) {
      se.printStackTrace();
    }
    }    
}
