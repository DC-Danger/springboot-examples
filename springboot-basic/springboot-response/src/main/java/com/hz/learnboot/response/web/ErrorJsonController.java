package com.hz.learnboot.response.web;

import com.hz.learnboot.response.domain.City;
import com.hz.learnboot.response.result.GlobalErrorInfoEnum;
import com.hz.learnboot.response.result.GlobalErrorInfoException;
import com.hz.learnboot.response.result.ResultBody;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 错误码案例
 */
@RestController
public class ErrorJsonController {

    /**
     * 获取城市接口
     *
     * @param cityName
     * @return
     * @throws GlobalErrorInfoException
     */
    @RequestMapping(value = "/api/city", method = RequestMethod.GET)
    public ResultBody findOneCity(@RequestParam(value = "cityName", required = false) String cityName) throws GlobalErrorInfoException {
        // 入参为空
        if (StringUtils.isEmpty(cityName)) {
            throw new GlobalErrorInfoException(GlobalErrorInfoEnum.PARAMS_MISSING);
        }
        return new ResultBody(new City(362330L,360000L,"鄱阳","是我的故乡"));
    }
}