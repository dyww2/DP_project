package DpMa.service.impl;

import DpMa.dao.AddressDao;
import DpMa.pojo.entity.Address;
import DpMa.service.AddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * creator：Administrator
 * date:2019/12/19
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Resource
    AddressDao addressDao;

    @Override
    public Address getDefaultAddress(Integer userId) {
        Address address = addressDao.selectByUserId(userId, "YES");
        if (address == null) {
            address = addressDao.selectByUserId(userId, "NO");
        }
        return address;
    }

    @Override
    public List<Address> getUserAddressList(Integer userId) {

        return addressDao.getUserAddressList(userId);
    }
}
