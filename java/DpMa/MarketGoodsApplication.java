package DpMa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.qianfeng.market.dao")
@EnableTransactionManagement  // 开启事务控制
public class MarketGoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketGoodsApplication.class, args);
    }

}
