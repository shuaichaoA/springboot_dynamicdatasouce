package com.tuling.dynamic.datasource.controller;

import com.tuling.dynamic.datasource.dto.FeeDTO;
import com.tuling.dynamic.datasource.entity.Fee;
import com.tuling.dynamic.datasource.service.FeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/***
 *
 * @Slogan 致敬大师，致敬未来的你
 */
@RestController
@RequestMapping("fee")
@Slf4j
public class FeeController {

    @Resource
    private FeeService feeService;

    @GetMapping
    public List<Fee> select() {
        return feeService.listAll();
    }


    @PostMapping
    public String add(@RequestBody FeeDTO dto) {
        Fee fee = new Fee();
        fee.setName(dto.getName());
        fee.setFee(new Random().nextInt(100));
        feeService.save(fee);
        return "创建成功";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable int id) {
        feeService.delete(id);
        return "删除成功";
    }
}
