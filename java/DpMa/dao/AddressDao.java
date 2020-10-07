package DpMa.dao;

import DpMa.pojo.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressDao {
    int deleteByPrimaryKey(Integer addressId);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Integer addressId);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

    Address selectByUserId(@Param("userId") Integer userId, @Param("moren") String moren);

    List<Address> getUserAddressList(Integer userId);
}
