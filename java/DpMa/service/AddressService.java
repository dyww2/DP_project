package DpMa.service;

import DpMa.pojo.entity.Address;

import java.util.List;


public interface AddressService {

    Address getDefaultAddress(Integer userId);

    /**
     * 查询用户所有收货地址，以供用户进行选择
     *
     * @param userId
     * @return
     */
    List<Address> getUserAddressList(Integer userId);
}
