package com.dzmsoft.sms.rest.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dzmsoft.framework.base.util.CheckEmptyUtil;
import com.dzmsoft.framework.base.web.mvc.controller.validate.ValidateField;
import com.dzmsoft.framework.base.web.mvc.controller.validate.ValidateGroup;
import com.dzmsoft.framework.base.web.mvc.view.GenericResponse;
import com.dzmsoft.framework.file.util.FileUtil;
import com.dzmsoft.framework.file.util.ImageUtil;
import com.dzmsoft.sms.base.pojo.SmsMember;
import com.dzmsoft.sms.base.pojo.SmsMemberAddress;
import com.dzmsoft.sms.base.service.SmsMemberAddressService;
import com.dzmsoft.sms.base.service.SmsMemberService;

@RestController
@RequestMapping("rest")
public class SmsRestCommonController {
    @Autowired
    private SmsMemberAddressService smsMemberAddressService;
    @Autowired
    private SmsMemberService smsMemberService;
    @Value("${upload.path}")
    private String uploadPath;
    
    /**
     * 会员获取服务地址
     * @param ucsId
     * @return
     */
    @RequestMapping(value = "common01/{ucsId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public GenericResponse<List<SmsMemberAddress>> getAddresses(@PathVariable String ucsId){
        List<SmsMemberAddress> smsMemberAddresses = smsMemberAddressService.selectByMain(ucsId);
        GenericResponse<List<SmsMemberAddress>> genericResponse = new GenericResponse<List<SmsMemberAddress>>(true);
        genericResponse.setData(smsMemberAddresses);
        return genericResponse;
    }
    
    /**
     * 删除服务地址
     * @param id
     * @return
     */
    @RequestMapping(value = "common02/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public GenericResponse<String> removeAddress(@PathVariable("id") String id){
        int flag = smsMemberAddressService.deleteByPrimaryKey(id);
        GenericResponse<String> genericResponse = flag>0?new GenericResponse<String>(true,"删除成功"):new GenericResponse<String>(false,"删除失败");
        return genericResponse;
    }
    
