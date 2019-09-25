package com.example.design.cjx_ms.a_factory.b_factory_method;

import com.example.design.cjx_ms.a_factory.a_easy_factory.AddOperate;
import com.example.design.cjx_ms.a_factory.a_easy_factory.Operate;

/**
 * @time 2019/9/25 19:48
 * @desc
 */
public class AddFractory implements Factory {
    @Override
    public Operate createOperate() {
        return new AddOperate();
    }
}
