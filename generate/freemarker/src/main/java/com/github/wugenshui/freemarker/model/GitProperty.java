package com.github.wugenshui.freemarker.model;

import lombok.Data;

/**
 * @author : chenbo
 * @date : 2022-04-30
 */
@Data
public class GitProperty {
    /**
     * 命名空间,例如 com.abc
     */
    protected String namespace;

    /**
     * 项目名称，_会替换成-,例如 test-service,
     */
    protected String name;

    /**
     * 仓库地址
     */
    protected String repo;

    /**
     * docker镜像仓库地址，用于推送镜像
     */
    protected String registry;

    /**
     * 仓库名称
     */
    protected String company;
}
