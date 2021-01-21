package com.orange.tbk.admin.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orange.tbk.admin.mapper.AdminUserMapper;
import com.orange.tbk.api.bean.AdminUser;
import com.orange.tbk.api.redis.RedisKeyConstant;
import com.orange.tbk.api.service.AdminUserService;
import com.orange.tbk.api.utils.HttpUtils;
import org.apache.dubbo.config.annotation.Service;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service(version = "${version}")
public class AdminUserImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements AdminUserService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public String verifyUser(String username, String password) {
        int verifyUser = super.baseMapper.verifyUser(username, password);
        if (verifyUser == 0) return "";
        String token = IdUtil.simpleUUID() + IdUtil.simpleUUID();
        redisTemplate.opsForValue().set(RedisKeyConstant.TOKEN + token, token, 1, TimeUnit.DAYS);
        return token;
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete(RedisKeyConstant.TOKEN + token);
    }

    @Override
    public String sendMsg(String mobile) {
        //短信模板id
        String templateID = "20201007232843";
        mobile = "[" + mobile + "]";
        String host = "https://intlsms.market.alicloudapi.com";
        String path = "/comms/sms/groupmessaging";
        String method = "POST";
        String appcode = "480698fac66f4e479271ee13e7e28a74";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("callbackUrl", "http://test.dev.esandcloud.com");
        bodys.put("channel", "0");
        bodys.put("mobileSet", mobile);
        bodys.put("templateID", templateID);
        String code = createRandomCode();
        redisTemplate.opsForValue().set(RedisKeyConstant.CODE + code, code, 15, TimeUnit.MINUTES);
        String time = "'" + 15 + "'";
        bodys.put("templateParamSet", "[" + code + "," + time + "]");
        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println();
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String createRandomCode() {
        //验证码
        String vcode = "";
        for (int i = 0; i < 6; i++) {
            vcode = vcode + (int) (Math.random() * 9);
        }
        return "'" + vcode + "'";
    }

}
