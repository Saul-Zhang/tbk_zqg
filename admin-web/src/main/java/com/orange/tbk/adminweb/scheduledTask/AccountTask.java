package com.orange.tbk.adminweb.scheduledTask;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.orange.tbk.api.bean.Account;
import com.orange.tbk.api.bean.Order;
import com.orange.tbk.api.bean.User;
import com.orange.tbk.api.service.AccountService;
import com.orange.tbk.api.service.OrderService;
import com.orange.tbk.api.service.UserService;
import com.orange.tbk.api.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class AccountTask {
    /**
     * 30天有效
     */
    private static final String SESSION = "7000010163827581a2c8d5cb345cdcc876635a15fc2722fa851af3565ea0e4809d628a72976585268";
    private static final String HOST = "http://gateway.kouss.com";
    private static final String ORDER_DETAIL = "/tbpub/orderDetailGet";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Reference(version = "${admin.version}", check = false)
    private AccountService accountService;

    @Reference(version = "${admin.version}", check = false)
    private OrderService orderService;

    @Reference(version = "${admin.version}", check = false)
    private UserService userService;

    /**
     * 获取订单信息
     * https://www.yuque.com/kouss/taoke/iwvta8
     */
    @Scheduled(fixedRate = 1000 * 6)
    public void getOrderInfo() {
//        LocalDateTime now = LocalDateTime.of(2020, 10, 31, 17, 1, 20);
        LocalDateTime now = LocalDateTime.now();
        boolean has_next = true;
        String position_index = "";
        while (has_next) {
            LocalDateTime startTime = now.minusMinutes(20);
            Map<String, String> body = new HashMap<>();
            body.put("session", SESSION);
            body.put("start_time", DATE_TIME_FORMATTER.format(startTime));
            body.put("end_time", DATE_TIME_FORMATTER.format(now));
            body.put("page_size", "100");
            body.put("query_type", "1");
            body.put("position_index", position_index);
            try {
                HttpResponse response = HttpUtils.doPost(HOST, ORDER_DETAIL, "", new HashMap<>(), new HashMap<>(), body);
                String s = EntityUtils.toString(response.getEntity());
                JSONObject jsonObject = JSONObject.parseObject(s);
                JSONObject data = (JSONObject) jsonObject.get("data");
                if (data == null) {
                    return;
                }
                has_next = data.getBoolean("has_next");
                position_index = data.getString("position_index");
                JSONArray dto = (JSONArray) ((JSONObject) data.get("results")).get("publisher_order_dto");
                if (dto != null) {
                    for (int i = 0; i < dto.size(); i++) {
                        JSONObject orderJson = dto.getJSONObject(i);
                        Order order = JSONObject.toJavaObject(orderJson, Order.class);
                        Order one = orderService.selectById(order.getTradeId());
                        if (one != null) {
                            if (!order.getTkStatus().equals(one.getTkStatus())) {
                                order.setTkStatus(one.getTkStatus());
                                orderService.update(order, null);
                                dealCountInfo();
                            }
                        } else {
                            User user = userService.findByAdzoneId(order.getAdzoneId());
                            if (user != null && user.getGrade() != null && order.getPubSharePreFee() != null) {
                                NumberFormat currency = NumberFormat.getCurrencyInstance();
                                BigDecimal b1 = new BigDecimal(user.getGrade());
                                BigDecimal b2 = new BigDecimal(order.getPubSharePreFee());
                                String format = currency.format(b1.multiply(b2));
                                order.setPubSharePreFeeUser(format);
                            }
                            orderService.insert(order);
                            dealCountInfo();
                        }
                    }


                } else {
                    return;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void dealCountInfo() {
        List<Account> objects = orderService.getOrderFee();
        accountService.saveOrUpdateBatch(new ArrayList<>(objects));
    }

}
