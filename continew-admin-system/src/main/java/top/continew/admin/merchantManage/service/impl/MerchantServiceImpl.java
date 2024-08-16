package top.continew.admin.merchantManage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import top.continew.admin.common.enums.DisEnableStatusEnum;
import top.continew.admin.common.enums.GenderEnum;
import top.continew.admin.common.util.SecureUtils;
import top.continew.admin.system.model.entity.DeptDO;
import top.continew.admin.system.model.entity.RoleDO;
import top.continew.admin.system.model.entity.UserDO;
import top.continew.admin.system.model.req.DeptReq;
import top.continew.admin.system.model.req.UserReq;
import top.continew.admin.system.service.DeptService;
import top.continew.admin.system.service.RoleService;
import top.continew.admin.system.service.UserService;
import top.continew.starter.core.util.validate.CheckUtils;
import top.continew.starter.data.mybatis.plus.base.BaseMapper;
import top.continew.starter.extension.crud.annotation.DictField;
import top.continew.starter.extension.crud.model.entity.BaseIdDO;
import top.continew.starter.extension.crud.model.query.SortQuery;
import top.continew.starter.extension.crud.model.resp.LabelValueResp;
import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;
import top.continew.admin.merchantManage.mapper.MerchantMapper;
import top.continew.admin.merchantManage.model.entity.MerchantDO;
import top.continew.admin.merchantManage.model.query.MerchantQuery;
import top.continew.admin.merchantManage.model.req.MerchantReq;
import top.continew.admin.merchantManage.model.resp.MerchantDetailResp;
import top.continew.admin.merchantManage.model.resp.MerchantResp;
import top.continew.admin.merchantManage.service.MerchantService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 商户管理业务实现
 *
 * @author Jhon sam
 * @since 2024/07/30 16:14
 */
@Service
@RequiredArgsConstructor
public class MerchantServiceImpl extends BaseServiceImpl<MerchantMapper, MerchantDO, MerchantResp, MerchantDetailResp, MerchantQuery, MerchantReq> implements MerchantService {

    private final UserService userService;
    private final DeptService deptService;
    private final RoleService roleService;
    private final MerchantMapper mapper;

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Long add(MerchantReq req) {

        //新增部门
        DeptReq deptReq = new DeptReq();
        deptReq.setParentId(1l);
        deptReq.setName(req.getMchName());
        deptReq.setSort(1);
        deptReq.setStatus(DisEnableStatusEnum.ENABLE);
        Long deptId = deptService.add(deptReq);
        RoleDO roleDO = roleService.getByCode("ordinary_merchant");
        //新增用户
        UserReq user = new UserReq();
        user.setUsername(req.getUsername());
        user.setNickname(req.getMchName());
        user.setPassword(SecureUtils.encryptByRsaPublicKey("123456"));
        user.setGender(GenderEnum.UNKNOWN);
        user.setPhone(req.getContactTel());
        user.setStatus(DisEnableStatusEnum.ENABLE);
        user.setDeptId(deptId);
        user.setRoleIds(new ArrayList<>(List.of(roleDO.getId())));
        Long newUserId = userService.add(user);
        //新增商户
        this.beforeAdd(req);
        MerchantDO entity = BeanUtil.copyProperties(req, this.entityClass, new String[0]);
        entity.setMchNo("M" + DateUtil.currentSeconds());
        entity.setInitUserId(newUserId);
        this.baseMapper.insert(entity);
        this.afterAdd(req, entity);
        return entity.getId();
    }


    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(MerchantReq req,Long id) {
        //新增部门
        DeptReq deptReq = new DeptReq();
        deptReq.setParentId(1l);
        deptReq.setName(req.getMchName());
        deptReq.setSort(1);
        deptReq.setStatus(DisEnableStatusEnum.ENABLE);
        Long deptId = deptService.add(deptReq);
        RoleDO roleDO = roleService.getByCode("ordinary_merchant");
        //新增用户
        UserReq user = new UserReq();
        user.setUsername(req.getUsername());
        user.setNickname(req.getMchName());
        user.setPassword(SecureUtils.encryptByRsaPublicKey("123456"));
        user.setGender(GenderEnum.UNKNOWN);
        user.setPhone(req.getContactTel());
        user.setStatus(DisEnableStatusEnum.ENABLE);
        user.setDeptId(deptId);
        user.setRoleIds(new ArrayList<>(List.of(roleDO.getId())));
        Long newUserId = userService.add(user);
        //新增商户
        this.beforeAdd(req);
        MerchantDO entity = BeanUtil.copyProperties(req, this.entityClass, new String[0]);
        entity.setMchNo("M" + DateUtil.currentSeconds());
        entity.setInitUserId(newUserId);
        this.baseMapper.insert(entity);
        this.afterAdd(req, entity);
    }

    @Override
    public List<LabelValueResp> listDict(MerchantQuery query, SortQuery sortQuery) {
        QueryWrapper queryWrapper = this.buildQueryWrapper(query);
        this.sort(queryWrapper, sortQuery);
        queryWrapper.select("mch_name", "mch_no");
        List<MerchantDO> entityList = this.baseMapper.selectList(queryWrapper);
        Map<String, String> fieldMapping = MapUtil.newHashMap(2);
        fieldMapping.put("mchName", "label");
        fieldMapping.put("mchNo", "value");
        return BeanUtil.copyToList(entityList, LabelValueResp.class, CopyOptions.create().setFieldMapping(fieldMapping));
    }

    @Override
    public MerchantDO getMerchantByMchNo(String mchNo) {
        return baseMapper.getMerchantByMchNo(mchNo);
    }
}