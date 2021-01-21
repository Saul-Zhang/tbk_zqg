package com.orange.tbk.admin.impl;

import com.orange.tbk.admin.dao.AdzoneIdAllocationDao;
import com.orange.tbk.admin.dao.UserDao;
import com.orange.tbk.api.bean.AdzoneIdAllocation;
import com.orange.tbk.api.bean.User;
import com.orange.tbk.api.redis.RedisKeyConstant;
import com.orange.tbk.api.service.UserService;
import com.orange.tbk.api.utils.HttpUtils;
import com.orange.tbk.api.utils.IDUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service(version = "${version}")
public class UserImpl implements UserService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AdzoneIdAllocationDao adzoneIdAllocationDao;

    @Override
    public User login(String mobile, String password, String code) {
        if (code == null || "".equals(code)) {
            return userDao.findTopByUsernameAndPassword(mobile, password);
        } else {
            return userDao.findByMobile(mobile);
        }
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete(RedisKeyConstant.TOKEN + token);
    }

    @Override
    public String sendMsg(String mobile) {

        //短信模板id
        String templateID = "20201007232843";
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
        String phone = "[86" + mobile + "]";
        bodys.put("mobileSet",  phone);
        bodys.put("templateID", templateID);
        String code = createRandomCode();
        redisTemplate.opsForValue().set(RedisKeyConstant.CODE + mobile, code, 15, TimeUnit.MINUTES);
        String time = "'" + 15 + "'";
        bodys.put("templateParamSet", "[" + code + "," + time + "]");
        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            //获取response的body
            //System.out.println();
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserInfo(String userId) {
        return userDao.findById(userId).get();
    }

    @Override
    public User register(String mobile, String password, String rePassword, String code) {
        User u = new User();
        u.setId(IDUtils.getID(mobile));
        u.setUsername(mobile);
        u.setMobile(mobile);
        if (StringUtils.isBlank(code)) {
            u.setPassword(password);
        } else {
            Object o = redisTemplate.opsForValue().get(RedisKeyConstant.CODE + mobile);
            if (o == null) {
                throw new RuntimeException("验证码错误");
            }
        }
        AdzoneIdAllocation firstByStatus = adzoneIdAllocationDao.findFirstByStatus(0);
        u.setAdzoneId(firstByStatus.getAdzoneId());
        firstByStatus.setStatus(1);
        adzoneIdAllocationDao.save(firstByStatus);
        return userDao.saveAndFlush(u);
    }

    @Override
    public User findByAdzoneId(String adzoneId) {
        return userDao.findByAdzoneId(adzoneId);
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
