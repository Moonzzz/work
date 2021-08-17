package com.moon.work.design2;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Map;

/**
 * 模板方法
 *
 * @author zhenzhen.peng
 * @date 2021-08-17 18:05
 **/
public class TemplateTests {

    @Test
    void testTemplate() {
        new MyTemplate().dispatch("hello", Collections.singletonMap("msg", "hello"));
    }

    static class MyTemplate extends TemplateInvoke {
        Logger log = LoggerFactory.getLogger(MyTemplate.class);

        @Override
        protected void doAfter(String url, Map<String, String> params) {
            log.info("分发预处理,参数：{}", params.toString());
        }

        @Override
        protected void doBefore(String url, Map<String, String> params) {
            log.info("分发后处理");
        }
    }

    static abstract class TemplateInvoke {
        Logger log = LoggerFactory.getLogger(TemplateInvoke.class);

        public void dispatch(String url, Map<String, String> params) {
            log.info("开始分发");
            doBefore(url, params);

            doSomething(url);

            doAfter(url, params);
        }

        private void doSomething(String url) {
            log.info("处理url:" + url);
        }

        protected abstract void doAfter(String url, Map<String, String> params);

        protected abstract void doBefore(String url, Map<String, String> params);

    }

}
