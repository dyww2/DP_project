package DpMa.service.impl;

import DpMa.dao.GoodsTypeDao;
import DpMa.pojo.dto.ResponseDTO;
import DpMa.pojo.entity.GoodsType;
import DpMa.pojo.vo.GoodsTypeVO;
import DpMa.service.TypeService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * creator：Administrator
 * date:2019/12/18
 */
@Service
public class TypeServiceImpl implements TypeService {
    @Resource
    GoodsTypeDao goodsTypeDao;

    @Override
    public ResponseDTO add(GoodsType goodsType) {
        if (goodsTypeDao.insert(goodsType) == 1) {// 表示增加成功
            return ResponseDTO.ok("增加商品类型成功");

        } else {
            return ResponseDTO.fail("增加商品类型失败");

        }
    }

    @Override
    public List<GoodsTypeVO> selectTypesByParentId(int i) {
        List<GoodsTypeVO> parentTypes = goodsTypeDao.selectTypesByParentId(i);
//        for (GoodsTypeVO g : parentTypes) {// for循环里边调用查询语句，非常影响性能
//            List<GoodsTypeVO> childrenTypes = goodsTypeDao.selectTypesByParentId(g.getGoodsTypeId());
//            g.setChildrenTypes(childrenTypes);
//        }

        if(!CollectionUtils.isEmpty(parentTypes)){
            List<GoodsTypeVO> childrenTypes = goodsTypeDao.selectTypesByParentIds(parentTypes);
            Map<Integer, List<GoodsTypeVO>> collect = childrenTypes.stream().collect(Collectors.groupingBy(GoodsTypeVO::getParentTypeId));
            for (GoodsTypeVO g : parentTypes) {// for循环里边调用查询语句，非常影响性能
                List<GoodsTypeVO> childrenTypes1 = collect.get(g.getGoodsTypeId());
                g.setChildrenTypes(childrenTypes1);
            }
        }

        return parentTypes;
    }


}
