package org.zerock.persistence;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import static org.junit.Assert.fail;

@Slf4j
public class JDBCTest {
    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void testConnection() {
        try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "Rladncjf1")){
            log.info(String.valueOf(con));
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }
}