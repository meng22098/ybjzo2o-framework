package com.ybjzo2o.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ybjzo2o.api.customer.dto.response.AddressBookResDTO;
import com.ybjzo2o.common.model.PageResult;
import com.ybjzo2o.customer.model.domain.AddressBook;
import com.ybjzo2o.customer.model.dto.request.AddressBookPageQueryReqDTO;
import com.ybjzo2o.customer.model.dto.request.AddressBookUpsertReqDTO;

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

    void add(AddressBookUpsertReqDTO addressBookUpsertReqDTO);

    PageResult<AddressBookResDTO> pageQuery(AddressBookPageQueryReqDTO addressBookPageQueryReqDTO);

    void updateAdd(Long id, AddressBookUpsertReqDTO addressBookUpsertReqDTO);

    void updateDefaultStatus(Long userId, Long id, Integer flag);

    AddressBookResDTO defaultAddress(Long userId);
}
