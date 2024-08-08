package com.ybjzo2o.customer.service;

import com.ybjzo2o.api.customer.dto.response.AddressBookResDTO;
import com.ybjzo2o.common.model.PageResult;
import com.ybjzo2o.customer.model.domain.AddressBook;
import com.ybjzo2o.customer.model.dto.request.AddressBookPageQueryReqDTO;
import com.ybjzo2o.customer.model.dto.request.AddressBookUpsertReqDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 地址薄 服务类
 * </p>
 *
 * @author ithyfjs
 * @since 2024-07-06
 */
public interface IAddressBookService extends IService<AddressBook> {

    /**
     * 根据用户id和城市编码获取地址
     *
     * @param userId 用户id
     * @param cityCode 城市编码
     * @return 地址编码
     */
    List<AddressBookResDTO> getByUserIdAndCity(Long userId, String cityCode);
}
