package com.github.wugenshui.freemarker.model;

import lombok.Data;

/**
 * @author : chenbo
 * @date : 2022-04-30
 */
@Data
public class JavaProperty {
    /**
     * 命名空间,例如 com.abc
     */
    private String namespace;

    /**
     * 项目名称，_会替换成-,例如 test-service,
     */
    private String name;

    public String getName() {
        return name.replace("_", "-");
    }

    /**
     * 包名，例如 test.service
     *
     * @return
     */
    public String getPackageName() {
        return name.replace("-", ".");
    }

    /**
     * 命名空间与项目名称组合成的目录 com/abc/test/service/
     *
     * @return
     */
    public String getDirectory() {
        return (namespace + "." + name).replace(".", "/").replace("-", "/") + "/";
    }

}
