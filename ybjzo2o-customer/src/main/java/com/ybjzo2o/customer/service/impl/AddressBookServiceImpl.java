package com.ybjzo2o.customer.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
    @Resource
    private AddressBookMapper addressBookMapper;
    @Resource
    private MapApi mapApi;
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
    //... ...
    /**
     * 地址薄新增
     * @param addressBookUpsertReqDTO 插入更新地址薄
     */
    @Override
    public void add(AddressBookUpsertReqDTO addressBookUpsertReqDTO) {
        //当前用户id
        Long userId = UserContext.currentUserId();
        //如果新增地址设为默认，取消其他默认地址
        if (1 == addressBookUpsertReqDTO.getIsDefault()) {
            cancelDefault(userId);
        }
        AddressBook addressBook = BeanUtil.toBean(addressBookUpsertReqDTO,AddressBook.class);
        addressBook.setUserId(userId);
        //组装详细地址
        String completeAddress = addressBookUpsertReqDTO.getProvince() + addressBookUpsertReqDTO.getCity() + addressBookUpsertReqDTO.getCounty() + addressBookUpsertReqDTO.getAddress();
        //如果请求体中没有经纬度，需要调用第三方api根据详细地址获取经纬度
        if(ObjectUtil.isEmpty(addressBookUpsertReqDTO.getLocation())){
            //远程请求高德获取经纬度
            LocationResDTO locationDto = mapApi.getLocationByAddress(completeAddress);
            //经纬度(字符串格式：经度,纬度),经度在前，纬度在后
            String location = locationDto.getLocation();
            addressBookUpsertReqDTO.setLocation(location);
        }
        if(StringUtils.isNotEmpty(addressBookUpsertReqDTO.getLocation())) {
            // 经度

            addressBook.setLon(NumberUtils.parseDouble(addressBookUpsertReqDTO.getLocation(
            ).split(",")[0]));
            // 纬度

            addressBook.setLat(NumberUtils.parseDouble(addressBookUpsertReqDTO.getLocation(
            ).split(",")[1]));
        }
        addressBookMapper.insert(addressBook);
    }

    /**
     * 清空默认地址
     * @param userId
     */
    private void cancelDefault(Long userId) {
       addressBookMapper.updateCancel(userId);
    }

    /**
     * 分页查询
     * @param addressBookPageQueryReqDTO
     * @return
     */
    @Override
    public PageResult<AddressBookResDTO> pageQuery(AddressBookPageQueryReqDTO addressBookPageQueryReqDTO) {
        Page<AddressBook> page =
                PageUtils.parsePageQuery(addressBookPageQueryReqDTO, AddressBook.class);
        LambdaQueryWrapper<AddressBook> queryWrapper = Wrappers.
                <AddressBook>lambdaQuery().eq(AddressBook::getUserId,
                UserContext.currentUserId());
        Page<AddressBook> serveTypePage = addressBookMapper.selectPage(page,
                queryWrapper);
        return PageUtils.toPage(serveTypePage, AddressBookResDTO.class);
    }

    /**
     * 编辑地址
     * @param id
     * @param addressBookUpsertReqDTO
     */
    @Override
    public void updateAdd(Long id, AddressBookUpsertReqDTO addressBookUpsertReqDTO) {
        if (1 == addressBookUpsertReqDTO.getIsDefault()) {
            cancelDefault(UserContext.currentUserId());
        }
        AddressBook addressBook = BeanUtil.toBean(addressBookUpsertReqDTO,
                AddressBook.class);
        addressBook.setId(id);
        //调用第三方，根据地址获取经纬度坐标
        String completeAddress = addressBookUpsertReqDTO.getProvince() +
                addressBookUpsertReqDTO.getCity() +
                addressBookUpsertReqDTO.getCounty() +
                addressBookUpsertReqDTO.getAddress();
        //远程请求高德获取经纬度
        LocationResDTO locationDto =
                mapApi.getLocationByAddress(completeAddress);
        //经纬度(字符串格式：经度,纬度),经度在前，纬度在后
        String location = locationDto.getLocation();
        if(StringUtils.isNotEmpty(location)) {
            // 经度

            addressBook.setLon(NumberUtils.parseDouble(locationDto.getLocation().split(",")
                    [0]));
            // 纬度

            addressBook.setLat(NumberUtils.parseDouble(locationDto.getLocation().split(",")
                    [1]));
        }
        addressBookMapper.updateById(addressBook);
    }

    /**
     * 设默认地址
     * @param userId
     * @param id
     * @param flag
     */
    @Override
    public void updateDefaultStatus(Long userId, Long id, Integer flag) {
        if (1 == flag) {//如果设默认地址，先把其他地址取消默认
            cancelDefault(userId);
        }
        AddressBook addressBook = new AddressBook();
        addressBook.setId(id);
        addressBook.setIsDefault(flag);
        addressBookMapper.updateById(addressBook);
    }

    /**
     * 获取默认地址接口
     * @param userId
     * @return
     */
    @Override
    public AddressBookResDTO defaultAddress(Long userId) {
        AddressBook addressBook=new AddressBook();
        addressBook.setUserId(userId);
        addressBook.setIsDefault(1);
        return addressBookMapper.defaultAddress(addressBook);
    }
}