    /**
     * 新增服务地址
     * @param smsMemberAddress
     * @return
     */
    @RequestMapping(value = "common03", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ValidateGroup(
            fileds ={
                    @ValidateField(index=0 ,notNull=true ),
                    @ValidateField(index=0 ,notNull=true, fieldName="ucsId"),
                    @ValidateField(index=0 ,notNull=true, fieldName="city"),
                    @ValidateField(index=0 ,notNull=true, fieldName="cityName"),
                    @ValidateField(index=0 ,notNull=true, fieldName="division"),
                    @ValidateField(index=0 ,notNull=true, fieldName="divisionName"),
                    @ValidateField(index=0 ,notNull=true, fieldName="shortAddress"),
                    @ValidateField(index=0 ,notNull=true, fieldName="longAddress")
            }
    )
    public GenericResponse<String> addAddress(SmsMemberAddress smsMemberAddress){
        int flag = smsMemberAddressService.insertSelective(smsMemberAddress);
        GenericResponse<String> genericResponse = flag>0?new GenericResponse<String>(true,"新增成功"):new GenericResponse<String>(false,"新增失败");
        genericResponse.setData(smsMemberAddress.getId());
        return genericResponse;
    }
    
    /**
     * 修改服务地址
     * @param smsMemberAddress
     * @return
     */
    @RequestMapping(value = "common04", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ValidateGroup(
            fileds ={
                    @ValidateField(index=0 ,notNull=true ),
                    @ValidateField(index=0 ,notNull=true, fieldName="id"),
                    @ValidateField(index=0 ,notNull=true, fieldName="city"),
                    @ValidateField(index=0 ,notNull=true, fieldName="cityName"),
                    @ValidateField(index=0 ,notNull=true, fieldName="division"),
                    @ValidateField(index=0 ,notNull=true, fieldName="divisionName"),
                    @ValidateField(index=0 ,notNull=true, fieldName="shortAddress"),
                    @ValidateField(index=0 ,notNull=true, fieldName="longAddress")
            }
    )
    public GenericResponse<String> editAddress(SmsMemberAddress smsMemberAddress){
        int flag = smsMemberAddressService.updateByPrimaryKeySelective(smsMemberAddress);
        GenericResponse<String> genericResponse = flag>0?new GenericResponse<String>(true,"编辑成功"):new GenericResponse<String>(false,"编辑失败");
        genericResponse.setData(smsMemberAddress.getId());
        return genericResponse;
    }
    
    /**
     * 设置默认服务地址
     * @param id
     * @return
     */
    @RequestMapping(value = "common05/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public GenericResponse<String> setDefaultAddress(@PathVariable String id){
        int flag = smsMemberAddressService.setDefaultAddress(id);
        GenericResponse<String> genericResponse = flag>0?new GenericResponse<String>(true,"设置地址成功"):new GenericResponse<String>(false,"设置默认地址失败");
        return genericResponse;
    }
    
    /**
     *  会员新注册
     * @param smsMember
     * @return
     */
    @RequestMapping(value = "common06", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ValidateGroup(
            fileds ={
                    @ValidateField(index=0 ,notNull=true ),
                    @ValidateField(index=0 ,notNull=true, fieldName="ucsId"),
                    @ValidateField(index=0 ,notNull=true, fieldName="mobile")
            }
    )
    public GenericResponse<String> createMember(SmsMember smsMember){
        if (smsMemberService.isExist(smsMember.getUcsId())){
            return new GenericResponse<String>(false,"会员已生成");
        }
        smsMember.setId(smsMember.getUcsId());
        int flag = smsMemberService.insertSelective(smsMember);
        GenericResponse<String> genericResponse = flag>0?new GenericResponse<String>(true,"会员生成成功"):new GenericResponse<String>(false,"会员生成失败");
        return genericResponse;
    }
    
    /**
     * 获取会员信息
     * @param ucsId
     * @return
     */
    @RequestMapping(value = "common07/{ucsId}", method = RequestMethod.GET)
    public GenericResponse<SmsMember> getMember(@PathVariable String ucsId){
        SmsMember smsMember = smsMemberService.selectByPrimaryKey(ucsId);
        GenericResponse<SmsMember> genericResponse = new GenericResponse<SmsMember>(true);
        genericResponse.setData(smsMember);
        return genericResponse;
    }
    
    
    /**
     * 更改会员头像
     * @param ucsId
     * @param img
     * @return
     */
    @RequestMapping(value = "common08/{ucsId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public GenericResponse<String> updateMemberHead(@PathVariable String ucsId, String img){
        if (!CheckEmptyUtil.isEmpty(img)){
            String fileName=FileUtil.spliceFileName("memberPic.jpg", true,true);
            ImageUtil.GenerateImage(img, uploadPath + fileName);
            int flag  = smsMemberService.updateMemberHead(ucsId, fileName);
            return flag>0?new GenericResponse<String>(true,"更新业主头像成功"):new GenericResponse<String>(false, "更新业主头像失败");
        } else{
            return new GenericResponse<String>(false, "更新业主头像失败");
        }
    }
    
    @RequestMapping(value = "common09", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ValidateGroup(
            fileds ={
                    @ValidateField(index=0 ,notNull=true ),
                    @ValidateField(index=0 ,notNull=true, fieldName="id"),
                    @ValidateField(index=0 ,notNull=true, fieldName="name")
            }
    )
    public GenericResponse<String> updateMember(SmsMember smsMember){
        int flag = smsMemberService.updateByPrimaryKeySelective(smsMember);
        return flag>0?new GenericResponse<String>(true,"更新业主信息成功"):new GenericResponse<String>(false, "更新业主信息失败");
    }
    
}
