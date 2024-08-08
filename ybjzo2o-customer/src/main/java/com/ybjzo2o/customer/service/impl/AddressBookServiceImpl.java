package com.ybjzo2o.customer.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybjzo2o.api.customer.dto.response.AddressBookResDTO;
import com.ybjzo2o.api.publics.MapApi;
import com.ybjzo2o.api.publics.dto.response.LocationResDTO;
import com.ybjzo2o.common.model.PageResult;
import com.ybjzo2o.common.utils.BeanUtils;
import com.ybjzo2o.common.utils.CollUtils;
import com.ybjzo2o.common.utils.NumberUtils;
import com.ybjzo2o.common.utils.StringUtils;
import com.ybjzo2o.customer.mapper.AddressBookMapper;
import com.ybjzo2o.customer.model.domain.AddressBook;
import com.ybjzo2o.customer.model.dto.request.AddressBookPageQueryReqDTO;
import com.ybjzo2o.customer.model.dto.request.AddressBookUpsertReqDTO;
import com.ybjzo2o.customer.service.IAddressBookService;
import com.ybjzo2o.mvc.utils.UserContext;
import com.ybjzo2o.mysql.utils.PageUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 地址薄 服务实现类
 * </p>
 *
 * @author ithyfjs
 * @since 2024-07-06
 */
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements IAddressBookService {

    @Override
    public List<AddressBookResDTO> getByUserIdAndCity(Long userId, String city) {

        List<AddressBook> addressBooks = lambdaQuery()
                .eq(AddressBook::getUserId, userId)
                .eq(AddressBook::getCity, city)
                .list();
        if(CollUtils.isEmpty(addressBooks)) {
            return new ArrayList<>();
        }
        return BeanUtils.copyToList(addressBooks, AddressBookResDTO.class);
    }
}
