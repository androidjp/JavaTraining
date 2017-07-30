
package properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
* PropertiesUtil Tester. 
* 
* @author <Authors name> 
* @since <pre>七月 23, 2017</pre> 
* @version 1.0 
*/ 
public class PropertiesUtilTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: readJVM() 
* 
*/ 
@Test
public void testReadJVM() throws Exception { 
    PropertiesUtil.readJVM();
} 

/** 
* 
* Method: getValueByKey(String filePath, String key) 
* 
*/ 
@Test
public void testGetValueByKey() throws Exception {
    PropertiesUtil.getValueByKey("test.properties","address_ip");
}

/** 
* 
* Method: getAllProperties(String filePath) 
* 
*/ 
@Test
public void testGetAllProperties() throws Exception {
    PropertiesUtil.getAllProperties("test.properties");
}

/** 
* 
* Method: writeProperties(String filePath, String pKey, String pValue) 
* 
*/ 
@Test
public void testWriteProperties() throws Exception {
    PropertiesUtil.writeProperties("test.properties", "address_ip","127.0.0.1");
}


} 
