package com.ybjzo2o.customer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ybjzo2o.api.customer.dto.response.AddressBookResDTO;
import com.ybjzo2o.customer.model.domain.AddressBook;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 地址薄 Mapper 接口
 * </p>
 *
 * @author ithyfjs
 * @since 2024-07-06
 */
public interface AddressBookMapper extends BaseMapper<AddressBook> {
    AddressBookResDTO defaultAddress(AddressBook addressBook);

    @Select("update address_book set is_default=0 where user_id=#{userId}")
    void updateCancel(Long userId);
}
