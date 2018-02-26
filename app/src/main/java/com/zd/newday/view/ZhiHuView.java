package com.zd.newday.view;

import com.zd.newday.bean.zhihu.ZhiHuDaily;

/**
 * 知乎view
 * Created by zhangdong on 2017/11/20.
 */

public interface ZhiHuView extends BaseView {
    void initNewsList(ZhiHuDaily zhiHuDaily);
}
